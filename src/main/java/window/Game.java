package window;

import window.menu.MenuPanel;
import window.menu.SettingsPanel;

import java.awt.*;

public class Game{
    private GameCanvas gameCanvas;
    private MenuPanel menuPanel;
    private SettingsPanel setingsPanel;
    private GameWindow gameWindow;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    public Game(){
        this.gameWindow = new GameWindow(WIDTH,HEIGHT,"Once Upon a Dungeon");
    }

    public static void main(String[] args){
        new Game();
    }

}