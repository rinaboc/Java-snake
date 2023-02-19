package ui;

import main.Game;
import main.GamePanel;

public class PauseScreen extends Menu{

    private TitleText pauseText;

    public PauseScreen(Game game, GamePanel gamePanel) {
        super(game, gamePanel);
        pauseText = new TitleText(100, 100, 200, 50, "PAUSED");
        gamePanel.add(pauseText);
    }

    @Override
    public void setVisibility(boolean visible){
        super.setVisibility(visible);
        pauseText.setVisible(visible);
    }
}
