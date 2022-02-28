package core.spawns;

import core.Colour;
import core.SpriteSheet;
import object.*;

import java.awt.image.BufferedImage;

public class CharacterSpawn {
    private Handler handler;

    private SpriteSheet spriteSheet;

    public CharacterSpawn(Handler handler, SpriteSheet ss){
        this.spriteSheet = ss;
        this.handler = handler;
    }

    public void loadCharacter(int xx, int yy){
        handler.player = new Player(xx * 64, yy * 64, ID.Player, handler, spriteSheet);

    }

    public void setPosition(int xx, int yy) {
        handler.player.setX(xx);
        handler.player.setY(yy);
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
}
