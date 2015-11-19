package mx.itesm.monsterkiller;

import android.content.Context;
import android.content.SharedPreferences;

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

    private ITextureRegion regionGray1;
    private ITextureRegion regionGray2;
    private ITextureRegion regionGray3;
    private ITextureRegion regionGray4;
    private ITextureRegion regionGray5;

    private ITextureRegion estrella2;
    private ITextureRegion estrella3;
    private ITextureRegion estrella4;
    private ITextureRegion estrella5;


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

    private int puntos;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoNivelespt1.jpg");

        regionStar1 = cargarImagen("niv1.png");
        regionStar2 = cargarImagen("niv2.png");
        regionStar3 = cargarImagen("niv3.png");
        regionStar4 = cargarImagen("niv4.png");
        regionStar5 = cargarImagen("niv5.png");

        regionGray1 = cargarImagen("egris1.png");
        regionGray2 = cargarImagen("egris2.png");
        regionGray3 = cargarImagen("egris3.png");
        regionGray4 = cargarImagen("egris4.png");
        regionGray5 = cargarImagen("egris5.png");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
        agregarMenu();
    }

    private void marcadorAlto() {
        // Obtener de las preferencias el marcador mayor
        SharedPreferences preferencias = actividadJuego.getSharedPreferences("marcadorAlto", Context.MODE_PRIVATE);
        puntos = preferencias.getInt("puntos", 0);
    }

    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        marcadorAlto();

        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2);
        // Crea las opciones (por ahora, acerca de y jugar)
        if(puntos == 0){
            estrella2 = regionGray2;
            estrella3 = regionGray3;
            estrella4 = regionGray4;
            estrella5 = regionGray5;
        }

        if (puntos>= 20){
            estrella2 = regionStar2;
            estrella3 = regionGray3;
            estrella4 = regionGray4;
            estrella5 = regionGray5;
        }

        if (puntos >= 120){
            estrella2 = regionStar2;
            estrella3 = regionStar3;
            estrella4 = regionGray4;
            estrella5 = regionGray5;

        }
        if (puntos >= 160){
            estrella2 = regionStar2;
            estrella3 = regionStar3;
            estrella4 = regionStar4;
            estrella5 = regionGray5;

        }
        if (puntos >= 200){
            estrella2 = regionStar2;
            estrella3 = regionStar3;
            estrella4 = regionStar4;
            estrella5 = regionStar5;
        }

        IMenuItem opcion1 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_1, regionStar1, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion2 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_2, estrella2, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion3 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_3, estrella3, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion4 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_4, estrella4, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcion5 = new ScaleMenuItemDecorator(new SpriteMenuItem(NIVEL_5, estrella5, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

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
        opcion1.setPosition(190, -210);
        opcion2.setPosition(-170,-100);
        opcion3.setPosition(-80, 112);
        opcion4.setPosition(200, 130);
        opcion5.setPosition(270, -30);

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
                        admEscenas.crearEscenaNvl2();
                        admEscenas.setEscena(TipoEscena.ESCENA_NVL2);
                        admEscenas.liberarEscenaNiveles();
                        break;

                    case NIVEL_3:
                        admEscenas.crearEscenaNvl3();
                        admEscenas.setEscena(TipoEscena.ESCENA_NVL3);
                        admEscenas.liberarEscenaNiveles();
                        break;

                    case NIVEL_4:
                        admEscenas.crearEscenaNvl4();
                        admEscenas.setEscena(TipoEscena.ESCENA_NVL4);
                        admEscenas.liberarEscenaNiveles();
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
        regionGray1.getTexture().unload();
        regionGray1 = null;
        regionGray2.getTexture().unload();
        regionGray2 = null;
        regionGray3.getTexture().unload();
        regionGray3 = null;
        regionGray4.getTexture().unload();
        regionGray4 = null;
        regionGray5.getTexture().unload();
        regionGray5 = null;
        regionStar1.getTexture().unload();
        regionStar1 = null;
        regionStar2.getTexture().unload();
        regionStar2 = null;
        regionStar3.getTexture().unload();
        regionStar3 = null;
        regionStar4.getTexture().unload();
        regionStar4 = null;
        regionStar5.getTexture().unload();
        regionStar5 = null;
    }
}
