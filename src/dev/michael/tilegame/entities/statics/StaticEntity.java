package dev.michael.tilegame.entities.statics;

import dev.michael.tilegame.Handler;
import dev.michael.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }

}
