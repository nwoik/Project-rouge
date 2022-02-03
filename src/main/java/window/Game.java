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
        this.menuPanel = new MenuPanel();
        this.setingsPanel = new SettingsPanel();
        this.gameWindow = new GameWindow(WIDTH,HEIGHT,"Once Upon a Dungeon");
        this.gameWindow.add(this.setingsPanel);
        this.gameWindow.add(this.menuPanel);
        this.gameWindow.setVisible(true);
    }

    public static void main(String[] args){
        new Game();
    }

}