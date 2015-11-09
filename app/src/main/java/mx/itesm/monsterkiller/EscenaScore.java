package mx.itesm.monsterkiller;

import android.content.Context;
import android.content.SharedPreferences;

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



    public void cargarRecursos() {
        regionFondo = cargarImagen("scorefin.png");

        // Marcador
        fontMonster = cargarFont("Fonts/Alice and the Wicked Monster.ttf",80,0xFF00CC00,"Mejor punta:0123456789");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        agregarMarcadorAlto();
    }

    private void agregarMarcadorAlto() {
        // Obtener de las preferencias el marcador mayor
        SharedPreferences preferencias = actividadJuego.getSharedPreferences("marcadorAlto", Context.MODE_PRIVATE);
        int puntos = preferencias.getInt("puntos",0);

        txtMarcador = new Text(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, fontMonster,"Mejor puntaje: "+ puntos, actividadJuego.getVertexBufferObjectManager());
        attachChild(txtMarcador);
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú opciones
        admEscenas.crearEscenaOpciones();
        admEscenas.setEscena(TipoEscena.ESCENA_OPCIONES);
        admEscenas.liberarEscenaScore();
        return null;
    }

}
