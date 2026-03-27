package net.biff;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickDetector extends MouseAdapter {
    private final GameScreen screen;
    private final WaterManager wm;
    public ClickDetector(GameScreen screen,WaterManager wm){
        this.screen = screen;
        this.wm = wm;
    }
    @Override
    public void mouseReleased(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        int row = (y-75)/75;
        int col = (x-75)/75;
        if ((float)(y-75)/75 < 0 ||
                (float)(y-75)/75 >= 10 ||
                (float)(x-75)/75 < 0 ||
                (float)(x-75)/75 >= 10){
            //water();
            wm.spreadWater();
            screen.repaint();
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
    public void water(){
        for (int row = screen.level.blockMap.length-1; row >=0; row--){
            Block[] line = screen.level.blockMap[row];
            for (int column = line.length-1; column >= 0; column--){
                Block block = line[column];
                System.out.println(screen.level.blockMap[4][1].moved);
                if (block.waterlogged &&
                        !screen.level.blockMap[Math.min(9,row+1)][column].waterlogged &&
                        screen.level.blockMap[Math.min(9,row+1)][column].open &&
                        screen.level.blockMap[Math.min(9,row+1)][column].color!=Color.DARK_GRAY &&
                        !block.moved){
                    block.drain();
                    screen.level.blockMap[Math.min(9,row+1)][column].fill();
                    continue;
                }
                if (block.waterlogged &&
                        !screen.level.blockMap[row][Math.min(9,column+1)].waterlogged &&
                        !block.moved &&
                        screen.level.blockMap[row][Math.min(9,column+1)].open &&
                        screen.level.blockMap[row][Math.min(9,column+1)].color!=Color.DARK_GRAY){
                    screen.level.blockMap[row][Math.min(9,column+1)].fill();
                    block.drain();
                    continue;
                }
                if (block.waterlogged &&
                        !screen.level.blockMap[row][Math.max(0,column-1)].waterlogged &&
                        !block.moved &&
                        screen.level.blockMap[row][Math.max(0,column-1)].open &&
                        screen.level.blockMap[row][Math.max(0,column-1)].color != Color.DARK_GRAY){
                    screen.level.blockMap[row][Math.max(0,column-1)].fill();
                    block.drain();
                    break;
                }
            }
        }
        screen.level.blockMap[0][0].fill();
        for (Block[] line:screen.level.blockMap){
            for (Block block : line){
                block.moved = false;
            }
        }
    }
}
