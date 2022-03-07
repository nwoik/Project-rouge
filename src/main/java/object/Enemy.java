package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

//Class for creating enemy objects. Might need to expand this more or make more variations for different enemies,
//not sure yet how to implement this
public class Enemy extends AnimateObject {
    private Random r = new Random();
    private int choose = 0;
    private int hp = 100;
    private int movementSpeed = 3;
    private int movementSpeed1 = this.movementSpeed +1;
    private int movementSpeed2 = this.movementSpeed1 * 2;
    private boolean collided = false;
    private boolean lineCollided = false;

    public int knockBackFrames = 7;
    public String knockBackDirection = "";

    private Line2D center;
    private Color lineColour = Color.cyan;
    //enemy image to be drawn
    private BufferedImage enemyImage;

    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, handler, id, ss);
        this.handler = handler;
        this.width = 64;
        this.height = 64;

        this.enemyImage = ss.grabImage(2, 3, 64, 96);
    }

    //updates enemy every tick. Mostly for checking movement and collision with blocks
    public void tick() {
        //remove on death
        //if(hp <= 0) handler.removeObject(this, handler.enemy);

        //check collision
        collision();

        //move enemy
        if (this.knockBackFrames > 6) {

            this.x += this.velX;
            this.y += this.velY;
            //If player is close enough, run directed movement
            if (playerInRange()){
                directMovement();
            }
            //otherwise move randomly
            else {
                randomMovement();
            }
        }
        else {
            if (this.knockBackFrames == 0) {
                this.movementSpeed = 10;
            }

            switch (knockBackDirection) {
                case "up" -> subY(this.movementSpeed);
                case "down" -> addY(this.movementSpeed);
                case "left" -> subX(this.movementSpeed);
                case "right" -> addX(this.movementSpeed);
            }
            if (this.knockBackFrames == 6) {
                this.movementSpeed = 6;
            }
            this.knockBackFrames += 1;
        }

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
        else if (this.handler.player.getX() + (this.handler.player.getWidth()/2)  > this.x + (this.width/2)){
            this.velX = this.movementSpeed;
        }
        else if (this.handler.player.getX() + (this.handler.player.getWidth()/2)  < this.x + (this.width/2)){
            this.velX = -this.movementSpeed;
        }
        if (this.handler.player.getY() + ((this.handler.player.getHeight())/2)  < this.y + ((this.height)/2) + 16 && this.handler.player.getY() + ((this.handler.player.getHeight())/2)   > this.y + ((this.height)/2)){
            this.velY = 0;
        }
        else if (this.handler.player.getY() + ((this.handler.player.getHeight())/2)   > this.y + ((this.height)/2)){
            this.velY = this.movementSpeed;
        }
        else if (this.handler.player.getY() + ((this.handler.player.getHeight())/2)   < this.y + ((this.height)/2)){
            this.velY = -this.movementSpeed;
        }

        //Check if enemy is close enough to player to attack
        if (getLineLength(this.center) < 400){
            this.lineColour = Color.white;
            this.velX = 0;
            this.velY = 0;
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

        for(GameObject gameObject : this.handler.objects){
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
        g.drawImage(this.enemyImage, this.x, this.y-32, null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(this.x,this.y,this.width,this.height);
        g.drawOval((this.x-600) + (this.width/2),(this.y-600) + ((this.height)/2), 1200, 1200);
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

    private int getLineLength(Line2D line){
        return (int)(Math.sqrt(Math.pow(line.getX2() - line.getX1(), 2) + Math.pow(line.getY2() - line.getY1(), 2)));
    }

    public Ellipse2D getBoundsSmallFOV(int x, int y, int width, int height) {
        return new Ellipse2D.Float(x,y,width,height);
    }
    //Get Radius of field of view and implement it
    public Ellipse2D getBoundsFOV() {
        return new Ellipse2D.Float((this.x-600) + ((float)(this.width)/2),(this.y-600) + ((float)(this.height)/2),1200,1200);
    }
}