package mx.itesm.monsterkiller;

import android.util.Log;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 28/09/15.
 */
public class EscenaNvl1 extends EscenaBase implements IAccelerationListener{

    // Fondo
    private ITextureRegion regionFondo;

    //Sprite animado - monsters


    // Sprite para el fondo
    private Sprite spriteFondo;

    //Banderas
    private boolean juegoCorriendo = true;

    //Energía del personaje
    private int energia = 100;

    //Controles
    private ITexture regionBtnShoot;
    private ITexture regionBtnCollect;

    // Escena de PAUSA
    private CameraScene escenaPausa;    // La escena que se muestra al hacer pausa
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;

    //Fin del juego
    //private ITextureRegion regionFin;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoNvl1.jpg");
        //regionFin = cargarImagen("");

        //Controles
        regionBtnShoot = cargarImagen("BotShoot.png");
        regionBtnCollect = cargarImagen("BotCollect.png");

        // Pausa
        regionBtnPausa = cargarImagen("BotonHome2.png");
        regionPausa = cargarImagen("FondoPausa.jpg");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
        actividadJuego.getEngine().enableAccelerationSensor(actividadJuego, this);

        //Crear botón SHOOT y agregarlo a la escena
        Sprite btnShoot = new Sprite(regionBtnShoot.getWidth(),ControlJuego.ALTO_CAMARA-regionBtnShoot.getHeight(), regionBtnShoot, actividadJuego.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnShoot);
        registerTouchArea(btnShoot);

        // Crea el botón de PAUSA y lo agrega a la escena
        Sprite btnPausa = new Sprite(regionBtnPausa.getWidth()-100, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()+50, regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pausarJuego();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnPausa);
        registerTouchArea(btnPausa);

        // Crear la escena de PAUSA, pero NO lo agrega a la escena
        escenaPausa = new CameraScene(actividadJuego.camara);
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionPausa);
        escenaPausa.attachChild(fondoPausa);
        escenaPausa.setBackgroundEnabled(false);
    }

    private void pausarJuego() {
        if (juegoCorriendo) {
            setChildScene(escenaPausa,false,true,false);
            juegoCorriendo = false;
        } else {
            clearChildScene();
            juegoCorriendo = true;
        }
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }

    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaNvl1();
        return null;
    }

    @Override public void onAccelerationChanged(AccelerationData pAccelerationData) {
        float dx = pAccelerationData.getX();
        float nx = spriteFondo.getX() - 2*dx;  // Nueva posición

        if (dx<0) {
            // Izquierda
            if ( nx<spriteFondo.getWidth()/2 ) {
                spriteFondo.setX(nx);
            }
        } else {
            // derecha
            if ( nx>spriteFondo.getWidth()/2-ControlJuego.ANCHO_CAMARA) {
                spriteFondo.setX(nx);
            }
        }

    }

    @Override public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_NVL1;
    }

    @Override
    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        //Detiene el acelerometro
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);

        regionFondo.getTexture().unload();
        regionFondo = null;
        regionBtnPausa.getTexture().unload();
        regionBtnPausa = null;
        regionPausa.getTexture().unload();
        regionPausa = null;
    }
}
