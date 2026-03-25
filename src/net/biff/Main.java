package net.biff;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        String FP ="C:\\Users\\saahi\\Desktop\\JavaProjects\\Lazzy\\resources\\level.lizzy";
        List<String> lines = new ArrayList<>();
        try (FileReader fr = new FileReader(FP);
             BufferedReader br = new BufferedReader(fr)){
            String line;
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Level l = new Level(lines);
        JFrame frame = new JFrame("Lizzy");
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var screen = new GameScreen(l);
        frame.add(screen);
        frame.setVisible(true);
    }

}