package net.biff;


public class WaterManager {
    private final Level level;
    boolean first = true;
    public WaterManager(Level level) {
        this.level = level;
    }
    //Capital! Capital!
    public void spreadWater(){
        if (first){level.blockMap[0][0].fill();first=false;}
        for (int row = 0;row<level.blockMap.length;row++){
            Block[] line = level.blockMap[row];
            for (int column = 0; column<line.length;column++){
                Block block = line[column];
                if (!block.waterlogged || block.moved){
                    continue;
                }
                if (!level.blockMap[Math.min(row + 1, 9)][column].waterlogged &&
                        level.blockMap[Math.min(row + 1, 9)][column].open){
                    level.blockMap[Math.min(row+1,9)][column].fill();
                    continue;
                }
                if (!level.blockMap[row][Math.max(column-1,0)].waterlogged &&
                        level.blockMap[row][Math.max(column-1,0)].open &&
                        column>0){
                    level.blockMap[row][Math.max(column-1,0)].fill();
                    continue;
                }
                if (!level.blockMap[row][Math.min(column+1,9)].waterlogged &&
                        level.blockMap[row][Math.min(column+1,9)].open &&
                        column<9){
                    level.blockMap[row][Math.min(column+1,9)].fill();
                    continue;
                }
                if (!level.blockMap[Math.max(row-1,0)][column].waterlogged &&
                        level.blockMap[Math.max(row-1,0)][column].open &&
                        row > 0){
                    level.blockMap[Math.max(row-1,0)][column].fill();
                }
            }
        }
        for (Block[] line:level.blockMap){
            for (Block block : line){
                block.moved = false;
            }
        }
    }
}
