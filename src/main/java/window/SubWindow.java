package window;

import javax.swing.*;
import java.awt.*;

public class SubWindow extends JFrame {
    private SubMenu subMenu;
    public SubWindow(GameCanvas gameCanvas) {
        this.subMenu = new SubMenu(this, gameCanvas);
        setTitle("SubMenu");
        setBackground(new Color(255, 200, 200));
        setPreferredSize(new Dimension(500, 800));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(subMenu);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
