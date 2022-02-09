package audio;

import window.menu.Settings;

import javax.sound.sampled.Clip;

public class Music extends Audio {
    public Music(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(Settings settings) {
        return settings.getMusicVol();
    }
}
