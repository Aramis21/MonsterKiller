package mx.itesm.monsterkiller;

/**
 * Created by Aramis on 09/11/15.
 */
public class EscenaLogros extends EscenaOpciones {

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
