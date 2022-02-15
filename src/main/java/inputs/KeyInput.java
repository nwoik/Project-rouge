package inputs;

import object.GameObject;
import object.Handler;
import object.ID;
import window.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter{
    Game game;
    ArrayList<Integer> keysList = new ArrayList<Integer>();
    Handler handler; //Note: not creating new handler because we need to be using same handler as before
    //                 Don't want a new list, just to access movement methods.
    //                 Reason for putting movement there is to prevent input lag.

    public KeyInput(Handler handler) {
        this.handler = handler;
        this.keysList=keysList;

        try {
            readText(this.keysList);
        }
        catch(IOException a) {
            a.printStackTrace();
        }
    }

    public void readText(ArrayList<Integer> keysList) throws IOException {
        short inc = 0;
        File file = new File("src/main/java/window/menu/Settings.txt");
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            if (inc >= 4) {
                int key=Integer.parseInt(line);
                keysList.add(key);
            }
            inc ++;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //loop through all our objects, and if the current object is the player then do something.
            if (tempObject.getId() == ID.Player) {
                if (key == this.keysList.get(0)) handler.setUp(true);
                if (key == this.keysList.get(1)) handler.setDown(true);
                if (key == this.keysList.get(2)) handler.setLeft(true);
                if (key == this.keysList.get(3)) handler.setRight(true);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            //loop through all our objects, and if the current object is the player then do something.
            if(tempObject.getId() == ID.Player){
                if(key == this.keysList.get(0)) handler.setUp(false);
                if(key == this.keysList.get(1)) handler.setDown(false);
                if(key == this.keysList.get(2)) handler.setLeft(false);
                if(key == this.keysList.get(3)) handler.setRight(false);
            }
        }
    }

}

