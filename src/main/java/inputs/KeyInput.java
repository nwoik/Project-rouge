package inputs;

import debug.DebugSettings;
import object.Handler;
import window.GameCanvas;
import object.Player;
import object.Player;

import javax.swing.text.PlainDocument;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;

public class KeyInput extends KeyAdapter{
    ArrayList<Integer> keysList = new ArrayList<Integer>();
    private final Handler handler;
    private DebugSettings debugSettings;
    private final GameCanvas gameCanvas;

    public KeyInput(Handler handler, DebugSettings debugSettings, GameCanvas gameCanvas) {
        this.handler = handler;
        this.debugSettings = debugSettings;
        this.gameCanvas = gameCanvas;
        this.keysList=keysList;

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
            if (inc >= 4) {
                int key=Integer.parseInt(line);
                keysList.add(key);
            }
            inc ++;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                this.handler.player.left = true;
                this.handler.player.leftPressed = true;
                break;
            case KeyEvent.VK_D:
                this.handler.player.right = true;
                this.handler.player.rightPressed = true;
                break;
            case KeyEvent.VK_S:
                this.handler.player.down = true;
                this.handler.player.downPressed = true;
                break;
            case KeyEvent.VK_W:
                this.handler.player.up = true;
                this.handler.player.upPressed = true;
                break;
            case KeyEvent.VK_F1:
                this.debugSettings.changeDebugMode();
                break;
            case KeyEvent.VK_F2:
                this.gameCanvas.stopped = !this.gameCanvas.stopped;
                break;
            case KeyEvent.VK_SPACE:
                this.handler.player.dash = true;
                break;
        }
    }

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
    }

}

