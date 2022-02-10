package object;

import java.util.LinkedList;

import java.awt.Graphics;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public LinkedList<GameObject> block = new LinkedList<GameObject>();
    public LinkedList<GameObject> enemy = new LinkedList<GameObject>();
    public Player player;

    //ticks every object in our list.
    public void tick(boolean debugMode) {
        for(GameObject gameObject : object){
            gameObject.tick();
        }
        for(GameObject gameObject : enemy){
            gameObject.tick();
        }
        player.tick();
        if (debugMode) {
            player.checkEnemyDetection();
        }
    }

    //renders every object in list
    public void render(Graphics g, boolean debugMode) {
        for(GameObject gameObject : block){
            gameObject.render(g);
            if (debugMode) {
                gameObject.debugRender(g);
            }
        }
        for(GameObject gameObject : object){
            gameObject.render(g);
            if (debugMode) {
                gameObject.debugRender(g);
            }
        }
        for(GameObject gameObject : enemy){
            gameObject.render(g);
            if (debugMode) {
                gameObject.debugRender(g);
            }
        }
        player.render(g);
        if (debugMode) {
            player.debugRender(g);
        }

    }

    //empty list (for loading new level)
    public void emptyList() {
        object = new LinkedList<GameObject>();
        block = new LinkedList<GameObject>();
        enemy = new LinkedList<GameObject>();
    }

    //add to list
    public void addObject(GameObject gameObject, LinkedList gameObjectList) {
        gameObjectList.add(gameObject);
    }

    //remove from list
    public void removeObject(GameObject gameObject, LinkedList gameObjectList) {
        gameObjectList.remove(gameObject);
    }
}

