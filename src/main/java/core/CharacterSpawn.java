package core;

import object.*;

import java.awt.image.BufferedImage;

public class CharacterSpawn {
    private Handler handler;

    private SpriteSheet spriteSheet;

    public CharacterSpawn(Handler handler, SpriteSheet ss){
        this.spriteSheet = ss;
        this.handler = handler;
    }

    public void loadCharacter(int xx, int yy, Colour colour){
        handler.player = new Player(xx * 64, yy * 64, ID.Player, handler, spriteSheet);

    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
}
