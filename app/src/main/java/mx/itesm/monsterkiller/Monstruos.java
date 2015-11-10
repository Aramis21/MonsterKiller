package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Aramis on 14/10/15.
 */
public class Monstruos {



    private int DX = -5;
    private int DY = -3;
    private int acomulador = 0;

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

    public void movimiento() {
        if (tipo == 1){
            spriteMonster.setX(spriteMonster.getX()+ DX);
            acomulador = acomulador + 3;
            if (spriteMonster.getX()>2550) {
                spriteMonster.setFlippedHorizontal(false);
                DX = DX*(-1);
            }
            if (spriteMonster.getX()<100 ){
                spriteMonster.setFlippedHorizontal(true);
                DX = DX*(-1);
            }

            if (spriteMonster.getX()< 1900 && spriteMonster.getX()>600) {
                if (acomulador >= 150) {
                    spriteMonster.setX(spriteMonster.getX() + (DX * 60));
                    acomulador = 0;
                }
            }
        }

        if (tipo == 2){
            spriteMonster.setX(spriteMonster.getX()+ DX);
            spriteMonster.setY(spriteMonster.getY()+ DY);
            acomulador = acomulador + 2;
            if (spriteMonster.getX()>2550){
                spriteMonster.setFlippedHorizontal(false);
                DX = DX*(-1);
            }
            if (spriteMonster.getX()<100 ){
                spriteMonster.setFlippedHorizontal(true);
                DX = DX*(-1);
            }
            if (spriteMonster.getY()>600){
                DY = DY*(-1);
            }
            if (spriteMonster.getY()<100){
                DY= DY*(-1);
            }
            if (spriteMonster.getX()< 1900 && spriteMonster.getX()>600 && spriteMonster.getY()<600 && spriteMonster.getY() > 100) {
                if (acomulador >= 200) {
                    spriteMonster.setX(spriteMonster.getX() + (DX * 60));
                    acomulador = 0;
                }
            }
        }
    }

}
