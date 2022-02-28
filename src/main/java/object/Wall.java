package object;

import core.SpriteSheet;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Wall extends Block{

    public Wall(int x, int y, int width, int height, ID id, SpriteSheet ss, BlockID bID) {
        super(x, y, id, ss, bID);
        this.width = width;
        this.height = height;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(x, y, this.width, this.height);
    }
}
