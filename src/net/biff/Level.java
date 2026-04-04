package net.biff;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Level {
    private final Map<String, Color> colors = new HashMap<>();
    public Block[][] blockMap;
    public String name;
    public String creator;
    public int horizontalBlocks;
    public int verticalBlocks;
    public int horizontalOffset;
    public int verticalOffset;
    public int blockLength;
    public int windowWidth;
    public int windowHeight;
    public Point start;
    public Point end;


    public Level(List<String> lines){
        lines = new ArrayList<>(lines.stream().filter(x->!x.startsWith("//")).filter(x->!(x.equals("\n"))).toList());
        readMetaData(lines);
        blockMap = new Block[horizontalBlocks][verticalBlocks];
        if (lines.getFirst().equals("C reg")){
            regularColors();
        } else {
            readColorDefinition(new ArrayList<String>(lines));
        }
        readBoard(lines);
    }
    private void regularColors(){
        colors.put("0O",Color.LIGHT_GRAY);
        colors.put("0C",Color.DARK_GRAY);
        colors.put("1",null);
        colors.put("2",new Color(83, 40, 89));
        colors.put("3",new Color(139,0,0));
        colors.put("4",new Color(199,21,133));
        colors.put("5",Color.YELLOW);
        colors.put("6",Color.GREEN);
        colors.put("7",new Color(92, 64, 51));
    }
    /////////// FINISH THIS
    private void readColorDefinition(List<String> lines){
        lines.removeFirst();
        boolean removable = true;
        Iterator<String> i = lines.iterator();
        while (i.hasNext()){
            String n;
            if ((n = i.next()).equals("C reg")||n.equals("def Colors")){
                removable = false;
            }
            if (removable || n.equals("def board")){
                removable = true;
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
                blockMap[boardRow][column] = new Block((column)*(blockLength+5)+verticalOffset,(boardRow)*(blockLength+5)+horizontalOffset, color, open, blockLength);
            }
            boardRow += 1;
        }
    }
    private void readMetaData(List<String> lines){
        Iterator<String> it = lines.iterator();
        boolean nonMeta = false;
        while (it.hasNext()){
            String line = it.next();
            if (line.equals("C reg") || line.equals("def Colors") || nonMeta){
                nonMeta = true;
                continue;
            }
            it.remove();
            String[] entry = line.split(": ");
            String number = "[0-9]+";
            switch (entry[0].toLowerCase()){
                case "creator": creator = entry[1]; break;
                case "horizontal-blocks":
                    if (entry[1].matches(number)){
                        horizontalBlocks = Integer.parseInt(entry[1]);
                    } break;
                case "vertical-blocks":
                    if (entry[1].matches(number)){
                        verticalBlocks = Integer.parseInt(entry[1]);
                    } break;
                case "horizontal-offset":
                    if (entry[1].matches(number)){
                        horizontalOffset = Integer.parseInt(entry[1]);
                    } break;
                case "vertical-offset":
                    if (entry[1].matches(number)){
                        verticalOffset = Integer.parseInt(entry[1]);
                    } break;
                case "window-width":
                    if (entry[1].matches(number)){
                        windowWidth = Integer.parseInt(entry[1]);
                    } break;
                case "window-height":
                    if (entry[1].matches(number)){
                        windowHeight = Integer.parseInt(entry[1]);
                    } break;
                case "block-length":
                    if (entry[1].matches(number)){
                        blockLength = Integer.parseInt(entry[1]);
                    } break;
                case "start":
                    String[] processed = entry[1].replace("(","").replace(")","").split(",");
                    start = new Point(Integer.parseInt(processed[0]),Integer.parseInt(processed[1]));
                    break;
                case "end":
                    String[] process = entry[1].replace("(","").replace(")","").split(",");
                    end = new Point(Integer.parseInt(process[0]),Integer.parseInt(process[1]));
                    break;
            }
        }
        fixMetaData();
    }
    private void fixMetaData(){
        //Horizontal values
        boolean repeatable = false;
        if (windowWidth == 0 && horizontalBlocks != 0 && horizontalOffset != 0 && blockLength !=0){
            windowWidth = horizontalBlocks*blockLength+2*horizontalOffset;
        }if (windowWidth != 0 && horizontalBlocks == 0 && horizontalOffset != 0 && blockLength !=0){
            horizontalBlocks = (windowWidth-2*horizontalOffset)/blockLength;
        }if (windowWidth != 0 && horizontalBlocks != 0 && horizontalOffset == 0 && blockLength !=0){
            horizontalOffset = (windowWidth-horizontalBlocks*blockLength)/2;
        }if (windowWidth != 0 && horizontalBlocks != 0 && horizontalOffset != 0 && blockLength == 0){
            blockLength = (windowWidth-2*horizontalOffset)/horizontalBlocks;
        }
        //vertical Values
        if (windowHeight == 0 && verticalBlocks != 0 && verticalOffset != 0 && blockLength !=0){
            windowHeight = verticalBlocks*blockLength+2*verticalOffset;
            repeatable = true;
        }if (windowHeight != 0 && verticalBlocks == 0 && verticalOffset != 0 && blockLength !=0){
            verticalBlocks = (windowHeight-2*verticalOffset)/blockLength;
            repeatable = true;
        }if (windowHeight != 0 && verticalBlocks != 0 && verticalOffset == 0 && blockLength !=0){
            verticalOffset = (windowHeight-verticalBlocks*blockLength)/2;
            repeatable = true;
        }if (windowHeight != 0 && verticalBlocks != 0 && verticalOffset != 0 && blockLength == 0){
            blockLength = (windowHeight-2*verticalOffset)/horizontalBlocks;
            repeatable = true;
        }
        if (repeatable){fixMetaData();return;}
        defaultMetaData();
    }
    private void defaultMetaData(){
        if (windowWidth == 0){
            windowWidth = 900;
        }if (windowHeight == 0){
            windowHeight = 900;
        }if (creator == null){
            creator = "Anonymous";
        }if (horizontalBlocks == 0){
            horizontalBlocks = 10;
        }if (verticalBlocks == 0){
            verticalBlocks = 10;
        }if (horizontalOffset == 0){
            horizontalOffset = 75;
        }if (verticalOffset == 0){
            verticalOffset = 75;
        } if (blockLength == 0){
            blockLength = 70;
        }if (start == null){
            start = new Point(0,0);
        }if (end == null){
            end = new Point(horizontalBlocks-1,horizontalBlocks-1);
        }
        windowHeight+=30;
    }
}
