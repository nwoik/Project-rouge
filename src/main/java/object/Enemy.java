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
public class Enemy extends GameObject{
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    int movementSpeed = 3;
    int movementSpeed1 = this.movementSpeed +1;
    private boolean collided = false;
    private boolean lineCollided = false;

    private Line2D center;
    private Color lineColour = Color.cyan;
    //enemy image to be drawn
    private BufferedImage enemyImage;

    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.width = 64;
        this.height = 64;

        enemyImage = ss.grabImage(2, 3, 64, 96);
    }

    //updates enemy every tick. Mostly for checking movement and collision with blocks
    public void tick() {
        //remove on death
        //if(hp <= 0) handler.removeObject(this, handler.enemy);

        //check collision
        collision();

        //move enemy
        x += velX;
        y += velY;

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
        if (getBoundsFOV().intersects(handler.player.getBounds())) {
            return true;
        }
        return false;
    }

    private void directMovement(){
        this.center = plotLine(x +(width/2), y+(height/2), handler.player.getX() +(handler.player.getWidth()/2), handler.player.getY()+(handler.player.getHeight()/2));
        //If line collides with a block then return, otherwise implement line of sight actions
        for (GameObject gameObject: handler.block){
            if (center.intersects(gameObject.getBounds())){
                lineColour = Color.orange;
                lineCollided = true;
                randomMovement();
                return;
            }
        }
        //Debugging lines
        lineColour = Color.cyan;
        lineCollided = false;
        //Determine movement direction towards player
        if (handler.player.getX() + (handler.player.getWidth()/2)  < x + (width/2) + 16 && handler.player.getX() + (handler.player.getWidth()/2)  > x + (width/2)){
            velX = 0;
        }
        else if (handler.player.getX() + (handler.player.getWidth()/2)  > x + (width/2)){
            velX = movementSpeed;
        }
        else if (handler.player.getX() + (handler.player.getWidth()/2)  < x + (width/2)){
            velX = -movementSpeed;
        }
        if (handler.player.getY() + ((handler.player.getHeight())/2)  < y + ((height)/2) + 16 && handler.player.getY() + ((handler.player.getHeight())/2)   > y + ((height)/2)){
            velY = 0;
        }
        else if (handler.player.getY() + ((handler.player.getHeight())/2)   > y + ((height)/2)){
            velY = movementSpeed;
        }
        else if (handler.player.getY() + ((handler.player.getHeight())/2)   < y + ((height)/2)){
            velY = -movementSpeed;
        }

        //Check if enemy is close enough to player to attack
        if (Math.abs(center.getX1() - center.getX2()) < 100 && Math.abs(center.getY1() - center.getY2()) < 100){
            lineColour = Color.white;
        }
        else{
            lineColour = Color.cyan;
        }
    }

    //create line for checking line of sight
    private Line2D plotLine(int x1,int y1,int x2,int y2){
        return new Line2D.Double(x1,y1,x2,y2);
    }

    //Function for moving enemy randomly
    private void randomMovement(){
        //if collision occurred, don't create new random movement direction
        if (collided){
            collided = false;
            return;
        }

        //Get a random number
        choose = r.nextInt(200);


        //randomly change movement (or stop movement)
        switch (choose) {
            case 0:
                velX = 0;
                velY = 0;
                break;
            case 1:
                velX = movementSpeed;
                velY = 0;
                break;
            case 2:
                velX = 0;
                velY = movementSpeed;
                break;
            case 3:
                velX = movementSpeed;
                velY = movementSpeed;
                break;
            case 4:
                velX = -movementSpeed;
                velY = 0;
                break;
            case 5:
                velX = 0;
                velY = -movementSpeed;
                break;
            case 6:
                velX = -movementSpeed;
                velY = -movementSpeed;
                break;
            case 7:
                velX = movementSpeed;
                velY = -movementSpeed;
                break;
            case 8:
                velX = -movementSpeed;
                velY = movementSpeed;
                break;
            case 9:
                velX = 0;
                velY = 0;
                break;
            case 10:
                velX = 0;
                velY = 0;
                break;
            case 11:
                velX = 0;
                velY = 0;
                break;
            case 12:
                velX = 0;
                velY = 0;
                break;
        }
    }
    //Check if enemy collided with block
    private void collision(){
        for(GameObject gameObject : handler.block){

            //if collide with wall, go the opposite way
            if(getBounds().intersects(gameObject.getBounds())){
                collided = true;
                if ( ((this.x + movementSpeed1 >= gameObject.getX() && this.x + movementSpeed1 <= gameObject.getX() + gameObject.getHeight())
                        ||(this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - movementSpeed1) <= gameObject.getX() + gameObject.getHeight()))&&
                        (this.y + movementSpeed1 >= gameObject.getY() + gameObject.getHeight() && this.y <= gameObject.getY() + gameObject.getHeight())){
                    velY = movementSpeed;
                }
                else if (((this.x + movementSpeed1 >= gameObject.getX() && this.x + movementSpeed1 <= gameObject.getX() + gameObject.getHeight())
                        ||(this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - movementSpeed1) <= gameObject.getX() + gameObject.getHeight()))&&
                        (this.y + (this.height - movementSpeed1) <= gameObject.getY() && this.y + this.height >= gameObject.getY())){
                    velY = -movementSpeed;
                }
                else if (((this.y + movementSpeed1 >= gameObject.getY() && this.y + movementSpeed1 <= gameObject.getY() + gameObject.getWidth())
                        || (this.y + (this.height - movementSpeed1) >= gameObject.getY() && this.y + (this.height - movementSpeed1) <= gameObject.getY() + gameObject.getWidth()))&&
                        (this.x + movementSpeed1 >= gameObject.getX() + gameObject.getWidth() && this.x <= gameObject.getX() + gameObject.getWidth())){
                    velX = movementSpeed;

                }
                else if (((this.y + movementSpeed1 >= gameObject.getY() && this.y + movementSpeed1 <= gameObject.getY() + gameObject.getWidth())
                        || (this.y + (this.height - movementSpeed1) >= gameObject.getY() && this.y + (this.height - movementSpeed1) <= gameObject.getY() + gameObject.getWidth()))&&
                        (this.x + (this.width - movementSpeed1) <= gameObject.getX() && this.x + this.width >= gameObject.getX())){
                    velX = -movementSpeed;
                }

                else{
                    if (this.x + movementSpeed1 <= gameObject.getX() + gameObject.getWidth() && this.x + (2*movementSpeed1) >= gameObject.getX() + gameObject.getWidth()){
                        velX = movementSpeed;

                    }
                    else if (this.x + (this.width - movementSpeed1) >= gameObject.getX() && this.x + (this.width - (movementSpeed1*2)) <= gameObject.getX()){
                        velX = -movementSpeed;
                    }
                }
            }
        }
    }
    public void render(Graphics g) {
        g.drawImage(enemyImage, x, y-32, null);
    }

    @Override
    public void debugRender(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x,y,this.width,this.height);
        g.drawOval((x-400) + (width/2),(y-400) + ((height)/2), 800, 800);
//        The following options check if enemy is centered in its detection radius
//        g.setColor(Color.pink);
//        g.drawRect((x-400) + (width/2),(y-400) + ((height+offset)/2), 800, 800);
//        g.setColor(Color.green);
//        g.drawLine((x - 400) + (width/2),(y -400) + ((height+offset)/2),(x + 400) + (width/2),(y + 400) + ((height+offset)/2));
//        g.drawLine((x - 400) + (width/2),(y + 400) + ((height+offset)/2),(x + 400) + (width/2),(y -400) + ((height+offset)/2));
//        If player is in range draw lines of sight
        if (playerInRange()) {
            g.setColor(lineColour);
            g.drawLine(x +(width/2), y+(height/2), handler.player.getX() +(handler.player.getWidth()/2),  handler.player.getY()+(handler.player.getHeight()/2));
        }

    }

    //   for collision
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, this.width, this.height);
    }

    //Get Radius of field of view and implement it
    public Ellipse2D getBoundsFOV() {
        return new Ellipse2D.Double((x-400) + ((double)(width)/2),(y-400) + ((double)(height)/2),800,800) {
        };
    }
}
