package mx.itesm.monsterkiller;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Representa la escena del MENU PRINCIPAL
 *
 * @author Roberto Martínez Román
 */
public class EscenaMenu extends EscenaBase {
    // Regiones para las imágenes de la escena
    private ITextureRegion regionFondo;
    private ITextureRegion regionBtnIntrucciones;
    private ITextureRegion regionBtnJugar;
    private ITextureRegion regionBtnCreditos;
    private ITextureRegion regionBtnOpciones;

    // Sprites sobre la escena
    private Sprite spriteFondo;

    // Un menú de tipo SceneMenu
    private MenuScene menu;     // Contenedor de las opciones
    // Constantes para cada opción
    private final int OPCION_INSTRUCCIONES = 0;
    private final int OPCION_JUGAR = 1;
    private final int OPCION_CREDITOS = 2;
    private final int OPCION_OPCIONES = 3;

    // Botones de cada opción
    private ButtonSprite btnIntrucciones;
    private ButtonSprite btnJugar;
    private ButtonSprite btnCreditos;
    private ButtonSprite btnOpciones;

    @Override
    public void cargarRecursos() {
        // Fondo
        regionFondo = cargarImagen("FondoPrincipal.jpg");
        // Botones del menú
        regionBtnIntrucciones = cargarImagen("BotonInstrucciones2.png");
        regionBtnJugar = cargarImagen("BotonPlay2.png");
        regionBtnCreditos = cargarImagen("BotonCredits2.png");
        regionBtnOpciones = cargarImagen("BotonOptions2.png");
    }

    @Override
    public void crearEscena() {
        // Creamos el sprite de manera óptima
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);

        // Crea el fondo de la pantalla
        SpriteBackground fondo = new SpriteBackground(1,1,1,spriteFondo);
        setBackground(fondo);
        setBackgroundEnabled(true);

        // Mostrar un recuadro atrás del menú
        //agregarFondoMenu();
        // Mostrar opciones de menú
        agregarMenu();
    }

    /*
    private void agregarFondoMenu() {
        Rectangle cuadro = new Rectangle(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2,
                0.75f*ControlJuego.ANCHO_CAMARA, 0.75f*ControlJuego.ALTO_CAMARA, actividadJuego.getVertexBufferObjectManager());
        cuadro.setColor(0.8f, 0.8f, 0.8f, 0.4f);
        attachChild(cuadro);
    }
    */

    private void agregarMenu() {
        // Crea el objeto que representa el menú
        menu = new MenuScene(actividadJuego.camara);
        // Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2,ControlJuego.ALTO_CAMARA/2);
        // Crea las opciones (por ahora, acerca de y jugar)
        IMenuItem opcionIntrucciones = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_INSTRUCCIONES, regionBtnIntrucciones, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcionJugar = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_JUGAR, regionBtnJugar, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcionCreditos = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_CREDITOS, regionBtnCreditos, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcionOpciones = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_OPCIONES, regionBtnOpciones, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        // Agrega las opciones al menú
        menu.addMenuItem(opcionIntrucciones);
        menu.addMenuItem(opcionJugar);
        menu.addMenuItem(opcionCreditos);
        menu.addMenuItem(opcionOpciones);

        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)
        opcionIntrucciones.setPosition(0, -220);
        opcionJugar.setPosition(0, 80);
        opcionCreditos.setPosition(-200, -70 );
        opcionOpciones.setPosition(200, -70);

        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch(pMenuItem.getID()) {
                    case OPCION_INSTRUCCIONES:
                        // Mostrar la escena de AcercaDe
                        admEscenas.crearEscenaIntrucciones();
                        admEscenas.setEscena(TipoEscena.ESCENA_INSTRUCCIONES);
                        admEscenas.liberarEscenaMenu();
                        break;

                    case OPCION_JUGAR:
                        admEscenas.crearEscenaNiveles();
                        admEscenas.setEscena(TipoEscena.ESCENA_NVLS);
                        admEscenas.liberarEscenaMenu();
                        break;

                    case OPCION_CREDITOS:
                        admEscenas.crearEscenaCreditos();
                        admEscenas.setEscena(TipoEscena.ESCENA_CREDITOS);
                        admEscenas.liberarEscenaMenu();
                        break;

                    case OPCION_OPCIONES:
                        admEscenas.crearEscenaOpciones();
                        admEscenas.setEscena(TipoEscena.ESCENA_OPCIONES);
                        admEscenas.liberarEscenaMenu();
                        break;

                }
                return true;
            }
        });

        // Asigna este menú a la escena
        setChildScene(menu);
    }

    // La escena se debe actualizar en este método que se repite "varias" veces por segundo
    // Aquí es donde programan TODA la acción de la escena (movimientos, choques, disparos, etc.)
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

    }


    @Override
    public TipoEscena onBackKeyPressed() {
        // Salir del juego, no hacemos nada
        return null;
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_MENU;
    }

    @Override
    public void liberarEscena() {
        this.detachSelf();      // La escena se deconecta del engine
        this.dispose();         // Libera la memoria
        liberarRecursos();
    }

    @Override
    public void liberarRecursos() {
        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
