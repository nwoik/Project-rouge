package object;

import window.Game;

import java.util.LinkedList;

import java.awt.Graphics;

public class Handler {
    public LinkedList<GameObject> objects = new LinkedList<GameObject>();
    public Player player;

    //ticks every object in our list.
    public void tick() {
        for(GameObject gameObject : objects){
            gameObject.tick();
        }
        player.tick();
    }

    //renders every object in list
    public void render(Graphics g) {
        for(GameObject gameObject : objects){
            gameObject.render(g);
        }
        player.render(g);
    }

    //empty list (for loading new level)
    public void emptyList() {
        objects = new LinkedList<GameObject>();
    }

    //add to list
    public void addObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    //remove from list
    public void removeObject(GameObject gameObject) {
        objects.remove(gameObject);
    }
}

