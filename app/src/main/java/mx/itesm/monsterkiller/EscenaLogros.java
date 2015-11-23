package mx.itesm.monsterkiller;

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

    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoLogros.jpg");
    }

    @Override
    public void crearEscena(){

        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
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
