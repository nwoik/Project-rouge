package window;

public class Game{
    private GameCanvas gameCanvas;
    private GameWindow gameWindow;

    public Game(){
        this.gameCanvas = new GameCanvas(Panel.Game);
        this.gameWindow = new GameWindow(1000,563,"Once Upon a Dungeon", this.gameCanvas);
        gameCanvas.start();

    }

    //renders everything in the game. updated thousands of times per second.

    public static void main(String[] args){
        new Game();
    }

}