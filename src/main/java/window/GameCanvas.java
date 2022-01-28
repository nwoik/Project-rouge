package window;

import inputs.KeyInput;
import object.Handler;
import object.ID;
import object.Protagonist;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas {
    public Handler handler;
    public boolean isRunning = false;
    public Panel panel;

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
