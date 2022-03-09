package inputs;

import debug.DebugSettings;
import object.Handler;
import window.GameCanvas;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;

public class KeyInput extends KeyAdapter{
    private ArrayList<Integer> keysList;

    private final Handler handler;
    private DebugSettings debugSettings;
    private final GameCanvas gameCanvas;

    public KeyInput(Handler handler, DebugSettings debugSettings, GameCanvas gameCanvas) {
        this.handler = handler;
        this.debugSettings = debugSettings;
        this.gameCanvas = gameCanvas;
        this.keysList = new ArrayList<>();

        try {
            readText(this.keysList);
        }
        catch(IOException a) {
            a.printStackTrace();
        }
    }

    public void readText(ArrayList<Integer> keysList) throws IOException {
        short inc = 0;
        File file = new File("src/main/java/window/menu/Settings.txt");
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            if (inc >= 6) {
                keysList.add(Integer.parseInt(line));
            }
            inc ++;
        }
    }

    public int indexOf(int value, ArrayList<Integer> array){
        for(int i = 0; i < array.size(); i++){
            if(array.get(i).equals(value)){
                return i;
            }
        }
        //return a place holder value
        return -1;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (indexOf(key, this.keysList)) {
            case 2 -> {
                this.handler.player.left = true;
                this.handler.player.leftPressed = true;
            }
            case 3 -> {
                this.handler.player.right = true;
                this.handler.player.rightPressed = true;
            }
            case 1 -> {
                this.handler.player.down = true;
                this.handler.player.downPressed = true;
            }
            case 0 -> {
                this.handler.player.up = true;
                this.handler.player.upPressed = true;
            }
            case 4 -> this.handler.player.isAttacking = true;
            case 7 -> {
                this.debugSettings.changeDebugMode();
            }
            case 6 -> this.gameCanvas.stopped = !this.gameCanvas.stopped;
            case 5 -> {
                this.handler.player.dash = true;
                this.handler.player.dashFrames = 0;
            }
            case 8 -> this.gameCanvas.openMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (indexOf(key, this.keysList)) {
            case 1 -> {
                this.handler.player.down = false;
                this.handler.player.downPressed = false;
                this.handler.player.animation.stop();
                this.handler.player.setAnimation(this.handler.player.standFacingDown);
            }
            case 0 -> {
                this.handler.player.up = false;
                this.handler.player.upPressed = false;
                this.handler.player.animation.stop();
                this.handler.player.setAnimation(this.handler.player.standFacingUp);
            }
            case 2 -> {
                this.handler.player.left = false;
                this.handler.player.leftPressed = false;
                this.handler.player.animation.stop();
                if (this.handler.player.upPressed || this.handler.player.downPressed) {
                    break;
                }
                this.handler.player.setAnimation(this.handler.player.standFacingLeft);
            }
            case 3 -> {
                this.handler.player.right = false;
                this.handler.player.rightPressed = false;
                this.handler.player.animation.stop();
                if (this.handler.player.upPressed || this.handler.player.downPressed) {
                    break;
                }
                this.handler.player.setAnimation(this.handler.player.standFacingRight);
            }
        }
    }
/*
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A -> {
                this.handler.player.left = true;
                this.handler.player.leftPressed = true;
            }
            case KeyEvent.VK_D -> {
                this.handler.player.right = true;
                this.handler.player.rightPressed = true;
            }
            case KeyEvent.VK_S -> {
                this.handler.player.down = true;
                this.handler.player.downPressed = true;
            }
            case KeyEvent.VK_W -> {
                this.handler.player.up = true;
                this.handler.player.upPressed = true;
            }
            case KeyEvent.VK_G -> this.handler.player.attack = true;
            case KeyEvent.VK_F1 -> this.debugSettings.changeDebugMode();
            case KeyEvent.VK_F2 -> this.gameCanvas.stopped = !this.gameCanvas.stopped;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                this.handler.player.down = false;
                this.handler.player.downPressed = false;
                this.handler.player.animation.stop();
                this.handler.player.setAnimation(this.handler.player.standFacingDown);
                break;
            case KeyEvent.VK_W:
                this.handler.player.up = false;
                this.handler.player.upPressed = false;
                this.handler.player.animation.stop();
                this.handler.player.setAnimation(this.handler.player.standFacingUp);
                break;
            case KeyEvent.VK_A:
                this.handler.player.left = false;
                this.handler.player.leftPressed = false;
                this.handler.player.animation.stop();
                if (this.handler.player.upPressed || this.handler.player.downPressed) {
                    break;
                }
                this.handler.player.setAnimation(this.handler.player.standFacingLeft);
                break;
            case KeyEvent.VK_D:
                this.handler.player.right = false;
                this.handler.player.rightPressed = false;
                this.handler.player.animation.stop();
                if (this.handler.player.upPressed || this.handler.player.downPressed) {
                    break;
                }
                this.handler.player.setAnimation(this.handler.player.standFacingRight);
                break;
        }
    }*/
}