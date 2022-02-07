package window.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import core.BufferedImageLoader;


public class SettingsPanel extends JPanel implements ActionListener {
    private final BufferedImageLoader bufferLoader;
    private Graphics2D graphics2D;
    private Font gameFont;
    private Font exceptionFont = new Font("Times New Roman", Font.PLAIN, 120);

    public SettingsPanel() {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/GameFont-Regular.ttf");
            assert is != null;
            gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            gameFont = exceptionFont;
        }

        this.bufferLoader = new BufferedImageLoader();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.graphics2D = (Graphics2D)g;
        drawScreen();
    }

    public void drawScreen() {
        graphics2D.setFont(gameFont.deriveFont(64F));
        for (int xx=0; xx<128*70; xx+=128){
            for(int yy = 0; yy<128*70; yy+=128){
                graphics2D.drawImage(this.bufferLoader.loadImage("/MenuBackground/Wood Texture Bottom side.png"), xx,yy, 128, 128, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
