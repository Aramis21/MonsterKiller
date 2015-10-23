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
        spriteMonster = sprite;
    }

    public AnimatedSprite getSpriteMonster(){
        return spriteMonster;
    }

    public void setAnimatedSpriteMonster (AnimatedSprite spriteMonster){
        this.spriteMonster = spriteMonster;
    }

    public void mover (int dx, int dy){
        spriteMonster.setPosition(spriteMonster.getX()+dx, spriteMonster.getY()+dy);
    }

}
