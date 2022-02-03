package object;

import core.SpriteSheet;
import object.GameObject;
import object.Handler;
import object.ID;
import window.Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    public int height;
    public int width;
    Handler handler;
    private BufferedImage playerImage;

    public Player(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.width = 64;
        this.height = 96;
        this.handler = handler;
        this.movementSpeed = 5;

        playerImage = ss.grabImage(1,3,64,96);
    }

    @Override
    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        collision();

        if(this.up) velY = -6;
        else if (!this.down) velY = 0;

        if (this.down) velY=6;
        else if (!this.up) velY = 0;

        if (this.right) velX = 6;
        else if (!this.left) velX = 0;

        if (this.left) velX = -6;
        else if (!this.right) velX = 0;

    }

    //check collision with block
    private void collision(){
        try{
            for(GameObject gameObject : handler.objects){
                if (gameObject.getId() == ID.Block){
                    if (this.getBoundsHorizontal().intersects(gameObject.getBounds())) {
                        if (velX > 0) {
                            velX = 0;
                            this.x = gameObject.x - 64;
                        }
                        else if (velX < 0) {
                            velX = 0;
                            this.x = gameObject.x + 64;
                        }
                    }
                    if (this.getBoundsVertical().intersects(gameObject.getBounds())) {
                        if (velY > 0) {
                            velY = 0;
                            this.y = gameObject.y - 96;
                        }
                        else if (velY < 0) {
                            velY = 0;
                            this.y = gameObject.y + 96;
                        }
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException e) {
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerImage, x, y, null);
    }

    @Override
    protected Rectangle getBounds() {
        return null;
    }

    public Rectangle getBoundsVertical() {
        float bx = this.x;
        float by = this.y + this.velY;
        float bw = this.width;
        float bh = this.height + this.velY;

        return new Rectangle((int) bx, (int) by, (int) bw, (int) bh); //useful for collision for future
    }

    public Rectangle getBoundsHorizontal() {
        float bx = this.x + this.velX;
        float by = this.y;
        float bw = this.width + this.velY;
        float bh = this.height;

        return new Rectangle((int) bx, (int) by, (int) bw, (int) bh); //useful for collision for future
    }

}