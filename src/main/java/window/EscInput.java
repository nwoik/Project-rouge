package window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EscInput extends KeyAdapter {
    public SubMenu subMenu;

    public EscInput(SubMenu subMenu) {
        this.subMenu = subMenu;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 27) {
            this.subMenu.closeMenu();
        }
    }
}
