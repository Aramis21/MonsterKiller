package mx.itesm.monsterkiller;

import android.util.Log;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

/**
 * Created by Aramis on 07/10/15.
 */
public class EscenaOpciones extends EscenaBase {

    // Regiones para imágenes
    private ITextureRegion regionFondo;
    private ITextureRegion regionBtnLogros;
    private ITextureRegion regionBtnScores;

    //Sprite para el fondo
    private Sprite spriteFondo;

    // SpriteButton con estado ON/OFF
    private ButtonSprite btnMusic;
    private ITiledTextureRegion regionBtnMusic; // Imagen de mosaico con dos estados: normal-prendido

    //Menu de tipo SceneMenu
    private MenuScene menu; //contenedor de las opciones

    //Constantes para cada opcion del menu
    private final int OPCION_MUSICON = 0;
    private final int OPCION_MUSICOFF= 1;
    private final int OPCION_LOGROS = 2;
    private final int OPCION_SCORES = 3;

    // Botones de cada opción
    private ButtonSprite btnLogros;
    private ButtonSprite btnScores;

    //Bandera
    private boolean conSonido = true;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoOpciones.jpg");
        regionBtnMusic = cargarImagenMosaico("BotonMusic.png",381,135,1,2);
        regionBtnLogros = cargarImagen("BotonLogros2.png");
        regionBtnScores = cargarImagen("BotonScores2.png");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA / 2, ControlJuego.ALTO_CAMARA / 2, regionFondo);
        attachChild(spriteFondo);
        agregarMenu();
        agregarBtnMusic();
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaOpciones();
        return null;
    }

    private void agregarBtnMusic() {

        // Toggle button
        btnMusic = new ButtonSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionBtnMusic,actividadJuego.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                if (pSceneTouchEvent.isActionDown()) {
                    // Cambia el índice entre 0 y 1 ed manera alternada
                    btnMusic.setCurrentTileIndex((btnMusic.getCurrentTileIndex()+1)%2);
                }
                // 0-NORMAL, 1-PRESIONADO
                Log.i("Musica", "" + btnMusic.getCurrentTileIndex());
                return false; // Regresa falso para que Android no cambie el botón
            }
        };
        // El estado inicial del botón se lee desde las preferencias o se toma un valor por default
        // en este demo, siempre inicia prendido
        btnMusic.setCurrentTileIndex(1);
        registerTouchArea(btnMusic);
        attachChild(btnMusic);
    }

    private void agregarMenu(){
        //Crear el objeto que representa el menu
        menu = new MenuScene(actividadJuego.camara);
        //Centrado en la pantalla
        menu.setPosition(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2);
        //Crea las opciones
        IMenuItem opcionLogros = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_LOGROS, regionBtnLogros, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);
        IMenuItem opcionScores = new ScaleMenuItemDecorator(new SpriteMenuItem(OPCION_SCORES, regionBtnScores, actividadJuego.getVertexBufferObjectManager()), 1.5f, 1);

        //Agregar las opciones
        menu.addMenuItem(opcionLogros);
        menu.addMenuItem(opcionScores);

        // Termina la configuración
        menu.buildAnimations();
        menu.setBackgroundEnabled(false);   // Completamente transparente

        // Ubicar las opciones DENTRO del menú. El centro del menú es (0,0)

        opcionLogros.setPosition(-200, -200);
        opcionScores.setPosition(200, -200);

        // Registra el Listener para atender las opciones
        menu.setOnMenuItemClickListener(new MenuScene.IOnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
                                             float pMenuItemLocalX, float pMenuItemLocalY) {
                // El parámetro pMenuItem indica la opción oprimida
                switch (pMenuItem.getID()) {

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
