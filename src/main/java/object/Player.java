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

    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;

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

        if (leftPressed){
            this.left = true;
        }
        if (rightPressed){
            this.right = true;
        }
        if (upPressed){
            this.up = true;
        }
        if (downPressed){
            this.down = true;
        }
        collision();
    }

    //check collision with block
    private void collision(){
        try{
            for(int i =0; i < handler.object.size(); i++){
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Block){
                    if (getBounds().intersects(tempObject.getBounds())){
                            if ( ((this.x + 6 >= tempObject.getX() && this.x + 6 <= tempObject.getX() + 64) ||(this.x + 58 >= tempObject.getX() && this.x + 58 <= tempObject.getX() + 64))&&
                                    (this.y + 6 >= tempObject.getY() + 64 && this.y <= tempObject.getY() + 64)){
                                this.up = false;
                                this.y += movementSpeed;
                            }
                            else if (((this.x + 6 >= tempObject.getX() && this.x + 6 <= tempObject.getX() + 64) ||(this.x + 58 >= tempObject.getX() && this.x + 58 <= tempObject.getX() + 64))&&
                                    (this.y + 90 <= tempObject.getY() && this.y + 96 >= tempObject.getY())){
                                this.down = false;
                                this.y -= movementSpeed;
                            }
                            else if (((this.y + 6 >= tempObject.getY() && this.y + 6 <= tempObject.getY() + 64) || (this.y + 90 >= tempObject.getY() && this.y + 90 <= tempObject.getY() + 64))&&
                                    (this.x + 6 >= tempObject.getX() + 64 && this.x <= tempObject.getX() + 64)){
                                this.left = false;
                                this.x += movementSpeed;
                            }
                            else if (((this.y + 6 >= tempObject.getY() && this.y + 6 <= tempObject.getY() + 64) || (this.y + 90 >= tempObject.getY() && this.y + 90 <= tempObject.getY() + 64))&&
                                        (this.x + 58 <= tempObject.getX() && this.x + 64 >= tempObject.getX())){
                                    this.right = false;
                                    this.x -= movementSpeed;
                                }

                            else{
                                if (this.x + 6 <= tempObject.getX() + 64 && this.x + 11 >= tempObject.getX() + 64){
                                    this.x += 2*movementSpeed;
                                    System.out.println("YUPIKAYO");
                                }
                                else if (this.x + 58 >= tempObject.getX() && this.x + 52 <= tempObject.getX()){
                                    this.x -= 2*movementSpeed;
                                    System.out.println("LESSGO");
                                }
                            }


                    }
                }
            }}
        catch (IndexOutOfBoundsException e) {
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerImage, x, y, null);
        g.setColor(Color.red);
        g.drawRect(x,y,64,96);
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