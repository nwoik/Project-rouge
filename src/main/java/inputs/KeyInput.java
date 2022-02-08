package inputs;

import object.GameObject;
import object.Handler;
import object.ID;
import window.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    Game game;
    Handler handler; //Note: not creating new handler because we need to be using same handler as before
    //                 Don't want a new list, just to access movement methods.
    //                 Reason for putting movement there is to prevent input lag.

    public KeyInput(Handler handler) {this.handler = handler;}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //loop through all our objects, and if the current object is the player then do something.
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) handler.setUp(true);
                if (key == KeyEvent.VK_S) handler.setDown(true);
                if (key == KeyEvent.VK_A) handler.setLeft(true);
                if (key == KeyEvent.VK_D) handler.setRight(true);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //loop through all our objects, and if the current object is the player then do something.
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_W) handler.setUp(false);
                if(key == KeyEvent.VK_S) handler.setDown(false);
                if(key == KeyEvent.VK_A) handler.setLeft(false);
                if(key == KeyEvent.VK_D) handler.setRight(false);
            }
        }
    }

}

