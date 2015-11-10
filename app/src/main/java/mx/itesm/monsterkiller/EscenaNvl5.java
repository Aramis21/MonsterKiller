package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 09/11/15.
 */
public class EscenaNvl5 extends EscenaBase implements IAccelerationListener {

    // Fondo
    private ITextureRegion regionFondo;

    //Fondo negro
    private ITextureRegion regionFondoSombra;

    // Sprite para el fondo
    private Sprite spriteFondo;
    private Sprite spriteFondoSombra;

    //Contador
    private ITextureRegion regionContador;

    //Contador
    private Sprite spriteContador;

    //Texto
    private Text textoPuntaje;
    private IFont fontMonster;

    //Score
    private int score;

    @Override
    public void cargarRecursos(){

    }

    @Override
    public void crearEscena(){

    }

    @Override public void onAccelerationChanged(AccelerationData pAccelerationData){

    }

    @Override
    public TipoEscena onBackKeyPressed() {

        //guardarMarcadorAlto();

        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaNvl5();
        return null;
    }

    @Override public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_NVL5;
    }

    @Override
    public void liberarEscena(){

    }

    @Override
    public void liberarRecursos(){

    }
}
