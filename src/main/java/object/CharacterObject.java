package object;

import animations.Animation;
import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public abstract class CharacterObject {
    protected Handler handler;
    protected int x, y;
    protected boolean interactive;

    protected SpriteSheet spriteSheet;
    protected int width;
    protected int height;
    protected int hp;

    protected int knockBackFrames;
    protected String knockBackDirection;

    protected float velX = 0, velY = 0;
    protected int movementSpeed, alignmentY;
    // The following line depends. Player has all 5, skeleton has isAttacking only, slimes and eyes have none.
    // public boolean up, down, left, right, isAttacking;
    protected int framedelay = 2;

    public Animation animation;

    public CharacterObject(int x, int y, SpriteSheet ss, Handler handler){
        this.x = x;
        this.y = y;
        this.spriteSheet = ss;
        this.handler = handler;
    }

    // update GameObject
    public abstract void tick();
    // making the image draw
    public abstract void render(Graphics g);

    public abstract void debugRender(Graphics g);

    // collision (everything has the hitbox of rectangle)
    public abstract Rectangle2D getBounds();

    public abstract Ellipse2D getBoundsFOV();

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
    public Animation getAnimation() {return this.animation;}

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

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHp() {
        return this.hp;
    }

    public void addHp(int value) {
        this.hp += value;
    }

    public void subHp(int value) {
        this.hp -= value;
    }

    public void setKnockBackFrames() {this.knockBackFrames = 0;}

    public void setKnockBackDirection(String value) {this.knockBackDirection = value;}
}
