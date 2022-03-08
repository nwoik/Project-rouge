package object;

import animations.Animation;
import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ButtonObject extends GameObject {
    public Animation animation;
    public List<BufferedImage> buttonUnpressed = new ArrayList<BufferedImage>();
    public List<BufferedImage> buttonPressed = new ArrayList<BufferedImage>();

    public Animation unpressButton;
    public Animation pressButton;

    public int framedelay;
    public Handler handler;

    public ButtonObject(int x, int y, Handler handler, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
        this.x = x;
        this.y = y;
        this.width = 64;
        this.height = 64;
        this.spriteSheet = ss;
        this.framedelay = 2;
        this.interactive = true;
        this.handler = handler;

        this.buttonUnpressed.add(spriteSheet.grabImage(1, 1, 64, 64));
        this.buttonPressed.add(spriteSheet.grabImage(2, 1, 64, 64));

        this.unpressButton = new Animation(this.buttonUnpressed, framedelay, 0, 0, true);
        this.pressButton = new Animation(this.buttonPressed, framedelay, 0, 0, true);

        this.animation = this.unpressButton;
    }

    public void collision() {
        if(this.handler.player.getBounds().intersects(this.getBounds())){
            activateButton();
        }
    }

    public void activateButton() {
        this.animation = this.pressButton;
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
