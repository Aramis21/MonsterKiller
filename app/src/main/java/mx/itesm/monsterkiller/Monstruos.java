package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Aramis on 14/10/15.
 */
public class Monstruos {



    private int DX = -10;
    private int DY = 3;
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

            //if (spriteMonster.getX()< al tamaño que va a vanza y tmb del otro lado)
            if (acomulador >= 90){
                spriteMonster.setX(spriteMonster.getX()+(DX*20));
                acomulador=0;
            }
        }

        if (tipo == 2){
            if (spriteMonster.getX()>2550 && spriteMonster.getY()>700){
                spriteMonster.setPosition(spriteMonster.getX()-(DX*2), spriteMonster.getY() - DY);
            }
            if (spriteMonster.getX()<100 && spriteMonster.getY()>700){
                spriteMonster.isFlippedHorizontal();
                spriteMonster.setPosition(spriteMonster.getX()+(DX*2), spriteMonster.getY() - DY);
            }
            if (spriteMonster.getX()>2550 && spriteMonster.getY()<100){
                spriteMonster.setPosition(spriteMonster.getX()-(DX*2), spriteMonster.getY() + DY);
            }
            if (spriteMonster.getX()<100 && spriteMonster.getY()<100){
                spriteMonster.isFlippedHorizontal();
                spriteMonster.setPosition(spriteMonster.getX()+ (DX*2), spriteMonster.getY() + DY);
            }
        }
    }

}
