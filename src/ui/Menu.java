package ui;

import main.Game;
import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

import static utils.Constants.ColorPalette.*;
import static utils.Constants.PlayerColors.*;

public class Menu {

    protected static class GameButton extends JButton{

        public Function<Void, Void> funktor;

        public GameButton(Function<Void, Void> funktor, int x, int y, int width, int height, String text){
            super();

            this.setText(text);
            this.setFont(new Font("Arial", Font.BOLD, 20));
            this.setForeground(PRIMARY_COLOR);
            this.setBounds(x,y,width,height);
            this.setBorderPainted(false);
            this.setFocusPainted(false);
            this.setBackground(SECONDARY_COLOR);
            this.setFocusable(false);

            this.funktor = funktor;
            addActionListener(e -> funktor.apply(null));
        }

        public void setFontSize(int size){
            this.setFont(new Font("Arial", Font.BOLD, size));
        }
    }

    protected static class TitleText extends JLabel{
        public TitleText(int x, int y, int width, int height, String text){
            super(text, CENTER);

            this.setFont(new Font("Monospaced", Font.BOLD, 35));
            this.setBounds(x, y, width, height);
            this.setForeground(SECONDARY_COLOR);
            this.setFocusable(false);
        }
    }

    protected final Game game;
    protected final GamePanel gamePanel;

    protected final GameButton soundButton;
    protected final GameButton colorButton;

    public Menu(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;

        soundButton = new GameButton(unused -> {
            game.toggleSound();
            return null;
        }, 15, 340, 60, 40, "BGM");
        soundButton.setFontSize(12);

        colorButton = new GameButton(unused -> {
            Color nextColor = game.getNextColor();
            game.getPlayer().setPlayerColor(nextColor);

            updateButtonText();

            return null;
        }, 300, 340, 80, 40, "PURPLE");
        colorButton.setFontSize(10);

        gamePanel.add(colorButton);
        gamePanel.add(soundButton);
    }

    public void render(Graphics g){
        g.setColor(PRIMARY_COLOR);
        g.fillRect(0,0, gamePanel.getWidth(), gamePanel.getHeight());
    }

    public void setVisibility(boolean visible){
        soundButton.setVisible(visible);
        colorButton.setVisible(visible);
    }

    public void updateButtonText(){
        Color currentColor = game.getCurrentColor();

        if (currentColor == PURPLE) {
            colorButton.setText("PURPLE");
        } else if (currentColor == RED) {
            colorButton.setText("RED");
        } else if (currentColor == GREEN) {
            colorButton.setText("GREEN");
        } else if (currentColor == GLASS) {
            colorButton.setText("GLASS");
        }
    }
}