package object;

import java.util.LinkedList;

import audio.AudioHandler;
import core.Camera;
import object.Block;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Handler {
    public LinkedList<Block> floors = new LinkedList<Block>();
    public LinkedList<Block> walls = new LinkedList<Block>();
    public LinkedList<GameObject> enemies = new LinkedList<GameObject>();
    public LinkedList<GameObject> objects = new LinkedList<GameObject>();
    public Player player;
    public Camera camera;
    private AudioHandler audio = new AudioHandler();

    //ticks every object in our list.
    public void tick(boolean debugMode) {
        for(GameObject gameObject : objects){
            gameObject.tick();
        }
        for(GameObject gameObject : enemies){
            gameObject.tick();
            if (gameObject.getHp() <= 0){
                if (gameObject.toString().contains("Skeleton")) {
                    audio.playSFX("sfx/skeleton/death.wav");
                } else if (gameObject.toString().contains("Eye")) {
                    audio.playSFX("sfx/bat/death.wav");
                }
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

        for(Block block : walls){
            if (block.getBounds().intersects(rect)) {
                block.render(g);
                if (debugMode) {
                    block.debugRender(g);
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
        for(Block block : floors){
            if (block.getBounds().intersects(rect)) {
                block.render(g);
            }
        }
    }

    //empty list (for loading new level)
    public void emptyList() {
        floors = new LinkedList<Block>();
        walls = new LinkedList<Block>();
        enemies = new LinkedList<GameObject>();
        objects = new LinkedList<GameObject>();
    }

    //add to list
    public void addObject(GameObject gameObject, LinkedList gameObjectList) {
        gameObjectList.add(gameObject);
    }
    public void addObject(Block block, LinkedList blockList) {
        blockList.add(block);
    }
//    public void addObject(GameObject gameObject, LinkedList gameObjectList) {
//        gameObjectList.add(gameObject);
//    }





    //remove from list
    public void removeObject(GameObject gameObject, LinkedList gameObjectList) {
        gameObjectList.remove(gameObject);
    }
    public void removeObject(Block block, LinkedList blockList) {
        blockList.remove(block);
    }
}

