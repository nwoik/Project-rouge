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
                    Rectangle gameRect = gameObject.getBounds();
                    Rectangle playerRect = this.getBounds();
                    if (gameRect.getBounds().intersects(playerRect)) {
                        Rectangle intersect = gameRect.intersection(playerRect);
                        if (playerRect.x == intersect.x &&
                                (playerRect.y == intersect.y || playerRect.y < intersect.y + intersect.height)) { // left
                            System.out.println(playerRect.x + " " + intersect.x + "|" );
                            this.velX *= -1;
                            this.left = false;
                        }

                        else if (playerRect.x < intersect.x + intersect.width &&
                                (playerRect.y == intersect.y || playerRect.y < intersect.y + intersect.height)) { // right
                            System.out.println(playerRect.x + " " + intersect.x + "|" );
                            this.velX *= -1;
                            this.right = false;
                        }

                        if (playerRect.y == intersect.y &&
                                (playerRect.x < intersect.x + intersect.width || playerRect.x == intersect.x)) { // up
                            System.out.println(playerRect.y + " " + intersect.y + "|" );
                            this.velY *= -1;
                            this.up = false;
                        }

                        else if (playerRect.y < intersect.y + intersect.height) {
                            System.out.println(playerRect.y + " " + intersect.y + "|" );
                            this.velY *= -1;
                            this.down = false;
                        }

                        this.x += this.velX;
                        this.y += this.velY;
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
    public Rectangle getBounds() {
        return new Rectangle(x,y,this.width,this.height); //useful for collision for future
    }

}