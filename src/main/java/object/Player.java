package object;

import animations.Animation;
import core.SpriteSheet;

import java.awt.Graphics;
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
    private String knockBackDirection = "";

    public boolean dash = false;
    private String dashDirection = "";
    private int dashCooldown = 100;

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
        //check dash cooldown
        if (this.dashCooldown < 100){
            this.dashCooldown++;
            this.dash = false;
        }
        //if dash off cooldown and pressed, check if would collide with wall, if not, dash
        else if (this.dash){
            dash();
        }

        //Player getting knocked back after being hit
        if (this.knockBackFrames > 6) {
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
            if (this.knockBackFrames == 0) {
                this.movementSpeed = 10;
            }

            switch(knockBackDirection){
                case "up" -> subY(this.movementSpeed);
                case "down" -> addY(this.movementSpeed);
                case "left" -> subX(this.movementSpeed);
                case "right" -> addX(this.movementSpeed);
            }
            if (this.knockBackFrames == 6){
                this.movementSpeed = 6;
            }
            this.knockBackFrames += 1;
        }

        //animations for movement
        if (this.leftPressed){
            this.setAnimation(this.walkLeft);
            this.animation.start();
            this.left = true;
        }
        if (this.rightPressed){
            this.setAnimation(this.walkRight);
            this.animation.start();
            this.right = true;
        }
        if (this.upPressed){
            this.setAnimation(this.walkUp);
            this.animation.start();
            this.up = true;
        }
        if (this.downPressed){
            this.setAnimation(this.walkDown);
            this.animation.start();
            this.down = true;
        }

        this.animation.update();
        collision();
        checkEnemyDetection();
        this.inRange = false;
    }


    //checks if player can dash or if it would collide, if not, dash
    private void dash(){
        if (this.rightPressed){
            for (GameObject gameObject : this.handler.block){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x + this.width, this.y, this.width*2, this.height))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "right";
        }
        else if (this.leftPressed){
            for (GameObject gameObject : this.handler.block){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x - (this.width * 2), y, this.width*2, this.height))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "left";
        }
        else if (this.upPressed) {
            for (GameObject gameObject : this.handler.block){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x, y - (this.height*2), this.width, this.height*2))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "up";
        }
        else if (this.downPressed){
            for (GameObject gameObject : this.handler.block){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x, y + this.height, this.width, this.height*2))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "down";
        }
        else{
            this.dash = false;
            return;
        }
        switch (this.dashDirection) {
            case "up" -> this.y -= (this.height*1.5);
            case "down" -> this.y += (this.height*1.5);
            case "left" -> this.x -= (this.width*1.5);
            case "right" -> this.x += (this.width*1.5);
        }
        this.dash = false;
        this.dashDirection = "";
        this.dashCooldown = 0;
    }

    //check collision with block/enemy
    private void collision(){
        try{
            int tempX = 0;
            int tempY = 0;
            if (this.inRange) {

                for (GameObject gameObject : this.handler.enemy) {
                    if (getBounds().intersects(gameObject.getBounds())) {
                        if (getBoundsSmall(this.x + this.movementSpeed2, this.y, this.width - 2 * this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempY = this.movementSpeed;
                            this.knockBackDirection = "down";
                        }
                        if (getBoundsSmall(this.x + this.movementSpeed2, this.y + this.height - this.movementSpeed2, this.width - 2 * this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempY = -(this.movementSpeed);
                            this.knockBackDirection = "up";
                        }
                        if (getBoundsSmall(this.x, this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2 * this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempX = this.movementSpeed;
                            this.knockBackDirection = "right";
                        }
                        if (getBoundsSmall(x + this.width - this.movementSpeed2, this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2 * this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempX = -(this.movementSpeed);
                            this.knockBackDirection = "left";
                        }
                        this.knockBackFrames = 0;
                    }
                }
            }
            for(GameObject gameObject : this.handler.block){
                if (getBounds().intersects(gameObject.getBounds())){
                        if (getBoundsSmall(this.x + this.movementSpeed2, this.y, this.width - 2* this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())){
                            tempY = this.movementSpeed;
                        }
                        if (getBoundsSmall(this.x + this.movementSpeed2, this.y + this.height - this.movementSpeed2, this.width - 2* this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())){
                            tempY = -(this.movementSpeed);
                        }
                        if (getBoundsSmall(this.x, this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2*this.movementSpeed2).intersects(gameObject.getBounds())){
                            tempX = this.movementSpeed;
                        }
                        if (getBoundsSmall(this.x + this.width - this.movementSpeed2 , this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2*this.movementSpeed2).intersects(gameObject.getBounds())){
                            tempX = -(this.movementSpeed);
                        }
                }
            }

            this.x += tempX;
            this.y += tempY;
        }

        catch (IndexOutOfBoundsException ignored) {
        }
    }

    //Check if player is detected by enemy. For debugging
    public void checkEnemyDetection(){
        for (GameObject gameObject : this.handler.enemy){
            if (gameObject.getBoundsFOV().intersects(getBounds())){
                this.colour = Color.magenta;
                return;
            }
            this.colour = Color.black;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.animation.getSprite(), x + this.animation.getOffsetX(), y + this.animation.getOffsetY(), null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(this.colour);
        g.drawRect(this.x, this.y, this.width, this.height);
    }

    //Helper method for more precise collision checking
    public Rectangle2D getBoundsSmall(int x,int y,int width,int height) {
        return new Rectangle2D.Float(x,y,width,height);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(this.x, this.y, this.width, this.height); //useful for collision for future
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