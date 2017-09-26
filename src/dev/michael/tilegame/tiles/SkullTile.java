package dev.michael.tilegame.tiles;

import dev.michael.graphics.Assets;

public class SkullTile extends Tile {
    public SkullTile(int id){
        super(Assets.skull, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
