package net.biff.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickDetector extends MouseAdapter {
    private final GameScreen screen;
    KeyDetector kd;
    public ClickDetector(GameScreen screen,KeyDetector kd){
        this.screen = screen;
        this.kd=kd;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        if (kd.active){
            return;
        }
        int x = e.getX();
        int y = e.getY();
        //Hey Plaatic, some help overhere?
        int padded = screen.level.blockLength+5;
        float row = (y-screen.level.verticalOffset)/(float)padded;
        float col = (x-screen.level.horizontalOffset)/(float)padded;
        if (col < 0 ||
                col >= screen.level.horizontalBlocks ||
                row < 0 ||
                row >= screen.level.verticalBlocks){
            return;
        }
        Color color = screen.level.blockMap[(int)row][(int)col].color;
        if (color == null){
            return;
        }
        if (color.equals(Color.LIGHT_GRAY) || color.equals(Color.DARK_GRAY)){ return;}
        for (Block[] line : screen.level.blockMap){
            for (Block block : line){
                if (block.color == null){
                    continue;
                }
                if (block.color.equals(color)){
                    block.open = !block.open;
                }
            }
        }
        screen.repaint();
    }
}
