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
    private final int movementSpeed2;
    private Color colour = Color.black;
    public boolean inRange = false;
    private int knockBackFrames = 7;
    private String knockBackDirection;

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
        this.movementSpeed2 = this.movementSpeed1 * 2;
        this.width = 64;
        this.height = 64;

        this.standingFacingDown.add(spriteSheet.grabImage(1, 1, 64, 96));
        this.standingFacingLeft.add(spriteSheet.grabImage(2, 1, 64, 96));
        this.standingFacingUp.add(spriteSheet.grabImage(3, 1, 64, 96));
        this.standingFacingRight.add(spriteSheet.grabImage(4, 1, 64, 96));

        int alignmentY = -32;
        int framedelay = 2;
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

        if (knockBackFrames > 6) {
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
        }
        else {
            if (knockBackFrames == 0) {
                this.movementSpeed = 10;
            }

            switch(knockBackDirection){
                case "up" -> subY(this.movementSpeed);
                case "down" -> addY(this.movementSpeed);
                case "left" -> subX(this.movementSpeed);
                case "right" -> addX(this.movementSpeed);
            }
            if (knockBackFrames == 6){
                this.movementSpeed = 6;
            }
            knockBackFrames += 1;
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
        this.inRange = false;
    }

//    private void knockBack

    //check collision with block
    private void collision(){
        try{
            int tempX = 0;
            int tempY = 0;
            if (this.inRange) {

                for (GameObject gameObject : handler.enemy) {
                    if (getBounds().intersects(gameObject.getBounds())) {
                        if (getBoundsSmall(x + movementSpeed2, y, this.width - 2 * movementSpeed2, movementSpeed2).intersects(gameObject.getBounds())) {
                            tempY = movementSpeed;
                            knockBackDirection = "down";
                        }
                        if (getBoundsSmall(x + movementSpeed2, y + this.height - movementSpeed2, this.width - 2 * movementSpeed2, movementSpeed2).intersects(gameObject.getBounds())) {
                            tempY = -(movementSpeed);
                            knockBackDirection = "up";
                        }
                        if (getBoundsSmall(x, y + movementSpeed2, movementSpeed2, this.height - 2 * movementSpeed2).intersects(gameObject.getBounds())) {
                            tempX = movementSpeed;
                            knockBackDirection = "right";
                        }
                        if (getBoundsSmall(x + this.width - movementSpeed2, y + movementSpeed2, movementSpeed2, this.height - 2 * movementSpeed2).intersects(gameObject.getBounds())) {
                            tempX = -(movementSpeed);
                            knockBackDirection = "left";
                        }
                        knockBackFrames = 0;
                    }
                }
            }
            for(GameObject gameObject : handler.block){
                if (getBounds().intersects(gameObject.getBounds())){
                        if (getBoundsSmall(x + movementSpeed2, y, this.width - 2* movementSpeed2, movementSpeed2).intersects(gameObject.getBounds())){
                            tempY = movementSpeed;
                        }
                        if (getBoundsSmall(x + movementSpeed2, y + this.height - movementSpeed2, this.width - 2* movementSpeed2, movementSpeed2).intersects(gameObject.getBounds())){
                            tempY = -(movementSpeed);
                        }
                        if (getBoundsSmall(x, y + movementSpeed2, movementSpeed2, this.height - 2*movementSpeed2).intersects(gameObject.getBounds())){
                            tempX = movementSpeed;
                        }
                        if (getBoundsSmall(x + this.width - movementSpeed2 , y + movementSpeed2, movementSpeed2, this.height - 2*movementSpeed2).intersects(gameObject.getBounds())){
                            tempX = -(movementSpeed);
                        }
                }
            }

            this.x += tempX;
            this.y += tempY;
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

    public Rectangle2D getBoundsSmall(int x,int y,int width,int height) {
        return new Rectangle2D.Float(x,y,width,height);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(x, y, this.width, this.height); //useful for collision for future
    }

    @Override
    public Ellipse2D getBoundsFOV() {
        return null;
    }

//    public int xOffset(int offset){
//        return this.x + offset;
//    }
//
//    public int yOffset(int offset){
//        return this.y + offset;
//    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}