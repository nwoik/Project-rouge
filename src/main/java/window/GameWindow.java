package window;

import java.awt.*;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
    public GameWindow(int width, int height, String title, Canvas canvas){
        setTitle(title);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        add(canvas);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
