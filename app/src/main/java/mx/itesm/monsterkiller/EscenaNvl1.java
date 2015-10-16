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

    //Fondo negro
    private ITextureRegion regionFondoSombra;

    //Baterias
    private ITextureRegion regionBateria5;
    private ITextureRegion regionBateria4;
    private ITextureRegion regionBateria3;
    private ITextureRegion regionBateria2;
    private ITextureRegion regionBateria1;
    private ITextureRegion regionBateria0;

    //Sprite animado - monsters


    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondoSombra;

    //Sprite bateria
    private Sprite spriteBateria;

    //Banderas
    private boolean juegoCorriendo = true;

    //Energía del personaje
    private int energia = 100;

    //Controles
    private ITextureRegion regionBtnShoot;
    private ITextureRegion regionBtnCollect;

    // Escena de PAUSA
    private CameraScene escenaPausa;    // La escena que se muestra al hacer pausa
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;

    //Fin del juego
    //private ITextureRegion regionFin;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoNvl1.jpg");
        regionFondoSombra = cargarImagen("Sombra.png");
        //regionFin = cargarImagen("");

        //Controles
        regionBtnShoot = cargarImagen("BotShoot.png");
        regionBtnCollect = cargarImagen("BotCollect.png");

        //bateria
        regionBateria5 = cargarImagen("PIlaMax.png");
        regionBateria4 = cargarImagen("PilaCasi.png");
        regionBateria3 = cargarImagen("PilaMed.png");
        regionBateria2 = cargarImagen("PilaBaja.png");
        regionBateria1 = cargarImagen("PIlaMuerta.png");
        regionBateria0 = cargarImagen("NoPila2.png");


        // Pausa
        regionBtnPausa = cargarImagen("PauseBotonJuego.png");
        regionPausa = cargarImagen("PauseChica.jpg");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
        spriteFondoSombra = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondoSombra);
        attachChild(spriteFondoSombra);
        actividadJuego.getEngine().enableAccelerationSensor(actividadJuego, this);
        //spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria5);
        //attachChild(spriteBateria);

        float t = getSecondsElapsedTotal();
        if (t >=0f){
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight()+50, regionBateria5);
            attachChild(spriteBateria);
        }
        if (t >= .10f){
            //regionBateria = cargarImagen("PilaCasi.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight()+50, regionBateria4);
            attachChild(spriteBateria);
        }
        if (t >= .20f){
            //regionBateria = cargarImagen("PilaMed.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight()+50, regionBateria3);
            attachChild(spriteBateria);
        }
        if (t >= .30f){
            //regionBateria = cargarImagen("PilaBaja.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight()+50, regionBateria2);
            attachChild(spriteBateria);
        }
        if (t >= .40f){
            //regionBateria = cargarImagen("PIlaMuerta.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight()+50, regionBateria1);
            attachChild(spriteBateria);
        }
        if (t >= .50f){
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight()+50, regionBateria0);
            attachChild(spriteBateria);
        }


        //Crear botón SHOOT y agregarlo a la escena
        Sprite btnShoot = new Sprite (ControlJuego.ANCHO_CAMARA - regionBtnShoot.getWidth()+100, regionBtnCollect.getHeight()-70, regionBtnShoot, actividadJuego.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTounchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if (pSceneTounchEvent.isActionDown()){
                    pausarJuego();
                }
                return super.onAreaTouched(pSceneTounchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnShoot);
        registerTouchArea(btnShoot);

        //Crear botón COLLECT y agregarlo a la escena
        Sprite btnCollect = new Sprite (regionBtnCollect.getWidth()-100,  regionBtnCollect.getHeight()-70, regionBtnCollect, actividadJuego.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTounchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if (pSceneTounchEvent.isActionDown()){
                    pausarJuego();
                }
                return super.onAreaTouched(pSceneTounchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnCollect);
        registerTouchArea(btnCollect);


        // Crea el botón de PAUSA y lo agrega a la escena
        Sprite btnPausa = new Sprite(ControlJuego.ANCHO_CAMARA - regionBtnPausa.getWidth(), ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight(), regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
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
    /*
    private void perderPila(){
       float t = getSecondsElapsedTotal();
        if (t >=0f){
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria5);
            attachChild(spriteBateria);
        }
        if (t >= .10f){
            //regionBateria = cargarImagen("PilaCasi.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria4);
            attachChild(spriteBateria);
        }
        if (t >= .20f){
            //regionBateria = cargarImagen("PilaMed.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria3);
            attachChild(spriteBateria);
        }
        if (t >= .30f){
            //regionBateria = cargarImagen("PilaBaja.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria2);
            attachChild(spriteBateria);
        }
        if (t >= .40f){
            //regionBateria = cargarImagen("PIlaMuerta.png");
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria1);
            attachChild(spriteBateria);
        }
        if (t >= .50f){
            spriteBateria = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria0);
            attachChild(spriteBateria);
        }
    }*/

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
        float nx = spriteFondo.getX() - dx;  // Nueva posición
        float dy = pAccelerationData.getY();
        float ny = spriteFondoSombra.getY() - dy;
        float somx = spriteFondoSombra.getX()-dx;

        if (dx<0) {
            // Izquierda
            if ( nx<spriteFondo.getWidth()/2 ) {
                spriteFondo.setX(nx);
                spriteFondoSombra.setX(somx);

            }
        } else {
            // Derecha
            if ( nx>spriteFondo.getWidth()/2-ControlJuego.ANCHO_CAMARA) {
                spriteFondo.setX(nx);
                spriteFondoSombra.setX(somx);
            }
        }

        if (ny<0){

            if (nx<spriteFondoSombra.getHeight()/2){
                spriteFondoSombra.setY(ny);
            }
        }else{

            if(ny>spriteFondoSombra.getHeight()/2 - ControlJuego.ALTO_CAMARA){
                spriteFondoSombra.setY(ny);
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
