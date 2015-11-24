package mx.itesm.monsterkiller;

import android.content.Context;
import android.content.SharedPreferences;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 09/11/15.
 */
public class EscenaLogros extends EscenaOpciones {

    //region fondo
    private ITextureRegion regionFondo;

    //sprite fondo
    private Sprite spriteFondo;

    private ITextureRegion regionTrofeo1;
    private ITextureRegion regionTrofeo2;
    private ITextureRegion regionTrofeo3;
    private ITextureRegion regionTrofeo4;

    private ITextureRegion regionTrofeoG1;
    private ITextureRegion regionTrofeoG2;
    private ITextureRegion regionTrofeoG3;
    private ITextureRegion regionTrofeoG4;

    private ITextureRegion regtrofeo1;
    private ITextureRegion regtrofeo2;
    private ITextureRegion regtrofeo3;
    private ITextureRegion regtrofeo4;

    private int puntos;

    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoLogros.jpg");

        regionTrofeo1 = cargarImagen("trofeocolor1.png");
        regionTrofeo2 = cargarImagen("trofeocolor2.png");
        regionTrofeo3 = cargarImagen("trofeocolor3.png");
        regionTrofeo4 = cargarImagen("trofeocolor4.png");

        regionTrofeoG1 = cargarImagen("trofeoByN1.png");
        regionTrofeoG2 = cargarImagen("trofeoByN2.png");
        regionTrofeoG3 = cargarImagen("trofeoByN3.png");
        regionTrofeoG4 = cargarImagen("trofeoByN4.png");
    }

    @Override
    public void crearEscena(){

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);

        marcadorAlto();

        if(puntos == 0){
            regtrofeo1 = regionTrofeoG1;
            regtrofeo2 = regionTrofeoG2;
            regtrofeo3 = regionTrofeoG3;
            regtrofeo4 = regionTrofeoG4;
        }

        if (puntos>= 20){
            regtrofeo1 = regionTrofeo1;
            regtrofeo2 = regionTrofeoG2;
            regtrofeo3 = regionTrofeoG3;
            regtrofeo4 = regionTrofeoG4;
        }

        if (puntos >= 120){
            regtrofeo1 = regionTrofeo1;
            regtrofeo2 = regionTrofeo2;
            regtrofeo3 = regionTrofeoG3;
            regtrofeo4 = regionTrofeoG4;

        }
        if (puntos >= 160){
            regtrofeo1 = regionTrofeo1;
            regtrofeo2 = regionTrofeo2;
            regtrofeo3 = regionTrofeo3;
            regtrofeo4 = regionTrofeoG4;

        }
        if (puntos >= 200){
            regtrofeo1 = regionTrofeo1;
            regtrofeo2 = regionTrofeo2;
            regtrofeo3 = regionTrofeo3;
            regtrofeo4 = regionTrofeo4;
        }

        Sprite trofeo4 = cargarSprite(ControlJuego.ANCHO_CAMARA/2 + 290, ControlJuego.ALTO_CAMARA/2 - 230, regtrofeo4);
        attachChild(trofeo4);
        Sprite trofeo3 = cargarSprite(ControlJuego.ANCHO_CAMARA/2 - 260, ControlJuego.ALTO_CAMARA/2 - 220, regtrofeo3);
        attachChild(trofeo3);
        Sprite trofeo2 = cargarSprite(ControlJuego.ANCHO_CAMARA/2 + 340, ControlJuego.ALTO_CAMARA/2 + 125, regtrofeo2);
        attachChild(trofeo2);
        Sprite trofeo1 = cargarSprite(ControlJuego.ANCHO_CAMARA/2 - 335, ControlJuego.ALTO_CAMARA/2 + 150, regtrofeo1);
        attachChild(trofeo1);
    }

    private void marcadorAlto() {
        // Obtener de las preferencias el marcador mayor
        SharedPreferences preferencias = actividadJuego.getSharedPreferences("marcadorAlto", Context.MODE_PRIVATE);
        puntos = preferencias.getInt("puntos", 0);
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú opciones
        admEscenas.crearEscenaOpciones();
        admEscenas.setEscena(TipoEscena.ESCENA_OPCIONES);
        admEscenas.liberarEscenaLogros();
        return null;
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_LOGROS;
    }

    @Override
    public void liberarEscena() {
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        //regionFondo.getTexture().unload();
        //regionFondo = null;
    }


}
