package dev.michael.tilegame.entities.creatures;

import dev.michael.graphics.Animation;
import dev.michael.graphics.Assets;
import dev.michael.tilegame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    // Animations
    private Animation animUp;
    private Animation animRight;
    private Animation animDown;
    private Animation animLeft;


    public Player(Handler handler, float x, float y){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        // initialize player collision box
        bounds.x = 20;
        bounds.y = 32;
        bounds.width = 24;
        bounds.height = 32;

        // Animations
        animUp = new Animation(300, Assets.player_up);
        animRight = new Animation(300, Assets.player_right);
        animDown = new Animation(300, Assets.player_down);
        animLeft = new Animation(300, Assets.player_left);

    }

    @Override
    public void tick() {
        // animations
        animUp.tick();
        animRight.tick();
        animDown.tick();
        animLeft.tick();

        // movement
        getInput();
        move();

        // shift camera
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up){
            yMove = -speed;
        }
        if (handler.getKeyManager().down){
            yMove = speed;
        }
        if (handler.getKeyManager().right){
            xMove = speed;
        }
        if (handler.getKeyManager().left){
            xMove = -speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()), (int)(y - handler.getGameCamera().getyOffSet()), width, height, null);

//        --- shows collision box ---
        g.setColor(Color.red);
        g.drawRect((int)( x + bounds.x - handler.getGameCamera().getxOffSet() ),
                (int)( y + bounds.y - handler.getGameCamera().getyOffSet() ),
                bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return animLeft.getCurrentFrame();
        }
        else if (xMove > 0){
            return animRight.getCurrentFrame();
        }
        else if (yMove < 0){
            return animUp.getCurrentFrame();
        }
        else {
            return animDown.getCurrentFrame();
        }
    }
}
