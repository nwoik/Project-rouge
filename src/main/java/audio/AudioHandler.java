package audio;

import window.menu.LayoutPanel;
import window.menu.Settings;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class AudioHandler {
    private ArrayList<Audio> audioList = new ArrayList<Audio>();

    public void update(Settings settings) {
        audioList.forEach(audio1 -> audio1.update(settings));

        List.copyOf(audioList).forEach(audio1 -> {
            if(audio1.hasCompleted()) {
                audio1.finish();
                audioList.remove(audio1);
            }
        });
    }

    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        audioList.add(new Music(clip));
    }

    public void playSFX(String fileName) {
        final Clip clip = getClip(fileName);
        audioList.add(new SFX(clip));

    }



    public Clip getClip(String fileName) {
        final URL soundFile = AudioHandler.class.getResource("/Audio/Once_upon_a_dungeon.wav");
        //final URL soundFile = AudioHandler.class.getResource("/Audio/" + fileName);

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
