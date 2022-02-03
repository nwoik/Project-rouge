package object;

import core.SpriteSheet;

import java.awt.Graphics;
import java.awt.Rectangle;

//template for creating any object we could possibly want in our game
public abstract class GameObject {
    protected int x, y, width, height;
    protected float velX = 0, velY = 0;
    protected int movementSpeed;
    protected ID id;
    protected SpriteSheet spriteSheet;
    public boolean up, down, left, right;

    public GameObject(int x, int y, ID id, SpriteSheet ss){
        this.x = x;
        this.y = y;
        this.id = id;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.spriteSheet = ss;
    }

    // update GameObject
    public abstract void tick();
    // making the image draw
    public abstract void render(Graphics g);
    // collision (everything has the hitbox of rectangle)


    public ID getId(){
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
    public int getX(){
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public float getVelX(){
        return velX;
    }
    public void setVelX(float velX) {
        this.velX = velX;
    }
    public float getVelY(){
        return velY;
    }
    public void setVelY(float velY) {
        this.velY = velY;
    }
    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }


    protected abstract Rectangle getBounds();
}