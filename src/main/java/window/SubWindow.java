package window;

import window.menu.LayoutPanel;

import javax.swing.*;
import java.awt.*;

public class SubWindow extends JFrame {
    private SubMenu subMenu;
    public SubWindow(GameCanvas gameCanvas, GameWindow gameWindow, LayoutPanel layoutPanel) {
        this.subMenu = new SubMenu(this, gameCanvas, gameWindow, layoutPanel);
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
