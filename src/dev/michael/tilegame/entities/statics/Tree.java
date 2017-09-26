package dev.michael.tilegame.entities.statics;

import dev.michael.graphics.Assets;
import dev.michael.tilegame.Handler;
import dev.michael.tilegame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);

        // collision box
        bounds.x = 8;
        bounds.y = 80;
        bounds.width = width - 16;
        bounds.height = 24;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int)( x - handler.getGameCamera().getxOffSet() ), (int)( y - handler.getGameCamera().getyOffSet() ), width, height, null);

        //        --- shows collision box ---
        g.setColor(Color.red);
        g.drawRect((int)( x + bounds.x - handler.getGameCamera().getxOffSet() ),
                (int)( y + bounds.y - handler.getGameCamera().getyOffSet() ),
                bounds.width, bounds.height);
    }
}
