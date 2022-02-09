package window;

import core.BufferedImageLoader;
import core.Camera;
import core.LevelLoader;
import core.SpriteSheet;
import inputs.KeyInput;
import object.Handler;
import object.ID;
import object.Protagonist;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas implements Runnable{
    private Handler handler;
    private Camera camera;
    public boolean isRunning = false;
    private Thread thread;
    private SpriteSheet ss;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;

    // For callFPS
    String outputFPS = "";

    //initialise the game canvas
    public GameCanvas() {
        this.handler = new Handler();
        camera = new Camera(0,0, HEIGHT, WIDTH);

        addKeyListener(new KeyInput(handler));

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/Levels/level1.png");
        sprite_sheet = loader.loadImage("/Levels/Dungeon_1.png");

        ss = new SpriteSheet(sprite_sheet);

        LevelLoader levelLoader = new LevelLoader(this.handler, ss);

        floor = ss.grabImage(2,1,64,64);

        levelLoader.loadLevel(level);


    }
    //stop game
    public void start(){
        System.out.println("aloha");
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
                tick();
                //updates++;
                delta--;
            }
            this.render();
            frames++;

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
    public void tick(){
        //camera follows player every tick
        for(int i=0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
        this.handler.tick();
    }
    //Draw everything
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            //preload 3 frames before they are actually shown. Improve efficiency

            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ////////////////////////////
        //Anything between these comments will be drawn.

        //Set background (What's outside level, otherwise looks glitchy)
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);

        //Pans camera. Most things that move in level go between the two translations. Static things like FPS and UI
        //are called after second translate (don't move regardless of everything)
        g2d.translate(-camera.getX(), -camera.getY());

        //Print the floor. Floor is represented by black pixels on level.png (for now as black is unassigned)
        //Basically every part of the level that isn't obstructed by something reveals the floor, even though floor
        //is drawn everywhere (but some things are drawn over it.)
        for (int xx=0; xx<60*70; xx+=64){
            for(int yy = 0; yy<60*70; yy+=64){
                g.drawImage(floor, xx, yy, null);
            }
        }

        //Render every single object.
        handler.render(g);
        g2d.translate(camera.getX(), camera.getY());
        //Write out fps
        g.setColor(Color.yellow);
        g.drawString(outputFPS, 20, 20);
        ///////////////////////////
        g.dispose();
        bs.show();
    }



}
