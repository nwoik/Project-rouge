package animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private int frameCount;
    private int frameDuration;
    private int offsetX;
    private int offsetY;
    private int currentFrame;
    private int animationDirection;
    private int totalFrames;
    private boolean stop;

    private List<Frame> frames = new ArrayList<Frame>();

    public Animation(List<BufferedImage> frames, int frameDelay, int offsetX, int offsetY) {
        this.frameDuration = frameDelay;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.stop = true;

        for (BufferedImage frame: frames) {
            addFrame(frame, frameDelay);
        }

        this.frameCount = 0;
        this.frameDuration = frameDelay;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();
    }

    public void start() {
        if (!stop) {
            return;
        }
        if (frames.isEmpty()) {
            return;
        }
        stop = false;
    }

    public void stop() {
        if (frames.isEmpty()) {
            return;
        }
        stop = true;
    }

    public void restart() {
        if (frames.isEmpty()) {
            return;
        }
        stop = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stop = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    private void addFrame(BufferedImage frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }

        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public void update() {
        if (!stop) {
            frameCount++;
            if (frameCount > frameDuration) {
                frameCount = 0;
                currentFrame += animationDirection;
                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                }
                else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }
    }
}
