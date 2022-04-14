package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Wall extends Block{
    public int offsetX;
    public int offsetWidth;

    public Wall(int x, int y, SpriteSheet ss, BlockID bID, int offsetX, int offsetWidth) {
        super(x, y, ss, bID);
        this.offsetX = offsetX;
        this.offsetWidth = offsetWidth;
    }

    public void setBlock_image(BufferedImage block_image) {
        this.block_image = block_image;
    }

    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    public void debugRender(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(x+this.offsetX, y, this.width+this.offsetWidth, this.height);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(x+this.offsetX, y, this.width+this.offsetWidth, this.height);
    }
}
