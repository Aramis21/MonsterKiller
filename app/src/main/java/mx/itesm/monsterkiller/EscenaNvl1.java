package mx.itesm.monsterkiller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
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
    private Sprite spritePila2;

    //Contador
    private Sprite spriteContador;

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
    private boolean pila2Visible = false;

    //banderas de game over
    private boolean gameOver = false;

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

    //fin de luz
    private TiledTextureRegion regionLuzApagada;

    //Fin del juego
    private ITextureRegion regionGO;
    private  ITextureRegion regionBtnContinuar;
    private ITextureRegion regionBtnSalir;


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
        regionBateria5 = cargarImagen("Llena.png");
        regionBateria4 = cargarImagen("Llena2.png");
        regionBateria3 = cargarImagen("Media.png");
        regionBateria2 = cargarImagen("media2.png");
        regionBateria1 = cargarImagen("Muerta.png");
        regionBateria0 = cargarImagen("Empty.png");

        //Monstruos
        regionMonstruo1 = cargarImagenMosaico("Monster1.png", 800, 198, 1, 6);
        regionMonstruo2 = cargarImagenMosaico("Monster2.png", 969, 301, 1, 5);

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

        //luz apagada
        regionLuzApagada = cargarImagenMosaico("FinLuz.png", 2560, 801, 1, 2);

        //Fin
        regionGO = cargarImagen("GameOver.png");
        regionBtnContinuar = cargarImagen("BackBot.png");
        regionBtnSalir = cargarImagen("BotonHome.png");
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
        spritePila1 = cargarSprite((int)(2400*Math.random())+200, (int)(350*Math.random())+110, regionPila);
        spritePila2 = cargarSprite((int)(2400*Math.random())+200, (int)(350*Math.random())+110, regionPila);
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
                    recolectarPilas();
                }
                return super.onAreaTouched(pSceneTounchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        attachChild(btnCollect);
        registerTouchArea(btnCollect);

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
        Sprite botonHome = cargarSprite(regionPausa.getWidth()-100, regionPausa.getHeight()-200, regionBtnHome);
        escenaPausa.attachChild(botonHome);
        Sprite botonReanudar = cargarSprite(regionPausa.getWidth()-100, regionPausa.getHeight()-430, regionBtnReanudar);
        escenaPausa.attachChild(botonReanudar);
        escenaPausa.setBackgroundEnabled(false);
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

        AnimatedSprite monster = cargarAnimatedSprite((int)(2500*Math.random())+100, /*(int)(600*Math.random())+*/300, regionMonstruo1);
        Monstruos monstruo = new Monstruos(monster, 2);
        listaMonst.add(monstruo);
        spriteFondo.attachChild(monstruo.getSprite());

        AnimatedSprite monster2 = cargarAnimatedSprite((int)(2500*Math.random())+100, (int)(300*Math.random())+100, regionMonstruo2);
        Monstruos monstruo2 = new Monstruos(monster2, 1);
        listaMonst.add(monstruo2);
        spriteFondo.attachChild(monstruo2.getSprite());
    }

    private void crearPilas(){
        if (tiempo >= 15f && !pila1Visible){
            spriteFondo.attachChild(spritePila1);
            pila1Visible = true;
        }
        if (tiempo >= 20f && !pila2Visible){
            spriteFondo.attachChild(spritePila2);
            pila2Visible = true;
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
            detachChild(spriteBateria5);
            attachChild(spriteBateria4);
            bat4Visible = true;
            bateriaActual = spriteBateria4;
            bateriaPasada = spriteBateria5;
        }
        if (tiempo > 15f && tiempo <=20f && !bat3Visible){
            detachChild(spriteBateria4);
            attachChild(spriteBateria3);
            bat3Visible = true;
            bateriaActual = spriteBateria3;
            bateriaPasada = spriteBateria4;
        }
        if (tiempo > 20f && tiempo <=25f && !bat2Visible){
            detachChild(spriteBateria3);
            attachChild(spriteBateria2);
            bat2Visible = true;
            bateriaActual = spriteBateria2;
            bateriaPasada = spriteBateria3;
        }
        if (tiempo >25f && tiempo <= 30f && !bat1Visible){
            detachChild(spriteBateria2);
            attachChild(spriteBateria1);
            bat1Visible = true;
            bateriaActual = spriteBateria1;
            bateriaPasada = spriteBateria2;
        }
        if (tiempo >30f && tiempo <=34f && !bat0Visible){
            detachChild(spriteBateria1);
            attachChild(spriteBateria0);
            bat0Visible = true;
            bateriaActual = spriteBateria0;
            bateriaPasada = spriteBateria1;
        }
        if (tiempo >= 34f && !gameOver){
            AnimatedSprite luz = cargarAnimatedSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionLuzApagada);
            luz.animate(100, 4);
            attachChild(luz);
            actividadJuego.getEngine().registerUpdateHandler(new TimerHandler(1.8f,
                    new ITimerCallback() {
                        @Override
                        public void onTimePassed(TimerHandler pTimerHandler) {
                            actividadJuego.getEngine().unregisterUpdateHandler(pTimerHandler);
                            perdiste();
                        }
                    }));
            gameOver = true;
        }
    }

    private void recolectarPilas(){
        if (spriteFondoSombra.getX() >= (spritePila1.getX()-200) && spriteFondoSombra.getX() <= (spritePila1.getX() + 200) && spriteFondoSombra.getY() >= (spritePila1.getY()-200) && spriteFondoSombra.getY() <= (spritePila1.getY() + 200)){
            spriteFondo.detachChild(spritePila1);
            tiempo=tiempo-5;
            detachChild(bateriaActual);
            attachChild(bateriaPasada);
        }
        if (spriteFondoSombra.getX() >= (spritePila2.getX()-200) && spriteFondoSombra.getX() <= (spritePila2.getX() + 200) && spriteFondoSombra.getY() >= (spritePila2.getY()-200) && spriteFondoSombra.getY() <= (spritePila2.getY() + 200)){
            spriteFondo.detachChild(spritePila2);
            tiempo= tiempo-5;
            detachChild(bateriaActual);
            attachChild(bateriaPasada);
        }
    }

    private void perdiste(){
        Sprite spritePerdiste = new Sprite(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2, regionGO, actividadJuego.getVertexBufferObjectManager()) {
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

    }

    private void actualizarMonstruos() {
        for (int k = listaMonst.size() - 1; k >= 0; k--) {
            Monstruos monstruo = listaMonst.get(k);
            monstruo.movimiento();
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
        float nx = spriteFondo.getX() - dx*5;  // Nueva posición de la habitacion
        float nxs = spriteFondoSombra.getX() + dx*5;
        float dy = pAccelerationData.getY()+6;
        float ny = spriteFondoSombra.getY() - dy; //nueva posición del fondo negro
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
    }
}