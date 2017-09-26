package dev.michael.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage[][] images;
    public static final int width = 32;
    public static final int height = 32;

    public static BufferedImage[] player_up;
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_right;
    public static BufferedImage[] player_left;

    public static BufferedImage playerU0;
    public static BufferedImage playerD0;
    public static BufferedImage playerR0;
    public static BufferedImage playerL0;

    public static BufferedImage playerU1;
    public static BufferedImage playerD1;
    public static BufferedImage playerR1;
    public static BufferedImage playerL1;

    public static BufferedImage playerU2;
    public static BufferedImage playerD2;
    public static BufferedImage playerR2;
    public static BufferedImage playerL2;

    public static BufferedImage grass;
    public static BufferedImage dirt;
    public static BufferedImage skull;
    public static BufferedImage wall;
    public static BufferedImage carpet;
    public static BufferedImage chestClosed;
    public static BufferedImage chestOpen;
    public static BufferedImage tree;

    public static void init(){
        images = new BufferedImage[7][4];

        player_up = new BufferedImage[3];
        player_right = new BufferedImage[3];
        player_down = new BufferedImage[3];
        player_left = new BufferedImage[3];

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileGameSS.png"));

        for (int i = 0; i < 7; i += 1){
            for (int j = 0; j < 4; j += 1){
                images[i][j] = sheet.crop((i * width), (j * height), width, height);
            }
        }

        playerU0 = images[0][0];
        playerR0 = images[0][1];
        playerD0 = images[0][2];
        playerL0 = images[0][3];

        playerU1 = images[1][0];
        playerR1 = images[1][1];
        playerD1 = images[1][2];
        playerL1 = images[1][3];

        playerU2 = images[2][0];
        playerR2 = images[2][1];
        playerD2 = images[2][2];
        playerL2 = images[2][3];

        player_up[0] = playerU0;
        player_up[1] = playerU1;
        player_up[2] = playerU2;

        player_right[0] = playerR0;
        player_right[1] = playerR1;
        player_right[2] = playerR2;

        player_down[0] = playerD0;
        player_down[1] = playerD1;
        player_down[2] = playerD2;

        player_left[0] = playerL0;
        player_left[1] = playerL1;
        player_left[2] = playerL2;

        grass = images[3][0];
        dirt = images[3][1];
        carpet = images[3][2];
        wall = images[3][3];

        chestClosed = images[4][0];
        chestOpen = images[4][1];
        tree = images[5][0];

    }

}
