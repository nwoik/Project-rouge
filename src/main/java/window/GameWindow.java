package window;

import window.menu.LayoutPanel;

import java.awt.*;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    private LayoutPanel layoutPanel = new LayoutPanel(this);
    public GameWindow(int width, int height, String title) throws Exception{
        setTitle(title);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().add(layoutPanel);
        setUndecorated(true);
        pack();
        setVisible(true);
    }
}
