package net.biff.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickDetector extends MouseAdapter {
    private final GameScreen screen;
    private final WaterManager wm;
    private boolean active = false;
    Win win;
    public ClickDetector(GameScreen screen,WaterManager wm, Win win){
        this.screen = screen;
        this.wm = wm;
        this.win = win;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        if (active){
            return;
        }
        if (win.won != WinState.NONE){
            wm.dry();
            win.won = WinState.NONE;
            screen.repaint();
            return;
        }
        int x = e.getX();
        int y = e.getY();
        //Hey Plaatic, some help overhere?
        int padded = screen.level.blockLength+5;
        int row = (y-screen.level.verticalOffset)/padded;
        int col = (x-screen.level.horizontalOffset)/padded;
        if ((float)(y-padded)/padded < 0 ||
                (float)(y-padded)/padded >= screen.level.horizontalBlocks ||
                (float)(x-padded)/padded < 0 ||
                (float)(x-padded)/padded >= screen.level.verticalBlocks){
            //water();
            active = true;
            Timer t = new Timer(500,null);
            t.addActionListener(_ ->{
                boolean cont = wm.spreadWater();
                if (!cont){
                    t.stop();
                    win.won = WinState.LOST;
                    active = false;
                }
                if (win.checkWin()){
                    t.stop();
                    active = false;
                }
                SwingUtilities.invokeLater(screen::repaint);


            });
            t.start();
        }
        Color color = screen.level.blockMap[row][col].color;
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
