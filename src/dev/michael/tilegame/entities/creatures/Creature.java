package dev.michael.tilegame.entities.creatures;

import dev.michael.tilegame.Game;
import dev.michael.tilegame.Handler;
import dev.michael.tilegame.entities.Entity;
import dev.michael.tilegame.tiles.Tile;

public abstract class Creature extends Entity{

    // DEFAULT ATTRIBUTES
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;

    // ATTRIBUTES
    protected int health;
    protected float speed;
    protected float xMove;
    protected float yMove;

    public Creature(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        this.xMove = 0;
        this.yMove = 0;
    }

    public void move(){
        if(!checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    public void moveX(){
        int tx;
        // checks moving right
        if (xMove > 0){
            tx = (int)( x + xMove + bounds.x + bounds.width ) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) /Tile.TILEHEIGHT ) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) /Tile.TILEHEIGHT ) ){
                x += xMove;
            }

            // move player right next to the wall if there is a collision
            else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        }

        // checks moving left
        else if (xMove < 0){
            tx = (int)( x + xMove + bounds.x ) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) /Tile.TILEHEIGHT ) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) /Tile.TILEHEIGHT ) ){
                x += xMove;
            }
            // move player right next to the wall if there is a collision
            else{
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY(){
        int ty;
        // checks moving up
        if (yMove < 0) {
            ty = (int)( y + yMove + bounds.y ) / Tile.TILEHEIGHT;

            if (!collisionWithTile( (int)(x + bounds.x) / Tile.TILEWIDTH, ty ) &&
                    !collisionWithTile( (int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty ) ){
                y += yMove;
            }
            else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }
        // checks moving down
        else if (yMove > 0){
            ty = (int)( y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if (!collisionWithTile( (int)(x + bounds.x) / Tile.TILEWIDTH, ty ) &&
                    !collisionWithTile( (int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty ) ){
                y += yMove;
            }
            else{
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // GETTERS AND SETTERS

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
