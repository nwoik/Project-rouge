package window;

import java.awt.*;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public int width;
    public int height;

    public GameWindow(int width, int height, String title, GameCanvas gameCanvas) throws Exception{
        this.width = width;
        this.height = height;
        setTitle(title);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().add(gameCanvas);
        setUndecorated(true);
        pack();
        setVisible(true);
    }
}