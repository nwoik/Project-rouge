package window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
    public GameWindow(int width, int height, String title, MenuCanvas menuCanvas){
        setTitle(title);
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        add(menuCanvas);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }
}
