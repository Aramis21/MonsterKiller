package mx.itesm.monsterkiller;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 13/10/15.
 */
public class MapaNiveles extends EscenaBase {

    // Regiones para imágenes
    private ITextureRegion regionFondo;
    private ITextureRegion regionStar1;
    private ITextureRegion regionStar2;
    private ITextureRegion regionStar3;
    private ITextureRegion regionStar4;
    private ITextureRegion regionStar5;

    // Sprite para el fondo
    private Sprite spriteFondo;

    // Un menú de tipo SceneMenu
    private MenuScene menu;     // Contenedor de las opciones
    // Constantes para cada opción
    private final int NIVEL_1 = 0;
    private final int NIVEL_2 = 1;
    private final int NIVEL_3 = 2;
    private final int NIVEL_4 = 3;
    private final int NIVEL_5 = 4;

    //botones
    private ButtonSprite Star1;
    private ButtonSprite Star2;
    private ButtonSprite Star3;
    private ButtonSprite Star4;
    private ButtonSprite Star5;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoNivelespt1.jpg");

        regionStar1 = cargarImagen("niv1.png");
        regionStar2 = cargarImagen("niv2.png");
        regionStar3 = cargarImagen("niv3.png");
        regionStar4 = cargarImagen("niv4.png");
        regionStar5 = cargarImagen("niv5.png");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
        agregarMenu();
    }

    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2);
        // Crea las opciones (por ahora, acerca de y jugar)
        IMenuItem opcion1 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_1, regionStar1, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion2 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_2, regionStar2, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion3 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_3, regionStar3, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion4 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_4, regionStar4, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion5 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_5, regionStar5, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        // Agrega las opciones al menú
        menu.addMenuItem(opcion1);
        menu.addMenuItem(opcion2);
        menu.addMenuItem(opcion3);
        menu.addMenuItem(opcion4);
        menu.addMenuItem(opcion5);

        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcion1.setPosition(190, -220);
        opcion2.setPosition(-190,-100);
        opcion3.setPosition(-80, 130 );
        opcion4.setPosition(200, 130);
        opcion5.setPosition(300, -70);

        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch(pMenuItem.getID()) {
                    case NIVEL_1:
                        // Mostrar la escena de AcercaDe
                        admEscenas.crearEscenaNvl1();
                        admEscenas.setEscena(TipoEscena.ESCENA_NVL1);
                        admEscenas.liberarEscenaNiveles();
                        break;

                    case NIVEL_2:

                        break;

                    case NIVEL_3:

                        break;

                    case NIVEL_4:

                        break;
                    case NIVEL_5:

                        break;

                }
                return true;
            }
        });

        // Asigna este menú a la escena
        setChildScene(menu);
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaNiveles();
        return null;
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_NVLS;
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
