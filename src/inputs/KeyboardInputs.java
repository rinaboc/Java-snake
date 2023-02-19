package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private final GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setMovementDir(UP);
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setMovementDir(DOWN);
            case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setMovementDir(LEFT);
            case KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setMovementDir(RIGHT);
            case KeyEvent.VK_ESCAPE -> gamePanel.getGame().terminateGame();
            case KeyEvent.VK_P -> gamePanel.getGame().pauseGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
