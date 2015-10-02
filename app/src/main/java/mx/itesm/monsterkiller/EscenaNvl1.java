package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 28/09/15.
 */
public class EscenaNvl1 extends EscenaBase{

    // Regiones para imágenes
    private ITextureRegion regionFondo;
    // Sprite para el fondo
    private Sprite spriteFondo;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
    }

    @Override
    public void onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaCreditos();
    }

        @Override
        public TipoEscena getTipoEscena() {
            return TipoEscena.ESCENA_NVL1;
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
