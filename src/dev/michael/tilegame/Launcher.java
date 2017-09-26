package dev.michael.tilegame;

import dev.michael.display.Display;

public class Launcher {
    public static void main(String[] args){
        Game game = new Game("Tilegame!!!", 720, 480);
        game.start();
    }
}
