package window.menu;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class KeyBindings extends KeyAdapter {
    JButton button;
    Map<Integer, String> arrowMap = new HashMap<>();
    /*
    arrowMap.put(letter[i], gpa[i]);
    arrowMap.put(letter[i], gpa[i]);
    arrowMap.put(letter[i], gpa[i]);*/

    public KeyBindings(JButton button) {
        this.button=button;
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
            System.out.println(""+arrowKey);
            this.button.setText(""+arrowKey);
            return;
        }

        System.out.println(""+key);
        this.button.setText(""+key);
    }
}