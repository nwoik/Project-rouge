package object;

import animations.Animation;
import audio.AudioHandler;
import core.LevelLoader;
import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class WarpZone extends GameObject {
    private final LevelLoader levelLoader;
    public Animation animation;
    public List<BufferedImage> warpOff = new ArrayList<BufferedImage>();
    public List<BufferedImage> warpOn = new ArrayList<BufferedImage>();

    public Animation unpowerWarp;
    public Animation powerWarp;

    public int framedelay;
    public Handler handler;
    private AudioHandler audio = new AudioHandler();

    public WarpZone(int x, int y, Handler handler, ID id, SpriteSheet ss, LevelLoader levelLoader) {
        super(x, y, id, ss);
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.spriteSheet = ss;
        this.framedelay = 2;
        this.interactive = true;
        this.handler = handler;
        this.levelLoader = levelLoader;

        this.warpOff.add(spriteSheet.grabImage(1, 1, 64, 64));
        fillAnimationList(this.spriteSheet, this.warpOn, 1, 2, 1, 64, 64, 4);

        this.unpowerWarp = new Animation(this.warpOff, framedelay, 0, 0, true);
        this.powerWarp = new Animation(this.warpOn, framedelay, 0, 0, false);

        this.animation = this.unpowerWarp;
    }

    public void collision() {
        if(this.handler.player.getBounds().intersects(this.getBounds()) & this.animation == this.powerWarp){
            System.out.println("warp");
            warp();
        }
    }

    public void fillAnimationList(SpriteSheet spriteSheet, List<BufferedImage> framesList, int column, int row, int increment, int width, int height, int frameCount) {
        for (int i = 0; i < frameCount; i++) {
            framesList.add(spriteSheet.grabImage(column, row, width, height));
            column += increment;
        }
    }

    public void activateWarp() {
        this.animation = this.powerWarp;
    }

    public void warp() {
        this.levelLoader.setCurrentLevel(this.levelLoader.getCurrentLevel()+1);
        this.levelLoader.loadLevel(this.levelLoader.levelList.get(this.levelLoader.getCurrentLevel()));
        audio.playSFX("sfx/state/warp.wav");
    }

    @Override
    public void tick() {
        collision();
        this.animation.update();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.animation.getSprite(), x + this.animation.getOffsetX(), y + this.animation.getOffsetY(), null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x, y, this.width, this.height);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(x, y, this.width, this.height);
    }

    @Override
    public Ellipse2D getBoundsFOV() {
        return null;
    }
}
