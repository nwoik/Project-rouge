package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Floor extends Block{

    public Floor(int x, int y, SpriteSheet ss, BlockID bID) {
        super(x, y, ss, bID);
        this.width = 64;
        this.height = 64;
        this.block_image = ss.grabImage(1, 1, this.width, this.height);
    }

    public void setBlock_image(BufferedImage block_image) {
        this.block_image = block_image;
    }

    public void render(Graphics g) {
        g.drawImage(this.block_image, x, y, null);
    }

}
