package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class EnemyMelee extends GameObject{
    private Random r = new Random();
    private int choose = 0;
    private int hp = 100;
    private int movementSpeed = 3;
    private int movementSpeed1 = this.movementSpeed +1;
    private int movementSpeed2 = this.movementSpeed1 * 2;
    private boolean collided = false;
    private boolean lineCollided = false;

    private Line2D center;
    private Color lineColour = Color.cyan;
    //enemy image to be drawn
    private BufferedImage enemyImage;

    public EnemyMelee(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
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
        for (GameObject gameObject: this.handler.block){
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
        if (Math.abs(this.center.getX1() - this.center.getX2()) < 100 && Math.abs(this.center.getY1() - this.center.getY2()) < 100){
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
            case 9:
                this.velX = 0;
                this.velY = 0;
                break;
            case 10:
                this.velX = 0;
                this.velY = 0;
                break;
            case 11:
                this.velX = 0;
                this.velY = 0;
                break;
            case 12:
                this.velX = 0;
                this.velY = 0;
                break;
        }
    }
    //Check if enemy collided with block
    private void collision(){
        for(GameObject gameObject : this.handler.block){

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
        g.drawOval((this.x-400) + (this.width/2),(this.y-400) + ((this.height)/2), 800, 800);
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
        return new Ellipse2D.Float((this.x-400) + ((float)(this.width)/2),(this.y-400) + ((float)(this.height)/2),800,800);
    }
}
