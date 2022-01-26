package window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame{
    public Window(int width, int height, String title, Game game){
        setTitle(title);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        add(game);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
