package net.biff;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    Level level;
    Win win;
    public GameScreen(Level level, Win win){
        this.level=level;
        this.win = win;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Block[] row : level.blockMap){
            for (Block block : row){
                block.draw(g);
            }
        }
        if (win.won.equals(WinState.WON)){
            Graphics2D g2d = (Graphics2D) g;
            Font f = new Font("Times New Roman",Font.PLAIN, 25);
            g2d.setFont(f);
            FontMetrics fm = g2d.getFontMetrics(f);
            g2d.drawString("Won",450-fm.stringWidth("Won"),50);
        }else if (win.won.equals(WinState.LOST)){
            Graphics2D g2d = (Graphics2D) g;
            Font f = new Font("Times New Roman",Font.PLAIN, 25);
            g2d.setFont(f);
            FontMetrics fm = g2d.getFontMetrics(f);
            g2d.drawString("lost",450-fm.stringWidth("Lost"),50);
        }
    }
}
