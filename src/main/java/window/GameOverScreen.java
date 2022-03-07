package window;

import audio.AudioHandler;
import core.BufferedImageLoader;
import window.menu.LayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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

        JLabel title = new JLabel("", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        AudioHandler clickAudio = new AudioHandler();

        Image titleImage = wordAtlas.getSubimage(0,228,73,12);
        Image scaledTitleImage = titleImage.getScaledInstance(400, 70,  java.awt.Image.SCALE_SMOOTH);
        title.setIcon(new ImageIcon(scaledTitleImage));

        Widget exitButton = new Widget(wordAtlas.getSubimage(0,36,31,12), wordAtlas.getSubimage(74,36,31,12));
        exitButton.scaleWidget(200, 60);

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
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
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
