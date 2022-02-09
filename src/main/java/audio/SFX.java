package audio;

import window.menu.LayoutPanel;
import window.menu.Settings;

import javax.sound.sampled.Clip;


public class SFX extends Audio {
    public SFX(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(Settings settings) {
        return settings.getSFXVol();
    }
}
