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

        JButton menuBtn = new JButton(new SwapCardAction("Menu", Menu.class.toString(), layoutPanel));
        setBackground(new Color(150, 200, 255));
        JPanel btnPanel = new JPanel();
        btnPanel.add(menuBtn);
        btnPanel.setOpaque(false);

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
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
