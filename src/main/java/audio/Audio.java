package audio;

import window.menu.LayoutPanel;
import window.menu.Settings;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public abstract class Audio {
    private final Clip clip;

    public Audio(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(Settings settings) {
        final FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(getVolume(settings));
    }

    void setVolume(Settings settings) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * getVolume(settings)) + control.getMinimum();

        control.setValue(gain);
    }

    public boolean hasCompleted() {
        return !clip.isRunning();
    }

    public void finish() {
        clip.close();
    }

    protected abstract float getVolume(Settings settings);

}
