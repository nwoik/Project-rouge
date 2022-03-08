package object;

import animations.Animation;
import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Torch extends AnimateObject {

    public Torch(int x, int y, Handler handler, ID id, SpriteSheet ss) {
        super(x, y, handler, id, ss);
        this.height = 64;
        this.width = 64;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.handler = handler;
        this.alignmentY = -64;

        fillAnimationList(spriteSheet, this.standingFacingDown, 1, 2, 1, 64, 128, 4);
        this.standFacingDown = new Animation(this.standingFacingDown, 4, 0, alignmentY, false);

        this.animation = this.standFacingDown;
    }

    public void tick() {
        this.animation.update();
    }

    public void render(Graphics g) {
        g.drawImage(this.animation.getSprite(), x + this.animation.getOffsetX(), y + this.animation.getOffsetY(), null);
    }

    public void debugRender(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x, y, this.width, this.height);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(x, y, this.width, this.height);
    }
}
