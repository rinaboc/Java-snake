package main;

import entities.Food;
import entities.Player;
import sound.Sound;
import stages.GameBackground;
import ui.GameOver;
import ui.PauseScreen;
import ui.StartingScreen;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static utils.Constants.Page.*;
import static utils.Constants.PlayerColors.*;
import static utils.Constants.SoundEffects.*;

public class Game{

    private final GameWindow gameWindow;
    private final GamePanel gamePanel;

    private static final int FPS_SET = 60;
    private static final int UPS_SET = 200;
    private int framesCounter = 0, updatesCounter = 0;
    private Timer[] gameTimers = new Timer[3];

    private final Random rnd = new Random();

    private Player player;
    private Food food;
    private GameBackground gameBackground;
    private StartingScreen menu;
    private GameOver gameOverPage;
    private PauseScreen pauseScreen;

    private int activePage;

    private Boolean soundOn = true;
    private final Sound bgm = new Sound();
    private final Sound sfx = new Sound();

    private final Color[] playerColors = new Color[]{PURPLE, RED, GREEN, GLASS};
    private int colorIndex = 0;

    private boolean paused = false;

    public Game(){

        gamePanel = new GamePanel(this);
        initClasses();
        gameWindow = new GameWindow(gamePanel);


        switchPage(MENU);

        startGameLoop();
        gamePanel.requestFocus();
    }

    private void initClasses() {
        gameBackground = new GameBackground(gamePanel);
        player = new Player(gamePanel.getGridSize() * 5, gamePanel.getGridSize() * 5, gamePanel);
        food = new Food(0, 0, gamePanel);

        menu = new StartingScreen(this, gamePanel);
        gameOverPage = new GameOver(this, gamePanel);
        pauseScreen = new PauseScreen(this, gamePanel);
        pauseScreen.setVisibility(false);

        if(soundOn){
            bgm.setFile(GAME_BGM);
            bgm.loop();
            bgm.setVolume(-5f);
            bgm.play();
        }

        changeFoodLocation();
    }

    private void changeFoodLocation() {
        int tiles = gamePanel.getNumberOfTiles();
        int gridSize = gamePanel.getGridSize();

        int foodX = rnd.nextInt(tiles) * gridSize;
        int foodY = rnd.nextInt(tiles) * gridSize;
        while(player.segmentCollisionDetection(foodX, foodY)){
            foodX = rnd.nextInt(tiles) * gridSize;
            foodY = rnd.nextInt(tiles) * gridSize;
        }

        food.setLocation(foodX, foodY);
    }

    public void triggerFoodEvent(){
        player.createSegment();

        sfx.setFile(EAT_SFX);
        sfx.setVolume(6f);
        sfx.play();

        changeFoodLocation();
    }

    private void startGameLoop() {
        for (int i = 0; i < gameTimers.length; i++) {
            gameTimers[i] = new Timer();
        }
        run();
    }

    public void pauseGame(){
        if(activePage != GAME) return;

        if (!paused) {
            for (int i = 0; i < gameTimers.length; i++) {
                gameTimers[i].cancel();
                gameTimers[i] = new Timer();
            }
            paused = true;
            pauseScreen.setVisibility(true);
        }
        else {
            run();
            paused = false;
            pauseScreen.setVisibility(false);
        }
    }

    public void run(){

        double milliSecPerFrame = 1e3f / FPS_SET;
        double milliSecPerUpdate = 1e3f / UPS_SET;

        // frame update timer
        gameTimers[0].scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gamePanel.repaint();
                framesCounter++;
            }
        }, 0, (int)(milliSecPerFrame));

        // game logic update timer
        gameTimers[1].scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (activePage == GAME) {
                    update();
                }
                updatesCounter++;
            }
        }, 0, (int)(milliSecPerUpdate));

        // FPS & UPS counter timer
        gameTimers[2].scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("FPS: " + framesCounter + " | UPS: " + updatesCounter);
                framesCounter = updatesCounter = 0;
                }
        }, 0, 1000);
    }

    private void update() {
        // game logic loop
        player.update();
        food.update();
    }

    public void render(Graphics g) {
        // rendering, called according to FPS_SET

        switch (activePage) {
            case MENU -> menu.render(g);
            case GAME -> {
                gameBackground.render(g);
                player.render(g);
                food.render(g);
            }
            case GAME_OVER -> gameOverPage.render(g);
        }

    }

    public Player getPlayer() {
        return player;
    }

    public void terminateGame() {
        gameWindow.closeWindow();
    }

    public void gameOver(){
        System.out.println("gameover");

        sfx.setFile(GAME_OVER_SFX);
        sfx.setVolume(6f);
        sfx.play();

        switchPage(GAME_OVER);

    }

    public void resetGame() {
        player.resetPlayer();
        food = new Food(0, 0, gamePanel);

        changeFoodLocation();
    }

    public void switchPage(int page){
        activePage = page;

        switch (activePage) {
            case MENU -> {
                menu.setVisibility(true);
                menu.updateButtonText();
                gameOverPage.setVisibility(false);
            }
            case GAME_OVER -> {
                gameOverPage.setVisibility(true);
                gameOverPage.updateButtonText();
                menu.setVisibility(false);
            }
            case GAME -> {
                gameOverPage.setVisibility(false);
                menu.setVisibility(false);
            }
        }
    }

    public void playSFX(int sfx){
        this.sfx.setFile(sfx);
        this.sfx.play();
    }

    public void toggleSound(){

        if(soundOn){
            soundOn = false;
            bgm.stop();
        } else {
            soundOn = true;
            bgm.loop();
            bgm.play();
        }
    }

    public Color getNextColor(){
        if(colorIndex < playerColors.length-1){
            colorIndex++;
        } else {
            colorIndex = 0;
        }

        return playerColors[colorIndex];
    }

    public Color getCurrentColor(){
        return playerColors[colorIndex];
    }
}
