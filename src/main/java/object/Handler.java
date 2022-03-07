package object;

import java.util.LinkedList;
import core.Camera;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Handler {
    public LinkedList<GameObject> floors = new LinkedList<GameObject>();
    public LinkedList<GameObject> walls = new LinkedList<GameObject>();
    public LinkedList<GameObject> enemies = new LinkedList<GameObject>();
    public LinkedList<GameObject> objects = new LinkedList<GameObject>();
    public Player player;
    public Camera camera;

    //ticks every object in our list.
    public void tick(boolean debugMode) {
        for(GameObject gameObject : objects){
            gameObject.tick();
        }
        for(GameObject gameObject : enemies){
            gameObject.tick();
            if (gameObject.getHp() <= 0){
                removeObject(gameObject, enemies);
                break;
            }
        }
        player.tick();
        if (debugMode) {
            player.checkEnemyDetection();
        }
    }

    //renders every object in list
    public void render(Graphics g, boolean debugMode) {
        Rectangle2D rect = new Rectangle2D.Float(camera.getX(), camera.getY(), camera.getWidth(), camera.getHeight());

        for(GameObject gameObject : walls){
            if (gameObject.getBounds().intersects(rect)) {
                gameObject.render(g);
                if (debugMode) {
                    gameObject.debugRender(g);
                }
            }
        }
        for(GameObject gameObject : objects){
            if (gameObject.getBounds().intersects(rect)) {
                gameObject.render(g);
                if (debugMode) {
                    gameObject.debugRender(g);
                }
            }
        }

        for(GameObject gameObject : enemies){
            if (gameObject.getBounds().intersects(rect)) {
                gameObject.render(g);
                if (debugMode) {
                    gameObject.debugRender(g);
                }
            }
        }
        player.render(g);
        if (debugMode) {
            player.debugRender(g);
        }

    }

    public void renderFloors(Graphics g) {
        Rectangle2D rect = new Rectangle2D.Float(camera.getX(), camera.getY(), camera.getWidth(), camera.getHeight());
        for(GameObject gameObject : floors){
            if (gameObject.getBounds().intersects(rect)) {
                gameObject.render(g);
            }
        }
    }

    //empty list (for loading new level)
    public void emptyList() {
        floors = new LinkedList<GameObject>();
        walls = new LinkedList<GameObject>();
        enemies = new LinkedList<GameObject>();
        objects = new LinkedList<GameObject>();
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

