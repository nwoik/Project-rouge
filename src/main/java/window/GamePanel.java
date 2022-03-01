package window;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameCanvas gameCanvas;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));

        this.gameCanvas = new GameCanvas();
        setLayout(new GridLayout());
        add(this.gameCanvas);

        this.gameCanvas.start();
    }
}
