package mx.itesm.monsterkiller;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 07/10/15.
 */
public class EscenaOpciones extends EscenaBase {

    // Regiones para imágenes
    private ITextureRegion regionFondo;
    private ITextureRegion regionBtnMusicOn;
    private ITextureRegion regionBtnMusicOff;
    private ITextureRegion regionBtnLogros;
    private ITextureRegion regionBtnScores;

    //Sprite para el fondo
    private Sprite spriteFondo;

    //Menu de tipo SceneMenu
    private MenuScene menu; //contenedor de las opciones

    //Constantes para cada opcion del menu
    private final int OPCION_MUSICON = 0;
    private final int OPCION_MUSICOFF= 1;
    private final int OPCION_LOGROS = 2;
    private final int OPCION_SCORES = 3;

    // Botones de cada opción
    private ButtonSprite btnMusicOn;
    private ButtonSprite btnMusicOff;
    private ButtonSprite btnLogros;
    private ButtonSprite btnScores;

    //Bandera
    private boolean conSonido = true;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoOpciones.jpg");

        regionBtnMusicOff = cargarImagen("BotonMusicOff2.png");
        regionBtnMusicOn = cargarImagen("BotonMusicOn2.png");
        regionBtnLogros = cargarImagen("BotonLogros2.png");
        regionBtnScores = cargarImagen("BotonScores2.png");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
        agregarMenu();
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaOpciones();
        return null;
    }

    private void agregarMenu(){
        //Crear el objeto que representa el menu
        menu = new MenuScene(actividadJuego.camara);
        //Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2);
        //Crea las opciones
        IMenuItem opcionMusicOn = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_MUSICON, regionBtnMusicOn, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcionMusicOff = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_MUSICOFF, regionBtnMusicOff, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcionLogros = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_LOGROS, regionBtnLogros, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcionScores = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_SCORES, regionBtnScores, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        //Agregar las opciones
        //menu.addMenuItem(opcionMusicOff);
        menu.addMenuItem(opcionMusicOn);
        menu.addMenuItem(opcionLogros);
        menu.addMenuItem(opcionScores);

        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)

        opcionMusicOff.setPosition(-20, 0);
        opcionMusicOn.setPosition(-20, 0);
        opcionLogros.setPosition(-200, -200);
        opcionScores.setPosition(200, -200);

        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch (pMenuItem.getID()) {
                    case OPCION_MUSICOFF:
                        // QUITAR SONIDO
                        break;

                    case OPCION_MUSICON:
                        //PONER SONIDO
                        break;

                    case OPCION_SCORES:
                        //PONER ESCENA DE SCORES
                        admEscenas.crearEscenaScore();
                        admEscenas.setEscena(TipoEscena.ESCENA_SCORE);
                        admEscenas.liberarEscenaOpciones();
                        break;

                    case OPCION_LOGROS:
                        //MOSTRAR ESCENA DE LOGROS
                        //admEscenas.crearEscenaOpciones();
                        //admEscenas.setEscena(TipoEscena.ESCENA_OPCIONES);
                        //admEscenas.liberarEscenaMenu();
                        break;

                }
                return true;
            }
        });

        // Asigna este menú a la escena
        setChildScene(menu);
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_OPCIONES;
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
