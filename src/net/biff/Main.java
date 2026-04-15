package net.biff;

import net.biff.game.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
public class Main{
    public static void main(String[] args) throws IOException {
        Image favicon = new ImageIcon(Main.class.getResource("/favicon.png")).getImage();

        //Map Selector
        JFrame fr = new JFrame("Map Selector");
        fr.setSize(500,500);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setIconImage(favicon);
        MapList mapList = new MapList(new String[]{"Random","Wynn","Pigglesworth"},new String[]{"www.example.com"});
        JScrollPane scroll = new JScrollPane(mapList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(488,500));
        fr.add(scroll,BorderLayout.EAST);
        fr.setVisible(true);


        //real window
        Level l = getLevel();
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

    private static Level getLevel() throws IOException {
        List<String> lines = new ArrayList<>();
        var url = new URL("https://raw.githubusercontent.com/BiffLord/LizzyArchives/refs/heads/master/Maps/level.lizzy");
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

}