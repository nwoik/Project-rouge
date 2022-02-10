package object;

import core.SpriteSheet;
import object.GameObject;
import object.Handler;
import object.ID;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;

public class Player extends GameObject {

    private BufferedImage playerImage;

    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;
    private final int movementSpeed1;
    private Color colour = Color.black;

    public Player(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.movementSpeed = 6;
        this.movementSpeed1 = this.movementSpeed +1;
        this.width = 64;
        this.height = 64;
        this.offset = 32;


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
            for(GameObject gameObject : handler.block){
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


        checkEnemyDetection();
        }
        catch (IndexOutOfBoundsException ignored) {
        }
    }

    public void checkEnemyDetection(){
        for (GameObject gameObject : handler.enemy){
            if (gameObject.getBoundsFOV().intersects(getBounds())){
                colour = Color.magenta;
                return;
            }
            colour = Color.black;
            }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerImage, x, y, null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(colour);
        g.drawRect(x, yOffset(), this.width, this.height);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, yOffset(), this.width, this.height); //useful for collision for future
    }

    @Override
    public Ellipse2D getBoundsFOV() {
        return null;
    }

    public int yOffset(){
        return this.y + this.offset;
    }
}