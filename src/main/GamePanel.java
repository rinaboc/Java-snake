package main;

import inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    protected static final int gridSize = 20;
    protected static final int numberOfTiles = 20;
    protected Dimension size;

    private final Game game;

    public GamePanel(Game game){
        setPanelSize();

        this.game = game;
        this.addKeyListener(new KeyboardInputs(this));
    }

    private void setPanelSize() {
        size = new Dimension(gridSize * numberOfTiles, gridSize * numberOfTiles);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

        this.setLayout(null);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }

    public boolean isOutsidePanelBounds(int x, int y){
        return x < 0 || y < 0 || x >= size.width || y >= size.height;
    }

    public int getGridSize() {
        return gridSize;
    }
    public int getNumberOfTiles() {
        return numberOfTiles;
    }
}
