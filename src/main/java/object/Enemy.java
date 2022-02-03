package object;

import core.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

//Class for creating enemy objects. Might need to expand this more or make more variations for different enemies,
//not sure yet how to implement this
public class Enemy extends GameObject{
    private Handler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    //enemy image to be drawn
    private BufferedImage enemyImage;


    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;

        enemyImage = ss.grabImage(2, 3, 64, 96);
    }

    //updates enemy every tick. Mostly for checking movement and collision with blocks
    public void tick() {
        x += velX;
        y += velY;

        choose = r.nextInt(10);

        for(GameObject gameObject : handler.objects){
            //if collide with wall, go the opposite way
            if(gameObject.getId() == ID.Block){
                if(getBoundsBig().intersects(gameObject.getBounds())){
                    x += (velX*2) * -1;
                    y += (velY*2) * -1;
                    velX *= 0;
                    velY *= 0;
                }
                else if(choose == 0){
                    velX = (r.nextInt(4 - -4)+ - 4);
                    velY = (r.nextInt(4 - -4)+ - 4);
                }
            }
            //Collision with bullet (for later). Decrement hp if collides
            /*
            if(tempObject.getId() == ID.Bullet){
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
             */
        }

        //remove on death
        if(hp <= 0) handler.removeObject(this);

        //randomly change movement. Terrible algorithm needs much work
        if(choose == 0){
            velX = (r.nextInt(4 - -4)+ -4); //between negative and positive 4
            velY = (r.nextInt(4 - -4)+ -4);
        }
    }

    public void render(Graphics g) {
        g.drawImage(enemyImage, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 96);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x - 16, y- 16, 32, 32);
    }

}
