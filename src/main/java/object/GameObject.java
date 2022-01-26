package object;

import java.awt.Graphics;
import java.awt.Rectangle;

//template for creating any object we could possibly want in our game
public abstract class GameObject {
    protected int x, y;
    protected float velX = 0, velY = 0;
    protected ID id;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    // check moving
    public abstract void tick();
    // making the image draw
    public abstract void render(Graphics g);
    // collision
    public abstract Rectangle getBounds();

    public ID getId(){
        return id;
    }
    public void setX(ID id) {
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
}
