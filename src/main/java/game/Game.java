package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;

    public Game(){
        new Window(1000,563,"Once Upon a Dungeon", this);
        start();

        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));
        handler.addObject(new Protagonist(100,100,ID.Player, handler));
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
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
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                //updates++;
                delta--;
            }
            render();
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
        handler.tick();
    }

    //renders everything in the game. updated thousands of times per second.
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            //preload 3 frames before they are actually shown. Improve efficiency
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ////////////////////////////
        //Anything between these comments will be drawn.

        g.setColor(Color.magenta);
        g.fillRect(0,0, 1000, 563);

        handler.render(g);
        ///////////////////////////
        g.dispose();
        bs.show();
    }
    public static void main(String[] args){
        new Game();
    }

}