package animations;

import java.awt.image.BufferedImage;

public class Frame {
    private BufferedImage frame;
    private int frameDuration;

    public Frame(BufferedImage frame, int frameDuration) {
        this.frame = frame;
        this.frameDuration = frameDuration;
    }

    public BufferedImage getFrame() {
        return frame;
    }

    public void setFrame(BufferedImage frame) {
        this.frame = frame;
    }

    public int getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(int frameDuration) {
        this.frameDuration = frameDuration;
    }
}
