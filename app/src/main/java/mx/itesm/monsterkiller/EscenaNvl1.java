package mx.itesm.monsterkiller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.andengine.audio.sound.Sound;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
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

import java.util.ArrayList;

/**
 * Created by Aramis on 28/09/15.
 */
public class EscenaNvl1 extends EscenaBase implements IAccelerationListener {

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
    private ArrayList<Monstruos> listaMonst;
    private TiledTextureRegion regionMonstruo1;
    private TiledTextureRegion regionMonstruo2;
    private TiledTextureRegion regionMonstruo3;

    //pilas
    private ITextureRegion regionPila;

    //Contador
    private ITextureRegion regionContador;

    //Texto
    private Text textoPuntaje;
    private IFont fontMonster;

    //Score
    private int score;

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

    //sprite pila
    private Sprite spritePila1;

    //Contador
    private Sprite spriteContador;

    //Botones
    private Sprite spriteBtnShoot;
    private Sprite spriteBtnCollect;

    //Banderas
    private boolean juegoCorriendo = true;

    //banderas de baterias
    private boolean bat4Visible = false;
    private boolean bat3Visible = false;
    private boolean bat2Visible = false;
    private boolean bat1Visible = false;
    private boolean bat0Visible = false;

    //variables para cambiar pila
    private Sprite bateriaActual;
    private Sprite bateriaPasada;

    //banderas de pilas
    private boolean pila1Visible = false;

    //banderas de game over
    private boolean gameOver = false;

    //bandera ganador
    private boolean gameWin = false;

    //Peluche
    private ITextureRegion regionOsito;
    private ArrayList<Sprite> listaPeluches;

    //Controles
    private ITextureRegion regionBtnShoot;
    private ITextureRegion regionBtnCollect;

    // Escena de PAUSA
    private CameraScene escenaPausa;    // La escena que se muestra al hacer pausa
    private ITextureRegion regionPausa;
    private ITextureRegion regionBtnPausa;
    private ITextureRegion regionBtnHome;
    private ITextureRegion regionBtnReanudar;

    //Ojo
    private TiledTextureRegion regionOjo;

    //Boton retry
    private ITextureRegion regionBtnRetry;

    //fin de luz
    private TiledTextureRegion regionLuzApagada;

    //Fin del juego
    private ITextureRegion regionGO;
    private ITextureRegion regionBtnContinuar;
    private ITextureRegion regionBtnSalir;
    private ITextureRegion regionWin;

    // Efectos de sonido
    private Sound sonidoApagador;
    private Sound sonidoGrito;
    private Sound sonidoLanzar;
    private Sound sonidoRugido;


    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("CuartoNino.png");
        regionFondoSombra = cargarImagen("Sombra.png");

        //pila
        regionPila = cargarImagen("Pila.png");

        //Controles
        regionBtnShoot = cargarImagen("BotShoot.png");
        regionBtnCollect = cargarImagen("BotCollect.png");

        //bateria
        regionBateria5 = cargarImagen("Pila5.png");
        regionBateria4 = cargarImagen("Pila4.png");
        regionBateria3 = cargarImagen("Pila3.png");
        regionBateria2 = cargarImagen("Pila2.png");
        regionBateria1 = cargarImagen("Pila1.png");
        regionBateria0 = cargarImagen("Pila0.png");

        //Monstruos
        regionMonstruo1 = cargarImagenMosaico("Monster1.png", 800, 198, 1, 6);
        regionMonstruo2 = cargarImagenMosaico("Monster2.png", 969, 301, 1, 5);
        regionMonstruo3 = cargarImagenMosaico("Monster1.png", 800, 198, 1, 6);

        //Osito
        regionOsito = cargarImagen("Conejo.png");

        //contador
        regionContador = cargarImagen("ContadorMons.png");

        //font
        fontMonster = cargarFont("Fonts/Alice and the Wicked Monster.ttf");

