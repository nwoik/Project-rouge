package window.menu;

import javax.swing.*;
import java.awt.*;

public class Tag extends JLabel {

    public Tag (Image image, int width, int height) {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
