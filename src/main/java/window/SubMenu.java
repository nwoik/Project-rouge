package window;

import javax.swing.*;
import java.awt.*;

public class SubMenu extends JPanel {
    public SubMenu() {
        setBackground(new Color(255, 200, 200));
        Canvas canvas = new Canvas();
        canvas.setSize(400, 400);
        add(canvas);
    }

    public void paint(Graphics g) {
        g.fillOval(100, 100, 200, 200);
    }

}
