package audio;

import window.menu.LayoutPanel;

import javax.sound.sampled.Clip;

public class Music extends Audio {
    public Music(Clip clip) {
        super(clip);
    }


    @Override
    protected float getVolume(LayoutPanel settings) {
        return settings.getMusicVol;
    }
}
