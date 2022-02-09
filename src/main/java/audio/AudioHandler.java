package audio;

import window.menu.SettingsPanel;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** in Game put "AudioHandler = new AudioHandler();" (and add a getter for it?)**/

/** in Game call the audioHandler.playMusic("") method **/

public class AudioHandler {
    private List<Audio> audio;

    private float audioVol;
    private float musicVol;

    public AudioHandler() {
        audio = new ArrayList<>();

        audioVol = 1;
        musicVol = 1;
    }

    public void update(SettingsPanel settings) {
        audio.forEach(audio1 -> audio1.update(settings));

        List.copyOf(audio).forEach(audio1 -> {
            if(audio1.hasCompleted()) {
                audio1.finish();
                audio.remove(audio1);
            }
        });
    }

    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        audio.add(new Music(clip));
    }

    public void playSFX(String fileName) {
        final Clip clip = getClip(fileName);
        audio.add(new SFX(clip));

    }



    public Clip getClip(String fileName) {
        final URL soundFile = AudioHandler.class.getResource("/sounds/" + fileName);

        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((soundFile))) {
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);
            return clip;
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        return null;
    }

    public float getAudioVol() {
        return audioVol;
    }

    public void setAudioVol(float audioVol) {
        this.audioVol = audioVol;
    }

    public float getMusicVol() {
        return musicVol;
    }

    public void setMusicVol(float musicVol) {
        this.musicVol = musicVol;
    }
}
