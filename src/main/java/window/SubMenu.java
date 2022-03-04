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
        JButton returnButton = new JButton();
        JButton exitButton = new JButton();

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        Image titleImage = wordAtlas.getSubimage(0,156,36,12);
        Image scaledTitleImage = titleImage.getScaledInstance(300, 70,  java.awt.Image.SCALE_SMOOTH);
        title.setIcon(new ImageIcon(scaledTitleImage));

        Image returnImage = wordAtlas.getSubimage(0,180,36,12);
        Image scaledReturnImage = returnImage.getScaledInstance(200, 60,  java.awt.Image.SCALE_SMOOTH);
        Image returnHover = wordAtlas.getSubimage(74,180,36,12);
        Image scaledReturnHover = returnHover.getScaledInstance(200, 60,  java.awt.Image.SCALE_SMOOTH);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        returnButton.setFocusPainted(false);
        returnButton.setOpaque(false);
        returnButton.setIcon(new ImageIcon(scaledReturnImage));

        Image exitImage = wordAtlas.getSubimage(0,36,31,12);
        Image scaledExitImage = exitImage.getScaledInstance(200, 60,  java.awt.Image.SCALE_SMOOTH);
        Image exitHover = wordAtlas.getSubimage(74,36,31,12);
        Image scaledExitHover = exitHover.getScaledInstance(200, 60,  java.awt.Image.SCALE_SMOOTH);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        exitButton.setIcon(new ImageIcon(scaledExitImage));

        returnButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                returnButton.setIcon(new ImageIcon(scaledReturnImage));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                returnButton.setIcon(new ImageIcon(scaledReturnHover));
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

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMenu();
                gameCanvas.comeBack();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
