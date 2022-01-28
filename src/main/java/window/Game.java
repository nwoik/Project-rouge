package window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game implements Runnable{
    private Thread thread;
    private GameCanvas gameCanvas;
    private MenuCanvas menuCanvas;
    private GameWindow gameWindow;

    public BufferedImage img;

    public Game(){
        this.gameCanvas = new GameCanvas(Panel.Game);
        this.menuCanvas = new MenuCanvas(Panel.Menu);
        this.gameWindow = new GameWindow(1000,563,"Once Upon a Dungeon", this.menuCanvas);
        this.menuCanvas.drawTitleScreen();
        if (gameCanvas.isRunning){start();}
    }

    // imports the background for menu
    public void importImg() {
        InputStream inputStream = getClass().getResourceAsStream("MenuBackground/Background.png");
        try {
            assert inputStream != null;
            img = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start(){
        gameCanvas.isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        gameCanvas.isRunning = false;
        try{
            thread.join();  //rejoins thread to main program
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Yet again, game loop from notch that is perfect for any game
    //updates window 60 times per second and updates render method
    //a couple thousand times per second.
    public void run() {
        this.gameCanvas.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(gameCanvas.isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                //updates++;
                delta--;
            }
            this.gameCanvas.render();
            frames++;

            if(System.currentTimeMillis()-timer>1000){
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        stop();
    }

    //updates everything in the game. updated 60 times per second
    public void tick(){
        this.gameCanvas.handler.tick();
    }

    //renders everything in the game. updated thousands of times per second.

    public static void main(String[] args){
        new Game();
    }

}