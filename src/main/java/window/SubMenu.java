package window;

import audio.AudioHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubMenu extends JPanel {
    private SubWindow subWindow;
    private GameCanvas gameCanvas;
    public SubMenu(SubWindow subWindow, GameCanvas gameCanvas) {
        this.subWindow = subWindow;
        this.gameCanvas = gameCanvas;

        setBackground(new Color(255, 200, 200));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JButton returnButton = new JButton("Return");
        JButton exitButton = new JButton("Exit");

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMenu();
                gameCanvas.comeBack();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subWindow.dispose();
            }
        });

        add(Box.createRigidArea(new Dimension(0, 400)));
        add(returnButton);
        add(Box.createRigidArea(new Dimension(0, 100)));
        add(exitButton);

        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void closeMenu() {
        this.subWindow.dispose();
        this.gameCanvas.stopped = !this.gameCanvas.stopped;
    }
}
