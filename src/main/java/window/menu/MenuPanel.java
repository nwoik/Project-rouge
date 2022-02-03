package window.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import core.BufferedImageLoader;
import window.Game;
import window.GameCanvas;
import window.GameWindow;
import window.Panel;

public class MenuPanel extends JPanel implements ActionListener {
    public window.Panel panel;
    private final BufferedImageLoader bufferLoader;
    Game game;
    Graphics2D graphics2D;
    GameWindow gameWindow;
    GameCanvas gameCanvas;
    Font gameFont;
    Font exceptionFont = new Font("Times New Roman", Font.PLAIN, 120);
    private final JLabel title;
    private final JButton playButton, settingsButton, exitButton;

    //Screen Settings
    final int defaultTileSize = 16; // 16 bit
    final int scale = 3;
    final int tileSize = defaultTileSize * scale; //Larger image size with 16 bit look
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public MenuPanel(Panel panel) {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/GameFont-Regular.ttf");
            assert is != null;
            gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            gameFont = exceptionFont;
        }

        this.bufferLoader = new BufferedImageLoader();
        this.panel = panel;
        this.gameCanvas = new GameCanvas(Panel.Game);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.title = new JLabel("Once Upon a Dungeon");

        this.playButton = new JButton("Play");
        this.settingsButton = new JButton("Settings");
        this.exitButton = new JButton("Exit");

        this.title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.playButton.addActionListener(this);
        this.settingsButton.addActionListener(this);
        this.exitButton.addActionListener(this);

        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(this.title);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(this.playButton);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(this.settingsButton);
        this.add(Box.createRigidArea(new Dimension(0, 100)));
        this.add(this.exitButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.graphics2D = (Graphics2D)g;
        drawTitleScreen();
    }

    public void drawTitleScreen() {
        //Title Name
        graphics2D.setFont(gameFont.deriveFont(64F));
        for (int xx=0; xx<128*70; xx+=128){
            for(int yy = 0; yy<128*70; yy+=128){
                graphics2D.drawImage(this.bufferLoader.loadImage("/MenuBackground/Wood Texture Bottom side.png"), xx,yy, 128, 128, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==this.playButton) {
            //start loop of game
            this.gameCanvas.start();
        }

        if (event.getSource()==this.settingsButton) {
            //start loop of game
            this.gameCanvas.start();
        }

        if (event.getSource()==this.exitButton) {
            //exit game
            System.exit(0);
        }
    }


}
