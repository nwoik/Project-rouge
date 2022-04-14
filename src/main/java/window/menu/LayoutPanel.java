package window.menu;

import core.BufferedImageLoader;
import window.Game;
import window.GameCanvas;
import window.GameWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.Clip;
import javax.swing.*;

public class LayoutPanel extends JPanel {
    private GameWindow gameWindow;
    private final CardLayout cardLayout = new CardLayout();
    public Menu menu;
    private Settings settings = new Settings(this);

    public LayoutPanel(GameWindow gameWindow) throws Exception{
        this.gameWindow = gameWindow;
        menu = new Menu(this, this.gameWindow, settings);
        setLayout(cardLayout);
        add(menu, Menu.class.toString());
        add(settings, Settings.class.toString());
    }

    public void showCard(String name) {
        cardLayout.show(this, name);
    }
}