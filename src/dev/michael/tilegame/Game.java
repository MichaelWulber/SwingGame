package dev.michael.tilegame;

import dev.michael.display.Display;
import dev.michael.graphics.Assets;
import dev.michael.graphics.GameCamera;
import dev.michael.graphics.ImageLoader;
import dev.michael.graphics.SpriteSheet;
import dev.michael.states.MenuState;
import dev.michael.states.State;
import dev.michael.states.GameState;
import dev.michael.tilegame.input.KeyManager;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{
    private Display display;
    public String title;

    private Thread thread;
    private boolean running = false;

    // frame dimensions
    private int width;
    private int height;

    private BufferStrategy bs;
    private Graphics g;

    // STATES
    private State gameState;
    private State menuState;

    // INPUT
    private KeyManager keyManager;

    // CAMERA
    private GameCamera gameCamera;

    // HANDLER
    private Handler handler;

    //constructor
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    // setup graphics and state
    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }

    // updates game data
    private void tick(){
        keyManager.tick();

        if (State.getState() != null){
            State.getState().tick();
        }
    }

    // renders graphics
    private void render(){
            bs = display.getCanvas().getBufferStrategy();
            if (bs == null){
                display.getCanvas().createBufferStrategy(3);
                return;
            }
            g = bs.getDrawGraphics();

            // clear screen
            g.clearRect(0, 0, 1080, 720);

            // render state
            if (State.getState() != null){
                State.getState().render(g);
            }

            // transfers graphics from buffer to the actual display
            bs.show();
            g.dispose();
    }

    // run
    public void run(){
        init();

        // timer
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        // game loop
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta > 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // start
    public synchronized void start(){
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    // stop
    public synchronized void stop(){
        if (running) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
