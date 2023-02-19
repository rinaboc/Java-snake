package entities;

import main.GamePanel;

public abstract class Entity {

    protected GamePanel gamePanel;
    protected float x, y;
    protected int gridStep;

    public Entity(float x, float y, GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        gridStep = gamePanel.getGridSize();
    }
}
