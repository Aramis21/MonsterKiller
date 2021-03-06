package mx.itesm.monsterkiller;

import android.content.Context;
import android.content.SharedPreferences;

import org.andengine.audio.sound.Sound;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 09/11/15.
 */
public class EscenaScore extends EscenaOpciones {

    //region fondo
    private ITextureRegion regionFondo;

    //sprite fondo
    private Sprite spriteFondo;

    //marcador alto
    private Text txtMarcador;
    private IFont fontMonster;

    //marcador anterior
    private Text txtAnterior;

    // Efectos de sonido
    private Sound sonidoSplat;

    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoScore.png");

        // Marcador
        fontMonster = cargarFont("Fonts/Alice and the Wicked Monster.ttf",140,0xFFD5EDF7,"Best cor La:0123456789");

        //Efectos de sonido
        sonidoSplat = cargarEfecto("Audio/Splat.mp3");
    }

    @Override
    public void crearEscena() {

        actividadJuego.getEngine().registerUpdateHandler(new TimerHandler(0.7f,
                new ITimerCallback() {
                    @Override
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        actividadJuego.getEngine().unregisterUpdateHandler(pTimerHandler);
                        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
                        ScaleModifier agrandar = new ScaleModifier(0.7f, 0, spriteFondo.getScaleX(), 0, spriteFondo.getScaleY());
                        attachChild(spriteFondo);
                        spriteFondo.registerEntityModifier(agrandar);
                    }
                }));

        actividadJuego.getEngine().registerUpdateHandler(new TimerHandler(1.5f,
                new ITimerCallback() {
                    @Override
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        actividadJuego.getEngine().unregisterUpdateHandler(pTimerHandler);
                        sonidoSplat.play();
                        agregarMarcadorAlto();
                    }
                }));
    }

    private void agregarMarcadorAlto() {
        // Obtener de las preferencias el marcador mayor
        SharedPreferences preferencias = actividadJuego.getSharedPreferences("marcadorAlto", Context.MODE_PRIVATE);
        int puntos = preferencias.getInt("puntos", 0);
        int actual = preferencias.getInt("score", 0);

        txtMarcador = new Text(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2 + 150, fontMonster,"Best score: "+ puntos, actividadJuego.getVertexBufferObjectManager());
        attachChild(txtMarcador);

        txtAnterior = new Text(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2 -150, fontMonster, "Last score: " + actual, actividadJuego.getVertexBufferObjectManager());
        attachChild(txtAnterior);
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú opciones
        admEscenas.crearEscenaOpciones();
        admEscenas.setEscena(TipoEscena.ESCENA_OPCIONES);
        admEscenas.liberarEscenaScore();
        return null;
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_SCORE;
    }

    @Override
    public void liberarEscena() {
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        regionFondo.getTexture().unload();
        regionFondo = null;
    }

}
