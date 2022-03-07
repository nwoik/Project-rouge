package object;

import animations.Animation;
import audio.AudioHandler;
import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Bat extends AnimateObject{
    private AudioHandler audio;
    private Random r = new Random();
    private int choose = 0;
    private int movementSpeed1;
    private int movementSpeed2;
    private boolean collided = false;
    private boolean lineCollided = false;

    private Line2D center;
    private Color lineColour = Color.cyan;
    //enemy image to be drawn
    private BufferedImage enemyImage;

    public Bat(int x, int y, ID id, Handler handler, SpriteSheet ss) {

        super(x, y, handler, id, ss);

        audio = new AudioHandler();
        this.handler = handler;
        this.width = 64;
        this.height = 64;
        this.knockBackDirection = "";
        this.knockBackFrames = 7;
        this.hp = 15;
        this.movementSpeed = 3;
        this.movementSpeed1 = this.movementSpeed +1;
        this.movementSpeed2 = this.movementSpeed1 * 2;
        this.alignmentY = -28;

        this.standingFacingDown.add(spriteSheet.grabImage(1, 1, 64, 96));
        this.standingFacingLeft.add(spriteSheet.grabImage(3, 1, 64, 96));
        this.standingFacingUp.add(spriteSheet.grabImage(4, 1, 64, 96));
        this.standingFacingRight.add(spriteSheet.grabImage(2, 1, 64, 96));

        this.standFacingDown = new Animation(this.standingFacingDown, framedelay, 0, alignmentY, true);
        this.standFacingLeft = new Animation(this.standingFacingLeft, framedelay, 0, alignmentY, true);
        this.standFacingUp = new Animation(this.standingFacingUp, framedelay, 0, alignmentY, true);
        this.standFacingRight = new Animation(this.standingFacingRight, framedelay, 0, alignmentY, true);

        fillAnimationList(spriteSheet, this.walkingUp, 1, 9, 1, 64, 96, 10);
        fillAnimationList(spriteSheet, this.walkingDown, 1, 3, 1, 64, 96, 10);
        fillAnimationList(spriteSheet, this.walkingLeft, 1, 5, 1, 64, 96, 10);
        fillAnimationList(spriteSheet, this.walkingRight, 1, 7, 1, 64, 96, 10);

        this.walkUp = new Animation(this.walkingUp, framedelay, 0, alignmentY, false);
        this.walkDown = new Animation(this.walkingDown, framedelay, 0, alignmentY, false);
        this.walkLeft = new Animation(this.walkingLeft, framedelay, 0, alignmentY, false);
        this.walkRight = new Animation(this.walkingRight, framedelay, 0, alignmentY, false);

        fillAnimationList(spriteSheet, this.attackingDown, 1, 11, 1, 64, 96, 5);
        fillAnimationList(spriteSheet, this.attackingUp, 1, 17, 1, 64, 96, 5);
        fillAnimationList(spriteSheet, this.attackingLeft, 1, 15, 1, 64, 96, 5);
        fillAnimationList(spriteSheet, this.attackingRight, 1, 13, 1, 64, 96, 5);

        this.attackDown = new Animation(this.attackingDown, 4, 0, alignmentY, true);
        this.attackUp = new Animation(this.attackingUp, 4, 0, alignmentY, true);
        this.attackLeft = new Animation(this.attackingLeft, 4, 0, alignmentY, true);
        this.attackRight = new Animation(this.attackingRight, 4, 0, alignmentY, true);


        this.animation = this.standFacingDown;
    }

    //updates enemy every tick. Mostly for checking movement and collision with blocks
    public void tick() {
        //remove on death
        //if(hp <= 0) handler.removeObject(this, handler.enemy);

        //move enemy
        if (this.knockBackFrames > 6) {
            this.x += this.velX;
            this.y += this.velY;

            //If player is close enough, run directed movement
            if (playerInRange()) {
                directMovement();
            }
            //otherwise move randomly
            else {
                randomMovement();
            }

            //animations for movement
            if (this.velX < 0 & !isAttacking) {
                this.setAnimation(this.walkLeft);
                this.animation.start();
            }
            if (this.velX > 0 & !isAttacking) {
                this.setAnimation(this.walkRight);
                this.animation.start();
            }
            if (this.velY < 0 & !isAttacking) {
                this.setAnimation(this.walkUp);
                this.animation.start();
            }
            if (this.velY > 0 & !isAttacking) {
                this.setAnimation(this.walkDown);
                this.animation.start();
            }

            if (this.velY == 0 & this.animation == this.walkDown) {
                this.setAnimation(this.standFacingDown);
                this.animation.start();
            }

            if (this.velY == 0 & this.animation == this.walkUp) {
                this.setAnimation(this.standFacingUp);
                this.animation.start();
            }

            if (this.velX == 0 & this.animation == this.walkLeft) {
                this.setAnimation(this.standFacingLeft);
                this.animation.start();
            }

            if (this.velX == 0 & this.animation == this.walkRight) {
                this.setAnimation(this.standFacingRight);
                this.animation.start();
            }
            this.animation.update();
        }
        else {
            if (this.knockBackFrames == 0) {
                audio.playSFX("sfx/skeleton/hurt1.wav");
                this.movementSpeed = 10;
            }
            if (!this.collided) {
                switch (this.knockBackDirection) {
                    case "up" -> subY(this.movementSpeed);
                    case "down" -> addY(this.movementSpeed);
                    case "left" -> subX(this.movementSpeed);
                    case "right" -> addX(this.movementSpeed);
                }
            }
            if (this.knockBackFrames == 6) {
                this.movementSpeed = 3;
            }
            this.knockBackFrames += 1;
        }
        //check collision
        this.collided = false;
        collision();
    }

    //Check if player is within 800 units of enemy
    private Boolean playerInRange(){
        if (getBoundsFOV().intersects(this.handler.player.getBounds())) {
            if (!this.handler.player.inRange) {
                this.handler.player.inRange = true;
            }
            return true;
        }
        return false;
    }

    private void directMovement(){
        this.center = plotLine(this.x +(this.width/2), this.y+(this.height/2), this.handler.player.getX() +(this.handler.player.getWidth()/2), this.handler.player.getY()+(this.handler.player.getHeight()/2));
        //If line collides with a block then return, otherwise implement line of sight actions
        for (GameObject gameObject: this.handler.walls){
            if (this.center.intersects(gameObject.getBounds())){
                this.lineColour = Color.orange;
                this.lineCollided = true;
                randomMovement();
                return;
            }
        }
        //Debugging lines
        this.lineColour = Color.cyan;
        this.lineCollided = false;
        //Determine movement direction towards player
        if (this.handler.player.getX() + (this.handler.player.getWidth()/2)  < this.x + (this.width/2) + 16 && this.handler.player.getX() + (this.handler.player.getWidth()/2)  > this.x + (this.width/2)){
            this.velX = 0;
        }
        else if (!this.isAttacking & this.handler.player.getX() + (this.handler.player.getWidth()/2)  > this.x + (this.width/2)){
            this.velX = this.movementSpeed;
        }
        else if (!this.isAttacking & this.handler.player.getX() + (this.handler.player.getWidth()/2)  < this.x + (this.width/2)){
            this.velX = -this.movementSpeed;
        }
        if (this.handler.player.getY() + ((this.handler.player.getHeight())/2)  < this.y + ((this.height)/2) + 16 && this.handler.player.getY() + ((this.handler.player.getHeight())/2)   > this.y + ((this.height)/2)){
            this.velY = 0;
        }
        else if (!this.isAttacking & this.handler.player.getY() + ((this.handler.player.getHeight())/2)   > this.y + ((this.height)/2)){
            this.velY = this.movementSpeed;
        }
        else if (!this.isAttacking & this.handler.player.getY() + ((this.handler.player.getHeight())/2)   < this.y + ((this.height)/2)){
            this.velY = -this.movementSpeed;
        }

        //Check if enemy is close enough to player to attack
        if (Math.abs(this.center.getX1() - this.center.getX2()) < 100 && Math.abs(this.center.getY1() - this.center.getY2()) < 100){
            this.lineColour = Color.white;
            this.velX = 0;
            this.velY = 0;
            if (this.animation == this.standFacingDown || this.animation == this.walkDown) {
                this.setAnimation(this.attackDown);
                this.animation.start();
                return;
            } else if (this.animation.stop && this.animation == this.attackDown) {
                this.isAttacking = false;
                this.setAnimation(standFacingDown);
            }
            if (this.animation == this.standFacingUp || this.animation == this.walkUp) {
                this.setAnimation(this.attackUp);
                this.animation.start();
                return;
            } else if (this.animation.stop && this.animation == this.attackUp) {
                this.isAttacking = false;
                this.setAnimation(standFacingUp);
            }
            if (this.animation == this.standFacingLeft || this.animation == this.walkLeft) {
                this.setAnimation(this.attackLeft);
                this.animation.start();
                return;
            } else if (this.animation.stop && this.animation == this.attackLeft) {
                this.isAttacking = false;
                this.setAnimation(standFacingLeft);
            }
            if (this.animation == this.standFacingRight || this.animation == this.walkRight) {
                this.setAnimation(this.attackRight);
                this.animation.start();
            } else if (this.animation.stop && this.animation == this.attackRight) {
                this.isAttacking = false;
                this.setAnimation(standFacingRight);
            }
        }
        else{
            this.lineColour = Color.cyan;
        }
    }

    //create line for checking line of sight
    private Line2D plotLine(int x1,int y1,int x2,int y2){
        return new Line2D.Double(x1,y1,x2,y2);
    }

    //Function for moving enemy randomly
    private void randomMovement(){
        //if collision occurred, don't create new random movement direction
        if (this.collided){
            this.collided = false;
            return;
        }

        //Get a random number
        this.choose = r.nextInt(200);

        //randomly change movement (or stop movement)
        switch (this.choose) {
            case 0:
            case 9:
            case 10:
            case 11:
            case 12:
                this.velX = 0;
                this.velY = 0;
                break;
            case 1:
                this.velX = this.movementSpeed;
                this.velY = 0;
                break;
            case 2:
                this.velX = 0;
                this.velY = this.movementSpeed;
                break;
            case 3:
                this.velX = this.movementSpeed;
                this.velY = this.movementSpeed;
                break;
            case 4:
                this.velX = -this.movementSpeed;
                this.velY = 0;
                break;
            case 5:
                this.velX = 0;
                this.velY = -this.movementSpeed;
                break;
            case 6:
                this.velX = -this.movementSpeed;
                this.velY = -this.movementSpeed;
                break;
            case 7:
                this.velX = this.movementSpeed;
                this.velY = -this.movementSpeed;
                break;
            case 8:
                this.velX = -this.movementSpeed;
                this.velY = this.movementSpeed;
                break;
        }
    }
    //Check if enemy collided with block
    private void collision(){
        for(GameObject gameObject : this.handler.walls){

            //if collide with wall, go the opposite way
            if(getBounds().intersects(gameObject.getBounds())){
                this.collided = true;
                if (getBoundsSmall(this.x + this.movementSpeed2, this.y, this.width - 2* this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())){
                    this.velY = this.movementSpeed;
                }
                if (getBoundsSmall(this.x + this.movementSpeed2, this.y + this.height - this.movementSpeed2, this.width - 2* this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())){
                    this.velY = -(this.movementSpeed);
                }
                if (getBoundsSmall(this.x, this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2*this.movementSpeed2).intersects(gameObject.getBounds())){
                    this.velX = this.movementSpeed;
                }
                if (getBoundsSmall(this.x + this.width - this.movementSpeed2 , this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2*this.movementSpeed2).intersects(gameObject.getBounds())){
                    this.velX = -(this.movementSpeed);
                }
            }
        }
    }
    public void render(Graphics g) {
        g.drawImage(this.animation.getSprite(), x + this.animation.getOffsetX(), y + this.animation.getOffsetY(), null);
        if (Math.random() <= 0.005) {
            audio.playSFX("sfx/skeleton/say2.wav");
        }
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(this.x,this.y,this.width,this.height);
        g.drawOval((this.x-300) + (this.width/2),(this.y-300) + ((this.height)/2), 600, 600);
//        The following options check if enemy is centered in its detection radius
//        g.setColor(Color.pink);
//        g.drawRect((x-400) + (width/2),(y-400) + ((height+offset)/2), 800, 800);
//        g.setColor(Color.green);
//        g.drawLine((x - 400) + (width/2),(y -400) + ((height+offset)/2),(x + 400) + (width/2),(y + 400) + ((height+offset)/2));
//        g.drawLine((x - 400) + (width/2),(y + 400) + ((height+offset)/2),(x + 400) + (width/2),(y -400) + ((height+offset)/2));
//        If player is in range draw lines of sight
        if (playerInRange()) {
            g.setColor(this.lineColour);
            g.drawLine(this.x +(this.width/2), this.y+(this.height/2), this.handler.player.getX() +(this.handler.player.getWidth()/2),  this.handler.player.getY()+(this.handler.player.getHeight()/2));
        }

    }

    //   for collision
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(this.x, this.y, this.width, this.height);
    }

    public Rectangle2D getBoundsSmall(int x,int y,int width,int height) {
        return new Rectangle2D.Float(x,y,width,height);
    }

    //Get Radius of field of view and implement it
    public Ellipse2D getBoundsFOV() {
        return new Ellipse2D.Float((this.x-300) + ((float)(this.width)/2),(this.y-300) + ((float)(this.height)/2),600,600);
    }
}
