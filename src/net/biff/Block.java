package net.biff;

import java.awt.*;

public class Block extends Rectangle {
    Color color;
    boolean open;
    public Block(int x, int y, Color color, boolean open){
        super(x,y,70,70);
        this.color = color;
        this.open = open;
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(5.0f));
        if (open){
            g2d.drawRect(x,y,70,70);
            return;
        }
        g2d.fillRect(x,y,70,70);

    }
}
