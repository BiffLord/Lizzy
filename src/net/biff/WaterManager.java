package net.biff;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WaterManager {
    private final Level level;
    boolean first = true;
    private final List<Point> paths = new ArrayList<>();
    public WaterManager(Level level) {
        this.level = level;
        paths.add(new Point(0,0));
    }
    //Capital! Capital!
    public boolean spreadWater(){
        //Rows (y axis) are x, and Columns (x axis) are y.
        //Idk why lol kill me its too long to fix haha
        List<Point> addable = new ArrayList<>();
        for (int i = paths.size()-1;i >= 0; i--){
            Point p  = paths.get(i);
            Block block = level.blockMap[p.x][p.y];
            if (block.moved){
                continue;
            }
            if (!level.blockMap[Math.min(p.x + 1, 9)][p.y].waterlogged &&
                    level.blockMap[Math.min(p.x + 1, 9)][p.y].open &&
                    p.x < 9){
                level.blockMap[Math.min(p.x + 1, 9)][p.y].fill();
                block.drain();
                Point newPoint;
                if (!paths.contains(newPoint = new Point(Math.min(p.x + 1, 9),p.y))){
                    addable.add(newPoint);
                }
                continue;
            }
            if (!level.blockMap[p.x][Math.max(p.y-1,0)].waterlogged &&
                    level.blockMap[p.x][Math.max(p.y-1,0)].open &&
                    p.y>0){
                level.blockMap[p.x][Math.max(p.y-1,0)].fill();
                block.drain();
                Point newPoint;
                if (!paths.contains(newPoint = new Point(p.x,Math.max(0,p.y-1)))){
                    addable.add(newPoint);
                }
                continue;
            }
            if (!level.blockMap[p.x][Math.min(p.y+1,9)].waterlogged &&
                    level.blockMap[p.x][Math.min(p.y+1,9)].open &&
                    p.y<9){
                level.blockMap[p.x][Math.min(p.y+1,9)].fill();
                block.drain();
                Point newPoint;
                if (!paths.contains(newPoint = new Point(p.x, Math.min(p.y+1,9)))){
                    addable.add(newPoint);
                }
                continue;
            }
            if (!level.blockMap[Math.max(p.x-1,0)][p.y].waterlogged &&
                    level.blockMap[Math.max(p.x-1,0)][p.y].open &&
                    p.x > 0){
                level.blockMap[Math.max(p.x-1,0)][p.y].fill();
                block.drain();
                Point newPoint;
                if (!paths.contains(newPoint = new Point(Math.max(p.x-1,0),p.y))){
                    addable.add(newPoint);
                }
            }
        }
        paths.addAll(addable);
        for (Block[] line:level.blockMap){
            for (Block block : line){
                block.moved = false;
            }
        }
        if (level.blockMap[0][0].waterlogged){
            return false;
        }
        level.blockMap[0][0].waterlogged = true;
        return true;
    }
    public void dry(){
        Iterator<Point> i = paths.iterator();
        while (i.hasNext()){
            Point p = i.next();
            level.blockMap[p.x][p.y].drain();
            i.remove();
            System.out.println(level.blockMap[0][0].waterlogged);
        }
        paths.add(new Point(0,0));
    }
}
