package window.menu;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyBindings extends KeyAdapter {
    JButton button;
    public KeyBindings(JButton button) {
        this.button=button;
    }
    @Override
    public void keyPressed(KeyEvent event) {
        char key = event.getKeyChar();
        System.out.println(""+key);
        this.button.setText(""+key);
        return;
    }
}