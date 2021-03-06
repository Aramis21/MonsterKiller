package mx.itesm.monsterkiller;

import org.andengine.engine.Engine;

/**
 * Administra la escena que se verá en la pantalla
 */
public class AdministradorEscenas {
    // Instancia única
    private static final AdministradorEscenas INSTANCE = new AdministradorEscenas();
    protected ControlJuego actividadJuego;

    // Declara las distintas escenas que forman el juego
    private EscenaBase escenaSplash;
    private EscenaBase escenaMenu;
    private EscenaBase escenaIntrucciones;
    private EscenaBase escenaCreditos;
    private EscenaBase escenaNvl1;
    private EscenaBase escenaNvl2;
    private EscenaBase escenaNvl3;
    private EscenaBase escenaNvl4;
    private EscenaBase escenaNvl5;
    private EscenaBase escenaOpciones;
    private EscenaBase escenaNiveles;
    private EscenaBase escenaScore;
    private EscenaBase escenaLogros;

    // El tipo de escena que se está mostrando
    private TipoEscena tipoEscenaActual = TipoEscena.ESCENA_SPLASH;
    // La escena que se está mostrando
    private EscenaBase escenaActual;
    // El engine para hacer el cambio de escenas
    private Engine engine;

    // Asigna valores iniciales del administrador
    public static void inicializarAdministrador(ControlJuego actividadJuego, Engine engine) {
        getInstance().actividadJuego = actividadJuego;
        getInstance().engine = engine;
    }

    // Regresa la instancia del administrador de escenas
    public static AdministradorEscenas getInstance() {
        return INSTANCE;
    }

    // Regresa el tipo de la escena actual
    public TipoEscena getTipoEscenaActual() {
        return tipoEscenaActual;
    }

    // Regresa la escena actual
    public EscenaBase getEscenaActual() {

        return escenaActual;
    }

    public  EscenaBase liberarEscenaActual(){
        getEscenaActual().liberarEscena();
        return null;
    }

    /*
     * Pone en la pantalla la escena que llega como parámetro y guarda el nuevo estado
     */
    private void setEscenaBase(EscenaBase nueva) {
        engine.setScene(nueva);
        escenaActual = nueva;
        tipoEscenaActual = nueva.getTipoEscena();
    }

    /**
     * Cambia a la escena especificada en el parámetro
     * @param nuevoTipo la nueva escena que se quiere mostrar
     */
    public void setEscena(TipoEscena nuevoTipo) {
        switch (nuevoTipo) {
            case ESCENA_SPLASH:
                setEscenaBase(escenaSplash);
                break;
            case ESCENA_MENU:
                setEscenaBase(escenaMenu);
                break;
            case ESCENA_INSTRUCCIONES:
                setEscenaBase(escenaIntrucciones);
                break;
            case ESCENA_CREDITOS:
                setEscenaBase (escenaCreditos);
                break;
            case ESCENA_NVL1:
                setEscenaBase(escenaNvl1);
                break;
            case ESCENA_NVL2:
                setEscenaBase(escenaNvl2);
                break;
            case ESCENA_NVL3:
                setEscenaBase(escenaNvl3);
                break;
            case ESCENA_NVL4:
                setEscenaBase(escenaNvl4);
                break;
            case ESCENA_NVL5:
                setEscenaBase(escenaNvl5);
                break;
            case ESCENA_OPCIONES:
                setEscenaBase(escenaOpciones);
                break;
            case ESCENA_NVLS:
                setEscenaBase(escenaNiveles);
                break;
            case ESCENA_SCORE:
                setEscenaBase(escenaScore);
                break;
            case ESCENA_LOGROS:
                setEscenaBase(escenaLogros);
                break;
        }
    }

    //*** Crea la escena de Splash

    public void crearEscenaSplash() {
        // Carga los recursos
        escenaSplash = new EscenaSplash();
    }

    //*** Libera la escena de Splash

