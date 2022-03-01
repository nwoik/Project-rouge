package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Wall extends Block{

    public Wall(int x, int y, ID id, SpriteSheet ss, BlockID bID) {
        super(x, y, id, ss, bID);
        this.width = 64;
        this.height = 64;
    }

    public void setBlock_image(BufferedImage block_image) {
        this.block_image = block_image;
    }

    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(x, y, this.width, this.height);
    }
}
