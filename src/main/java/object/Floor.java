package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Floor extends Block{

    public Floor(int x, int y, SpriteSheet ss, BlockID bID) {
        super(x, y, ss, bID);
    }

    public void setBlock_image(BufferedImage block_image) {
        this.block_image = block_image;
    }

    public void render(Graphics g) {
        g.drawImage(this.block_image, x, y, null);
    }

    public void debugRender(Graphics g) {

    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(this.x, this.y, this.width, this.height);
    }

}
