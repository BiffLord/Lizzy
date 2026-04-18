package net.biff.game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyDetector extends KeyAdapter {
    private WaterManager wm;
    GameScreen screen;
    Win win;
    boolean active = false;
    public KeyDetector(GameScreen screen, Win win){
        this.screen = screen;
        this.win = win;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() != ' ' || active || win.won==WinState.WON){return;}
        if (win.won == WinState.LOST){
            wm.dry();
            wm = null;
            win.won = WinState.NONE;
            screen.repaint();
            return;
        }
        active = true;
        Timer t = new Timer(500,null);
        t.addActionListener(_ ->{
            if (wm == null){wm = new WaterManager(screen.level);SwingUtilities.invokeLater(screen::repaint);return;}
            boolean cont = wm.spreadWater();
            if (!cont){
                t.stop();
                win.won = WinState.LOST;
                active = false;
            }
            if (win.checkWin()){
                t.stop();
                active = false;
            }
            SwingUtilities.invokeLater(screen::repaint);
        });
        t.start();
    }
}
