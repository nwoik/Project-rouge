package window;

import audio.AudioHandler;
import core.BufferedImageLoader;
import window.menu.LayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class SubMenu extends JPanel {
    public SubWindow subWindow;
    public GameCanvas gameCanvas;
    private GameWindow gameWindow;
    private LayoutPanel layoutPanel;

    public SubMenu(SubWindow subWindow, GameCanvas gameCanvas, GameWindow gameWindow, LayoutPanel layoutPanel) {
        this.subWindow = subWindow;
        this.gameCanvas = gameCanvas;
        this.gameWindow = gameWindow;
        this.layoutPanel = layoutPanel;

        setFocusable(true);
        requestFocus();
        addKeyListener(new EscInput(this));

        setBackground(new Color(255, 200, 200));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("", SwingConstants.CENTER);

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        Widget returnButton = new Widget(wordAtlas.getSubimage(0,180,36,12), wordAtlas.getSubimage(74,180,36,12));
        returnButton.scaleWidget(240,60);

        Widget exitButton = new Widget(wordAtlas.getSubimage(0,36,31,12), wordAtlas.getSubimage(74,36,31,12));
        exitButton.scaleWidget(200, 60);

        Image titleImage = wordAtlas.getSubimage(0,156,36,12);
        Image scaledTitleImage = titleImage.getScaledInstance(300, 70,  java.awt.Image.SCALE_SMOOTH);
        title.setIcon(new ImageIcon(scaledTitleImage));

        AudioHandler clickAudio = new AudioHandler();

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickAudio.playSFX("sfx/menu/wood_click.wav");
                closeMenu();
                gameCanvas.comeBack();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickAudio.playSFX("sfx/menu/wood_click.wav");
                subWindow.dispose();
                gameWindow.remove(gameCanvas);
                layoutPanel.setVisible(true);
            }
        });

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(returnButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(exitButton);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void closeMenu() {
        this.subWindow.dispose();
        this.gameCanvas.stopped = !this.gameCanvas.stopped;
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
