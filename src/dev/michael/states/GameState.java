package dev.michael.states;

import dev.michael.tilegame.Handler;
import dev.michael.tilegame.entities.creatures.Player;
import dev.michael.tilegame.entities.statics.Tree;
import dev.michael.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
    }

    public void tick(){
        world.tick();
    }

    public void render(Graphics g){
        world.render(g);
    }

}
