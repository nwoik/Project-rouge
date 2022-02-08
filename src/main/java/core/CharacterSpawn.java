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

    private void clearCharacter() {
        handler.emptyList();
    }

    public void loadCharacter(int xx, int yy, Colour colour){
        clearCharacter();
        if (colour.getRed() == 0 && colour.getGreen() == 0 && colour.getBlue() == 255) {
            handler.player = new Player(xx * 64, yy * 64, ID.Player, handler, spriteSheet);
        }
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
}
