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
        g.dispose();
        bs.show();
    }
}
