package audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public abstract class Audio {
    private final Clip clip;

    public Audio(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update() {
        final FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    void setVolume() {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * 1) + control.getMinimum();

        control.setValue(gain);
    }

    public boolean hasCompleted() {
        return !clip.isRunning();
    }

    public void finish() {
        clip.close();
    }

}
