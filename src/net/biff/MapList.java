package net.biff;

import javax.swing.*;
import java.awt.*;
import java.security.PrivateKey;

public class MapList extends JTextArea {
    private static Font font = new Font("Book Antiqua",Font.PLAIN,24);
    private String[] mapLinks;
    public MapList(String[] items, String[] mapLinks){
        StringBuilder sb = new StringBuilder();
        for (String s : items){
            sb.append(s).append("\n");
        }
        setText(sb.toString());
        setFocusable(false);
        setFont(font);
        addMouseListener(new MapSelectorClickDetector(this));
        this.mapLinks = mapLinks;
    }
    public String[] getMapLinks(){
        return mapLinks;
    }
}
