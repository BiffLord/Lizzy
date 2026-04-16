package net.biff;

import net.biff.game.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
public class Main{
    private static Image favicon = new ImageIcon(Main.class.getResource("/favicon.png")).getImage();

    public static void main(String[] args) throws IOException {

        //Map Selector
        JFrame fr = new JFrame("Map Selector");
        fr.setSize(500,500);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setIconImage(favicon);
        String[] links = new String[]{
                null,
                "https://raw.githubusercontent.com/BiffLord/LizzyArchives/refs/heads/master/Maps/level.lizzy",
                "https://raw.githubusercontent.com/BiffLord/LizzyArchives/refs/heads/master/Maps/pigg.lizzy"
        };
        MapList mapList = new MapList(new String[]{"Random","Wynn","Pigglesworth"},links);
        mapList.addMouseListener(new MapSelectorClickDetector(mapList,fr));
        JScrollPane scroll = new JScrollPane(mapList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(488,500));
        mapList.scrollHeight = () -> scroll.getVerticalScrollBar().getValue();
        fr.add(scroll,BorderLayout.EAST);
        fr.pack();
        fr.setVisible(true);

        //real window
    }

    private static Level getLevel(String link) throws IOException {
        List<String> lines = new ArrayList<>();
        var url = new URL(link);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setConnectTimeout(5000);
        http.setReadTimeout(5000);
        if (http.getResponseCode() != HttpURLConnection.HTTP_OK){
            throw new IOException();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()))){
            String line;
            while ((line = br.readLine())!=null){
                lines.add(line);
            }
        }
        Level l = new Level(lines);
        return l;
    }
    public static void gameWindow(String link) throws IOException {
        Level l = getLevel(link);
        JFrame frame = new JFrame("Lizzy");
        frame.setIconImage(favicon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Win win = new Win(l);
        var screen = new GameScreen(l, win);
        WaterManager wm = new WaterManager(l);
        frame.setSize(l.windowWidth,l.windowHeight);
        screen.addMouseListener(new ClickDetector(screen,wm,win));
        frame.add(screen);
        frame.setVisible(true);
    }

}