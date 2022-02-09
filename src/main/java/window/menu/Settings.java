package window.menu;

import core.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Settings extends JPanel {
    private float sfxVol;
    private float musicVol;

    public Settings(LayoutPanel layoutPanel) {
        musicVol = 1;
        sfxVol = 1;

        KeyListener listener = new KeyBindings();
        addKeyListener(listener);
        setFocusable(true);

        setBackground(new Color(150, 200, 255));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Settings", SwingConstants.CENTER);
        JLabel keyLabel = new JLabel("Key Bindings", SwingConstants.CENTER);
        JLabel upLabel = new JLabel("Move Up", SwingConstants.CENTER);
        JLabel downLabel = new JLabel("Move Down", SwingConstants.CENTER);
        JLabel leftLabel = new JLabel("Move Left", SwingConstants.CENTER);
        JLabel rightLabel = new JLabel("Move Right", SwingConstants.CENTER);

        JButton moveUp = new JButton("w");
        JButton moveDown = new JButton("s");
        JButton moveLeft = new JButton("a");
        JButton moveRight = new JButton("d");

        JButton menuButton = new JButton(new SwapCardAction("Back", Menu.class.toString(), layoutPanel));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        downLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        moveUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveDown.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveRight.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(keyLabel);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(upLabel);
        add(moveUp);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(downLabel);
        add(moveDown);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(leftLabel);
        add(moveLeft);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(rightLabel);
        add(moveRight);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(menuButton);
    }



    public float getSFXVol() {
        return sfxVol;
    }

    public void getSFXVol(float audioVol) {
        this.sfxVol = audioVol;
    }

    public float getMusicVol() {
        return musicVol;
    }

    public void setMusicVol(float musicVol) {
        this.musicVol = musicVol;
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