        // Pausa
        regionBtnPausa = cargarImagen("BotonPausa.png");
        regionPausa = cargarImagen("PauseChica.png");
        regionBtnHome = cargarImagen("BotonHome.png");
        regionBtnReanudar =  cargarImagen("BackBot.png");

        //Ojo
        regionOjo = cargarImagenMosaico("Ojo3.png", 270, 97, 1, 4);

        //Btn retry
        regionBtnRetry = cargarImagen("BackBot.png");

        //luz apagada
        regionLuzApagada = cargarImagenMosaico("FinLuz.png", 2560, 801, 1, 2);

        //Fin
        regionGO = cargarImagen("GameOver.png");
        regionBtnContinuar = cargarImagen("BackBot.png");
        regionBtnSalir = cargarImagen("BotonHome.png");
        regionWin = cargarImagen("NiceJob.png");

        //Efectos de sonido
        sonidoApagador = cargarEfecto("Audio/EncenderLuz.wav");
        sonidoGrito = cargarEfecto("Audio/Grito.mp3");
        sonidoLanzar = cargarEfecto("Audio/LanzarPeluche.mp3");
        sonidoRugido = cargarEfecto("Audio/Monstruo.mp3");
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
        //peluches
        listaPeluches = new ArrayList<>();

        //monstruos
        listaMonst = new ArrayList<>();

