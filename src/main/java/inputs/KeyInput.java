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
import java.util.HashMap;

public class KeyInput extends KeyAdapter{
    private ArrayList<Integer> keysList;
    public HashMap<Integer, Key> keyBindings;
    public static boolean other[] = new boolean[256];
    private final Handler handler;
    private DebugSettings debugSettings;
    private final GameCanvas gameCanvas;

    public KeyInput(Handler handler, DebugSettings debugSettings, GameCanvas gameCanvas) {
        this.handler = handler;
        this.debugSettings = debugSettings;
        this.gameCanvas = gameCanvas;
        this.keyBindings = new HashMap<Integer, Key>();

        bind(KeyEvent.VK_W, Key.up);
        bind(KeyEvent.VK_S, Key.down);
        bind(KeyEvent.VK_A, Key.left);
        bind(KeyEvent.VK_D, Key.right);
        bind(KeyEvent.VK_J, Key.dash);
        bind(KeyEvent.VK_K, Key.attack);
        bind(KeyEvent.VK_9, Key.debug);

        this.keysList = new ArrayList<>(this.keyBindings.keySet());
    }

    public void bind(Integer keyCode, Key key){
        keyBindings.put(keyCode, key);
    }

    public boolean isKeyBinded(int extendedKey){
        return keyBindings.containsKey(extendedKey);
    }

    public void keyPressed(KeyEvent e) {
        switch (keysList.indexOf(e.getKeyCode())) {
            case 0 -> {
                this.handler.player.left = true;
                this.handler.player.leftPressed = true;
            }
            case 2 -> {
                this.handler.player.right = true;
                this.handler.player.rightPressed = true;
            }
            case 1 -> {
                this.handler.player.down = true;
                this.handler.player.downPressed = true;
            }
            case 3 -> {
                this.handler.player.up = true;
                this.handler.player.upPressed = true;
            }
            case 6 -> {
                this.handler.player.isAttacking = true;
            }
            case 4 -> {
                this.debugSettings.changeDebugMode();
            }
            case 5 -> {
                this.handler.player.dash = true;
                this.handler.player.dashFrames = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (keysList.indexOf(e.getKeyCode())) {
            case 1 -> {
                this.handler.player.down = false;
                this.handler.player.downPressed = false;
                this.handler.player.animation.stop();
                this.handler.player.setAnimation(this.handler.player.standFacingDown);
            }
            case 3 -> {
                this.handler.player.up = false;
                this.handler.player.upPressed = false;
                this.handler.player.animation.stop();
                this.handler.player.setAnimation(this.handler.player.standFacingUp);
            }
            case 0 -> {
                this.handler.player.left = false;
                this.handler.player.leftPressed = false;
                this.handler.player.animation.stop();
                if (this.handler.player.upPressed || this.handler.player.downPressed) {
                    break;
                }
                this.handler.player.setAnimation(this.handler.player.standFacingLeft);
            }
            case 2 -> {
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
}