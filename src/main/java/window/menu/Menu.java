package window.menu;

import audio.AudioHandler;
import core.BufferedImageLoader;
import window.GameCanvas;
import window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class Menu extends JPanel {

    public Menu(LayoutPanel layoutPanel, GameWindow gameWindow, Settings settings) {

        setBackground(new Color(255, 200, 200));
        System.out.println("DAVE THIS BETTER WORK");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Once Upon a Dungeon", SwingConstants.CENTER);

        JButton playButton = new JButton();
        JButton settingsButton = new JButton(new SwapCardAction("Settings", Settings.class.toString(), layoutPanel));
        JButton exitButton = new JButton();

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        Image newGameImage = wordAtlas.getSubimage(1,13,63,10);
        Image scaledNewGameImage = newGameImage.getScaledInstance(360, 60,  java.awt.Image.SCALE_SMOOTH);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setOpaque(false);
        playButton.setIcon(new ImageIcon(scaledNewGameImage));

        Image settingsImage = wordAtlas.getSubimage(1,1,63,10);
        Image scaledSettingsImage = settingsImage.getScaledInstance(360, 60,  java.awt.Image.SCALE_SMOOTH);
        settingsButton.setText("");
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setOpaque(false);
        settingsButton.setIcon(new ImageIcon(scaledSettingsImage));

        Image exitImage = wordAtlas.getSubimage(1,37,29,10);
        Image scaledExitImage = exitImage.getScaledInstance(200, 60,  java.awt.Image.SCALE_SMOOTH);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        exitButton.setIcon(new ImageIcon(scaledExitImage));

        AudioHandler audio = new AudioHandler("Once_upon_a_dungeon.wav");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameCanvas gameCanvas = new GameCanvas();

                setVisible(false);
                settings.setVisible(false);
                layoutPanel.setVisible(false);

                gameWindow.add(gameCanvas);
                gameCanvas.start();

                System.out.println(audio.audioList);
                audio.clear();
//                audio.audioList.remove(audio.clip);
                System.out.println(audio.audioList);
            }
        });
        exitButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {System.exit(0);}});

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.setMaximumSize(new Dimension(400, 400));

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
