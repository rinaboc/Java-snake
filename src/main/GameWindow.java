package main;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class GameWindow {
    private final JFrame jframe;

    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame("Snake");

        // window state settings
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);

        // window content
        jframe.add(gamePanel);
        jframe.pack();

        // window position/visibility settings
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void closeWindow(){
        jframe.dispatchEvent(new WindowEvent(jframe, WindowEvent.WINDOW_CLOSING));
        jframe.dispose();
    }
}
