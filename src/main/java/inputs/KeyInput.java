package inputs;

import object.Handler;
import object.Player;
import object.Player;

import javax.swing.text.PlainDocument;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
    private final Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
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
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                this.handler.player.left = false;
                this.handler.player.leftPressed = false;
                break;
            case KeyEvent.VK_D:
                this.handler.player.right = false;
                this.handler.player.rightPressed = false;
                break;
            case KeyEvent.VK_S:
                this.handler.player.down = false;
                this.handler.player.downPressed = false;
                break;
            case KeyEvent.VK_W:
                this.handler.player.up = false;
                this.handler.player.upPressed = false;
                break;
        }
    }
}