package mx.itesm.monsterkiller;

import android.util.Log;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Aramis on 14/10/15.
 */
public class Monstruos {



    private int DX = -5;
    private int DY = -6;
    private int DXX = -10;
    private int acumulador = 0;


    private AnimatedSprite spriteMonster;

    private int tipo;

    public Monstruos(AnimatedSprite sprite, int tipo) {
        this.spriteMonster = sprite;
        this.spriteMonster.animate(200);
        this.tipo = tipo;
    }

    public AnimatedSprite getSprite(){
        return spriteMonster;
    }

    public void setSprite (AnimatedSprite sprite){
        this.spriteMonster = sprite;
    }

    public void movimiento(EscenaBase escena) {
        if (tipo == 1){
            spriteMonster.setX(spriteMonster.getX()+ DX);
            acumulador = acumulador + 3;
            if (spriteMonster.getX()>2550) {
                spriteMonster.setFlippedHorizontal(false);
                DX = DX*(-1);
            }
            if (spriteMonster.getX()<100 ){
                spriteMonster.setFlippedHorizontal(true);
                DX = DX*(-1);
            }

            if (spriteMonster.getX()< 1900 && spriteMonster.getX()>600) {
                if (acumulador >= 150) {
                    spriteMonster.setX(spriteMonster.getX() + (DX * 60));
                    acumulador = 0;
                }
            }
        }

        if (tipo == 2){
            spriteMonster.setX(spriteMonster.getX()+ DX);
            spriteMonster.setY(spriteMonster.getY()+ DY);
            acumulador = acumulador + 2;
            if (spriteMonster.getX()>2550){
                spriteMonster.setFlippedHorizontal(false);
                DX = DX*(-1);
            }
            if (spriteMonster.getX()<100 ){
                spriteMonster.setFlippedHorizontal(true);
                DX = DX*(-1);
            }
            if (spriteMonster.getY()> 600){
                DY = DY*(-1);
            }
            if (spriteMonster.getY()<100){
                DY= DY*(-1);
            }
            if (spriteMonster.getX()< 1900 && spriteMonster.getX()> 600 && spriteMonster.getY() < 480 && spriteMonster.getY() > 220) {
                if (acumulador >= 200) {
                    spriteMonster.setX(spriteMonster.getX() + (DX * 60));
                    spriteMonster.setY(spriteMonster.getY() + (DY * 20));
                    acumulador = 0;
                }
            }
        }

        if (tipo == 3){
            spriteMonster.setX(spriteMonster.getX()+ DXX);
            spriteMonster.setY(spriteMonster.getY()+ DY);
            acumulador = acumulador + 4;
            if (spriteMonster.getX()>2550){
                spriteMonster.setFlippedHorizontal(false);
                DXX = DXX*(-1);
            }
            if (spriteMonster.getX()<100 ){
                spriteMonster.setFlippedHorizontal(true);
                DXX = DXX*(-1);
            }
            if (spriteMonster.getY()> 600){
                DY = DY*(-1);
            }
            if (spriteMonster.getY()<100){
                DY= DY*(-1);
            }
            if (spriteMonster.getX()< 1900 && spriteMonster.getX()> 600 && spriteMonster.getY() < 480 && spriteMonster.getY() > 220) {
                Log.i("cumplio cond 1", "cond 1");
                if (acumulador >= 400) {
                    ((EscenaNvl1)escena).getSpriteFondo().detachChild(spriteMonster);
                }
                if (acumulador >= 900) {
                    ((EscenaNvl1)escena).getSpriteFondo().attachChild(spriteMonster);
                    acumulador=0;
                }
            }
        }
    }

}
