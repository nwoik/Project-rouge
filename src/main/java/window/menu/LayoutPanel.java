package window.menu;

import core.BufferedImageLoader;
import window.Game;
import window.GameCanvas;
import window.GameWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

public class LayoutPanel extends JPanel {
    private GameWindow gameWindow;
    private final CardLayout cardLayout = new CardLayout();
    private Menu menu;
    private Settings settings = new Settings(this);

    public LayoutPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        menu = new Menu(this, this.gameWindow);
        setLayout(cardLayout);
        add(menu, Menu.class.toString());
        add(settings, Settings.class.toString());
    }

    public void showCard(String name) {
        cardLayout.show(this, name);
    }
}

/*
class Menu extends JPanel implements ActionListener {
    public static final String NAME = "menu";
    private final BufferedImageLoader bufferLoader;
    private Graphics2D graphics2D;
    private final GameCanvas gameCanvas;
    private Font gameFont;
    private Font exceptionFont = new Font("Times New Roman", Font.PLAIN, 120);
    private final JLabel title;
    private final JButton playButton, settingsButton, exitButton;

    public Menu(LayoutPanel layoutPanel) {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/GameFont-Regular.ttf");
            assert is != null;
            gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            gameFont = exceptionFont;
        }

        this.bufferLoader = new BufferedImageLoader();
        this.gameCanvas = new GameCanvas();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.title = new JLabel("Once Upon a Dungeon");

        this.playButton = new JButton("Play");
        settingsButton = new JButton(new SwapCardAction("Settings", Settings.NAME, layoutPanel));
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
        drawScreen();
    }

    public void drawScreen() {
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

        if (event.getSource()==this.exitButton) {
            //exit game
            System.exit(0);
        }
    }
}

class Settings extends JPanel implements ActionListener {
    public static final String NAME = "settings";
    private final BufferedImageLoader bufferLoader;
    private Graphics2D graphics2D;
    private Font gameFont;
    private Font exceptionFont = new Font("Times New Roman", Font.PLAIN, 120);

    public Settings() {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/GameFont-Regular.ttf");
            assert is != null;
            gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            gameFont = exceptionFont;
        }

        this.bufferLoader = new BufferedImageLoader();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.graphics2D = (Graphics2D)g;
        drawScreen();
    }

    public void drawScreen() {
        graphics2D.setFont(gameFont.deriveFont(64F));
        for (int xx=0; xx<128*70; xx+=128){
            for(int yy = 0; yy<128*70; yy+=128){
                graphics2D.drawImage(this.bufferLoader.loadImage("/MenuBackground/Wood Texture Bottom side.png"), xx,yy, 128, 128, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
*/