        //Fondo
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        //Pilas
        spritePila1 = cargarSprite((int)(2400*Math.random())+200, (int)(150*Math.random())+80, regionPila);
        crearPilas();

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
                if (pSceneTounchEvent.isActionDown() && !gameWin && !gameOver){
                    sonidoLanzar.play();
                    disparar();
                }
                return super.onAreaTouched(pSceneTounchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        spriteBtnShoot = btnShoot;
        attachChild(spriteBtnShoot);
        registerTouchArea(spriteBtnShoot);

        //Crear botón COLLECT y agregarlo a la escena
        Sprite btnCollect = new Sprite (regionBtnCollect.getWidth()-100,  regionBtnCollect.getHeight()-70, regionBtnCollect, actividadJuego.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTounchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if (pSceneTounchEvent.isActionDown() && !gameWin && !gameOver){
                    recolectarPilas();
                }
                return super.onAreaTouched(pSceneTounchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        spriteBtnCollect = btnCollect;
        attachChild(spriteBtnCollect);
        registerTouchArea(spriteBtnCollect);

        // Crea el botón de PAUSA y lo agrega a la escena
        Sprite btnPausa = new Sprite(ControlJuego.ANCHO_CAMARA - regionBtnPausa.getWidth()+21, ControlJuego.ALTO_CAMARA - regionBtnPausa.getHeight()-30, regionBtnPausa, actividadJuego.getVertexBufferObjectManager()) {
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

        //tiempo
        tiempo = 0;

        // Crear la escena de PAUSA, pero NO lo agrega a la escena
        escenaPausa = new CameraScene(actividadJuego.camara);
        Sprite fondoPausa = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionPausa);
        escenaPausa.attachChild(fondoPausa);
        Sprite botonHome = new Sprite (regionPausa.getWidth()-100, regionPausa.getHeight()-200, regionBtnHome, actividadJuego.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTounchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if (pSceneTounchEvent.isActionDown() && !gameWin && !gameOver){
                    onBackKeyPressed();
                }
                return super.onAreaTouched(pSceneTounchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(botonHome);
        escenaPausa.registerTouchArea(botonHome);
        Sprite botonReanudar = new Sprite (regionPausa.getWidth() - 100, regionPausa.getHeight() - 430, regionBtnReanudar, actividadJuego.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTounchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){
                if (pSceneTounchEvent.isActionDown() && !gameWin && !gameOver){
                    reiniciarJuego();
                }
                return super.onAreaTouched(pSceneTounchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        escenaPausa.attachChild(botonReanudar);
        escenaPausa.registerTouchArea(botonReanudar);
        escenaPausa.setBackgroundEnabled(false);
        AnimatedSprite spriteOjo = cargarAnimatedSprite(670, 500, regionOjo);
        spriteOjo.animate(250);
        escenaPausa.attachChild(spriteOjo);

        // Reproduce música de fondo
        actividadJuego.reproducirMusica("Audio/Marty Gots a Plan.mp3",true);
    }

    private void agregarTexto() {
        score = 0;

        textoPuntaje = new Text(ControlJuego.ANCHO_CAMARA - regionContador.getWidth()+30,ControlJuego.ALTO_CAMARA - regionContador.getHeight()-4,fontMonster," "+score + " ",actividadJuego.getVertexBufferObjectManager());
        attachChild(textoPuntaje);
    }

    private void disparar(){
        Sprite spriteOsito = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, 0, regionOsito);
        attachChild(spriteOsito);
        JumpModifier disparo = new JumpModifier(1, spriteOsito.getX(), spriteFondoSombra.getX(), spriteOsito.getY(), spriteFondoSombra.getY(), 10);
        ScaleModifier pequeño = new ScaleModifier(1, spriteOsito.getScaleX(), spriteOsito.getScaleX()/5, spriteOsito.getScaleY(), spriteOsito.getScaleY()/5);
        ParallelEntityModifier paralelo = new ParallelEntityModifier(disparo, pequeño) { // dos modificadores en paralelo, (saltar y rotar)
            @Override
            protected void onModifierFinished(IEntity pItem) {  // Cuando termina el salto
                unregisterEntityModifier(this);
                super.onModifierFinished(pItem);
            }
        };
        spriteOsito.registerEntityModifier(paralelo);
        listaPeluches.add(spriteOsito);
    }

    private void agregarMonstruos(){

        AnimatedSprite monster = cargarAnimatedSprite((int)(2500*Math.random())+100, 300, regionMonstruo1);
        Monstruos monstruo = new Monstruos(monster, 2, -5, -6);
        listaMonst.add(monstruo);
        spriteFondo.attachChild(monstruo.getSprite());

        AnimatedSprite monster2 = cargarAnimatedSprite((int)(2500*Math.random())+100, (int)(200*Math.random())+50, regionMonstruo2);
        Monstruos monstruo2 = new Monstruos(monster2, 1, -5, -6);
        listaMonst.add(monstruo2);
        spriteFondo.attachChild(monstruo2.getSprite());

        AnimatedSprite monster3 = cargarAnimatedSprite((int)(2500*Math.random())+100, 200, regionMonstruo3);
        Monstruos monstruo3 = new Monstruos(monster3, 2, -7, -8);
        listaMonst.add(monstruo3);
        spriteFondo.attachChild(monstruo3.getSprite());
    }

    private void crearPilas(){
        if (tiempo >= 18f && !pila1Visible && !gameWin && !gameOver){
            spriteFondo.attachChild(spritePila1);
            pila1Visible = true;
        }
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
        if (tiempo > 10f && tiempo <=15f && !bat4Visible){
            eliminarSprite(spriteBateria5); //detachChild(spriteBateria5);
            attachChild(spriteBateria4);
            bat4Visible = true;
            bateriaActual = spriteBateria4;
            bateriaPasada = spriteBateria5;
        }
        if (tiempo > 15f && tiempo <=20f && !bat3Visible){
            eliminarSprite(spriteBateria4); //detachChild(spriteBateria4);
            attachChild(spriteBateria3);
            bat3Visible = true;
            bateriaActual = spriteBateria3;
            bateriaPasada = spriteBateria4;
        }
        if (tiempo > 20f && tiempo <=25f && !bat2Visible){
            eliminarSprite(spriteBateria3); //detachChild(spriteBateria3);
            attachChild(spriteBateria2);
            bat2Visible = true;
            bateriaActual = spriteBateria2;
            bateriaPasada = spriteBateria3;
        }
        if (tiempo >25f && tiempo <= 30f && !bat1Visible){
            eliminarSprite(spriteBateria2); //detachChild(spriteBateria2);
            attachChild(spriteBateria1);
            bat1Visible = true;
            bateriaActual = spriteBateria1;
            bateriaPasada = spriteBateria2;
        }
        if (tiempo >30f && tiempo <=34.1f && !bat0Visible){
            eliminarSprite(spriteBateria1);
            //detachChild(spriteBateria1);
            attachChild(spriteBateria0);
            bat0Visible = true;
            bateriaActual = spriteBateria0;
            bateriaPasada = spriteBateria1;
        }
        if (tiempo >= 34.1f && !gameOver && !gameWin){
            actividadJuego.detenerMusica();
            AnimatedSprite luz = cargarAnimatedSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionLuzApagada);
            luz.animate(100, 4);
            attachChild(luz);
            sonidoGrito.play();
            actividadJuego.getEngine().registerUpdateHandler(new TimerHandler(1.8f,
                    new ITimerCallback() {
                        @Override
                        public void onTimePassed(TimerHandler pTimerHandler) {
                            actividadJuego.getEngine().unregisterUpdateHandler(pTimerHandler);
                            sonidoRugido.play();
                            perdiste();
                        }
                    }));
            actividadJuego.getEngine().registerUpdateHandler(new TimerHandler(2.7f,
                    new ITimerCallback() {
                        @Override
                        public void onTimePassed(TimerHandler pTimerHandler) {
                            actividadJuego.getEngine().unregisterUpdateHandler(pTimerHandler);
                            finJuegoPerdedor();
                        }
                    }));
            gameOver = true;
        }
    }

    private void eliminarSprite(Sprite sprite) {
        if (sprite.hasParent()) {
            detachChild(sprite);
        }
    }

    private void recolectarPilas(){
        float px1 = spriteFondo.getX() + spritePila1.getX() - spriteFondo.getWidth()/2;

        if (spritePila1.hasParent()) {
            if (spriteFondoSombra.getX() >= (px1 - 200) && spriteFondoSombra.getX() <= (px1 + 200) && spriteFondoSombra.getY() >= (spritePila1.getY() - 200) && spriteFondoSombra.getY() <= (spritePila1.getY() + 200)) {
                spriteFondo.detachChild(spritePila1);
                if (tiempo > 15) {
                    tiempo = tiempo - 5;
                    detachChild(bateriaActual);
                    detachChild(spritePila1);
                    bat0Visible = bat1Visible = bat2Visible = bat3Visible = bat4Visible = false;
                    Log.i("PILA", "recolecta 1");
                }
            }
        }

    }

    private void perdiste(){
        Sprite spritePerdiste = new Sprite(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2 + 100, regionGO, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    onBackKeyPressed();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(spritePerdiste);
        attachChild(spritePerdiste);
        ScaleModifier agrandar = new ScaleModifier(3, spritePerdiste.getScaleX()/3, spritePerdiste.getScaleX(), spritePerdiste.getScaleY()/3, spritePerdiste.getScaleY());
        spritePerdiste.registerEntityModifier(agrandar);
    }

    private void finJuegoPerdedor(){
        // Crea el botón de Retry y lo agrega a la escena
        Sprite btnRetry = new Sprite(ControlJuego.ANCHO_CAMARA/2 + regionBtnRetry.getWidth(), ControlJuego.ALTO_CAMARA/4, regionBtnRetry, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    reiniciarJuego();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnRetry);
        registerTouchArea(btnRetry);
        AlphaModifier aparecer = new AlphaModifier(3, 0, 1);
        btnRetry.registerEntityModifier(aparecer);

        // Crea el botón de SALIR y lo agrega a la escena
        Sprite btnSalir = new Sprite(ControlJuego.ANCHO_CAMARA/2 - regionBtnSalir.getWidth(), ControlJuego.ALTO_CAMARA/4, regionBtnSalir, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    onBackKeyPressed();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnSalir);
        registerTouchArea(btnSalir);
        AlphaModifier aparecer2 = new AlphaModifier(3, 0, 1);
        btnSalir.registerEntityModifier(aparecer2);
    }

    private void reiniciarJuego() {
        guardarMarcadorAlto();
        admEscenas.liberarEscenaNvl1();
        admEscenas.crearEscenaNvl1();
        admEscenas.setEscena(TipoEscena.ESCENA_NVL1);
    }

    private void ganaste(){
        guardarMarcadorAlto();
        sonidoApagador.play();
        detachChild(spriteFondoSombra);
        detachChild(bateriaActual);
        detachChild(spriteBtnShoot);
        detachChild(spriteBtnCollect);

        Sprite spriteGanaste = new Sprite(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2 + 100, regionWin, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionUp()) {
                    onBackKeyPressed();
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        registerTouchArea(spriteGanaste);
        attachChild(spriteGanaste);
        ScaleModifier agrandar2 = new ScaleModifier(3, spriteGanaste.getScaleX()/3, spriteGanaste.getScaleX(), spriteGanaste.getScaleY()/3, spriteGanaste.getScaleY());
        spriteGanaste.registerEntityModifier(agrandar2);
    }

    private void checarScore(){
        if (score == 60 & !gameWin){
            gameWin = true;
            ganaste();
            finJuegoGanador();
        }
    }

    private void finJuegoGanador(){
        // Crea el botón de CONTINUE y lo agrega a la escena
        Sprite btnContinuar = new Sprite(ControlJuego.ANCHO_CAMARA/2 + regionBtnContinuar.getWidth(), ControlJuego.ALTO_CAMARA/3,
                regionBtnContinuar, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    pasarSigNivel();
                    guardarMarcadorAlto();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnContinuar);
        registerTouchArea(btnContinuar);
        AlphaModifier aparecer = new AlphaModifier(3, 0, 1);
        btnContinuar.registerEntityModifier(aparecer);

        // Crea el botón de SALIR y lo agrega a la escena
        Sprite btnSalir = new Sprite(ControlJuego.ANCHO_CAMARA/2 - regionBtnSalir.getWidth(), ControlJuego.ALTO_CAMARA/3,
                regionBtnSalir, actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    onBackKeyPressed();
                    return true;
                }
                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnSalir);
        registerTouchArea(btnSalir);
        AlphaModifier aparecer2 = new AlphaModifier(3, 0, 1);
        btnSalir.registerEntityModifier(aparecer2);
    }

    private void pasarSigNivel(){
        admEscenas.liberarEscenaNvl1();
        admEscenas.crearEscenaNvl2();
        admEscenas.setEscena(TipoEscena.ESCENA_NVL2);
    }

    private void actualizarPeluches() {

        // Se visita cada proyectil dentro de la lista, se recorre con el índice porque se pueden borrar datos
        for (int i = listaPeluches.size() - 1; i>=0; i--) {
            Sprite osito = listaPeluches.get(i);

            if (osito.getScaleX() <= 0.2f || osito.getScaleY() <= 0.2f) {  // 0.2 es 1/5 de la escala original
                detachChild(osito);
                listaPeluches.remove(osito);
                continue;
            }
            // Probar si colisionó con un enemigo
            // Se visita cada proyectil dentro de la lista, se recorre con el índice porque se pueden borrar datos
            for (int k = listaMonst.size() - 1; k >= 0; k--) {
                Monstruos monstruo = listaMonst.get(k);
                if (osito.collidesWith(monstruo.getSprite())) {
                    // Lo destruye
                    spriteFondo.detachChild(monstruo.getSprite());
                    listaMonst.remove(monstruo);
                    // desaparece el proyectil
                    detachChild(osito);
                    listaPeluches.remove(osito);
                    //agrega score
                    score = score + 20;
                    textoPuntaje.setText(score+"");
                    break;
                }
            }
        }
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (!juegoCorriendo) {
            return;
        }

        tiempo= tiempo + pSecondsElapsed;
        crearPilas();
        perderPila();
        actualizarPeluches();
        actualizarMonstruos();
        checarScore();

    }

    private void actualizarMonstruos() {
        for (int k = listaMonst.size() - 1; k >= 0; k--) {
            Monstruos monstruo = listaMonst.get(k);
            monstruo.movimiento(this);
        }
    }

    @Override
    public TipoEscena onBackKeyPressed() {

        guardarMarcadorAlto();

        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaNvl1();
        return null;
    }

    private void guardarMarcadorAlto() {
        // Abre preferencias y ve si el marcador actual es mayor que el guardado
        SharedPreferences preferencias = actividadJuego.getSharedPreferences("marcadorAlto", Context.MODE_PRIVATE);
        int anterior = preferencias.getInt("score", 0);
        SharedPreferences.Editor editor = preferencias.edit();
        if (score > anterior) {
            // Nuevo valor mayor, guardarlo
            editor.putInt("puntos", score);
        }
        editor.putInt("actual", score);
        editor.commit();
    }

    @Override public void onAccelerationChanged(AccelerationData pAccelerationData) {
        float dx = pAccelerationData.getX();
        float nx = spriteFondo.getX() - dx*5;  // Nueva posicion de la habitacion
        float nxs = spriteFondoSombra.getX() + dx*10;
        float dy = pAccelerationData.getY()*5+35;
        float ny = spriteFondoSombra.getY() - dy; //nueva posicion del fondo negro
        //Log.i("acelerometro", "dy=" + dy);


        if (dx < 0) {
            // Izquierda
            if (nx < spriteFondo.getWidth() / 2) {
                spriteFondo.setX(nx);

            } else {
                // Ya no se puede mover el fondo, pero podemos mover el centro del fondo negro
                if (nxs>=120) {
                    spriteFondoSombra.setX(nxs);
                }

            }
        } else {
            // Derecha
            if (nx > spriteFondo.getWidth() / 2 - ControlJuego.ANCHO_CAMARA) {
                spriteFondo.setX(nx);

            } else {
                // Ya no se puede mover el fondo, pero podemos mover el centro del fondo negro
                if (nxs<=ControlJuego.ANCHO_CAMARA-120) {
                    spriteFondoSombra.setX(nxs);
                }
            }
        }

        if (dy > 0) {
            if (ny > 200) {
                spriteFondoSombra.setY(ny);
            }
        } else {

            if (ny < ControlJuego.ALTO_CAMARA - 300) {
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
        actividadJuego.detenerMusica();

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
        regionMonstruo2.getTexture().unload();
        regionMonstruo2=null;
        regionLuzApagada.getTexture().unload();
        regionLuzApagada = null;
        regionGO.getTexture().unload();
        regionGO = null;
        regionPila.getTexture().unload();
        regionPila = null;
        regionBtnHome.getTexture().unload();
        regionBtnHome = null;
        regionBtnCollect.getTexture().unload();
        regionBtnCollect = null;
        regionBtnReanudar.getTexture().unload();
        regionBtnReanudar = null;
        regionBtnShoot.getTexture().unload();
        regionBtnShoot = null;
        regionFondoSombra.getTexture().unload();
        regionFondoSombra = null;
        regionOsito.getTexture().unload();
        regionOsito = null;
        regionBtnSalir.getTexture().unload();
        regionBtnSalir = null;
        regionBtnRetry.getTexture().unload();
        regionBtnRetry = null;
        regionBtnContinuar.getTexture().unload();
        regionBtnContinuar = null;
        fontMonster.getTexture().unload();
        fontMonster = null;
        regionMonstruo3.getTexture().unload();
        regionMonstruo3 = null;
    }

    public Sprite getSpriteFondo() {
        return spriteFondo;
    }
}