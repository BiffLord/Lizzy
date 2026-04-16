package net.biff;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MapSelectorClickDetector extends MouseAdapter {
    MapList mapList;
    public MapSelectorClickDetector(MapList mapList){
        this.mapList = mapList;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Click!");
        int y = e.getY();
        FontMetrics fm = mapList.getFontMetrics(mapList.getFont());
        int height = fm.getHeight();
        int selection = (y)/(height);
        if (selection < 0 || selection >= mapList.getText().split("\n").length){
            return;
        }
        try {
            Main.gameWindow(mapList.getMapLinks()[selection]);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