    public void liberarEscenaSplash() {
        escenaSplash.liberarEscena();
        escenaSplash = null;
    }

    // ** MENU
    //*** Crea la escena de MENU

    public void crearEscenaMenu() {
        // Carga los recursos
        escenaMenu = new EscenaMenu();
    }

    //*** Libera la escena de MENU

    public void liberarEscenaMenu() {
        escenaMenu.liberarEscena();
        escenaMenu = null;
    }

    //*** Crea la escena de Acerca De

    public void crearEscenaIntrucciones() {
        // Carga los recursos
        escenaIntrucciones = new EscenaInstrucciones();
    }

    //*** Libera la escena de AcercDe

    public void liberarEscenaIntrucciones() {
        escenaIntrucciones.liberarEscena();
        escenaIntrucciones = null;
    }

    //***Crea la escena Creditos

    public void crearEscenaCreditos(){
        //Cargar los recursos
        escenaCreditos = new EscenaCreditos();
    }

    //***Libera la escena Creditos

    public void liberarEscenaCreditos(){
        escenaCreditos.liberarEscena();
        escenaCreditos = null;
    }

    //Crear Escena Niveles

    public void crearEscenaNiveles(){
        escenaNiveles = new MapaNiveles();
    }

    //Liberar escena Niveles

    public void liberarEscenaNiveles(){
        escenaNiveles.liberarEscena();
        escenaNiveles = null;
    }


    //*** Crea la escena de Nvl1

    public void crearEscenaNvl1() {
        // Carga los recursos
        escenaNvl1 = new EscenaNvl1();
    }

    //*** Libera la escena de Nvl1

    public void liberarEscenaNvl1() {
        escenaNvl1.liberarEscena();
        escenaNvl1 = null;
    }

    //*** Crea la escena de Nvl1

    public void crearEscenaNvl2() {
        // Carga los recursos
        escenaNvl2 = new EscenaNvl2();
    }

    //*** Libera la escena de Nvl1

    public void liberarEscenaNvl2() {
        escenaNvl2.liberarEscena();
        escenaNvl2 = null;
    }

    //*** Crea la escena de Nvl1

    public void crearEscenaNvl3() {
        // Carga los recursos
        escenaNvl3 = new EscenaNvl3();
    }

    //*** Libera la escena de Nvl1

    public void liberarEscenaNvl3() {
        escenaNvl3.liberarEscena();
        escenaNvl3 = null;
    }

    //*** Crea la escena de Nvl1

    public void crearEscenaNvl4() {
        // Carga los recursos
        escenaNvl4 = new EscenaNvl4();
    }

    //*** Libera la escena de Nvl1

    public void liberarEscenaNvl4() {
        escenaNvl4.liberarEscena();
        escenaNvl4 = null;
    }

    //*** Crea la escena de Nvl1

    public void crearEscenaNvl5() {
        // Carga los recursos
        escenaNvl5 = new EscenaNvl5();
    }

    //*** Libera la escena de Nvl1

    public void liberarEscenaNvl5() {
        escenaNvl5.liberarEscena();
        escenaNvl5 = null;
    }

    //*** Crea la escena de Opciones

    public void crearEscenaOpciones() {
         //Carga los recursos
         escenaOpciones = new EscenaOpciones();
    }

    //*** Libera la escena de Opciones

    public void liberarEscenaOpciones() {
        escenaOpciones.liberarEscena();
        escenaOpciones = null;
    }

    //Crear escena score

    public void crearEscenaScore(){
        escenaScore = new EscenaScore();
    }

    //liberar escena score

    public void liberarEscenaScore(){
        escenaScore.liberarEscena();
        escenaScore = null;
    }

    //Crear escena logros

    public void crearEscenaLogros(){
        escenaLogros = new EscenaLogros();
    }

    //liberar escena logros

    public void liberarEscenaLogros(){
        escenaLogros.liberarEscena();
        escenaLogros = null;
    }

}
