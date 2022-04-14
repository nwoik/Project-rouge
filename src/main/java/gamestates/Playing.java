package gamestates;

import audio.AudioHandler;
import core.BufferedImageLoader;
import core.Camera;
import core.LevelLoader;
import core.SpriteSheet;
import debug.DebugSettings;
import inputs.KeyInput;
import object.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Playing implements StateMethods {
    public Handler handler;
    private AudioHandler audio = new AudioHandler();
    private Camera camera;

    private Panel panel;
    private SpriteSheet uiSheet;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public int HEIGHT = dimension.height;
    public int WIDTH = dimension.width;

    private BufferedImage ui = null;

    public LevelLoader levelLoader;
    private String attackButton, dashButton;
    private DebugSettings debugSettings;

    private String outputFPS;
    public Font gameFont;

    public Playing(Font gameFont, DebugSettings debugSettings, String outputFPS) throws IOException {
        this.handler = new Handler();
        this.camera = new Camera(0,0, HEIGHT, WIDTH);
        this.handler.camera = camera;

        this.gameFont = gameFont;
        this.debugSettings = debugSettings;
        this.outputFPS = outputFPS;

        BufferedImageLoader loader = new BufferedImageLoader();
        ui = loader.loadImage("/UI.png");
        uiSheet = new SpriteSheet(ui);

        this.levelLoader = new LevelLoader(this.handler);
        this.levelLoader.loadLevel(levelLoader.level1);
    }

    @Override
    public void tick() {
        camera.tick(handler.player);
        this.handler.tick(this.debugSettings.isDebugMode());
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        ////////////////////////////
        //Anything between these comments will be drawn.

        //Set background (What's outside level, otherwise looks glitchy)
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

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
        g.setColor(Color.red);
        g.fillRect(20, 32 + (96 - handler.player.getHp()), 120, 96 - (96 - handler.player.getHp()));
        g.drawImage(this.uiSheet.image, 20, 20, null);

        g.setColor(new Color(88, 50, 27));
        g.setFont(this.gameFont.deriveFont(32F));
//                g.drawString(this.dashButton, 200, 60);
//                g.drawString(this.attackButton, 425, 60);
        ///////////////////////////
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
