package net.biff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

public class MapSelectorClickDetector extends MouseAdapter {
    MapList mapList;
    JFrame window;
    public MapSelectorClickDetector(MapList mapList, JFrame frame){
        this.mapList = mapList;
        this.window = frame;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int y = e.getY()+mapList.scrollHeight.get();
        FontMetrics fm = mapList.getFontMetrics(mapList.getFont());
        int height = fm.getHeight();
        int selection = (y)/(height);
        if (selection < 0 || selection >= mapList.getText().split("\n").length){
            return;
        }
        selection = (selection == 0)? new Random().nextInt(1,3):selection;
        try {
            Main.gameWindow(mapList.getMapLinks()[selection]);
            window.setVisible(false);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
