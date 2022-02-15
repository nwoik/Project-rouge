package window.menu;

import core.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Settings extends JPanel {
    private float sfxVol;
    private float musicVol;
    private ArrayList<String> movementSettings;
    private ArrayList<Integer> keyCodeList;
    public Settings(LayoutPanel layoutPanel) throws Exception{
        musicVol = 1;
        sfxVol = 1;

        setBackground(new Color(150, 200, 255));

        JLabel title = new JLabel("", SwingConstants.CENTER);
        JLabel keyLabel = new JLabel("", SwingConstants.CENTER);
        JLabel upLabel = new JLabel("", SwingConstants.CENTER);
        JLabel downLabel = new JLabel("", SwingConstants.CENTER);
        JLabel leftLabel = new JLabel("", SwingConstants.CENTER);
        JLabel rightLabel = new JLabel("", SwingConstants.CENTER);

        this.movementSettings = new ArrayList<String>();
        this.keyCodeList = new ArrayList<Integer>();
        readText(this.movementSettings, this.keyCodeList);

        JButton moveUp = new JButton(this.movementSettings.get(0));
        JButton moveDown = new JButton(this.movementSettings.get(1));
        JButton moveLeft = new JButton(this.movementSettings.get(2));
        JButton moveRight = new JButton(this.movementSettings.get(3));

        BufferedImageLoader image = new BufferedImageLoader();
        BufferedImage wordAtlas = image.loadImage("/Word Sheet.png");

        Image titleImage = wordAtlas.getSubimage(1,1,63,10);
        Image scaledTitleImage = titleImage.getScaledInstance(350, 70,  java.awt.Image.SCALE_SMOOTH);
        title.setIcon(new ImageIcon(scaledTitleImage));

        Image controlsImage = wordAtlas.getSubimage(1,25,68,10);
        Image scaledControlsImage = controlsImage.getScaledInstance(240, 50,  java.awt.Image.SCALE_SMOOTH);
        keyLabel.setIcon(new ImageIcon(scaledControlsImage));

        Image upImage = wordAtlas.getSubimage(1,73,18,10);
        Image scaledUpImage = upImage.getScaledInstance(50, 30,  java.awt.Image.SCALE_SMOOTH);
        upLabel.setIcon(new ImageIcon(scaledUpImage));

        Image downImage = wordAtlas.getSubimage(1,85,36,10);
        Image scaledDownImage = downImage.getScaledInstance(90, 30,  java.awt.Image.SCALE_SMOOTH);
        downLabel.setIcon(new ImageIcon(scaledDownImage));

        Image leftImage = wordAtlas.getSubimage(1,97,30,10);
        Image scaledLeftImage = leftImage.getScaledInstance(70, 30,  java.awt.Image.SCALE_SMOOTH);
        leftLabel.setIcon(new ImageIcon(scaledLeftImage));

        Image rightImage = wordAtlas.getSubimage(1,109,39,10);
        Image scaledRightImage = rightImage.getScaledInstance(90, 30,  java.awt.Image.SCALE_SMOOTH);
        rightLabel.setIcon(new ImageIcon(scaledRightImage));

        ArrayList<JButton> buttonList = new ArrayList<JButton>();
        buttonList.add(moveUp);
        buttonList.add(moveDown);
        buttonList.add(moveLeft);
        buttonList.add(moveRight);

        JLabel validLabel = new JLabel("");
        JButton menuButton = new JButton(new SwapCardAction("Back", Menu.class.toString(), layoutPanel));

        moveUp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveUp.setText("  ");
                moveUp.addKeyListener(new KeyBindings(moveUp, keyCodeList, 0));
            }
        });

        moveDown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveDown.setText("  ");
                moveDown.addKeyListener(new KeyBindings(moveDown, keyCodeList, 1));
            }
        });

        moveLeft.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveLeft.setText("  ");
                moveLeft.addKeyListener(new KeyBindings(moveLeft, keyCodeList, 2));
            }
        });

        moveRight.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                moveRight.setText("  ");
                moveRight.addKeyListener(new KeyBindings(moveRight, keyCodeList, 3));
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Set<String> newMovesSettings = new LinkedHashSet<String>();
                    boolean error = false;
                    short inc = 0;
                    for (JButton btn : buttonList) {
                        String newVal = btn.getText();
                        //Check if it's usable
                        int keycode = keyCodeList.get(inc);
                        if ((keycode > 47 && keycode < 58) || (keycode > 64 && keycode < 91) || (keycode >= 37 && keycode <= 40)) {
                            //Check for duplicates
                            if (!newMovesSettings.add(newVal)) {
                                validLabel.setText("Invalid inputs");
                                System.out.println("Error: Duplication of keys/No inputs");
                                error = true;
                                break;
                            }
                        }
                        else {break;}
                        inc ++;
                    }
                    if (!error) {
                        validLabel.setText("Valid inputs");
                        writeText(newMovesSettings, keyCodeList);
                    }
                    System.out.println("Settings saved");
                    } catch(IOException a) {
                        a.printStackTrace();
                }
            }
        });

        JPanel keyBind1Pane = new JPanel();
        keyBind1Pane.setOpaque( false );
        keyBind1Pane.setLayout(new BoxLayout(keyBind1Pane, BoxLayout.LINE_AXIS));
        keyBind1Pane.add(Box.createHorizontalGlue());
        keyBind1Pane.add(upLabel);
        keyBind1Pane.add(Box.createRigidArea(new Dimension(50, 0)));
        keyBind1Pane.add(downLabel);

        JPanel keyBind2Pane = new JPanel();
        keyBind2Pane.setOpaque( false );
        keyBind2Pane.setLayout(new BoxLayout(keyBind2Pane, BoxLayout.LINE_AXIS));
        keyBind2Pane.add(Box.createHorizontalGlue());
        keyBind2Pane.add(moveUp);
        keyBind2Pane.add(Box.createRigidArea(new Dimension(90, 0)));
        keyBind2Pane.add(moveDown);

        JPanel keyBind3Pane = new JPanel();
        keyBind3Pane.setOpaque( false );
        keyBind3Pane.setLayout(new BoxLayout(keyBind3Pane, BoxLayout.LINE_AXIS));
        keyBind3Pane.add(Box.createHorizontalGlue());
        keyBind3Pane.add(leftLabel);
        keyBind3Pane.add(Box.createRigidArea(new Dimension(50, 0)));
        keyBind3Pane.add(rightLabel);

        JPanel keyBind4Pane = new JPanel();
        keyBind4Pane.setOpaque( false );
        keyBind4Pane.setLayout(new BoxLayout(keyBind4Pane, BoxLayout.LINE_AXIS));
        keyBind3Pane.add(Box.createHorizontalGlue());
        keyBind4Pane.add(moveLeft);
        keyBind4Pane.add(Box.createRigidArea(new Dimension(90, 0)));
        keyBind4Pane.add(moveRight);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind1Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind2Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind3Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyBind4Pane.setAlignmentX(Component.CENTER_ALIGNMENT);
        validLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel settingsPane = new JPanel();
        settingsPane.setOpaque( false );
        settingsPane.setLayout(new BoxLayout(settingsPane, BoxLayout.PAGE_AXIS));
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(title);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(keyLabel);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 50)));
        settingsPane.add(keyBind1Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPane.add(keyBind2Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsPane.add(keyBind3Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPane.add(keyBind4Pane);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsPane.add(validLabel);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 30)));
        settingsPane.add(saveButton);
        settingsPane.add(Box.createRigidArea(new Dimension(0, 100)));
        settingsPane.add(menuButton);
        add(settingsPane);
    }

    public void readText(ArrayList<String> buttonList, ArrayList<Integer> keysList) throws IOException {
        short inc = 0;
        File file = new File("src/main/java/window/menu/Settings.txt");
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            if (inc < 4) {
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

        /*
        InputStream is = Settings.class.getResourceAsStream("/Settings.txt");
        BufferedWriter reader = new BufferedWriter(new FileWriter("/Settings.txt"));
        for (String line : arrayList) {
            reader.write(line);
        }
        reader.close();
        */
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

//BIGIFY - Kozlow