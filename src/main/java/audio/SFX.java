package audio;

import window.menu.LayoutPanel;

import javax.sound.sampled.Clip;


public class SFX extends Audio {
    public SFX(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(LayoutPanel settings) {
        return settings.getSFXVol;
    }
}
