package dev.michael.tilegame.worlds;

import dev.michael.tilegame.Handler;
import dev.michael.tilegame.entities.Entity;
import dev.michael.tilegame.entities.EntityManager;
import dev.michael.tilegame.entities.creatures.Player;
import dev.michael.tilegame.entities.statics.Tree;
import dev.michael.tilegame.tiles.Tile;
import dev.michael.tilegame.utils.Utils;

import java.awt.*;

public class World {

    Handler handler;

    // dimensions of world
    private int width;
    private int height;

    // 2d array that holds the tile id of each tile in the world
    private int[][] tiles;

    // player spawn positions
    private int spawnX;
    private int spawnY;

    // Entities
    private EntityManager entityManager;

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        entityManager.addEntity(new Tree(handler, 200, 200));
    }

    public void tick(){
        entityManager.tick();
    }

    public void render(Graphics g){
        // use these variables to draw only the tiles that need to be displayed;
        int xStart = (int)Math.max(0, handler.getGameCamera().getxOffSet() / Tile.TILEWIDTH);
        int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffSet() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int)Math.max(0, handler.getGameCamera().getyOffSet() / Tile.TILEHEIGHT);
        int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffSet() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int j = yStart; j < yEnd; j++){
            for (int i = xStart; i < xEnd; i++){
                getTile(i, j).render( g, (int)(i * Tile.TILEWIDTH - handler.getGameCamera().getxOffSet()), (int)(j * Tile.TILEHEIGHT - handler.getGameCamera().getyOffSet()) );
            }
        }

        // Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        // if player goes outside the map pretend that they are on a grass tile
        if (x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[ tiles[x][y] ];

        if (t == null){
            t = Tile.dirtTile;
        }

        return t;
    }

    // loads world from file
    private void loadWorld(String path){
        String file = Utils.loadFileAsSrting(path);
        // splits file by any white space i.e., spaces, \n
        String[] tokens = file.split("\\s+");

        // loading width and height
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        // loading player spawn positions
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        // load tile-map data
        tiles = new int[width][height];
        for (int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                tiles[i][j] = Utils.parseInt(tokens[( i + j * width + 4 )]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
