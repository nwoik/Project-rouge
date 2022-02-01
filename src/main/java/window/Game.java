package window;

import java.awt.*;

public class Game{
    private GameCanvas gameCanvas;
    private MenuCanvas menuCanvas;
    private GameWindow gameWindow;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    public Game(){
        this.gameCanvas = new GameCanvas(Panel.Game);
        this.menuCanvas = new MenuCanvas(Panel.Menu);
        this.gameWindow = new GameWindow(WIDTH,HEIGHT,"Once Upon a Dungeon", this.gameCanvas);

        //start loop of game
        gameCanvas.start();
    }

    public static void main(String[] args){
        new Game();
    }

}