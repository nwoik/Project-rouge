package animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import core.SpriteSheet;

public class AnimationHandler {
    public HashMap<String, Animation> animations = new HashMap<String, Animation>();
    private Animation animation;
    private SpriteSheet spriteSheet;

    public AnimationHandler(SpriteSheet ss){
        this.spriteSheet = ss;
    }

    // Helper method for addManyFrames. Fills in animation list
    protected List<BufferedImage> fillAnimationList(int column, int row, int width, int height, int increment, int frameCount) {
        List<BufferedImage> framesList = new ArrayList<BufferedImage>();
        for (int i = 0; i < frameCount; i++) {
            framesList.add(spriteSheet.grabImage(column, row, width, height));
            column += increment;
        }
        return framesList;

    }

    // allows us to add many frames
    // imagePositions = [col, row, width, height, increment, frameCount]
    public void addManyFrames(String name, List<Integer> imagePositions, int frameDelay, int offsetX, int offsetY, boolean playOnce){
        List<BufferedImage> list = fillAnimationList(imagePositions.get(0), imagePositions.get(1), imagePositions.get(2),
                                                    imagePositions.get(3), imagePositions.get(4), imagePositions.get(5));
        Animation a = new Animation(list, frameDelay, offsetX, offsetY, playOnce);
        animations.put(name, a);
    }


    // allows us to add a single frame
    // imagePosition = [col, row, width, height]
    public void addSingleFrame(String name, List<Integer> imagePosition, int frameDelay, int offsetX, int offsetY, boolean playOnce){
        BufferedImage img = spriteSheet.grabImage(imagePosition.get(0), imagePosition.get(1), imagePosition.get(2), imagePosition.get(3));
        Animation a = new Animation(img, frameDelay, offsetX, offsetY, playOnce);
        animations.put(name, a);
    }

    public void setAnimation(String name){
        this.animation = animations.get(name);
    }

    public Animation getAnimation(){
        return this.animation;
    }

}
