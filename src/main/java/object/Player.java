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
    private final int alignmentY = -32;

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

        this.standingFacingDown.add(spriteSheet.grabImage(1, 1, 64, 96));
        this.standingFacingLeft.add(spriteSheet.grabImage(2, 1, 64, 96));
        this.standingFacingUp.add(spriteSheet.grabImage(3, 1, 64, 96));
        this.standingFacingRight.add(spriteSheet.grabImage(4, 1, 64, 96));

        this.standFacingDown = new Animation(this.standingFacingDown, framedelay, 0, alignmentY);
        this.standFacingLeft = new Animation(this.standingFacingLeft, framedelay, 0, alignmentY);
        this.standFacingUp = new Animation(this.standingFacingUp, framedelay, 0, alignmentY);
        this.standFacingRight = new Animation(this.standingFacingRight, framedelay, 0, alignmentY);

        fillAnimationList(spriteSheet, this.walkingUp, 1, 3, 2, 72, 100, 10);
        fillAnimationList(spriteSheet, this.walkingDown, 1, 9, 2, 72, 96, 10);
        fillAnimationList(spriteSheet, this.walkingLeft, 1, 5, 2, 88, 96, 10);
        fillAnimationList(spriteSheet, this.walkingRight, 1, 7, 2, 82, 96, 10);

        this.walkUp = new Animation(this.walkingUp, framedelay, -4, alignmentY);
        this.walkDown = new Animation(this.walkingDown, framedelay, -4, alignmentY);
        this.walkLeft = new Animation(this.walkingLeft, framedelay, 0, alignmentY);
        this.walkRight = new Animation(this.walkingRight, framedelay, -16, alignmentY);

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
                                    (this.y + movementSpeed1 >= gameObject.getY() + gameObject.getHeight() && this.y <= gameObject.getY() + gameObject.getHeight())){
                                this.up = false;
                                this.y += movementSpeed;
                            }
                            else if (((this.x + movementSpeed1 >= gameObject.getX() && this.x + movementSpeed1 <= gameObject.getX() + gameObject.getHeight())
                                ||(this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - movementSpeed1) <= gameObject.getX() + gameObject.getHeight()))&&
                                    (this.y + (this.height - movementSpeed1) <= gameObject.getY() && this.y + this.height >= gameObject.getY())){
                                this.down = false;
                                this.y -= movementSpeed;
                            }
                            else if (((this.y + movementSpeed1 >= gameObject.getY() && this.y + movementSpeed1 <= gameObject.getY() + gameObject.getWidth())
                                || (this.y + (this.height - movementSpeed1) >= gameObject.getY() && this.y + (this.height - movementSpeed1) <= gameObject.getY() + gameObject.getWidth()))&&
                                    (this.x + movementSpeed1 >= gameObject.getX() + gameObject.getWidth() && this.x <= gameObject.getX() + gameObject.getWidth())){
                                this.left = false;
                                this.x += movementSpeed;

                        }
                        else if (((this.y + movementSpeed1 >= gameObject.getY() && this.y + movementSpeed1 <= gameObject.getY() + gameObject.getWidth())
                            || (this.y + (this.height - movementSpeed1) >= gameObject.getY() && this.y + (this.height - movementSpeed1) <= gameObject.getY() + gameObject.getWidth()))&&
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
        g.drawImage(this.animation.getSprite(), x + this.animation.getOffsetX(), y + this.animation.getOffsetY(), null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(x, y, this.width, this.height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, this.width, this.height); //useful for collision for future
    }

    public int xOffset(int offset){
        return this.x + offset;
    }

    public int yOffset(int offset){
        return this.y + offset;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}