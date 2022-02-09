package window.menu;

import core.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Settings extends JPanel {

    public Settings(LayoutPanel layoutPanel) {
        KeyListener listener = new KeyBindings();
        addKeyListener(listener);
        setFocusable(true);

        setBackground(new Color(150, 200, 255));

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

        JPanel keyBind1Pane = new JPanel();
        keyBind1Pane.setLayout(new BoxLayout(keyBind1Pane, BoxLayout.LINE_AXIS));
        keyBind1Pane.add(Box.createHorizontalGlue());
        keyBind1Pane.add(upLabel);
        keyBind1Pane.add(Box.createRigidArea(new Dimension(10, 0)));
        keyBind1Pane.add(downLabel);

        JPanel keyBind2Pane = new JPanel();
        keyBind2Pane.setLayout(new BoxLayout(keyBind2Pane, BoxLayout.LINE_AXIS));
        keyBind2Pane.add(Box.createHorizontalGlue());
        keyBind2Pane.add(moveUp);
        keyBind2Pane.add(Box.createRigidArea(new Dimension(10, 0)));
        keyBind2Pane.add(moveDown);

        JPanel keyBind3Pane = new JPanel();
        keyBind3Pane.setLayout(new BoxLayout(keyBind3Pane, BoxLayout.LINE_AXIS));
        keyBind3Pane.add(Box.createHorizontalGlue());
        keyBind3Pane.add(leftLabel);
        keyBind3Pane.add(Box.createRigidArea(new Dimension(10, 0)));
        keyBind3Pane.add(rightLabel);

        JPanel keyBind4Pane = new JPanel();
        keyBind4Pane.setLayout(new BoxLayout(keyBind4Pane, BoxLayout.LINE_AXIS));
        keyBind4Pane.add(Box.createHorizontalGlue());
        keyBind4Pane.add(moveLeft);
        keyBind4Pane.add(Box.createRigidArea(new Dimension(10, 0)));
        keyBind4Pane.add(moveRight);

        keyBind1Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind2Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind3Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind4Pane.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(title, BorderLayout.PAGE_START);
        add(keyBind1Pane, BorderLayout.LINE_START);
        add(keyBind2Pane, BorderLayout.LINE_END);
        add(menuButton, BorderLayout.PAGE_END);

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
