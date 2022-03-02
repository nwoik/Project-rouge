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
import java.io.IOException;

public class GameCanvas extends Canvas implements Runnable{
    private Handler handler;
    private Camera camera;
    public boolean isRunning = false;
    private Panel panel;
    private Thread thread;
    private SpriteSheet characterSheet, dungeon1Sheet;
    private SpriteSheet uiSheet;
    public Boolean stopped = false;

    public DebugSettings debugSettings;
    public GameWindow gameWindow;
    public LayoutPanel layoutPanel;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    private BufferedImage character = null;
    private BufferedImage ui = null;
    private final BufferedImage dungeon1;

    // For callFPS
    String outputFPS = "";

    //initialise the game canvas
    public GameCanvas(GameWindow gameWindow, LayoutPanel layoutPanel) throws IOException {
        this.handler = new Handler();
        camera = new Camera(0,0, HEIGHT, WIDTH);
        handler.camera = camera;

        this.debugSettings = new DebugSettings(false);
        this.gameWindow = gameWindow;
        this.layoutPanel = layoutPanel;

        addKeyListener(new KeyInput(handler, debugSettings, this));
        setBackground(new Color(0, 0, 0, 199));

        ReadCSVFile csvFile1 = new ReadCSVFile("src/main/java/core/levels/DC1_Floors.csv");
        ReadCSVFile csvFile2 = new ReadCSVFile("src/main/java/core/levels/DC1_Walls.csv");

        BufferedImageLoader loader = new BufferedImageLoader();
        character = loader.loadImage("/Player/Character_Atlas.png");
        ui = loader.loadImage("/UI.png");
        dungeon1 = loader.loadImage("/Dungeon 1 atlas.png");

        uiSheet = new SpriteSheet(ui);
        characterSheet = new SpriteSheet(character);
        dungeon1Sheet = new SpriteSheet(dungeon1);

        TileMap tileMap1 = new TileMap(dungeon1Sheet);
        Level level1 = new Level(tileMap1, csvFile1, csvFile2);

        CharacterSpawn characterSpawn = new CharacterSpawn(this.handler, characterSheet);
        LevelLoader levelLoader = new LevelLoader(this.handler, characterSpawn);
        levelLoader.loadLevel(level1);
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
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);

        //Pans camera. Most things that move in level go between the two translations. Static things like FPS and UI
        //are called after second translate (don't move regardless of everything)
        g2d.translate(-camera.getX(), -camera.getY());

        //Print the floor. Floor is represented by black pixels on level.png (for now as black is unassigned)
        //Basically every part of the level that isn't obstructed by something reveals the floor, even though floor
        //is drawn everywhere (but some things are drawn over it.)
        handler.renderFloors(g);

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