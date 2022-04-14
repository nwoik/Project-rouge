package window;

import audio.AudioHandler;
import core.*;
import debug.DebugSettings;
import gamestates.Gamestate;
import gamestates.Playing;
import gamestates.Menu;
import inputs.KeyInput;
import object.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;

public class GameCanvas extends Canvas implements Runnable{
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    public boolean isRunning = false;
    private Thread thread;
    public Boolean stopped = false;

    private Playing playing;
    private Menu menu;

    public DebugSettings debugSettings;
    public GameWindow gameWindow;

    public Font gameFont, defaultFont;

    // For callFPS
    String outputFPS = "";

    //initialise the game canvas
    public GameCanvas(GameWindow gameWindow) throws IOException {
        this.debugSettings = new DebugSettings(false);
        this.defaultFont = new Font("Dialog", Font.PLAIN, 12);
        try {
            InputStream inputStream = getClass().getResourceAsStream("/Font/GameFont-Regular.ttf");
            assert inputStream != null;
            gameFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        this.playing = new Playing(gameFont, debugSettings, this.outputFPS);
        addKeyListener(new KeyInput(this.playing.handler, debugSettings, this));
        setBackground(new Color(0, 0, 0, 19));
    }

    public void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    //run game
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
                if (!stopped) {
                    try {
                        tick();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //updates++;
                delta--;
            }
            if (!stopped) {
                this.render();
                frames++;
            }

            if(System.currentTimeMillis()-timer>1000){
                outputFPS = "" + frames;
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        stop();
    }

    //updates everything in the game. updated 60 times per second
    public void tick() throws IOException {
        //camera follows player every tick
        switch (Gamestate.gamestate) {
            case PLAYING -> {
                this.playing.tick();
                break;
            }
            case MENU -> {
                break;
            }
        }
    }

    //Draw everything
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            //preload 3 frames before they are actually shown. Improve efficiency
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        switch (Gamestate.gamestate) {
            case PLAYING -> {
                this.playing.render(g);
                break;
            }
            case MENU -> {
                break;
            }
        }

        g.setColor(Color.yellow);
        g.setFont(defaultFont);
        g.drawString(this.outputFPS, 20, 20);

        g.dispose();
        bs.show();
    }
}