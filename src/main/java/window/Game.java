package window;

import window.menu.MenuPanel;

import java.awt.*;

public class Game{
    private GameCanvas gameCanvas;
    private MenuPanel menuPanel;
    private GameWindow gameWindow;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    public Game(){
        this.menuPanel = new MenuPanel(Panel.Menu);
        this.gameWindow = new GameWindow(WIDTH,HEIGHT,"Once Upon a Dungeon", this.menuPanel);
    }

    public static void main(String[] args){
        new Game();
    }

}