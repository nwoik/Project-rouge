package window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import core.BufferedImageLoader;

public class MenuCanvas extends JPanel {
    public Panel panel;
    private BufferedImageLoader bufferLoader;
    Game game;
    Graphics2D graphics2D;
    GameWindow gameWindow;
    Font gameFont;
    Font exceptionFont = new Font("Times New Roman", Font.PLAIN, 120);
    public int commandNum = 0;

    //Screen Settings
    final int defaultTileSize = 16; // 16 bit
    final int scale = 3;
    final int tileSize = defaultTileSize * scale; //Larger image size with 16 bit look
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public MenuCanvas(Panel panel) {
        this.bufferLoader = new BufferedImageLoader();
        this.panel = panel;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/GameFont-Regular.ttf");
            assert is != null;
            gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            gameFont = exceptionFont;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.graphics2D = (Graphics2D)g;
        drawTitleScreen();
    }


    public void drawTitleScreen() {
        //Title Name
        graphics2D.setFont(gameFont.deriveFont(64F));
        for (int xx=0; xx<128*70; xx+=128){
            for(int yy = 0; yy<128*70; yy+=128){
                graphics2D.drawImage(this.bufferLoader.loadImage("/MenuBackground/Wood Texture Bottom side.png"), xx,yy, 128, 128, null);
            }
        }
        graphics2D.drawString("Once Upon a Dungeon", getXForCenteredText("Once Upon a Dungeon"), tileSize+0.5F);
        //Selectable
        graphics2D.drawString("New Game", getXForCenteredText("New Game"), tileSize*3);
        graphics2D.drawString("Settings", getXForCenteredText("Settings"), tileSize*4+10F);
        graphics2D.drawString("Exit", getXForCenteredText("Exit"), tileSize*5+20F);

        switch (commandNum) {
            case 0 -> graphics2D.drawString(">", getXForCenteredText("New Game")-tileSize, tileSize*3);
            case 1 -> graphics2D.drawString(">", getXForCenteredText("Settings")-tileSize, tileSize*4+10F);
            case 2 -> graphics2D.drawString(">", getXForCenteredText("Exit")-tileSize, tileSize*5+20F);
        }
    }

    // Centers the text
    public int getXForCenteredText(String text) {
        int length = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return screenWidth/2 - length/2;
    }
}
