package mx.itesm.monsterkiller;

import android.util.Log;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Aramis on 28/09/15.
 */
public class EscenaNvl1 extends EscenaBase implements IAccelerationListener{

    //Score
    private int score;

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

    //Monstruos
    private AnimatedSprite spriteMonstruo;
    private TiledTextureRegion regionMonstruo1;

    //Contador
    private ITextureRegion regionContador;

    //Texto
    private Text textoPuntaje;
    private IFont fontMonster;

    //Tiempo
    private float tiempo;

    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondoSombra;

    //Sprite bateria
    private Sprite spriteBateria5;
    private Sprite spriteBateria4;
    private Sprite spriteBateria3;
    private Sprite spriteBateria2;
    private Sprite spriteBateria1;
    private Sprite spriteBateria0;

    //Contador
    private Sprite spriteContador;

    //Banderas
    private boolean juegoCorriendo = true;
    private boolean bat4Visible = false;
    private boolean bat3Visible = false;
    private boolean bat2Visible = false;
    private boolean bat1Visible = false;
    private boolean bat0Visible = false;

    //Osito
    private ITextureRegion regionOsito;
    private Sprite spriteOsito;

    //Controles
    private ITextureRegion regionBtnShoot;
    private ITextureRegion regionBtnCollect;

    // Escena de PAUSA
    private CameraScene escenaPausa;    // La escena que se muestra al hacer pausa
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;
    private ITextureRegion regionBtnHome;
    private ITextureRegion regionBtnReanudar;

    //Fin del juego
    //private ITextureRegion regionFin;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("cuartonino-luz.png");
        regionFondoSombra = cargarImagen("Sombra.png");
        //regionFin = cargarImagen("");

        //Controles
        regionBtnShoot = cargarImagen("BotShoot1.png");
        regionBtnCollect = cargarImagen("BotCollect1.png");

        //bateria
        regionBateria5 = cargarImagen("Llena.png");
        regionBateria4 = cargarImagen("Llena2.png");
        regionBateria3 = cargarImagen("Media.png");
        regionBateria2 = cargarImagen("media2.png");
        regionBateria1 = cargarImagen("Muerta.png");
        regionBateria0 = cargarImagen("Empty.png");

        //Monstruos
        regionMonstruo1 = cargarImagenMosaico("Monster1.png", 781,244,1,6 );

        //Osito
        regionOsito = cargarImagen("bolita.png");

        //contador
        regionContador = cargarImagen("ContadorMons.png");

        //font
        fontMonster = cargarFont("Fonts/Alice and the Wicked Monster.ttf");

