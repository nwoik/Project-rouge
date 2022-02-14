package object;

import animations.Animation;
import animations.Frame;
import core.SpriteSheet;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;
    private final int movementSpeed1;
    private Color colour = Color.green;

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

    private List<BufferedImage> attackingDown = new ArrayList<BufferedImage>();
    private List<BufferedImage> attackingUp = new ArrayList<BufferedImage>();
    private List<BufferedImage> attackingLeft = new ArrayList<BufferedImage>();
    private List<BufferedImage> attackingRight = new ArrayList<BufferedImage>();

    private Animation attackDown;
    private Animation attackUp;
    private Animation attackLeft;
    private Animation attackRight;


    public Player(int x, int y, ID id, Handler handler, SpriteSheet spriteSheet) {
        super(x, y, id, spriteSheet);
        this.handler = handler;
        this.movementSpeed = 6;
        this.movementSpeed1 = this.movementSpeed +1;
        this.width = 64;
        this.height = 64;

        int alignmentY = -32;
        int framedelay = 2;

        this.standingFacingDown.add(spriteSheet.grabImage(1, 1, 64, 96));
        this.standingFacingLeft.add(spriteSheet.grabImage(2, 1, 64, 96));
        this.standingFacingUp.add(spriteSheet.grabImage(3, 1, 64, 96));
        this.standingFacingRight.add(spriteSheet.grabImage(4, 1, 64, 96));
     
        this.standFacingDown = new Animation(this.standingFacingDown, framedelay, 0, alignmentY, true);
        this.standFacingLeft = new Animation(this.standingFacingLeft, framedelay, 0, alignmentY, true);
        this.standFacingUp = new Animation(this.standingFacingUp, framedelay, 0, alignmentY, true);
        this.standFacingRight = new Animation(this.standingFacingRight, framedelay, 0, alignmentY, true);

        fillAnimationList(spriteSheet, this.walkingUp, 1, 3, 2, 72, 100, 10);
        fillAnimationList(spriteSheet, this.walkingDown, 1, 9, 2, 72, 96, 10);
        fillAnimationList(spriteSheet, this.walkingLeft, 1, 5, 2, 88, 96, 10);
        fillAnimationList(spriteSheet, this.walkingRight, 1, 7, 2, 82, 96, 10);

        this.walkUp = new Animation(this.walkingUp, framedelay, -4, alignmentY, true);
        this.walkDown = new Animation(this.walkingDown, framedelay, -4, alignmentY, true);
        this.walkLeft = new Animation(this.walkingLeft, framedelay, 0, alignmentY, true);
        this.walkRight = new Animation(this.walkingRight, framedelay, -16, alignmentY, true);

        //for seperated sword that I could'nt get workin
        // fillAnimationList(spriteSheet, this.attackingDown, 2, 11, 3, 80, 100, 5);
        // fillAnimationList(spriteSheet, this.attackingUp, 2, 15, 3, 88, 96, 5);
        // fillAnimationList(spriteSheet, this.attackingLeft, 2, 19, 3, 72, 100, 5);
        // fillAnimationList(spriteSheet, this.attackingRight, 2, 24, 3, 72, 100, 5);

        // Sword combined
        fillAnimationList(spriteSheet, this.attackingDown, 1, 11, 3, 192, 128, 5);
        fillAnimationList(spriteSheet, this.attackingUp, 1, 13, 3, 192, 192, 5);
        fillAnimationList(spriteSheet, this.attackingLeft, 1, 16, 3, 192, 192, 5);
        fillAnimationList(spriteSheet, this.attackingRight, 2, 20, 3, 128, 192, 5);

        this.attackDown = new Animation(this.attackingDown, framedelay, -64, alignmentY, true);
        this.attackUp = new Animation(this.attackingUp, framedelay, -64, -96, true);
        this.attackLeft = new Animation(this.attackingLeft, framedelay, -64, -96, true);
        this.attackRight = new Animation(this.attackingRight, framedelay, 0, alignmentY, true);

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
        if (this.attack) {
            if (this.animation == this.standFacingDown || this.animation == this.walkDown) {
                this.setAnimation(this.attackDown);
                this.animation.start();
                return;
            }
            else if (this.animation.stop && this.animation == this.attackDown) {
                this.attack = false;
                this.setAnimation(standFacingDown);
            }
            if (this.animation == this.standFacingUp || this.animation == this.walkUp) {
                this.setAnimation(this.attackUp);
                this.animation.start();
                return;
            }
            else if (this.animation.stop && this.animation == this.attackUp) {
                this.attack = false;
                this.setAnimation(standFacingUp);
            }
            if (this.animation == this.standFacingLeft || this.animation == this.walkLeft) {
                this.setAnimation(this.attackLeft);
                this.animation.start();
                return;
            }
            else if (this.animation.stop && this.animation == this.attackLeft) {
                this.attack = false;
                this.setAnimation(standFacingLeft);
            }
            if (this.animation == this.standFacingRight || this.animation == this.walkRight) {
                this.setAnimation(this.attackRight);
                this.animation.start();
                return;
            }
            else if (this.animation.stop && this.animation == this.attackRight) {
                this.attack = false;
                this.setAnimation(standFacingRight);
            }
        }
        if (this.right & !attack) {
            addX(this.movementSpeed);
        }
        if (this.left & !attack) {
            subX(this.movementSpeed);
        }
        if (this.down & !attack) {
            addY(this.movementSpeed);
        }
        if (this.up & !attack) {
            subY(this.movementSpeed);
        }

        if (leftPressed & !attack){
            this.setAnimation(this.walkLeft);
            this.animation.start();
            this.left = true;
        }
        if (rightPressed & !attack){
            this.setAnimation(this.walkRight);
            this.animation.start();
            this.right = true;
        }
        if (upPressed & !attack){
            this.setAnimation(this.walkUp);
            this.animation.start();
            this.up = true;
        }
        if (downPressed & !attack){
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
            for(GameObject gameObject : handler.block) {
                if (getBounds().intersects(gameObject.getBounds())) {
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
                                (this.x + (this.width - movementSpeed1) <= gameObject.getX() && this.x + this.width >= gameObject.getX())) {
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
        g.drawImage(this.animation.getSprite(), x + this.animation.getOffsetX(), y + this.animation.getOffsetY(), null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(colour);
        g.drawRect(x, y, this.width, this.height);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, this.width, this.height); //useful for collision for future
    }

    @Override
    public Ellipse2D getBoundsFOV() {
        return null;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}