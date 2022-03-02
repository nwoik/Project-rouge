package core;

import java.awt.*;
import java.awt.image.BufferedImage;

//Class for loading sprite sheets and resizing them to 64x64
public class SpriteSheet {

    public BufferedImage image;
    int width;
    int height;

    public SpriteSheet (BufferedImage image){
        this.image = image;
        width = this.image.getWidth();
        height = this.image.getHeight();

        resizeImage();
    }

    private void resizeImage(){
        BufferedImage resizedImage = new BufferedImage(width*4, height*4, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = resizedImage.getGraphics();
        graphics.drawImage(image, 0, 0, width*4, height*4, null);
        graphics.dispose();
        this.image = resizedImage;
    }

    public BufferedImage grabImage(int col, int row, int width, int height){
        //extract sprites from image
        return image.getSubimage((col*64)-64, (row*64)-64, width, height);
    }

    public SpriteSheet subSheet(BufferedImage bufferedImage) {
        return new SpriteSheet(bufferedImage);
    }
}