        // Pausa
        regionBtnPausa = cargarImagen("PauseBotonJuego.png");
        regionPausa = cargarImagen("PauseChica.png");
        regionBtnHome = cargarImagen("BotonHome2.png");
        regionBtnReanudar =  cargarImagen("BackBot.png");
    }

    // Crea y regresa un font que carga desde un archivo .ttf
    private Font cargarFont(String archivo) {

        // La imagen que contiene cada símbolo
        final ITexture fontTexture = new BitmapTextureAtlas(actividadJuego.getEngine().getTextureManager(),1024,256);

        // Carga el archivo, tamaño 36, antialias y color
        Font tipoLetra = FontFactory.createFromAsset(actividadJuego.getEngine().getFontManager(), fontTexture, actividadJuego.getAssets(), archivo, 44, true, 0xFF00FF00);
        tipoLetra.load();
        tipoLetra.prepareLetters("0123456789".toCharArray());

        return tipoLetra;
    }

    @Override
    public void crearEscena() {
        //Fondo
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        //Monstruos
        agregarMonstruos();

        //Sombra
        spriteFondoSombra = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondoSombra);
        attachChild(spriteFondoSombra);

        //Acelerometro
        actividadJuego.getEngine().enableAccelerationSensor(actividadJuego, this);

        //Bateria
        spriteBateria5 = cargarSprite(regionBateria5.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria5.getHeight(), regionBateria5);
        attachChild(spriteBateria5);
        spriteBateria4 = cargarSprite(regionBateria4.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria4.getHeight(), regionBateria4);
        spriteBateria3 = cargarSprite(regionBateria3.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria3.getHeight(), regionBateria3);
        spriteBateria2 = cargarSprite(regionBateria2.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria2.getHeight(), regionBateria2);
        spriteBateria1 = cargarSprite(regionBateria1.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria1.getHeight(), regionBateria1);
        spriteBateria0 = cargarSprite(regionBateria0.getWidth()-80, ControlJuego.ALTO_CAMARA - regionBateria0.getHeight(), regionBateria0);

        //Contador y texto
        spriteContador = cargarSprite(ControlJuego.ANCHO_CAMARA - regionContador.getWidth(), ControlJuego.ALTO_CAMARA - regionContador.getHeight(), regionContador);
        attachChild(spriteContador);
        agregarTexto();

        //Crear botón SHOOT y agregarlo a la escena
        Sprite btnShoot = new Sprite (ControlJuego.ANCHO_CAMARA - regionBtnShoot.getWidth()+100, regionBtnCollect.getHeight()-70, regionBtnShoot, actividadJuego.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTounchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if (pSceneTounchEvent.isActionDown()){
                    disparar();
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
        Sprite botonHome = cargarSprite(regionPausa.getWidth()-100, regionPausa.getHeight()-200, regionBtnHome);
        escenaPausa.attachChild(botonHome);
        Sprite botonReanudar = cargarSprite(regionPausa.getWidth()-100, regionPausa.getHeight()-430, regionBtnReanudar);
        escenaPausa.attachChild(botonReanudar);
        escenaPausa.setBackgroundEnabled(false);

        //tiempo
        tiempo = 0;
    }

    private void agregarTexto() {
        score = 0;

        textoPuntaje = new Text(ControlJuego.ANCHO_CAMARA - regionContador.getWidth()+30,ControlJuego.ALTO_CAMARA - regionContador.getHeight()-4,fontMonster,""+score,actividadJuego.getVertexBufferObjectManager());
        attachChild(textoPuntaje);
    }

    private void disparar(){
        spriteOsito = new Sprite(ControlJuego.ANCHO_CAMARA/2, 0, regionOsito, actividadJuego.getVertexBufferObjectManager());
        attachChild(spriteOsito);
        JumpModifier disparo = new JumpModifier(1, spriteOsito.getX(), spriteFondoSombra.getX(), spriteOsito.getY(), spriteFondoSombra.getY(), 10);
        ScaleModifier pequeño = new ScaleModifier(1, spriteOsito.getScaleX(), spriteOsito.getScaleX()/2, spriteOsito.getScaleY(), spriteOsito.getScaleY()/2);
        ParallelEntityModifier paralelo = new ParallelEntityModifier(disparo, pequeño) { // dos modificadores en paralelo, (saltar y rotar)
            @Override
            protected void onModifierFinished(IEntity pItem) {  // Cuando termina el salto
                unregisterEntityModifier(this);
                super.onModifierFinished(pItem);
            }
        };
        spriteOsito.registerEntityModifier(paralelo);

        if (spriteOsito.collidesWith(spriteMonstruo)){
            // Lo destruye
            detachChild(spriteMonstruo);
            //agrega puntaje
            score = score + 20;
            // desaparece el osito
            detachChild(spriteOsito);
        }
    }

    private void agregarMonstruos(){
        spriteMonstruo = new AnimatedSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionMonstruo1, actividadJuego.getVertexBufferObjectManager());
        spriteMonstruo.animate(200);
        attachChild(spriteMonstruo);
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

    private void perderPila(){
        if (tiempo <= 10f){
        }
        if (tiempo > 10f && tiempo <=20f && !bat4Visible){
            detachChild(spriteBateria5);
            attachChild(spriteBateria4);
            bat4Visible = true;
        }
        if (tiempo > 20f && tiempo <=30f && !bat3Visible){
            detachChild(spriteBateria4);
            attachChild(spriteBateria3);
            bat3Visible = true;
        }
        if (tiempo > 30f && tiempo <=40f && !bat2Visible){
            detachChild(spriteBateria3);
            attachChild(spriteBateria2);
            bat2Visible = true;
        }
        if (tiempo >40f && tiempo <= 50f && !bat1Visible){
            detachChild(spriteBateria2);
            attachChild(spriteBateria1);
            bat1Visible = true;
        }
        if (tiempo >50f && tiempo <=60f && !bat0Visible){
            detachChild(spriteBateria1);
            attachChild(spriteBateria0);
            bat0Visible = true;
        }
    }


    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }
        tiempo= tiempo + pSecondsElapsed;
        perderPila();
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
        float nx = spriteFondo.getX() - dx*5;  // Nueva posición de la habitacion
        float nxs = spriteFondoSombra.getX() - dx;
        float dy = pAccelerationData.getY()+6;
        float ny = spriteFondoSombra.getY() - dy; //nueva posición del fondo negro
        //Log.i("acelerometro", "dy=" + dy);

        if (dx < 0) {
            // Izquierda
            if (nx < spriteFondo.getWidth() / 2) {
                spriteFondo.setX(nx);
                if (nxs < spriteFondoSombra.getWidth()/2 - ControlJuego.ANCHO_CAMARA) {
                    spriteFondoSombra.setX(nxs);
                }
            }
        } else {
            // Derecha
            if (nx > spriteFondo.getWidth() / 2 - ControlJuego.ANCHO_CAMARA) {
                spriteFondo.setX(nx);
                if (nxs > spriteFondoSombra.getWidth()/2 - ControlJuego.ANCHO_CAMARA){
                    spriteFondoSombra.setX(nxs);
                }
            }
        }

        if (dy > 0) {
            if (ny > 200) {
                spriteFondoSombra.setY(ny);
            }
        } else {

            if (ny < ControlJuego.ALTO_CAMARA - 200) {
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
        regionBateria0.getTexture().unload();
        regionBateria0=null;
        regionContador.getTexture().unload();
        regionContador = null;
        regionMonstruo1.getTexture().unload();
        regionMonstruo1 = null;
        regionBateria1.getTexture().unload();
        regionBateria1 = null;
        regionBateria2.getTexture().unload();
        regionBateria2 = null;
        regionBateria3.getTexture().unload();
        regionBateria3 = null;
        regionBateria4.getTexture().unload();
        regionBateria4 = null;
        regionBateria5.getTexture().unload();
        regionBateria5 = null;
    }
}