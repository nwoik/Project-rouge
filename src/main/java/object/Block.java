package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

//Block class that represents each block pixel in level.png. Prevents player/enemy movement.
//Can Implement various textures in this specific object using BlockID (probably in constructor).
public class Block{
    protected BufferedImage block_image;
    protected BlockID blockID;

    protected Handler handler;
    protected int x, y;
    protected boolean interactive;

    protected SpriteSheet spriteSheet;
    protected int width;
    protected int height;


    public Block(int x, int y, SpriteSheet ss, BlockID bID) {
        this.x = x;
        this.y = y;
        this.spriteSheet = ss;
        this.blockID = bID;
        this.width = 64;
        this.height = 64;
        this.block_image = ss.grabImage(1, 1, this.width, this.height);
    }

    // collision (everything has the hitbox of rectangle)

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

//    public int getWidth() {
//        return this.width;
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    public int getHeight() {
//        return this.height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }

    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    public void debugRender(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x,y,this.width,this.height);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(x, y, this.width, this.height);
    }


}
