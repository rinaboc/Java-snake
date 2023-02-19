package stages;

import entities.Tile;
import main.GamePanel;

import java.awt.*;
import java.util.Vector;

public class GameBackground {

    private final Vector<Tile> tiles;
    private static final float[] colorMask = new float[]{0.05f, 0.6f, 0.3f};

    public GameBackground(GamePanel gamePanel){
        tiles = new Vector<>();

        int gridSize = gamePanel.getGridSize();
        for (int i = gamePanel.getNumberOfTiles(); i >= 0; --i) {
            for(int j = gamePanel.getNumberOfTiles(); j >= 0; --j){
                Tile newTile = new Tile(i*gridSize, j*gridSize, gamePanel);

                newTile.applyColorMask(colorMask);
                tiles.add(newTile);
            }
        }
    }

    public void render(Graphics g){
        for(Tile tile: tiles){
            tile.render(g);
        }
    }
}
