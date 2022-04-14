package window;

import java.awt.*;

public class Game{
    private GameWindow gameWindow;
    private GameCanvas gameCanvas;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    public Game() throws Exception{
        this.gameCanvas = new GameCanvas(this.gameWindow);
        this.gameWindow = new GameWindow(WIDTH,HEIGHT,"Once Upon a Dungeon", this.gameCanvas);
        this.gameCanvas.start();
    }

    public static void main(String[] args) throws Exception{
        new Game();
    }
}