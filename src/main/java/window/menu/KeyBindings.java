package window.menu;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class KeyBindings extends KeyAdapter {
    KeyWidget widget;
    ArrayList<Integer> keysList;
    int keyPos;
    Map<Integer, String> arrowMap = new HashMap<>();
    /*
    arrowMap.put(letter[i], gpa[i]);
    arrowMap.put(letter[i], gpa[i]);
    arrowMap.put(letter[i], gpa[i]);*/

    public KeyBindings(KeyWidget widget, ArrayList<Integer> keysList, int keyPos) {
        this.widget=widget;
        this.keysList=keysList;
        this.keyPos=keyPos;
        this.arrowMap.put(37, "LeftArrow");
        this.arrowMap.put(38, "UpArrow");
        this.arrowMap.put(39, "RightArrow");
        this.arrowMap.put(40, "DownArrow");
    }
    @Override
    public void keyPressed(KeyEvent event) {
        int keyNum = event.getKeyCode();
        char key = event.getKeyChar();
        if (this.arrowMap.containsKey(keyNum)) {
            String arrowKey = arrowMap.get(keyNum);
            this.widget.setText(""+arrowKey);
            this.keysList.set(this.keyPos, keyNum);
            return;
        }

        this.widget.setText(""+key);
        this.keysList.set(this.keyPos, keyNum);
    }
}