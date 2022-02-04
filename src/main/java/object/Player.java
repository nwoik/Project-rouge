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
    private int height;
    private int width;
    private int movementSpeed1;
    public int yOffset;

    public Player(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.movementSpeed = 5;
        this.movementSpeed1 = 6;
        this.width = 64;
        this.height = 64;


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
    /*
    Leave this here in case collision fucks up - last stable version
    if ( ((this.x + movementSpeed >= tempObject.getX() && this.x + movementSpeed <= tempObject.getX() + tempObject.getSize())
                                ||(this.x + (this.width - movementSpeed1) >= tempObject.getX() && this.x + (this.width - movementSpeed1) <= tempObject.getX() + tempObject.getSize()))&&
                                    (this.y + movementSpeed >= tempObject.getY() + tempObject.getSize() && this.y <= tempObject.getY() + tempObject.getSize())){
                                this.up = false;
                                this.y += movementSpeed;
                            }
                            else if (((this.x + movementSpeed >= tempObject.getX() && this.x + movementSpeed <= tempObject.getX() + tempObject.getSize())
                                ||(this.x + (this.width - movementSpeed1) >= tempObject.getX() && this.x + (this.width - movementSpeed1) <= tempObject.getX() + tempObject.getSize()))&&
                                    (this.y + (this.height - movementSpeed1) <= tempObject.getY() && this.y + this.height >= tempObject.getY())){
                                this.down = false;
                                this.y -= movementSpeed;
                            }
                            else if (((this.y + movementSpeed >= tempObject.getY() && this.y + movementSpeed <= tempObject.getY() + tempObject.getSize())
                                || (this.y + (this.height - movementSpeed1) >= tempObject.getY() && this.y + (this.height - movementSpeed1) <= tempObject.getY() + tempObject.getSize()))&&
                                    (this.x + movementSpeed >= tempObject.getX() + tempObject.getSize() && this.x <= tempObject.getX() + tempObject.getSize())){
                                this.left = false;
                                this.x += movementSpeed;
                            }
                            else if (((this.y + movementSpeed >= tempObject.getY() && this.y + movementSpeed <= tempObject.getY() + tempObject.getSize())
                                || (this.y + (this.height - movementSpeed1) >= tempObject.getY() && this.y + (this.height - movementSpeed1) <= tempObject.getY() + tempObject.getSize()))&&
                                        (this.x + (this.width - movementSpeed1) <= tempObject.getX() && this.x + this.width >= tempObject.getX())){
                                    this.right = false;
                                    this.x -= movementSpeed;
                                }

                            else{
                                if (this.x + movementSpeed <= tempObject.getX() + tempObject.getSize() && this.x + (2*movementSpeed) >= tempObject.getX() + tempObject.getSize()){
                                    this.x += movementSpeed;
                                }
                                else if (this.x + (this.width - movementSpeed1) >= tempObject.getX() && this.x + (this.width - (movementSpeed1*2)) <= tempObject.getX()){
                                    this.x -= 2*movementSpeed;
                                }
                            }
     */
    //check collision with block
    private void collision(){
        try{
            for(int i =0; i < handler.object.size(); i++){
                GameObject tempObject = handler.object.get(i);

                if (tempObject.getId() == ID.Block){
                    if (getBounds().intersects(tempObject.getBounds())){
                        if ( ((this.x + movementSpeed1 >= tempObject.getX() && this.x + movementSpeed1 <= tempObject.getX() + tempObject.getSize())
                                ||(this.x + (this.width - movementSpeed1) >= tempObject.getX() && this.x + (this.width - movementSpeed1) <= tempObject.getX() + tempObject.getSize()))&&
                                    (this.yOffset() + movementSpeed1 >= tempObject.getY() + tempObject.getSize() && this.yOffset() <= tempObject.getY() + tempObject.getSize())){
                                this.up = false;
                                this.y += movementSpeed;
                            }
                            else if (((this.x + movementSpeed1 >= tempObject.getX() && this.x + movementSpeed1 <= tempObject.getX() + tempObject.getSize())
                                ||(this.x + (this.width - movementSpeed1) >= tempObject.getX() && this.x + (this.width - movementSpeed1) <= tempObject.getX() + tempObject.getSize()))&&
                                    (this.yOffset() + (this.height - movementSpeed1) <= tempObject.getY() && this.yOffset() + this.height >= tempObject.getY())){
                                this.down = false;
                                this.y -= movementSpeed;
                            }
                            else if (((this.yOffset() + movementSpeed1 >= tempObject.getY() && this.yOffset() + movementSpeed1 <= tempObject.getY() + tempObject.getSize())
                                || (this.yOffset() + (this.height - movementSpeed1) >= tempObject.getY() && this.yOffset() + (this.height - movementSpeed1) <= tempObject.getY() + tempObject.getSize()))&&
                                    (this.x + movementSpeed1 >= tempObject.getX() + tempObject.getSize() && this.x <= tempObject.getX() + tempObject.getSize())){
                                this.left = false;
                                this.x += movementSpeed;

                        }
                            else if (((this.yOffset() + movementSpeed1 >= tempObject.getY() && this.yOffset() + movementSpeed1 <= tempObject.getY() + tempObject.getSize())
                                || (this.yOffset() + (this.height - movementSpeed1) >= tempObject.getY() && this.yOffset() + (this.height - movementSpeed1) <= tempObject.getY() + tempObject.getSize()))&&
                                        (this.x + (this.width - movementSpeed1) <= tempObject.getX() && this.x + this.width >= tempObject.getX())){
                                    this.right = false;
                                    this.x -= movementSpeed;
                                }

                            else{
                                if (this.x + movementSpeed1 <= tempObject.getX() + tempObject.getSize() && this.x + (2*movementSpeed1) >= tempObject.getX() + tempObject.getSize()){
                                    this.x += 2*movementSpeed;

                                }
                                else if (this.x + (this.width - movementSpeed1) >= tempObject.getX() && this.x + (this.width - (movementSpeed1*2)) <= tempObject.getX()){
                                    this.x -= 2*movementSpeed;
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
        g.drawRect(x,y + 32,64,64);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y + 32,64,64); //useful for collision for future
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

    public int yOffset(){
        return this.y + 32;
    }
}