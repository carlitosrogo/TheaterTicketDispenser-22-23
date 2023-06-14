package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import screens.Play;
import theater.TheaterArea;

/**
 *
 * @author carlosrodriguezgomez
 */
public class Theater {
    private String theaterPlay;
    private ArrayList<TheaterArea> theaterAreas = new ArrayList<TheaterArea>();
    private String theaterImage;
    private String dir = "./src/resources/theater.txt";
    private Play play;
    
    public Theater(){
        read(dir);
        this.play = new Play();
    }
    
    public int getNumAreas(){
        return this.theaterAreas.size();
    }
    public TheaterArea getArea(int pos){
        return this.theaterAreas.get(pos);
    }
    public ArrayList<TheaterArea> getArea(){
        return this.theaterAreas;
    }
    public String getName(){
        return theaterPlay;
    }
    public Play getPlay() {
        return play;
    }
    
    
    private void read(String fileName){
        try{
            Scanner r;
        r = new Scanner(new FileReader(fileName));
        while (r.hasNextLine()){
            String line = r.nextLine();
            if (line.startsWith("TheaterName:")){
                this.theaterPlay = cleanLine(line);
            }
            else if (line.startsWith("Area:")){
                String[] arrLine = line.split(";");
                String [] arrPrice = arrLine[1].split("€");
                int price = Integer.parseInt(arrPrice[0]);
                TheaterArea area = new TheaterArea(arrLine[0],price,"./src/resources/" + cleanLine(arrLine[2]));
                this.theaterAreas.add(area);
            }
            else if (line.startsWith("TheaterPlaneImageFile:")){
                this.theaterImage = new File ("./src/resources/images/" + cleanLine(line)).toString();
            }
        }
    }catch (FileNotFoundException ex) {
            Logger.getLogger(Theater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String cleanLine(String line) {
        return line.substring(line.indexOf(":") + 1).trim();    
    }
}
