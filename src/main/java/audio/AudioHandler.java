package audio;

import window.menu.LayoutPanel;
import window.menu.Settings;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class AudioHandler {
    public ArrayList<Audio> audioList = new ArrayList<Audio>();
    public Clip clip;

    public AudioHandler(String fileName) {
        clip = getClip(fileName);
    }

    public void update(Settings settings) {
        /**
         * entire settings object is getting added to each audio object???
         * **/
        audioList.forEach(audio1 -> audio1.update(settings));

        List.copyOf(audioList).forEach(audio1 -> {
            if(audio1.hasCompleted()) {
                audio1.finish();
                audioList.remove(audio1);
            }
        });
    }

    public void clear() {
        audioList.forEach(audio1 -> {
            audio1.finish();
        });
        audioList.clear();
    }

    public void playMusic() {
        audioList.add(new Music(clip));
    }

    public void playSFX(String fileName) {
        final Clip clip = getClip(fileName);
        audioList.add(new SFX(clip));

    }

    public void stopMusic(Clip clip) {
        audioList.remove(clip);
    }


    public void loopAudio() {
        clip.loop(999);
    }



    public Clip getClip(String filePath) {
        final URL soundFile = AudioHandler.class.getResource("/Audio/" + filePath);

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
}
