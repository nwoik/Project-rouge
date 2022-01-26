package game;

import org.w3c.dom.css.RGBColor;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Protagonist extends GameObject{

    Handler handler;

    public Protagonist(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //Movement. Implemented in handler instead of here for efficiency -
        //will only be used once so no need to copy it multiple times
        if(handler.isUp()) velY = -5;
        else if (!handler.isDown()) velY = 0;

        if (handler.isDown()) velY=5;
        else if (!handler.isUp()) velY = 0;

        if (handler.isRight()) velX = 5;
        else if (!handler.isLeft()) velX = 0;

        if (handler.isLeft()) velX = -5;
        else if (!handler.isRight()) velX = 0;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(240, 198, 76));
        g.fillRect(x,y,32,48);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48); //useful for collision for future
    }

}

