package window;


import core.LevelLoader;
import object.Handler;
import window.menu.LayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameOverWindow extends JFrame{
    public GameOverWindow(GameCanvas gameCanvas, GameWindow gameWindow, LayoutPanel layoutPanel, Handler handler, LevelLoader levelLoader) throws IOException {
        setTitle("Game Over");
        setBackground(new Color(255, 200, 200));
        setPreferredSize(new Dimension(800, 350));
        getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(72, 44, 25)));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(new GameOverScreen(this, gameCanvas, gameWindow, layoutPanel));
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
