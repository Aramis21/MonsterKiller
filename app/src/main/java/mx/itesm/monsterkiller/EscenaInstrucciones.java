package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by rmroman on 11/09/15.
 */
public class EscenaInstrucciones extends EscenaBase {
    // Regiones para imágenes
    private ITextureRegion regionFondo;
    // Sprite para el fondo
    private Sprite spriteFondo;

    //Ojo
    private TiledTextureRegion regionOjo;

    //Manos lado
    private TiledTextureRegion regionLado;

    //Manos arriba
    private TiledTextureRegion regionArriba;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("Instrucciones.jpg");

        //manos
        regionArriba = cargarImagenMosaico("ManitasUp.png", 2446, 300, 1, 7);
        regionLado = cargarImagenMosaico("ManitasLado.png", 2417, 300, 1, 6);

        regionOjo = cargarImagenMosaico("Ojo2.png", 869, 280, 1, 5);
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);

        //manitas
        AnimatedSprite spriteArriba = cargarAnimatedSprite(500, 160, regionArriba);
        spriteArriba.animate(400);
        attachChild(spriteArriba);

        AnimatedSprite spriteLado = cargarAnimatedSprite(1000, 160, regionLado);
        spriteLado.animate(400);
        attachChild(spriteLado);

        AnimatedSprite spriteOjo = cargarAnimatedSprite(1115, 580, regionOjo);
        spriteOjo.animate(250);
        attachChild(spriteOjo);
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaIntrucciones();
        return null;
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_INSTRUCCIONES;
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
        regionLado.getTexture().unload();
        regionLado = null;
        regionArriba.getTexture().unload();
        regionArriba = null;
        regionOjo.getTexture().unload();
        regionOjo = null;
    }
}
