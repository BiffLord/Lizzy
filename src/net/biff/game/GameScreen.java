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
    Font font;
    public GameScreen(Level level, Win win){
        this.level=level;
        this.win = win;
        try (InputStream iss = GameScreen.class.getResourceAsStream("/startNoBG.png");
             InputStream ise = GameScreen.class.getResourceAsStream("/bukkitNoBG.png")){
            assert iss != null && ise != null;
            start = ImageIO.read(iss);
            end = ImageIO.read(ise);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i = 2;
        boolean cont = true;
        Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();
        while (cont){
            Font f = new Font("Times New Roman",Font.PLAIN,i);
            g.setFont(f);
            FontMetrics fm = g.getFontMetrics();
            cont = fm.getHeight() <= level.verticalOffset*0.8 && i < 26;
            i++;
        }
        font = new Font("Times New Roman",Font.PLAIN,i-1);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(font);
        for (Block[] row : level.blockMap){
            for (Block block : row){
                block.draw(g);
            }
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics(font);
        String words = (win.won == WinState.NONE)? "Click Space to let water Flow":((win.won==WinState.WON)? "Won":"Lost. Press Space to Restart");
        g2d.drawString(words,(level.windowWidth-fm.stringWidth(words))/2,level.verticalOffset/2);

        int imageLength = (int) (level.blockLength*0.9);
        int y = level.start.x*(level.blockLength+5)+level.horizontalOffset+(level.blockLength-imageLength)/2;
        int x = level.start.y*(level.blockLength+5)+level.verticalOffset+(level.blockLength-imageLength)/2;
        g.drawImage(start,x,y,imageLength,imageLength,null);
        y = level.end.x*(level.blockLength+5)+level.horizontalOffset+(level.blockLength-imageLength)/2;
        x = level.end.y*(level.blockLength+5)+level.verticalOffset+(level.blockLength-imageLength)/2;
        g.drawImage(end,x,y,imageLength,imageLength,null);
    }
}
