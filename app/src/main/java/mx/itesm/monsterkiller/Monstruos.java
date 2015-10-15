package mx.itesm.monsterkiller;

import org.andengine.entity.sprite.Sprite;

/**
 * Created by Aramis on 14/10/15.
 */
public class Monstruos {

    private Sprite spriteMonster;

    public Monstruos(Sprite sprite) {
        spriteMonster = sprite;
    }

    public Sprite getSpriteMonster(){
        return spriteMonster;
    }

    public void setSpriteMonster (Sprite spriteMonster){
        this.spriteMonster = spriteMonster;
    }

    public void mover (int dx, int dy){
        spriteMonster.setPosition(spriteMonster.getX()+dx, spriteMonster.getY()+dy);
    }

}
