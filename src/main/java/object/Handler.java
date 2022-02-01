package object;

import java.util.LinkedList;

import java.awt.Graphics;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public Player player;

    //ticks every object in our list.
    public void tick() {
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
        player.tick();
    }

    //renders every object in list
    public void render(Graphics g) {
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
        player.render(g);
    }

    //empty list (for loading new level)
    public void emptyList() {
        object = new LinkedList<GameObject>();
    }

    //add to list
    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    //remove from list
    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }
}

