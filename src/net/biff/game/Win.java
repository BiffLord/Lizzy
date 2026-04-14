package net.biff.game;

public class Win {
    private final Level level;
    WinState won = WinState.NONE;
    public Win(Level level){
        this.level = level;
    }
    public boolean checkWin(){
        if (level.blockMap[level.end.x][level.end.y].waterlogged) {
            won = WinState.WON;
        }
        return level.blockMap[level.end.x][level.end.y].waterlogged;
    }
}
