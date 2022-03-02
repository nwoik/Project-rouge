package window;

import core.*;
import core.spawns.CharacterSpawn;
import debug.DebugSettings;
import inputs.KeyInput;
import object.Handler;
import window.menu.LayoutPanel;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas implements Runnable{
    private Handler handler;
    private Camera camera;
    public boolean isRunning = false;
    private Panel panel;
    private Thread thread;
    private SpriteSheet ss;
    private SpriteSheet characterSheet;
    private SpriteSheet uiSheet;
    public Boolean stopped = false;

    public DebugSettings debugSettings;
    public GameWindow gameWindow;
    public LayoutPanel layoutPanel;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;
    private BufferedImage character = null;
    private BufferedImage skeleton = null;
    private BufferedImage ui = null;

    // For callFPS
    String outputFPS = "";

    //initialise the game canvas
    public GameCanvas(GameWindow gameWindow, LayoutPanel layoutPanel) {
        this.handler = new Handler();
        camera = new Camera(0,0, HEIGHT, WIDTH);

        this.debugSettings = new DebugSettings(false);
        this.gameWindow = gameWindow;
        this.layoutPanel = layoutPanel;

        addKeyListener(new KeyInput(handler, debugSettings, this));
        setBackground(new Color(0, 0, 0, 199));


        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/Levels/level1.png");
        sprite_sheet = loader.loadImage("/Levels/Dungeon_1.png");
        character = loader.loadImage("/Player/Character_Atlas.png");
        skeleton = loader.loadImage("/Skeleton_Atlas.png");
        ui = loader.loadImage("/UI.png");

        uiSheet = new SpriteSheet(ui);
        characterSheet = new SpriteSheet(character);
        ss = new SpriteSheet(sprite_sheet);

        CharacterSpawn characterSpawn = new CharacterSpawn(this.handler, characterSheet);
        LevelLoader levelLoader = new LevelLoader(this.handler, characterSpawn, ss);

        floor = ss.grabImage(2,1,64,64);

        levelLoader.loadLevel(level);

    }

    //stop game
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
                    tick();
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

    public void comeBack(){
        setFocusable(true);
        this.stopped = !this.stopped;
        this.stopped = !this.stopped;

    }

    //updates everything in the game. updated 60 times per second
    public void tick(){
        //camera follows player every tick
        camera.tick(handler.player);
        this.handler.tick(this.debugSettings.isDebugMode());
    }

    public void openMenu(){
        this.stopped = !this.stopped;
        SubWindow subWindow;
        if (this.stopped) {
            subWindow = new SubWindow(this, this.gameWindow, this.layoutPanel);
        }
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
        g.setColor(Color.gray);
        g.fillRect(0,0, WIDTH, HEIGHT);

        //Pans camera. Most things that move in level go between the two translations. Static things like FPS and UI
        //are called after second translate (don't move regardless of everything)
        g2d.translate(-camera.getX(), -camera.getY());

        //Print the floor. Floor is represented by black pixels on level.png (for now as black is unassigned)
        //Basically every part of the level that isn't obstructed by something reveals the floor, even though floor
        //is drawn everywhere (but some things are drawn over it.)
        for (int xx=0; xx<60*70; xx+=128){
            for(int yy = 0; yy<60*70; yy+=128){
                g.drawImage(floor, xx, yy, null);
            }
        }

        //Render every single object.
        handler.render(g, this.debugSettings.isDebugMode());
        g2d.translate(camera.getX(), camera.getY());
        g.drawImage(this.uiSheet.image, 20,20, null);
        //Write out fps
        g.setColor(Color.yellow);
        g.drawString(outputFPS, 20, 20);
        ///////////////////////////
        g.dispose();
        bs.show();
    }
}