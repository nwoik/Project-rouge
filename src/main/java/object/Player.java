package object;

import animations.Animation;
import animations.Frame;
import core.SpriteSheet;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;
    private final int movementSpeed1;
    private int framedelay = 2;

    public Animation animation;

    private List<BufferedImage> standingFacingDown = new ArrayList<BufferedImage>();
    private List<BufferedImage> standingFacingLeft = new ArrayList<BufferedImage>();
    private List<BufferedImage> standingFacingUp = new ArrayList<BufferedImage>();
    private List<BufferedImage> standingFacingRight = new ArrayList<BufferedImage>();

    public Animation standFacingDown;
    public Animation standFacingLeft;
    public Animation standFacingUp;
    public Animation standFacingRight;

    private List<BufferedImage> walkingUp = new ArrayList<BufferedImage>();
    private List<BufferedImage> walkingLeft = new ArrayList<BufferedImage>();
    private List<BufferedImage> walkingRight = new ArrayList<BufferedImage>();
    private List<BufferedImage> walkingDown = new ArrayList<BufferedImage>();

    private Animation walkDown;
    private Animation walkUp;
    private Animation walkLeft;
    private Animation walkRight;


    public Player(int x, int y, ID id, Handler handler, SpriteSheet spriteSheet) {
        super(x, y, id, spriteSheet);
        this.handler = handler;
        this.movementSpeed = 6;
        this.movementSpeed1 = this.movementSpeed +1;
        this.width = 64;
        this.height = 64;
        this.offset = 32;

        this.standingFacingDown.add(spriteSheet.grabImage(1, 1, 64, 96));
        this.standingFacingLeft.add(spriteSheet.grabImage(2, 1, 64, 96));
        this.standingFacingUp.add(spriteSheet.grabImage(3, 1, 64, 96));
        this.standingFacingRight.add(spriteSheet.grabImage(4, 1, 64, 96));

        this.standFacingDown = new Animation(this.standingFacingDown, framedelay);
        this.standFacingLeft = new Animation(this.standingFacingLeft, framedelay);
        this.standFacingUp = new Animation(this.standingFacingUp, framedelay);
        this.standFacingRight = new Animation(this.standingFacingRight, framedelay);

        fillAnimationList(spriteSheet, this.walkingUp, 1, 3, 2, 72, 100, 10);
        fillAnimationList(spriteSheet, this.walkingDown, 1, 9, 2, 72, 96, 10);
        fillAnimationList(spriteSheet, this.walkingLeft, 1, 5, 2, 82, 96, 10);
        fillAnimationList(spriteSheet, this.walkingRight, 1, 7, 2, 82, 96, 10);

        this.walkUp = new Animation(this.walkingUp, framedelay);
        this.walkDown = new Animation(this.walkingDown, framedelay);
        this.walkLeft = new Animation(this.walkingLeft, framedelay);
        this.walkRight = new Animation(this.walkingRight, framedelay);


        this.animation = this.standFacingDown;
    }

    private void fillAnimationList(SpriteSheet spriteSheet, List<BufferedImage> framesList, int column, int row, int increment, int width, int height, int frameCount) {
        for (int i = 0; i < frameCount; i++) {
            framesList.add(spriteSheet.grabImage(column, row, width, height));
            column += increment;
        }
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
            this.setAnimation(this.walkLeft);
            this.animation.start();
            this.left = true;
        }
        if (rightPressed){
            this.setAnimation(this.walkRight);
            this.animation.start();
            this.right = true;
        }
        if (upPressed){
            this.setAnimation(this.walkUp);
            this.animation.start();
            this.up = true;
        }
        if (downPressed){
            this.setAnimation(this.walkDown);
            this.animation.start();
            this.down = true;
        }

        animation.update();
        collision();
    }

    //check collision with block
    private void collision(){
        try{
            for(GameObject gameObject : handler.object){
                if (gameObject.getId() == ID.Block){
                    if (getBounds().intersects(gameObject.getBounds())){
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

                        else {
                            if (this.x + movementSpeed1 <= gameObject.getX() + gameObject.getWidth() && this.x + (2*movementSpeed1) >= gameObject.getX() + gameObject.getWidth()){
                                this.x += 2*movementSpeed;

                            }
                            else if (this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - (movementSpeed1*2)) <= gameObject.getX()){
                                this.x -= 2*movementSpeed;
                            }
                        }
                    }
                }
            }
        }
        catch (IndexOutOfBoundsException ignored) {
        }
    }



    @Override
    public void render(Graphics g) {
        g.drawImage(this.animation.getSprite(), x, y, null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(x, yOffset(), this.width, this.height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, yOffset(), this.width, this.height); //useful for collision for future
    }

    public int yOffset(){
        return this.y + this.offset;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}