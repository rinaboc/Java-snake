package entities;

import main.GamePanel;

import java.awt.*;

import static utils.Constants.ColorPalette.PRIMARY_COLOR;

public class Food extends Entity{
    public Food(float x, float y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    public void render(Graphics g){
        g.setColor(PRIMARY_COLOR);
        g.fillRect((int)x, (int)y, gridStep, gridStep);
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update() {
        if(gamePanel.getGame().getPlayer().collisionDetection((int)x,(int)y)){
            gamePanel.getGame().triggerFoodEvent();
        }
    }
}
