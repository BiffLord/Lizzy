package net.biff;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    Level level;
    public GameScreen(Level level){
        this.level=level;
        addMouseListener(new ClickDetector(this));
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Block[] row : level.blockMap){
            for (Block block : row){
                block.draw(g);
            }
        }
    }
}
