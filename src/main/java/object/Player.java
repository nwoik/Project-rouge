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

    private BufferedImage playerImage;

    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;
    private final int movementSpeed1;

    private BufferedImage[] walking;

    public Player(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.movementSpeed = 6;
        this.movementSpeed1 = this.movementSpeed +1;
        this.width = 64;
        this.height = 64;
        this.offset = 32;

        playerImage = ss.grabImage(0,0,16,24);
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
            for(GameObject gameObject : handler.object){
                if (gameObject.getId() == ID.Block){
                    if (getBounds().intersects(gameObject.getBounds())){
                        System.out.println(gameObject.getHeight());
                        if ( ((this.x + movementSpeed1 >= gameObject.getX() && this.x + movementSpeed1 <= gameObject.getX() + gameObject.getHeight())
                                ||(this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - movementSpeed1) <= gameObject.getX() + gameObject.getHeight()))&&
                                    (this.yOffset() + movementSpeed1 >= gameObject.getY() + gameObject.getHeight() && this.yOffset() <= gameObject.getY() + gameObject.getHeight())){
                                this.up = false;
                                this.y += movementSpeed;
                            }
                            else if (((this.x + movementSpeed1 >= gameObject.getX() && this.x + movementSpeed1 <= gameObject.getX() + gameObject.getHeight())
                                ||(this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - movementSpeed1) <= gameObject.getX() + gameObject.getHeight()))&&
                                    (this.yOffset() + (this.height - movementSpeed1) <= gameObject.getY() && this.yOffset() + this.height >= gameObject.getY())){
                                this.down = false;
                                this.y -= movementSpeed;
                            }
                            else if (((this.yOffset() + movementSpeed1 >= gameObject.getY() && this.yOffset() + movementSpeed1 <= gameObject.getY() + gameObject.getWidth())
                                || (this.yOffset() + (this.height - movementSpeed1) >= gameObject.getY() && this.yOffset() + (this.height - movementSpeed1) <= gameObject.getY() + gameObject.getWidth()))&&
                                    (this.x + movementSpeed1 >= gameObject.getX() + gameObject.getWidth() && this.x <= gameObject.getX() + gameObject.getWidth())){
                                this.left = false;
                                this.x += movementSpeed;

                        }
                            else if (((this.yOffset() + movementSpeed1 >= gameObject.getY() && this.yOffset() + movementSpeed1 <= gameObject.getY() + gameObject.getWidth())
                                || (this.yOffset() + (this.height - movementSpeed1) >= gameObject.getY() && this.yOffset() + (this.height - movementSpeed1) <= gameObject.getY() + gameObject.getWidth()))&&
                                        (this.x + (this.width - movementSpeed1) <= gameObject.getX() && this.x + this.width >= gameObject.getX())){
                                    this.right = false;
                                    this.x -= movementSpeed;
                                }

                            else{
                                if (this.x + movementSpeed1 <= gameObject.getX() + gameObject.getWidth() && this.x + (2*movementSpeed1) >= gameObject.getX() + gameObject.getWidth()){
                                    this.x += 2*movementSpeed;

                                }
                                else if (this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - (movementSpeed1*2)) <= gameObject.getX()){
                                    this.x -= 2*movementSpeed;
                                }
                            }


                    }
                }
            }}
        catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerImage, x, y, null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(x, yOffset(), this.width, this.width);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, yOffset(), this.width, this.width); //useful for collision for future
    }

    public int yOffset(){
        return this.y + this.offset;
    }
}