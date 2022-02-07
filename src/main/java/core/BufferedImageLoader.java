package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Helper class to load buffered images e.g. sprite sheet, level etc.
public class BufferedImageLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String path){
        try{
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
