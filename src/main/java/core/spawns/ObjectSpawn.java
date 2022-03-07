package core.spawns;

import core.SpriteSheet;
import object.*;

public class ObjectSpawn {
    private  GameObject gameObject;
    private Handler handler;
    private SpriteSheet spriteSheet;

    public ObjectSpawn(Handler handler, GameObject gameObject){
        this.handler = handler;
        this.gameObject = gameObject;
    }

    public void loadEnemy(){
        this.handler.addObject(this.gameObject, this.handler.enemies);
    }

    public void loadObject(){
        this.handler.addObject(this.gameObject, this.handler.objects);
    }


    public SpriteSheet getSpriteSheet() {
        return this.gameObject.getSpriteSheet();
    }
}
