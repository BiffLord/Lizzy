package net.biff.game;

import java.awt.*;

public class Block extends Rectangle {
    Color color;
    boolean open;
    boolean waterlogged = false;
    public boolean moved;
    public Block(int x, int y, Color color, boolean open, int blockLength){
        super(x,y,blockLength,blockLength);
        this.color = color;
        this.open = open;
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5.0f));
        if (color == null){
            return;
        }
        if (waterlogged){
            g2d.setColor(Color.BLUE);
            g2d.fillRect(x,y,width,height);
            g2d.setColor(color);
            g2d.drawRect(x,y,width,height);
            return;
        }
        g2d.setColor(color);
        if (open){
            g2d.drawRect(x,y,width,height);
            return;
        }
        g2d.fillRect(x,y,width,height);

    }
    public void fill(){
        waterlogged = true;
        moved = true;
    }
    public void drain(){
        waterlogged = false;
    }
}
