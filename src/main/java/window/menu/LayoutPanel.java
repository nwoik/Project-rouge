package window.menu;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class LayoutPanel extends JPanel {
    private final CardLayout cardLayout = new CardLayout();

    public LayoutPanel() {
        setLayout(cardLayout);
        MenuPanel menuPanel = new MenuPanel();
        add(menuPanel, MenuPanel.NAME);
        SettingsPanel settingsPanel = new SettingsPanel();
        add(settingsPanel, SettingsPanel.NAME);
    }

    public void showCard(String name) {
        cardLayout.show(this, name);
    }
}
