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



    private int DX;
    private int DY;
    private int tipo;

    private int acumulador = 0;

    private AnimatedSprite spriteMonster;



    public Monstruos(AnimatedSprite sprite, int tipo, int DX, int DY) {
        this.spriteMonster = sprite;
        this.spriteMonster.animate(200);
        this.tipo = tipo;
        this.DX = DX;
        this.DY = DY;
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
                    spriteMonster.setX(Math.min(spriteMonster.getX() + (DX * 60), 2549));
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
                Log.i("sprite monster", "x=" + spriteMonster.getX());
                Log.i("sprite monster", "x=" +spriteMonster.getX());
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
            if (spriteMonster.getX()< 1900 && spriteMonster.getX()> 600 && spriteMonster.getY() < 400 && spriteMonster.getY() > 200) {
                if (acumulador >= 200) {
                    spriteMonster.setX(Math.min(spriteMonster.getX() + (DX * 60), 2549));
                    spriteMonster.setY(spriteMonster.getY() + (DY * 20));
                    acumulador = 0;
                }
            }
        }

        if (tipo == 3){
            spriteMonster.setX(spriteMonster.getX()+ DX);
            spriteMonster.setY(spriteMonster.getY()+ DY);
            acumulador = acumulador + 4;
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
            if (spriteMonster.getX()< 1900 && spriteMonster.getX()> 600 && spriteMonster.getY() < 400 && spriteMonster.getY() > 200) {
                if (acumulador >= 400) {
                    escena.getSpriteFondo().detachChild(spriteMonster);
                }
                if (acumulador >= 900) {
                    escena.getSpriteFondo().attachChild(spriteMonster);
                    acumulador=0;
                }
            }
        }

        if (tipo == 4){
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
                    escena.getSpriteFondo().detachChild(spriteMonster);
                }
                if (acumulador >= 210) {
                    escena.getSpriteFondo().attachChild(spriteMonster);
                    acumulador=0;
                }
            }
        }
    }

}
