package window.menu;

import audio.AudioHandler;
import core.BufferedImageLoader;
import window.GameCanvas;
import window.GameWindow;
import window.Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


public class Menu extends JPanel {

    public Menu(LayoutPanel layoutPanel, GameWindow gameWindow, Settings settings) {

        setBackground(new Color(255, 200, 200));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        JLabel title = new JLabel("Once Upon a Dungeon", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        Widget playButton = new Widget(wordAtlas.getSubimage(0,168,34,12), wordAtlas.getSubimage(74,168,34,12));
        playButton.scaleWidget(240,60);

        JButton settingsButton = new JButton(new SwapCardAction("Settings", Settings.class.toString(), layoutPanel));

        Widget exitButton = new Widget(wordAtlas.getSubimage(0,36,31,12), wordAtlas.getSubimage(74,36,31,12));
        exitButton.scaleWidget(200, 60);

        Image settingsImage = wordAtlas.getSubimage(74,0,65,12);
        Image scaledSettingsImage = settingsImage.getScaledInstance(360, 60,  java.awt.Image.SCALE_SMOOTH);
        Image settingsHover = wordAtlas.getSubimage(0,0,65,12);
        Image scaledSettingsHover = settingsHover.getScaledInstance(360, 60,  java.awt.Image.SCALE_SMOOTH);
        settingsButton.setText("");
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setIcon(new ImageIcon(scaledSettingsHover));

        AudioHandler audio = new AudioHandler("music/Once_upon_a_dungeon.wav");
        AudioHandler clickAudio = new AudioHandler("sfx/menu/wood_click.wav");

        settingsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                settingsButton.setIcon(new ImageIcon(scaledSettingsHover));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                settingsButton.setIcon(new ImageIcon(scaledSettingsImage));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audio.clear();
                clickAudio.playMusic();

                setVisible(false);
                settings.setVisible(false);
                layoutPanel.setVisible(false);

                SceneTransition sceneTransition = new SceneTransition(layoutPanel);

                gameWindow.add(sceneTransition);
                sceneTransition.load(gameWindow);
            }
        });
        settingsButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
            clickAudio.playMusic();
        }});
        exitButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
            clickAudio.playMusic();
            System.exit(0);
        }});
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(settingsButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(exitButton);
        add(Box.createVerticalGlue());

        audio.playMusic();
        audio.loopAudio();
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
