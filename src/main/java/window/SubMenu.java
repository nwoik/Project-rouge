package window;

import audio.AudioHandler;
import window.menu.LayoutPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SubMenu extends JPanel {
    public SubWindow subWindow;
    public GameCanvas gameCanvas;
    private GameWindow gameWindow;
    private LayoutPanel layoutPanel;

    public SubMenu(SubWindow subWindow, GameCanvas gameCanvas, GameWindow gameWindow, LayoutPanel layoutPanel) {
        this.subWindow = subWindow;
        this.gameCanvas = gameCanvas;
        this.gameWindow = gameWindow;
        this.layoutPanel = layoutPanel;

        setFocusable(true);
        requestFocus();
        addKeyListener(new EscInput(this));

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
                gameWindow.remove(gameCanvas);
                layoutPanel.setVisible(true);
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
