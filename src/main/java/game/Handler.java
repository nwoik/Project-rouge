package game;


import java.util.LinkedList;

import java.awt.Graphics;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    private boolean up=false,down=false,left=false,right=false;

    //ticks every object in our list.
    public void tick() {
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    //renders every object in list
    public void render(Graphics g) {
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    //add to list
    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    //remove from list
    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }
    public boolean isRight(){
        return right;
    }

    public void setRight(boolean b) {
        this.right = b;
    }

    public boolean isLeft(){
        return left;
    }

    public void setLeft(boolean b) {
        this.left = b;
    }

    public boolean isUp(){
        return up;
    }

    public void setUp(boolean b) {
        this.up = b;
    }

    public boolean isDown(){
        return down;
    }

    public void setDown(boolean b) {
        this.down = b;
    }
}

