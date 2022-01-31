package core;

import object.GameObject;

//Class that pans around the level, following the player.
public class Camera {
    private float x,y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object){
        //could do below line but our version smoother
        //x += (object.getX()) - 1000/2;

        x += ((object.getX() - x) - 1500/2)*0.05f;
        y += ((object.getY() - y) - 800/2)*0.05f;
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
}
