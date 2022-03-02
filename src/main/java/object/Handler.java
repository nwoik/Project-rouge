package object;

import java.util.LinkedList;

import java.awt.Graphics;

public class Handler {
    public LinkedList<GameObject> floors = new LinkedList<GameObject>();
    public LinkedList<GameObject> walls = new LinkedList<GameObject>();
    public LinkedList<GameObject> enemies = new LinkedList<GameObject>();
    public Player player;

    //ticks every object in our list.
    public void tick(boolean debugMode) {
        for(GameObject gameObject : walls){
            gameObject.tick();
        }
        for(GameObject gameObject : enemies){
            gameObject.tick();
        }
        player.tick();
        if (debugMode) {
            player.checkEnemyDetection();
        }
    }

    //renders every object in list
    public void render(Graphics g, boolean debugMode) {

        for(GameObject gameObject : walls){
            gameObject.render(g);
            if (debugMode) {
                gameObject.debugRender(g);
            }
        }
        for(GameObject gameObject : enemies){
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

    public void renderFloors(Graphics g) {
        for(GameObject gameObject : floors){
            gameObject.render(g);
        }
    }

    //empty list (for loading new level)
    public void emptyList() {
        floors = new LinkedList<GameObject>();
        walls = new LinkedList<GameObject>();
        enemies = new LinkedList<GameObject>();
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

