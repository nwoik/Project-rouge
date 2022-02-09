package audio;

import window.menu.LayoutPanel;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class Audio {
    private final Clip clip;

    public Audio(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(LayoutPanel settings) {
        final FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(getVolume(settings));
    }

    public boolean hasCompleted() {
        return !clip.isRunning();
    }

    public void finish() {
        clip.close();
    }

    protected abstract float getVolume(LayoutPanel settings);
}
