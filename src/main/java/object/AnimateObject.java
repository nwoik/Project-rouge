package object;

import animations.Animation;
import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimateObject extends GameObject{
    protected float velX = 0, velY = 0;
    protected int movementSpeed, alignmentY;
    public boolean up, down, left, right, attack;
    protected int framedelay = 2;

    public Animation animation;

    protected List<BufferedImage> standingFacingDown = new ArrayList<BufferedImage>();
    protected List<BufferedImage> standingFacingLeft = new ArrayList<BufferedImage>();
    protected List<BufferedImage> standingFacingUp = new ArrayList<BufferedImage>();
    protected List<BufferedImage> standingFacingRight = new ArrayList<BufferedImage>();

    public Animation standFacingDown;
    public Animation standFacingLeft;
    public Animation standFacingUp;
    public Animation standFacingRight;

    protected List<BufferedImage> walkingUp = new ArrayList<BufferedImage>();
    protected List<BufferedImage> walkingLeft = new ArrayList<BufferedImage>();
    protected List<BufferedImage> walkingRight = new ArrayList<BufferedImage>();
    protected List<BufferedImage> walkingDown = new ArrayList<BufferedImage>();

    protected Animation walkDown;
    protected Animation walkUp;
    protected Animation walkLeft;
    protected Animation walkRight;

    protected List<BufferedImage> attackingDown = new ArrayList<BufferedImage>();
    protected List<BufferedImage> attackingUp = new ArrayList<BufferedImage>();
    protected List<BufferedImage> attackingLeft = new ArrayList<BufferedImage>();
    protected List<BufferedImage> attackingRight = new ArrayList<BufferedImage>();

    protected Animation attackDown;
    protected Animation attackUp;
    protected Animation attackLeft;
    protected Animation attackRight;

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

    public AnimateObject(int x, int y, ID id, SpriteSheet ss){
        super(x, y, id, ss);
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void debugRender(Graphics g) {

    }

    @Override
    public Rectangle2D getBounds() {
        return null;
    }

    @Override
    public Ellipse2D getBoundsFOV() {
        return null;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int value) {
        this.x += value;
    }

    public void subX(int value) {
        this.x -= value;
    }

    public void addY(int value) {
        this.y += value;
    }

    public void subY(int value) {
        this.y -= value;
    }
}
