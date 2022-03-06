package window.menu;

import audio.AudioHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KeyWidget extends JButton implements ActionListener{
    ArrayList<Integer> keysList;
    int keyPosition;
    public KeyWidget(String InitialKey, ArrayList<Integer> keysList, int keyPosition) {
        this.keysList = keysList;
        this.keyPosition = keyPosition;
        setText(InitialKey);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AudioHandler audio1 = new AudioHandler("sfx/menu/wood_click.wav");
        audio1.playMusic();
        setText("  ");
        addKeyListener(new KeyBindings(this, keysList, keyPosition));
    }
}
