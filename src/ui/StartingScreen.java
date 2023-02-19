package ui;

import main.Game;
import main.GamePanel;

import static utils.Constants.Page.*;
import static utils.Constants.SoundEffects.*;


public class StartingScreen extends Menu {

    private final TitleText titleText;
    private final GameButton startButton;
    private final GameButton exitButton;

    public StartingScreen(Game game, GamePanel gamePanel) {
        super(game, gamePanel);

        titleText = new TitleText(125, 80, 150, 50, "SNAKE");

        startButton = new GameButton(unused -> {
            game.playSFX(GAME_START_SFX);
            game.switchPage(GAME);
            return null;
        }, 125, 150, 150, 50, "START");

        exitButton = new GameButton(unused -> {
            game.terminateGame();
            return null;
        }, 125, 205, 150, 50, "EXIT");

        gamePanel.add(titleText);
        gamePanel.add(startButton);
        gamePanel.add(exitButton);
    }

    @Override
    public void setVisibility(boolean visible){
        super.setVisibility(visible);
        titleText.setVisible(visible);
        startButton.setVisible(visible);
        exitButton.setVisible(visible);
    }
}
