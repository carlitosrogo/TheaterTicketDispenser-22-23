package theater;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Theater;

/**
 *
 * @author carlosrodriguezgomez
 */
public class TheaterArea {
    private int rows = 0;
    private int cols = 0;
    private String name;
    private SeatState[][] seats;
    private int price;

    public TheaterArea(String name, int price, String fileName) {
        read(fileName);
        String[] indx = name.split(":");
        this.name = indx[1];
        this.price = price;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String getName() {
        return name;
    }

    public SeatState getSeats(int rows, int cols) {
        return seats[rows][cols];
    }

    public int getPrice() {
        return price;
    }
    private void read(String fileRead){
        try{
            Scanner r;
            r = new Scanner(new FileReader(fileRead));
            LinkedList<String> rowsLine = new LinkedList<>();
            while (r.hasNextLine()){
                rowsLine.add(r.nextLine());
                String line = rowsLine.get(rowsLine.size()-1);
                if( line.length() > this.cols){
                    this.cols = line.length();
                }         
            this.rows++;
            }
            this.seats = new SeatState[this.rows][this.cols];
            for (int i = 0; i < this.rows; i++){
                String line = rowsLine.get(i);
                for (int j = 0; j < line.length(); j++){
                    if (line.charAt(j) == '*'){
                        this.seats[i][j] = SeatState.free;
                    } else{
                        this.seats[i][j] = SeatState.notAvaible;
                    }
                }
                for(int j = line.length(); j < this.cols; j++){
                    this.seats[i][j] = SeatState.notAvaible;
                }
            }
        r.close();    
        }  
        catch (FileNotFoundException ex) {
            Logger.getLogger(Theater.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
}
