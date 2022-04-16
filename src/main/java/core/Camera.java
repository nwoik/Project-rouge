package core;

import object.CharacterObject;
import object.GameObject;

//Class that pans around the level, following the player.
public class Camera {
    private float x,y;
    private int HEIGHT, WIDTH;

    public Camera(float x, float y, int HEIGHT, int WIDTH) {
        this.x = x;
        this.y = y;
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
    }

    public void tick(CharacterObject object){
        //could do below line but our version smoother
        //x += (object.getX()) - 1000/2;

        x += ((object.getX() - x) - WIDTH/2)*0.05f;
        y += ((object.getY() - y) - HEIGHT/2)*0.05f;
    }

    public float getX(){
        return x;
    }

    public void setX(float x){
        this.x = x;
    }

    public float getY(){
        return y;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getWidth() {return WIDTH;}

    public float getHeight() {return HEIGHT;}
}
