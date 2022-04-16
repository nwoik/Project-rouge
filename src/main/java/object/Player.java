package object;

import animations.Animation;
import animations.AnimationHandler;
import audio.AudioHandler;
import core.SpriteSheet;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Player extends CharacterObject {
    private AudioHandler audio;

    public boolean leftPressed;
    public boolean rightPressed;
    public boolean upPressed;
    public boolean downPressed;
    public final int movementSpeed1;
    public final int movementSpeed2;

    private Color colour = Color.blue;
    public boolean inRange = false;
    private String attackDirection = "";

    public boolean dash = false;
    private String dashDirection = "";
    public int dashFrames = 6;
    private int dashCooldown = 100;

    public boolean left, right, up ,down, isAttacking;

    private int damageCooldown = 20;


    public Player(int x, int y, Handler handler, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, handler);


        audio = new AudioHandler();
        this.handler = handler;
        this.animationHandler = new AnimationHandler(this.spriteSheet);
        this.movementSpeed = 6;
        this.movementSpeed1 = this.movementSpeed +1;
        this.movementSpeed2 = this.movementSpeed1 * 2;
        this.width = 64;
        this.height = 64;
        this.hp = 100;

        this.knockBackFrames = 7;
        this.knockBackDirection = "";

        this.alignmentY = -32;

//        this.standingFacingDown.add(spriteSheet.grabImage(1, 1, 64, 96));
//        this.standingFacingLeft.add(spriteSheet.grabImage(2, 1, 64, 96));
//        this.standingFacingUp.add(spriteSheet.grabImage(3, 1, 64, 96));
//        this.standingFacingRight.add(spriteSheet.grabImage(4, 1, 64, 96));

        this.animationHandler.addSingleFrame("standFacingDown", new int[] {1,1,64,96}, framedelay, 0, alignmentY, true );
        this.animationHandler.addSingleFrame("standFacingLeft", new int[] {2,1,64,96}, framedelay, 0, alignmentY, true );
        this.animationHandler.addSingleFrame("standFacingUp", new int[] {3,1,64,96}, framedelay, 0, alignmentY, true );
        this.animationHandler.addSingleFrame("standFacingRight", new int[] {4,1,64,96}, framedelay, 0, alignmentY, true );

//        fillAnimationList(spriteSheet, this.walkingUp, 1, 3, 2, 72, 100, 10);
//        fillAnimationList(spriteSheet, this.walkingDown, 1, 9, 2, 72, 96, 10);
//        fillAnimationList(spriteSheet, this.walkingLeft, 1, 5, 2, 88, 96, 10);
//        fillAnimationList(spriteSheet, this.walkingRight, 1, 7, 2, 82, 96, 10);

        this.animationHandler.addManyFrames("walkUp", new int[] {1,3,2,72,100,10}, framedelay, -4, alignmentY, true);
        this.animationHandler.addManyFrames("walkDown", new int[] {1,9,2,72,96,10}, framedelay, -4, alignmentY, true);
        this.animationHandler.addManyFrames("walkLeft", new int[] {1,5,2,88,96,10}, framedelay, 0, alignmentY, true);
        this.animationHandler.addManyFrames("walkRight", new int[] {1,7,2,82,96,10}, framedelay, -16, alignmentY, true);

        // Sword combined
//        fillAnimationList(spriteSheet, this.attackingDown, 1, 11, 3, 192, 128, 5);
//        fillAnimationList(spriteSheet, this.attackingUp, 1, 13, 3, 192, 192, 5);
//        fillAnimationList(spriteSheet, this.attackingLeft, 1, 16, 3, 192, 192, 5);
//        fillAnimationList(spriteSheet, this.attackingRight, 2, 20, 3, 128, 192, 5);

        this.animationHandler.addManyFrames("attackDown", new int[] {1,11,3,192,128,5}, framedelay, -64, alignmentY, true);
        this.animationHandler.addManyFrames("attackUp", new int[] {1,13,3,192,192,5}, framedelay, -64, -96, true);
        this.animationHandler.addManyFrames("attackLeft", new int[] {1,16,3,192,192,5}, framedelay, -64, -96, true);
        this.animationHandler.addManyFrames("attackRight", new int[] {2,20,3,128,192,5}, framedelay, 0, alignmentY, true);

        this.animation = this.animationHandler.getAnimation("standFacingDown");
    }


    @Override
    public void tick() {
        this.attackDirection = "";
        if (this.isAttacking) {
            if (this.animation.name == "standFacingDown" || this.animation.name == "walkDown") {
                audio.playSFX("sfx/player/sweep7.wav");
                this.setAnimation("attackDown");
                this.animation.start();
                this.attackDirection = "down";
                this.attack(getDownAttackBox());
                return;
            } else if (this.animation.stop && this.animation.name == "attackDown") {
                this.isAttacking = false;
                this.setAnimation("standFacingDown");
            }
            if (this.animation.name == "standFacingUp" || this.animation.name == "walkUp") {
                audio.playSFX("sfx/player/sweep7.wav");
                this.setAnimation("attackUp");
                this.animation.start();
                this.attackDirection = "up";
                this.attack(getUpAttackBox());
                return;
            } else if (this.animation.stop && this.animation.name == "attackUp") {
                this.isAttacking = false;
                this.setAnimation("standFacingUp");
            }
            if (this.animation.name == "standFacingLeft" || this.animation.name == "walkLeft") {
                AudioHandler audio1 = new AudioHandler();
                audio1.playSFX("sfx/player/sweep7.wav");
                this.setAnimation("attackLeft");
                this.animation.start();
                this.attackDirection = "left";
                this.attack(getLeftAttackBox());
                return;
            } else if (this.animation.stop && this.animation.name == "attackLeft") {
                this.isAttacking = false;
                this.setAnimation("standFacingLeft");
            }
            if (this.animation.name == "standFacingRight" || this.animation.name == "walkRight") {
                AudioHandler audio1 = new AudioHandler();
                audio1.playSFX("sfx/player/sweep7.wav");
                this.setAnimation("attackRight");
                this.animation.start();
                this.attackDirection = "right";
                this.attack(getRightAttackBox());
                return;
            } else if (this.animation.stop && this.animation.name == "attackRight") {
                this.isAttacking = false;
                this.setAnimation("standFacingRight");
            }
        }

        //check dash cooldown
        if (this.dashCooldown < 100){
            this.dashCooldown++;
            this.dash = false;
        }
        //if dash off cooldown and pressed, check if would collide with wall, if not, dash
        else if (this.dash){
            if (this.dashFrames == 0){
                this.dashCheck();
            }
            dash();
            if (this.dashFrames >= 5){
                this.dash = false;
                this.dashDirection = "";
                this.dashCooldown = 0;
                this.movementSpeed=6;
            }
            this.dashFrames++;

        }

        //Player getting knocked back after being hit
        if (!this.dash) {
            if (this.knockBackFrames > 6) {
                if (this.right & !isAttacking) {
                    addX(this.movementSpeed);
                }
                if (this.left & !isAttacking) {
                    subX(this.movementSpeed);
                }
                if (this.down & !isAttacking) {
                    addY(this.movementSpeed);
                }
                if (this.up & !isAttacking) {
                    subY(this.movementSpeed);
                }
            } else {
                if (this.knockBackFrames == 0) {
                    if (this.damageCooldown > 19) {
                        audio.playSFX("sfx/player/hit3.wav");
                        this.hp -= 5;
                        //overhaul dmg
                        this.damageCooldown = 0;
                    }
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

        //animations for movement
        if (this.leftPressed & !isAttacking){
            this.setAnimation("walkLeft");
            this.animation.start();
            this.left = true;
        }
        if (this.rightPressed & !isAttacking){
            this.setAnimation("walkRight");
            this.animation.start();
            this.right = true;
        }
        if (this.upPressed & !isAttacking){
            this.setAnimation("walkUp");
            this.animation.start();
            this.up = true;
        }
        if (this.downPressed & !isAttacking){
            this.setAnimation("walkDown");
            this.animation.start();
            this.down = true;
        }

        this.animation.update();
        collision();
        checkEnemyDetection();
        this.inRange = false;
        if (this.damageCooldown < 20){
            this.damageCooldown++;
        }
    }

    private void dash(){
        switch (this.dashDirection) {
            case "up" -> this.y -= this.movementSpeed;
            case "down" -> this.y += this.movementSpeed;
            case "right" -> this.x += this.movementSpeed;
            case "left" -> this.x -= this.movementSpeed;
        }
    }
    //checks if player can dash or if it would collide, if not, dash
    private void dashCheck(){

        if (this.rightPressed){
            audio.playSFX("sfx/player/dash.wav");
            for (Block block : this.handler.walls){
                if (block.getBounds().intersects(getBoundsSmall(this.x + this.width, this.y, this.width*2, this.height))){
                    this.dash = false;
                    return;
                }
            }
            for (GameObject gameObject : this.handler.enemies){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x + this.width, this.y, this.width*2, this.height))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "right";
            this.movementSpeed = 20;
        }
        else if (this.leftPressed){
            audio.playSFX("sfx/player/dash.wav");
            for (Block block : this.handler.walls){
                if (block.getBounds().intersects(getBoundsSmall(this.x - (this.width * 2), y, this.width*2, this.height))){
                    this.dash = false;
                    return;
                }
            }
            for (GameObject gameObject : this.handler.enemies){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x - (this.width * 2), y, this.width*2, this.height))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "left";
            this.movementSpeed = 20;
        }
        else if (this.upPressed) {
            audio.playSFX("sfx/player/dash.wav");
            for (Block block : this.handler.walls){
                if (block.getBounds().intersects(getBoundsSmall(this.x, y - (this.height*2), this.width, this.height*2))){
                    this.dash = false;
                    return;
                }
            }
            for (GameObject gameObject : this.handler.enemies){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x, y - (this.height*2), this.width, this.height*2))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "up";
            this.movementSpeed = 20;
        }
        else if (this.downPressed){
            for (Block block : this.handler.walls){
                if (block.getBounds().intersects(getBoundsSmall(this.x, y + this.height, this.width, this.height*2))){
                    this.dash = false;
                    return;
                }
            }
            for (GameObject gameObject : this.handler.enemies){
                if (gameObject.getBounds().intersects(getBoundsSmall(this.x, y + this.height, this.width, this.height*2))){
                    this.dash = false;
                    return;
                }
            }
            this.dashDirection = "down";
            this.movementSpeed = 20;
        }
        else{
            this.dash = false;
        }
    }


    //check collision with block/enemy
    private void collision(){
        try{
            int tempX = 0;
            int tempY = 0;
            if (this.inRange) {
                for (GameObject gameObject : this.handler.enemies) {
                    if (getBounds().intersects(gameObject.getBounds()) && !gameObject.toString().contains("Eye")) {
                        boolean check = false;
                        if (getBoundsSmall(this.x + this.movementSpeed2, this.y, this.width - 2 * this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempY = this.movementSpeed;
                            this.knockBackDirection = "down";
                            check = true;
                        }
                        if (getBoundsSmall(this.x + this.movementSpeed2, this.y + this.height - this.movementSpeed2, this.width - 2 * this.movementSpeed2, this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempY = -(this.movementSpeed);
                            this.knockBackDirection = "up";
                            check = true;
                        }
                        if (getBoundsSmall(this.x, this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2 * this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempX = this.movementSpeed;
                            this.knockBackDirection = "right";
                            check = true;
                        }
                        if (getBoundsSmall(this.x + this.width - this.movementSpeed2, this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2 * this.movementSpeed2).intersects(gameObject.getBounds())) {
                            tempX = -(this.movementSpeed);
                            this.knockBackDirection = "left";
                            check = true;
                        }
                        if (!check){
                            if (this.x + movementSpeed2 > gameObject.x + gameObject.width && this.x < gameObject.x + gameObject.width) {
                                tempX = -(this.movementSpeed);
                                this.knockBackDirection = "left";
                            }
                            else {
                                tempX = (this.movementSpeed);
                                this.knockBackDirection = "right";
                            }
                        }
                        this.knockBackFrames = 0;
                    }
                    else if (gameObject.toString().contains("Eye") && gameObject.getBounds().intersects(getBounds())){
                        if (this.damageCooldown > 19) {
                            audio.playSFX("sfx/player/hit3.wav");
                            this.hp -= 5;
                            //overhaul dmg
                            this.damageCooldown = 0;
                        }
                    }
                }
            }
            for(Block block : this.handler.walls){
                if (getBounds().intersects(block.getBounds())){
                    if (getBoundsSmall(this.x + this.movementSpeed2, this.y, this.width - 2* this.movementSpeed2, this.movementSpeed2).intersects(block.getBounds())){
                        tempY = this.movementSpeed;
                    }
                    if (getBoundsSmall(this.x + this.movementSpeed2, this.y + this.height - this.movementSpeed2, this.width - 2* this.movementSpeed2, this.movementSpeed2).intersects(block.getBounds())){
                        tempY = -(this.movementSpeed);
                    }
                    if (getBoundsSmall(this.x, this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2*this.movementSpeed2).intersects(block.getBounds())){
                        tempX = this.movementSpeed;
                    }
                    if (getBoundsSmall(this.x + this.width - this.movementSpeed2 , this.y + this.movementSpeed2, this.movementSpeed2, this.height - 2*this.movementSpeed2).intersects(block.getBounds())){
                        tempX = -(this.movementSpeed);
                    }
                }
            }
            for(GameObject gameObject : this.handler.objects){
                if (getBounds().intersects(gameObject.getBounds()) & !gameObject.interactive){
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

    public void attack(Rectangle2D attackBox) {
        if (!this.isAttacking) {
            return;
        }
        for (GameObject gameObject : this.handler.enemies) {
            if (attackBox.getBounds().intersects(gameObject.getBounds())) {
                gameObject.subHp(5);
                gameObject.setKnockBackFrames();
                if (this.animation.name == "attackUp") {
                    gameObject.setKnockBackDirection("up");
                }
                else if (this.animation.name == "attackDown") {
                    gameObject.setKnockBackDirection("down");
                }
                else if (this.animation.name == "attackLeft") {
                    gameObject.setKnockBackDirection("left");
                }
                else if (this.animation.name == "attackRight") {
                    gameObject.setKnockBackDirection("right");
                }

            }
        }
    }

    //Check if player is detected by enemy. For debugging
    public void checkEnemyDetection(){
        for (GameObject gameObject : this.handler.enemies){
            if (gameObject.getBoundsFOV().intersects(getBounds())){
                this.colour = Color.magenta;
                return;
            }
            this.colour = Color.blue;
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
        g.setColor(Color.yellow);
        if (this.animation.name == "attackUp") {
            g.drawRect(x-12, y-44, this.width*2, 70);
        }
        else if (this.animation.name == "attackDown") {
            g.drawRect(x-12, y+32, this.width*2, 70);
        }
        else if (this.animation.name == "attackLeft") {
            g.drawRect(x-48, y-48, this.width, 136);
        }
        else if (this.animation.name == "attackRight") {
            g.drawRect(x+40, y-24, this.width, 96);
        }
    }

    public Rectangle2D getUpAttackBox() {
        return new Rectangle2D.Float(x-12, y-44, this.width*2, 70);
    }

    public Rectangle2D getDownAttackBox() {
        return new Rectangle2D.Float(x-12, y+32, this.width*2, 70);
    }

    public Rectangle2D getLeftAttackBox() {
        return new Rectangle2D.Float(x-48, y-48, this.width, 136);
    }

    public Rectangle2D getRightAttackBox() {
        return new Rectangle2D.Float(x+40, y-24, this.width, 96);
    }

    //Helper method for more precise collision checking
    public Rectangle2D getBoundsSmall(int x,int y,int width,int height) {
        return new Rectangle2D.Float(x,y,width,height);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(this.x, this.y, this.width, this.height); //useful for collision for future
    }

    public Ellipse2D getBoundsFOV() {
        return null;
    }

    public void setAnimation(String name){
        this.animation = animationHandler.getAnimation(name);
    }

}