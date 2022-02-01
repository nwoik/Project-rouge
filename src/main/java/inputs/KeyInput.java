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
        System.out.println(e.getKeyChar());

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> this.handler.player.left = true;
            case KeyEvent.VK_D -> this.handler.player.right = true;
            case KeyEvent.VK_S -> this.handler.player.down = true;
            case KeyEvent.VK_W -> this.handler.player.up = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar() + " released");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> this.handler.player.left = false;
            case KeyEvent.VK_D -> this.handler.player.right = false;
            case KeyEvent.VK_S -> this.handler.player.down = false;
            case KeyEvent.VK_W -> this.handler.player.up = false;
        }
    }
}