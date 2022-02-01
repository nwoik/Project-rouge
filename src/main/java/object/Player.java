package object;

import core.SpriteSheet;
import object.GameObject;
import object.Handler;
import object.ID;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    Handler handler;
    private BufferedImage playerImage;

    public Player(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.movementSpeed = 5;


        playerImage = ss.grabImage(1,3,64,96);
    }

    @Override
    public void tick() {
        if (this.right) {
            addX(this.movementSpeed);
            System.out.println(x +" "+ y);
        }
        if (this.left) {
            subX(this.movementSpeed);
        }
        if (this.down) {
            addY(this.movementSpeed);
        }
        if (this.up) {
            subY(this.movementSpeed);
        }
        collision();
    }

    //check collision with block
    private void collision(){
        try{
            for(int i =0; i < handler.object.size(); i++){
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Block){
                    if (((this.y >= tempObject.getY() - 96) && (this.y <= tempObject.getY() + 64)) && (this.x <= tempObject.getX() + 64)) {
                        this.left = false;

                    }
//                    if (this.x + this.width >= WIDTH) {
//                        this.right = false;
//                        this.x = WIDTH - this.width;
//                    }
//                    if (this.y <= 0) {
//                        this.up = false;
//                        this.y = 0;
//                    }
//                    if (this.y + this.height >= HEIGHT) {
//                        this.down = false;
//                        this.y = HEIGHT - this.height;
//                    }
                }
            }}
        catch (IndexOutOfBoundsException e) {
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerImage, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,64,96); //useful for collision for future
    }

    @Override
    public void addX(int value) {
        this.x += value;
    }

    @Override
    public void subX(int value) {
        this.x -= value;
    }

    @Override
    public void addY(int value) {
        this.y += value;
    }

    @Override
    public void subY(int value) {
        this.y -= value;
    }
}