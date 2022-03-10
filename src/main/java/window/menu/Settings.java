package window.menu;

import audio.AudioHandler;
import core.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Settings extends JPanel {
    private float sfxVol;
    private float musicVol;
    private ArrayList<String> movementSettings;
    private ArrayList<Integer> keyCodeList;
    public Settings(LayoutPanel layoutPanel) throws Exception{
        musicVol = 1;
        sfxVol = 1;

        AudioHandler audio = new AudioHandler();

        setBackground(new Color(150, 200, 255));

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        Tag title = new Tag(wordAtlas.getSubimage(0,0,64,10), 400, 80);
        Tag keyLabel = new Tag(wordAtlas.getSubimage(0,25,69,10), 280, 50);
        Tag upLabel = new Tag(wordAtlas.getSubimage(0,73,18,10), 50, 30);
        Tag downLabel = new Tag(wordAtlas.getSubimage(0,85,36,10), 90, 30);
        Tag leftLabel = new Tag(wordAtlas.getSubimage(1,97,30,10), 70, 30);
        Tag rightLabel = new Tag(wordAtlas.getSubimage(1,109,39,10), 90, 30);
        Tag weapon1Label = new Tag(wordAtlas.getSubimage(1,120,52,10), 160, 30);
        Tag dashLabel = new Tag(wordAtlas.getSubimage(1,192,36,10), 100, 30);

        this.movementSettings = new ArrayList<String>();
        this.keyCodeList = new ArrayList<Integer>();
        readText(this.movementSettings, this.keyCodeList);

        KeyWidget moveUp = new KeyWidget(this.movementSettings.get(0), this.keyCodeList, 0);
        KeyWidget moveDown = new KeyWidget(this.movementSettings.get(1), this.keyCodeList, 1);
        KeyWidget moveLeft = new KeyWidget(this.movementSettings.get(2), this.keyCodeList, 2);
        KeyWidget moveRight = new KeyWidget(this.movementSettings.get(3), this.keyCodeList, 3);
        KeyWidget weapon1 = new KeyWidget(this.movementSettings.get(4), this.keyCodeList, 4);
        KeyWidget dash = new KeyWidget(this.movementSettings.get(5), this.keyCodeList, 5);

        ArrayList<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(moveUp);
        buttonList.add(moveDown);
        buttonList.add(moveLeft);
        buttonList.add(moveRight);
        buttonList.add(weapon1);
        buttonList.add(dash);

        JLabel validLabel = new JLabel("");
        JButton menuButton = new JButton(new SwapCardAction("Back", Menu.class.toString(), layoutPanel));

        JButton saveButton = new JButton("Save");
        JButton defaultButton = new JButton("Default");

        saveButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                audio.playSFX("sfx/menu/wood_click.wav");

                try {
                    Set<String> newMovesSettings = new LinkedHashSet<String>();
                    boolean error = false;
                    short inc = 0;
                    ArrayList<Integer> newKeysList = new ArrayList<Integer>();
                    ArrayList<String> arrowList = new ArrayList<>();
                    arrowList.add("LeftArrow");
                    arrowList.add("RightArrow");
                    arrowList.add("UpArrow");
                    arrowList.add("DownArrow");
                    for (JButton btn : buttonList) {
                        String newVal = btn.getText();
                        //Check if it's usable
                        int keycode = keyCodeList.get(inc);
                        if ((newVal.matches("[a-z]+")) || (newVal.equals(" ")) || arrowList.contains(newVal)) {
                            //Check for duplicates
                            if (!newMovesSettings.add(newVal)) {
                                validLabel.setText("Invalid inputs");
                                System.out.println("Error: Duplication of keys/No inputs");
                                error = true;
                                break;
                            }
                            newKeysList.add(keycode);
                        }
                        else {break;}
                        inc ++;
                    }
                    if (!error) {
                        if ((newKeysList.size() == 6)) {
                            validLabel.setText("Valid inputs");
                            writeText(newMovesSettings, newKeysList);}

                        else {validLabel.setText("Invalid inputs");}
                    }
                    System.out.println("Settings saved");
                    } catch(IOException a) {
                        a.printStackTrace();
                }
            }
        });

        defaultButton.addActionListener(new ActionListener()  {

            @Override
            public void actionPerformed(ActionEvent e) {
                int inc = 0;
                Map<String, Integer> defaultSettings = new HashMap<>();
                defaultSettings.put("w", 87);
                defaultSettings.put("s", 83);
                defaultSettings.put("a", 65);
                defaultSettings.put("d", 68);
                defaultSettings.put("j", 74);
                defaultSettings.put("k", 75);

                Set<String> mapDefault = new LinkedHashSet<String>();
                mapDefault.add("w");
                mapDefault.add("s");
                mapDefault.add("a");
                mapDefault.add("d");
                mapDefault.add("j");
                mapDefault.add("k");

                for (String t : mapDefault) {
                    JButton btn = buttonList.get(inc);
                    btn.setText(t);
                    keyCodeList.set(inc, defaultSettings.get(t));
                    inc ++;
                }
                try {
                    writeText(mapDefault, keyCodeList);
                }
                catch(IOException a) {
                    a.printStackTrace();
                }
            }});

        menuButton.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {
            audio.playSFX("sfx/menu/wood_click.wav");
        }});

        JPanel choicePanel = new JPanel();
        choicePanel.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        choicePanel.setLayout(new GridLayout(8,2));
        choicePanel.add(upLabel);
        choicePanel.add(downLabel);
        choicePanel.add(moveUp);
        choicePanel.add(moveDown);
        choicePanel.add(leftLabel);
        choicePanel.add(rightLabel);
        choicePanel.add(moveLeft);
        choicePanel.add(moveRight);
        choicePanel.add(weapon1Label);
        choicePanel.add(dashLabel);
        choicePanel.add(weapon1);
        choicePanel.add(dash);
        choicePanel.setOpaque(false);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        choicePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        validLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        defaultButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel settingsPane = new JPanel();
        settingsPane.setOpaque( false );
        settingsPane.setLayout(new BoxLayout(settingsPane, BoxLayout.PAGE_AXIS));
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(title);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(keyLabel);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 50)));
        settingsPane.add(choicePanel);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsPane.add(validLabel);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPane.add(saveButton);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsPane.add(defaultButton);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 30)));
        settingsPane.add(menuButton);
        add(settingsPane);
    }

    public void readText(ArrayList<String> buttonList, ArrayList<Integer> keysList) throws IOException {
        short inc = 0;
        File file = new File("src/main/java/window/menu/Settings.txt");
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            if (inc < 6) {
                buttonList.add(line);
            }
            else {
                int key=Integer.parseInt(line);
                keysList.add(key);
            }
            inc ++;
        }
    }

    public void writeText(Set<String> arrayList, ArrayList<Integer> keysList) throws IOException {
        System.out.println(arrayList);System.out.println(keysList);
        File newFile = new File("src/main/java/window/menu/Settings.txt");
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.flush();
        BufferedWriter writer = new BufferedWriter(fileWriter);
        for (String line : arrayList) {
            writer.write(line);
            writer.newLine();
        }

        for (Integer num : keysList) {
            writer.write(Integer.toString(num));
            writer.newLine();
        }
        writer.close();
        fileWriter.close();
        System.out.println("File Saved");
    }

    public float getSFXVol() {
        return sfxVol;
    }

    public void getSFXVol(float audioVol) {
        this.sfxVol = audioVol;
    }

    public float getMusicVol() {
        return musicVol;
    }

    public void setMusicVol(float musicVol) {
        this.musicVol = musicVol;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int HEIGHT = dimension.height;
        int WIDTH = dimension.width;

        BufferedImageLoader bufferLoader = new BufferedImageLoader();
        Graphics2D graphics2D = (Graphics2D)g;
        for (int xx=0; xx<WIDTH; xx+=128){
            for(int yy = 0; yy<HEIGHT; yy+=128){
                graphics2D.drawImage(bufferLoader.loadImage("/MenuBackground/Wood Texture Bottom side.png"), xx,yy, 128, 128, null);
            }
        }
    }
}