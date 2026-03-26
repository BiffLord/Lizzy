package net.biff;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickDetector extends MouseAdapter {
    private final GameScreen screen;
    public ClickDetector(GameScreen screen){
        this.screen = screen;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int row = (y-75)/75;
        int col = (x-75)/75;
        if (row < 0 ||
        row > 9 ||
        col < 0 ||
        col > 9){
            return;
        }
        Color color = screen.level.blockMap[row][col].color;
        if (color.equals(Color.LIGHT_GRAY) || color.equals(Color.DARK_GRAY)){ return;}
        for (Block[] line : screen.level.blockMap){
            for (Block block : line){
                if (block.color.equals(color)){
                    block.open = !block.open;
                }
            }
        }
        screen.repaint();
    }
}
