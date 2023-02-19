package entities;

import main.GamePanel;

import java.awt.*;
import java.util.Vector;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerColors.*;

public class Player extends Entity {

    private static class PlayerSegment extends Tile {

        public PlayerSegment(float x, float y, GamePanel gamePanel) {
            super(x, y, gamePanel);
        }

        public int[] getPosition() {
            return new int[]{(int)x, (int)y};
        }

        public void setPosition(int x, int y){
            this.x = x;
            this.y = y;
        }

        public boolean collisionDetection(int x, int y){
            return this.x == x && this.y == y;
        }
    }

    private static final float playerSpeed = 0.8f;
    private int movementDir = 0;
    private float gridStepCounter = 0;
    private float[] colorMask = PURPLE_COLORMASK;
    private Color activePlayerColor = PURPLE;

    private final Vector<PlayerSegment> playerSegments;

    public Player(float x, float y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        playerSegments = new Vector<>();
    }

    public void update(){
        updatePos();
        segmentCollisionCheck();
    }

    private void segmentCollisionCheck() {
        // cannot collide with itself at size 3
        if(playerSegments.size() <= 3){
            return;
        }

        // check possible collidable segments
        for (int i = playerSegments.size()-4; i >= 0; i--) {
            if(playerSegments.get(i).collisionDetection((int)x, (int)y)){
                movementDir = 0;
                gamePanel.getGame().gameOver();
            }
        }
    }

    private void updateSegmentPositions() {
        if(playerSegments.isEmpty()){
            return;
        }

        // positions are swapped between neighbouring segments
        for (int i = 0; i < playerSegments.size()-1; i++) {
            int[] pos = playerSegments.get(i+1).getPosition();
            playerSegments.get(i).setPosition(pos[0], pos[1]);
        }

        // newest follows the head's position
        playerSegments.get(playerSegments.size()-1).setPosition((int)x, (int)y);
    }

    private void updatePos() {

        // count when to trigger position update depending on grid size and speed
        gridStepCounter += playerSpeed;
        if(gridStepCounter >= gridStep){
            gridStepCounter = 0;
            updateSegmentPositions();
        } else {
            return;
        }

        float newXPos = x, newYPos = y;
        switch (movementDir) {
            case UP -> newYPos = y - gridStep;
            case DOWN -> newYPos = y + gridStep;
            case LEFT -> newXPos = x - gridStep;
            case RIGHT -> newXPos = x + gridStep;
        }

        // player hits wall
        if(gamePanel.isOutsidePanelBounds((int)newXPos, (int)newYPos)){
            movementDir = 0;
            gamePanel.getGame().gameOver();
            return;
        }

        x = newXPos;
        y = newYPos;
    }

    public void createSegment() {
        PlayerSegment newSegment = new PlayerSegment((int)x, (int)y, this.gamePanel);
        newSegment.applyColorMask(colorMask);
        newSegment.getColor().brighter();
        playerSegments.add(newSegment);
    }

    public void render(Graphics g) {
        g.setColor(activePlayerColor);
        g.fillRect((int)x, (int)y, gridStep, gridStep);
        for (PlayerSegment segment: playerSegments) {
            segment.render(g);
        }
    }

    public void setMovementDir(int movementDir) {

        // restrict moving in opposite direction
        if(this.movementDir * -1 == movementDir){
            return;
        }

        this.movementDir = movementDir;
    }

    public boolean collisionDetection(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean segmentCollisionDetection(int x, int y) {
        for(PlayerSegment segment : playerSegments){
            if(segment.collisionDetection(x, y)){
                return true;
            }
        }
        return collisionDetection(x ,y);
    }

    public void setPlayerColor(Color color){
        activePlayerColor = color;

        if (activePlayerColor == PURPLE) {
            colorMask = PURPLE_COLORMASK;
        } else if (activePlayerColor == RED) {
            colorMask = RED_COLORMASK;
        } else if (activePlayerColor == GREEN) {
            colorMask = GREEN_COLORMASK;
        } else if (activePlayerColor == GLASS) {
            colorMask = GLASS_COLORMASK;
        }
    }

    public void resetPlayer(){
        x = y = gamePanel.getGridSize() * 5;
        playerSegments.clear();
    }
}
