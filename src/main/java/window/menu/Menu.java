package window.menu;

import core.BufferedImageLoader;
import window.GameCanvas;
import window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    public Menu(final LayoutPanel layoutPanel, GameWindow gameWindow) {
        setBackground(new Color(255, 200, 200));
        System.out.println("DAVE THIS BETTER WORK");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Once Upon a Dungeon", SwingConstants.CENTER);

        JButton playButton = new JButton("Play");
        JButton settingsButton = new JButton(new SwapCardAction("Settings", Settings.class.toString(), layoutPanel));
        JButton exitButton = new JButton("Exit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameCanvas gameCanvas = new GameCanvas();
                //setVisible(false);
                layoutPanel.setVisible(false);
                gameWindow.add(gameCanvas);
                gameCanvas.start();
            }
        });
        exitButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {System.exit(0);}});

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(settingsButton);
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
