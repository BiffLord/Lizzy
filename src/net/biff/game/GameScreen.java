package net.biff.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameScreen extends JPanel {
    Level level;
    Win win;
    BufferedImage start;
    BufferedImage end;
    public GameScreen(Level level, Win win){
        this.level=level;
        this.win = win;
        try (InputStream iss = GameScreen.class.getResourceAsStream("/startNoBG.png");
             InputStream ise = GameScreen.class.getResourceAsStream("/bukkitNoBG.png");){
            assert iss != null && ise != null;
            start = ImageIO.read(iss);
            end = ImageIO.read(ise);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            g2d.drawString("Won",level.windowWidth/2-fm.stringWidth("Won"),level.verticalOffset/2);
        }else if (win.won.equals(WinState.LOST)){
            Graphics2D g2d = (Graphics2D) g;
            Font f = new Font("Times New Roman",Font.PLAIN, 25);
            g2d.setFont(f);
            FontMetrics fm = g2d.getFontMetrics(f);
            g2d.drawString("lost",level.windowWidth/2-fm.stringWidth("Lost"),level.verticalOffset/2);
        }
        int imageLength = (int) (level.blockLength*0.9);
        int y = level.start.x*(level.blockLength+5)+level.horizontalOffset+(level.blockLength-imageLength)/2;
        int x = level.start.y*(level.blockLength+5)+level.verticalOffset+(level.blockLength-imageLength)/2;
        g.drawImage(start,x,y,imageLength,imageLength,null);
        y = level.end.x*(level.blockLength+5)+level.horizontalOffset+(level.blockLength-imageLength)/2;
        x = level.end.y*(level.blockLength+5)+level.verticalOffset+(level.blockLength-imageLength)/2;
        g.drawImage(end,x,y,imageLength,imageLength,null);
    }
}
