package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Created by Aramis on 14/10/15.
 */
public class Monstruos {

    private AnimatedSprite spriteMonster;

    public Monstruos(AnimatedSprite sprite) {
        this.spriteMonster = sprite;
        this.spriteMonster.animate(200);

    }

    public AnimatedSprite getSprite(){
        return spriteMonster;
    }

    public void setSprite (AnimatedSprite sprite){
        this.spriteMonster = sprite;
    }

    public void movimiento1 (int dx, int dy){
        if (dx>2550 && dy >800) {
            spriteMonster.setPosition(spriteMonster.getX() - dx, spriteMonster.getY() - dy);
        }

        if (dx <100 && dy <100){
            spriteMonster.setPosition(spriteMonster.getX() + dx, spriteMonster.getY() + dy);
        }
    }

    public void movimiento2 (){

    }

}
