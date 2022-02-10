package inputs;

import debug.DebugSettings;
import object.Handler;
import object.Player;
import object.Player;

import javax.swing.text.PlainDocument;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
    private final Handler handler;
    private DebugSettings debugSettings;

    public KeyInput(Handler handler, DebugSettings debugSettings) {
        this.handler = handler;
        this.debugSettings = debugSettings;
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
            case KeyEvent.VK_F1:
                this.debugSettings.changeDebugMode();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                this.handler.player.left = false;
                this.handler.player.leftPressed = false;
                this.handler.player.animation.stop();
                if (!this.handler.player.up||!this.handler.player.down) {
                    this.handler.player.setAnimation(this.handler.player.standFacingLeft);
                }
                break;
            case KeyEvent.VK_D:
                this.handler.player.right = false;
                this.handler.player.rightPressed = false;
                this.handler.player.animation.stop();
                if (!this.handler.player.up||!this.handler.player.down) {
                    this.handler.player.setAnimation(this.handler.player.standFacingRight);
                }
                break;
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
        }
    }
}