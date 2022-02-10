package window.menu;

import core.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Settings extends JPanel {
    private float sfxVol;
    private float musicVol;

    public Settings(LayoutPanel layoutPanel) throws Exception{
        musicVol = 1;
        sfxVol = 1;

        setBackground(new Color(150, 200, 255));

        JLabel title = new JLabel("Settings", SwingConstants.CENTER);
        JLabel keyLabel = new JLabel("Key Bindings", SwingConstants.CENTER);
        JLabel upLabel = new JLabel("Move Up", SwingConstants.CENTER);
        JLabel downLabel = new JLabel("Move Down", SwingConstants.CENTER);
        JLabel leftLabel = new JLabel("Move Left", SwingConstants.CENTER);
        JLabel rightLabel = new JLabel("Move Right", SwingConstants.CENTER);

        readText();

        JButton moveUp = new JButton("w");
        JButton moveDown = new JButton("s");
        JButton moveLeft = new JButton("a");
        JButton moveRight = new JButton("d");

        moveUp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveUp.setText("  ");
                moveUp.addKeyListener(new KeyBindings(moveUp));
            }
        });

        moveDown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveDown.setText("  ");
                moveDown.addKeyListener(new KeyBindings(moveDown));
            }
        });

        moveLeft.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveLeft.setText("  ");
                moveLeft.addKeyListener(new KeyBindings(moveLeft));
            }
        });

        moveRight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveRight.setText("  ");
                moveRight.addKeyListener(new KeyBindings(moveRight));
            }
        });

        JButton saveButton = new JButton("Save");
        JLabel validLabel = new JLabel("");
        JButton menuButton = new JButton(new SwapCardAction("Back", Menu.class.toString(), layoutPanel));

        JPanel keyBind1Pane = new JPanel();
        keyBind1Pane.setOpaque( false );
        keyBind1Pane.setLayout(new BoxLayout(keyBind1Pane, BoxLayout.LINE_AXIS));
        keyBind1Pane.add(Box.createHorizontalGlue());
        keyBind1Pane.add(upLabel);
        keyBind1Pane.add(Box.createRigidArea(new Dimension(50, 0)));
        keyBind1Pane.add(downLabel);

        JPanel keyBind2Pane = new JPanel();
        keyBind2Pane.setOpaque( false );
        keyBind2Pane.setLayout(new BoxLayout(keyBind2Pane, BoxLayout.LINE_AXIS));
        keyBind2Pane.add(Box.createHorizontalGlue());
        keyBind2Pane.add(moveUp);
        keyBind2Pane.add(Box.createRigidArea(new Dimension(90, 0)));
        keyBind2Pane.add(moveDown);

        JPanel keyBind3Pane = new JPanel();
        keyBind3Pane.setOpaque( false );
        keyBind3Pane.setLayout(new BoxLayout(keyBind3Pane, BoxLayout.LINE_AXIS));
        keyBind3Pane.add(Box.createHorizontalGlue());
        keyBind3Pane.add(leftLabel);
        keyBind3Pane.add(Box.createRigidArea(new Dimension(50, 0)));
        keyBind3Pane.add(rightLabel);

        JPanel keyBind4Pane = new JPanel();
        keyBind4Pane.setOpaque( false );
        keyBind4Pane.setLayout(new BoxLayout(keyBind4Pane, BoxLayout.LINE_AXIS));
        keyBind3Pane.add(Box.createHorizontalGlue());
        keyBind4Pane.add(moveLeft);
        keyBind4Pane.add(Box.createRigidArea(new Dimension(90, 0)));
        keyBind4Pane.add(moveRight);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind1Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind2Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind3Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind4Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        validLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel settingsPane = new JPanel();
        settingsPane.setOpaque( false );
        settingsPane.setLayout(new BoxLayout(settingsPane, BoxLayout.PAGE_AXIS));
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(title);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(keyLabel);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 50)));
        settingsPane.add(keyBind1Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPane.add(keyBind2Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsPane.add(keyBind3Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPane.add(keyBind4Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsPane.add(validLabel);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 30)));
        settingsPane.add(saveButton);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(menuButton);
        add(settingsPane);
    }

    public void readText() throws IOException {
        InputStream is = Settings.class.getResourceAsStream("/Settings.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
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

//BIGIFY - Kozlow