package net.biff;

import javax.swing.*;
import java.awt.*;

public class MapList extends JTextArea {
    static Font font = new Font("Book Antiqua",Font.PLAIN,24);
    private String mapLink;
    public MapList(String[] items, String[] mapLinks){
        StringBuilder sb = new StringBuilder();
        for (String s : items){
            sb.append(s).append("\n");
        }
        setText(sb.toString());
        setFocusable(false);
        setFont(font);
        this.mapLink = mapLink;
    }
}
