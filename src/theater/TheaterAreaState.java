package theater;

import java.io.Serializable;

/**
 *
 * @author carlosrodriguezgomez
 */
public class TheaterAreaState implements Serializable{
    private SeatState[][] seatsState;
    private String name;
    private int cols;
    private int rows;
    private int price;
    
    public TheaterAreaState (TheaterArea area){
        this.cols = area.getCols();
        this.rows = area.getRows();
        this.name = area.getName();
        this.price = area.getPrice();
        this.seatsState = new SeatState[this.rows][this.cols];
        setSeats(area);
    }
    public void setSeats(TheaterArea area){
        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.cols; col++){
                this.seatsState[row][col] = area.getSeats(row, col);
            }
        }
    }

    public SeatState getSeatState(int row, int col) {
        return seatsState[row][col];
    }
    public void setSeatState(int row, int col, SeatState state){
        this.seatsState[row][col] = state;
    }

    public String getName() {
        return name;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
    public int getPrice(){
        return price;
    }
}
