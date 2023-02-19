package entities;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Tile extends Entity{

    protected final Random random;
    protected Color color;

    public Tile(float x, float y, GamePanel gamePanel) {
        super(x, y, gamePanel);

        random = new Random();
        color = new Color(random.nextInt(100,160),
                random.nextInt(100,160),
                random.nextInt(100,160));
    }

    public void applyColorMask(float[] colorMask){
        float[] rgbValues = new float[3];
        rgbValues[0] = color.getRed();
        rgbValues[1] = color.getGreen();
        rgbValues[2] = color.getBlue();

        color = new Color((int)(rgbValues[0] * colorMask[0]),
                (int)(rgbValues[1] * colorMask[1]),
                (int)(rgbValues[2] * colorMask[2]));

    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect((int)x, (int)y, gridStep, gridStep);
    }

    public Color getColor() {
        return color;
    }
}
