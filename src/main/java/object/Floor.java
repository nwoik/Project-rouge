package object;

import core.SpriteSheet;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Floor extends Block{

    public Floor(int x, int y, ID id, SpriteSheet ss, BlockID bID) {
        super(x, y, id, ss, bID);
        this.width = 64;
        this.height = 64;
    }
}
