package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Widget extends JButton {
    private Image image;
    private Image imageHover;
    public Widget(Image image, Image imageHover) {
        this.image = image;
        this.imageHover = imageHover;

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void scaleWidget(int width, int height) {
        this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.imageHover = this.imageHover.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(this.image));
        addListeners();
    }

    public void addListeners() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                setIcon(new ImageIcon(image));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                setIcon(new ImageIcon(imageHover));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });
    }
}
