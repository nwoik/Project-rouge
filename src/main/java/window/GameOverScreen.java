package window;

import audio.AudioHandler;
import core.BufferedImageLoader;
import core.LevelLoader;
import window.menu.LayoutPanel;
import window.menu.SceneTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameOverScreen extends JPanel {
    public GameOverWindow gameOverWindow;
    public GameCanvas gameCanvas;
    private GameWindow gameWindow;
    private LayoutPanel layoutPanel;

    public GameOverScreen(GameOverWindow gameOverWindow, GameCanvas gameCanvas, GameWindow gameWindow, LayoutPanel layoutPanel) {
        this.gameOverWindow = gameOverWindow;
        this.gameCanvas = gameCanvas;
        this.gameWindow = gameWindow;
        this.layoutPanel = layoutPanel;

        setBackground(new Color(255, 200, 200));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        AudioHandler clickAudio = new AudioHandler();

        Widget continueButton = new Widget(wordAtlas.getSubimage(0,48,67,12), wordAtlas.getSubimage(74,48,67,12));
        continueButton.scaleWidget(400, 60);

        Widget exitButton = new Widget(wordAtlas.getSubimage(0,36,31,12), wordAtlas.getSubimage(74,36,31,12));
        exitButton.scaleWidget(200, 60);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickAudio.playSFX("sfx/menu/wood_click.wav");
                gameOverWindow.dispose();
                gameCanvas.openMenu();
                gameCanvas.levelLoader.loadLevel(gameCanvas.levelLoader.levelList.get(gameCanvas.levelLoader.getCurrentLevel()));
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickAudio.playSFX("sfx/menu/wood_click.wav");
                gameOverWindow.dispose();
                gameWindow.remove(gameCanvas);
                layoutPanel.setVisible(true);
            }
        });

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(continueButton);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(exitButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int HEIGHT = dimension.height;
        int WIDTH = dimension.width;

        BufferedImageLoader bufferLoader = new BufferedImageLoader();
        Graphics2D graphics2D = (Graphics2D)g;
        for (int xx=0; xx<WIDTH; xx+=128){
            for(int yy = 0; yy<HEIGHT; yy+=128){
                graphics2D.drawImage(bufferLoader.loadImage("/MenuBackground/Wood Texture Bottom side.png"), xx,yy, 128, 128, null);
            }
        }
    }
}
