package window.menu;

import audio.AudioHandler;
import core.BufferedImageLoader;
import window.GameCanvas;
import window.GameWindow;

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
        System.out.println("DAVE THIS BETTER WORK");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Once Upon a Dungeon", SwingConstants.CENTER);

        JButton playButton = new JButton();
        JButton settingsButton = new JButton(new SwapCardAction("Settings", Settings.class.toString(), layoutPanel));
        JButton exitButton = new JButton();

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        Image newGameImage = wordAtlas.getSubimage(0,12,65,12);
        Image scaledNewGameImage = newGameImage.getScaledInstance(360, 60,  java.awt.Image.SCALE_SMOOTH);
        Image newGameHover = wordAtlas.getSubimage(74,12,65,12);
        Image scaledNewGameHover = newGameHover.getScaledInstance(360, 60,  java.awt.Image.SCALE_SMOOTH);
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setOpaque(false);
        playButton.setIcon(new ImageIcon(scaledNewGameImage));

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

        Image exitImage = wordAtlas.getSubimage(0,36,31,12);
        Image scaledExitImage = exitImage.getScaledInstance(200, 60,  java.awt.Image.SCALE_SMOOTH);
        Image exitHover = wordAtlas.getSubimage(74,36,31,12);
        Image scaledExitHover = exitHover.getScaledInstance(200, 60,  java.awt.Image.SCALE_SMOOTH);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        exitButton.setIcon(new ImageIcon(scaledExitImage));

        AudioHandler audio = new AudioHandler("music/Once_upon_a_dungeon.wav");

        playButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                playButton.setIcon(new ImageIcon(scaledNewGameImage));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                playButton.setIcon(new ImageIcon(scaledNewGameHover));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

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

        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                exitButton.setIcon(new ImageIcon(scaledExitImage));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                exitButton.setIcon(new ImageIcon(scaledExitHover));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                audio.clear();
                AudioHandler audio1 = new AudioHandler("sfx/menu/wood_click.wav");
                audio1.playMusic();

//                GameCanvas gameCanvas = new GameCanvas();
//                gameWindow.setVisible(false);
//                gameWindow.add(gameCanvas);

                SceneTransition sceneTransition = new SceneTransition();

                setVisible(false);
                settings.setVisible(false);
                layoutPanel.setVisible(false);

                gameWindow.add(sceneTransition);
                sceneTransition.load(gameWindow);

//                gameWindow.setVisible(true);
            }
        });
        settingsButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
            AudioHandler audio2 = new AudioHandler("sfx/menu/wood_click.wav");
            audio2.playMusic();
        }});
        exitButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
            AudioHandler audio2 = new AudioHandler("sfx/menu/wood_click.wav");
            audio2.playMusic();
            System.exit(0);
        }});

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
