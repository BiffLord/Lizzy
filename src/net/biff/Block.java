package net.biff;

import java.awt.*;

public class Block extends Rectangle {
    Color color;
    boolean open;
    boolean waterlogged = false;
    public boolean moved;
    public Block(int x, int y, Color color, boolean open){
        super(x,y,70,70);
        this.color = color;
        this.open = open;
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5.0f));
        if (waterlogged){
            g2d.setColor(Color.BLUE);
            g2d.fillRect(x,y,70,70);
            g2d.setColor(color);
            g2d.drawRect(x,y,70,70);
            return;
        }
        g2d.setColor(color);
        if (open){
            g2d.drawRect(x,y,70,70);
            return;
        }
        g2d.fillRect(x,y,70,70);

    }
    public void fill(){
        waterlogged = true;
        moved = true;
    }
    public void drain(){
        waterlogged = false;
    }
}
