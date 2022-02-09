package core;

import java.awt.image.BufferedImage;
import object.*;

public class LevelLoader {
    private Handler handler;
    private SpriteSheet spriteSheet;
    private CharacterSpawn characterSpawn;

    public LevelLoader(Handler handler, CharacterSpawn characterSpawn, SpriteSheet ss){
        this.spriteSheet = ss;
        this.handler = handler;
        this.characterSpawn = characterSpawn;
    }

    //might be useful for loading new level
    private void clearLevel() {
        handler.emptyList();
    }

    //loads level given buffered image
    public void loadLevel(BufferedImage image){
        clearLevel();
        int w = image.getWidth();
        int h = image.getHeight();

        Colour colour = new Colour();
        int cunt = 0;

        for(int xx=0; xx<w; xx++){
            for(int yy=0; yy<h; yy++){
                int pixel = image.getRGB(xx, yy);
                colour.setRed((pixel >> 16) & 0xff);
                colour.setGreen((pixel >> 8) & 0xff);
                colour.setBlue((pixel) & 0xff);

                //To add a new color to be read from the level, just simply put in the RGB values of that color
                //in a new else if statement, and add the appropriate object to the handler. For example, for different
                //block types we can implement the texture variation with BlockID enum

                if (colour.getRed() == 255 && colour.getGreen() == 0 && colour.getBlue() == 0) {
                    System.out.println();
                    handler.addObject(new Block(xx * 64, yy * 64, ID.Block, spriteSheet, BlockID.wall));
                    System.out.println(cunt++ + " | " + handler.object.size());
                }
                else if (colour.getRed() == 0 && colour.getGreen() == 255 && colour.getBlue() == 0) {
                    handler.addObject(new Enemy(xx * 64, yy * 64, ID.Enemy, handler, spriteSheet));
                }
                else if (colour.getRed() == 0 && colour.getGreen() == 0 && colour.getBlue() == 255) {
                    characterSpawn.loadCharacter(xx, yy, colour);
                    System.out.println("Plz spawn");
                }
            }
        }
    }
}
