package net.biff;

import java.util.*;
import java.awt.Color;


public class Level {
    private final Map<String, Color> colors = new HashMap<>();
    public Block[][] blockMap = new Block[10][10];
    private GameScreen screen;
    public Level(List<String> lines){
        lines = lines.stream().filter(x->!x.startsWith("//")).filter(x->!(x.equals("\n"))).toList();

        if (lines.getFirst().equals("C reg")){
            regularColors();
        } else {
            readColorDefinition(new ArrayList<String>(lines));
        }
        readBoard(lines);
    }
    public void setScreen(GameScreen gs){
        this.screen = gs;
    }
    private void regularColors(){
        colors.put("0O",Color.LIGHT_GRAY);
        colors.put("0C",Color.DARK_GRAY);
        colors.put("1",new Color(92, 64, 51));
        colors.put("2",new Color(83, 40, 89));
        colors.put("3",new Color(139,0,0));
        colors.put("4",new Color(199,21,133));
        colors.put("5",Color.YELLOW);
        colors.put("6",Color.GREEN);
    }
    /// //////// FINISH THIS
    private void readColorDefinition(List<String> lines){
        lines.removeFirst();
        boolean board = false;
        Iterator<String> i = lines.iterator();
        while (i.hasNext()){
            if (board || i.next().equals("def board")){
                board = true;
                i.remove();
            }
        }
    }
    private void readBoard(List<String> lines){
        boolean board = false;
        int boardRow = 0;
        for (String line : lines) {
            if (line.equals("def board")) {board = true;continue;}
            if (!board) {continue;}
            String[] bits = line.split(" ");
            for (int column = 0; column < bits.length; column++) {
                boolean open = bits[column].toCharArray()[1] == 'O';
                Color color;
                if (bits[column].toCharArray()[0] == '0'){
                    color  = colors.get(bits[column]);
                }else {
                    color = colors.get(bits[column].substring(0, 1));
                }
                blockMap[boardRow][column] = new Block((column+1)*75,(boardRow+1)*75, color, open);
            }
            boardRow += 1;
        }
    }
    private void readMetaData(List<String> lines){

    }
}
