package window.menu;

import window.GameCanvas;
import window.GameWindow;

import javax.swing.*;
import java.awt.*;

import java.util.Timer;
import java.util.TimerTask;


public class SceneTransition extends JPanel {

    public SceneTransition() {

        setBackground(new Color(0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    public void load(GameWindow gameWindow) {
        Timer timer = new Timer();


        TimerTask task = new TimerTask(){
            int i = 3;

            @Override public void run(){
                if(i>0){
                    System.out.println(i);
                    i--;
                }
                else{
//                    gameCanvas.setVisible(true);
//                    setVisible(false);

                    GameCanvas gameCanvas = new GameCanvas();
                    gameWindow.add(gameCanvas);
                    gameCanvas.start();
                    setVisible(false);
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 10, 10);
    }
}
