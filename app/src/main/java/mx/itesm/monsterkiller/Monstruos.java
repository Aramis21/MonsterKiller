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

    public void mover (int dx, int dy){
        spriteMonster.setPosition(spriteMonster.getX()+dx, spriteMonster.getY()+dy);
    }

}
