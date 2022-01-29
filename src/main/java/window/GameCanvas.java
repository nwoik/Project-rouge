package window;

import inputs.KeyInput;
import object.Handler;
import object.ID;
import object.Protagonist;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas implements Runnable{
    public Handler handler;
    public boolean isRunning = false;
    public Panel panel;
    private Thread thread;

    // For callFPS
    long lastTime;
    String outputFPS;
    int frames;

    public GameCanvas(Panel panel) {
        this.panel = panel;
        this.handler = new Handler();
        addKeyListener(new KeyInput(handler));
        handler.addObject(new Protagonist(100,100, ID.Player, handler));

    }

    public void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        isRunning = false;
        try{
            thread.join();  //rejoins thread to main program
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        requestFocus();
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
            this.render();
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
        this.handler.tick();
    }
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

        this.handler.render(g);
        ///////////////////////////
        g.setColor(Color.BLACK);
        g.drawString(callFPS(), 20, 20);
        g.dispose();
        bs.show();
    }

    public String callFPS() {
        frames++;
        if (System.currentTimeMillis() - lastTime >= 1000) { // if the time between the current and last time is over 1000ms
            outputFPS = "" + frames;
            frames = 0; // reset frame count
            lastTime = System.currentTimeMillis(); // reassign last time
        }
        return outputFPS;
    }
}
