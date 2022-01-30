package object;

import core.SpriteSheet;
import object.GameObject;
import object.Handler;
import object.ID;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Protagonist extends GameObject {

    Handler handler;

    private BufferedImage protagonistImage;

    public Protagonist(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;

        protagonistImage = ss.grabImage(1,3,64,96);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        collision();

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

    //check collision with block
    private void collision(){
        try{
            for(int i =0; i < handler.object.size(); i++){
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Block){
                    if (getBounds().intersects(tempObject.getBounds())){
                        x += velX * -1;
                        y += velY * -1;
                    }
                }
            }}
        catch (IndexOutOfBoundsException e) {
        }
    }

    @Override
    public void render(Graphics g) {
    g.drawImage(protagonistImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,96); //useful for collision for future
    }

}

