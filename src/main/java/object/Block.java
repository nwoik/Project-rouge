package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

//Block class that represents each block pixel in level.png. Prevents player/enemy movement.
//Can Implement various textures in this specific object using BlockID (probably in constructor).
public class Block extends GameObject{
    private BufferedImage block_image;
    private BlockID blockID;

    public Block(int x, int y, ID id, SpriteSheet ss, BlockID bID) {
        super(x, y, id, ss);
        this.blockID = bID;
        this.width = 64;
        this.height = 64;

        //switch statement - depending on block type grab different image from sprite sheet
        block_image = ss.grabImage(1, 1, this.width, this.height);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x,y,this.width,this.height);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, this.width, this.width);
    }

}
