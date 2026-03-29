package net.biff;

public class Win {
    private final Level level;
    WinState won = WinState.NONE;
    public Win(Level level){
        this.level = level;
    }
    public boolean checkWin(){
        if (level.blockMap[9][9].waterlogged) {
            won = WinState.WON;
        }
        return level.blockMap[9][9].waterlogged;
    }
}
