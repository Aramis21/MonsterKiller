package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Aramis on 28/09/15.
 */
public class EscenaNvl1 extends EscenaBase implements IAccelerationListener{

    // Fondo
    private ITextureRegion regionFondo;

    //Sprite animado - monsters


    // Sprite para el fondo
    private Sprite spriteFondo;

    //Energía del personaje
    private int energia = 100;

    //Fin del juego
    //private ITextureRegion regionFin;

    @Override
    public void cargarRecursos() {
        regionFondo = cargarImagen("FondoNvl1.jpg");
        //regionFin = cargarImagen("");
    }

    @Override
    public void crearEscena() {
        spriteFondo = cargarSprite(ControlJuego.ANCHO_CAMARA/2, ControlJuego.ALTO_CAMARA/2, regionFondo);
        attachChild(spriteFondo);
        actividadJuego.getEngine().enableAccelerationSensor(actividadJuego, this);
    }

    @Override
    public TipoEscena onBackKeyPressed() {
        // Regresar al menú principal
        admEscenas.crearEscenaMenu();
        admEscenas.setEscena(TipoEscena.ESCENA_MENU);
        admEscenas.liberarEscenaNvl1();
        return null;
    }

    @Override public void onAccelerationChanged(AccelerationData pAccelerationData) {
        float dx = pAccelerationData.getX();
        float nx = spriteFondo.getX() - 2*dx; //nueva posición
        if (dx < 0){
            //izquierda
            if (nx < spriteFondo.getWidth()/2){
                spriteFondo.setX(nx);
            }
            else{
                //derecha
                if (nx >spriteFondo.getWidth()/2-ControlJuego.ANCHO_CAMARA){
                    spriteFondo.setX(nx);
                }
            }
        }

    }

    @Override public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
    }

    @Override
    public TipoEscena getTipoEscena() {
        return TipoEscena.ESCENA_NVL1;
    }

    @Override
    public void liberarEscena() {
        liberarRecursos();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void liberarRecursos() {
        //Detiene el acelerometro
        actividadJuego.getEngine().disableAccelerationSensor(actividadJuego);

        regionFondo.getTexture().unload();
        regionFondo = null;
    }
}
