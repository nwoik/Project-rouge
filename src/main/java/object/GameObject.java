package object;

import animations.Animation;
import core.SpriteSheet;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


//template for creating any object we could possibly want in our game
public abstract class GameObject {
    protected Handler handler;
    protected int x, y;
    protected boolean interactive;

    protected ID id;
    protected SpriteSheet spriteSheet;
    protected int width;
    protected int height;
    protected int hp;

    protected int knockBackFrames;
    protected String knockBackDirection;

    public GameObject(int x, int y, ID id, SpriteSheet ss){
        this.x = x;
        this.y = y;
        this.id = id;
        this.spriteSheet = ss;
    }

    // update GameObject
    public abstract void tick();
    // making the image draw
    public abstract void render(Graphics g);

    public abstract void debugRender(Graphics g);

    // collision (everything has the hitbox of rectangle)
    public abstract Rectangle2D getBounds();

    public abstract Ellipse2D getBoundsFOV();


    public ID getId(){
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
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