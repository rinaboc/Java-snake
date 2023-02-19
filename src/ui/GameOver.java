package ui;

import main.Game;
import main.GamePanel;

import java.awt.*;

import static utils.Constants.Page.*;
import static utils.Constants.SoundEffects.GAME_START_SFX;

public class GameOver extends Menu {

    private final TitleText gameOverText;
    private final GameButton retryButton;
    private final GameButton exitButton;

    public GameOver(Game game, GamePanel gamePanel){
        super(game, gamePanel);

        gameOverText = new TitleText(100, 100, 200, 50, "GAME OVER");

        retryButton = new GameButton(unused -> {
            game.resetGame();
            game.switchPage(GAME);
            game.playSFX(GAME_START_SFX);
            return null;
        }, 125, 175, 150, 50, "RETRY");

        exitButton = new GameButton(unused -> {
            game.terminateGame();
            return null;
        }, 125, 240, 150, 50, "EXIT");

        gamePanel.add(gameOverText);
        gamePanel.add(retryButton);
        gamePanel.add(exitButton);
    }

    public void render(Graphics g){
        super.render(g);
    }

    @Override
    public void setVisibility(boolean visible){
        super.setVisibility(visible);
        gameOverText.setVisible(visible);
        retryButton.setVisible(visible);
        exitButton.setVisible(visible);
    }
}
