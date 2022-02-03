package window.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SwapCardAction extends AbstractAction {
    private String key;
    private LayoutPanel layoutPanel;

    public SwapCardAction(String name, String key, LayoutPanel layoutPanel) {
        super(name);
        this.key = key;
        this.layoutPanel = layoutPanel;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        this.layoutPanel.showCard(key);
    }
}
