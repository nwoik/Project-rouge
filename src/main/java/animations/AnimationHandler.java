package animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import core.SpriteSheet;

public class AnimationHandler {
    public HashMap<String, Animation> animations = new HashMap<String, Animation>();
    private SpriteSheet spriteSheet;

    public AnimationHandler(SpriteSheet ss){
        this.spriteSheet = ss;
    }

    // Helper method for addManyFrames. Fills in animation list
    protected List<BufferedImage> fillAnimationList(int column, int row, int increment, int width, int height, int frameCount) {
        List<BufferedImage> framesList = new ArrayList<BufferedImage>();
        for (int i = 0; i < frameCount; i++) {
            framesList.add(spriteSheet.grabImage(column, row, width, height));
            column += increment;
        }
        return framesList;

    }

    // allows us to add many frames
    // imagePositions = [col, row, increment, width, height, frameCount]
    public void addManyFrames(String name, int[] imagePositions, int frameDelay, int offsetX, int offsetY, boolean playOnce){
        List<BufferedImage> list = fillAnimationList(imagePositions[0], imagePositions[1], imagePositions[2],
                imagePositions[3], imagePositions[4], imagePositions[5]);
        Animation a = new Animation(list, frameDelay, offsetX, offsetY, playOnce, name);
        animations.put(name, a);
    }


    // allows us to add a single frame
    // imagePosition = [col, row, width, height]
    public void addSingleFrame(String name, int[] imagePosition, int frameDelay, int offsetX, int offsetY, boolean playOnce){
        BufferedImage img = spriteSheet.grabImage(imagePosition[0], imagePosition[1], imagePosition[2], imagePosition[3]);
        Animation a = new Animation(img, frameDelay, offsetX, offsetY, playOnce, name);
        animations.put(name, a);
    }

    public Animation getAnimation(String name){
        return animations.get(name);
    }

}
