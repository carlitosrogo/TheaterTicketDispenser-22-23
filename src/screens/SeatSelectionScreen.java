package screens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import main.DispenserHardware;
import main.Theater;
import main.DispenserManager;
import main.Screen;
import main.ScreenMode;
import main.ScreenResult;
import theater.SeatState;
import theater.TheaterAreaState;

/**
 *
 * @author carlosrodriguezgomez
 */
public class SeatSelectionScreen extends Screen{
    
    private TheaterAreaState areaSelection;
    private Theater theater;
    private String day;
    private int selected;
    private PaymentScreen payment;
    private final int maxSelected = 4;
    private List<String> selectedSeats = new LinkedList<>();
    private int price;
    
    public SeatSelectionScreen(Theater theater, String day, DispenserManager dispenseManager,TheaterAreaState areaSelection, ScreenMode mode) {
        super("SeatSelectionScreen", dispenseManager, mode);
        this.theater = theater;
        this.day = day;
        this.areaSelection = areaSelection;
        options = new ArrayList();
        setOptions(options);
    }
    public List<String> getOptions(){
        return options;
    }
    public int getPrice(){
        price = this.areaSelection.getPrice();
        if(this.day.contains("Fri") || this.day.contains("Sat") || this.day.contains("Sun")){
            price = areaSelection.getPrice() * 2;
        }
        return price;
    }
    public void setOptions(List<String> options){
        options.add("Cancelar");
        options.add("Continuar");
    }
    public ScreenResult begin(DispenserHardware dw){
        return ScreenResult.continueInScreen;
    }
    public ScreenResult end(DispenserHardware dw){
        if(this.payment != null){
            if (this.payment.isOperationDone()){
                this.reloadState(true);
                for(int i = 0; i < this.selectedSeats.size(); i++){
                    List<String> ticket = new LinkedList<>();
                    ticket.add("************************");
                    ticket.add(this.dispenserManager.getTranslator().translate("Entrada para") + " " + this.theater.getPlay().getTitle());
                    ticket.add("************************");
                    ticket.add(this.dispenserManager.getTranslator().translate("Teatro " + theater.getName()));
                    ticket.add(this.dispenserManager.getTranslator().translate(this.areaSelection.getName()));
                    ticket.add(this.dispenserManager.getTranslator().translate(this.day.substring(0, 3)) + this.day.substring(3));
                    String fileLine = this.selectedSeats.get(i);
                    String[] arrList = fileLine.split(":");
                    ticket.add(this.dispenserManager.getTranslator().translate("Fila") + ":" + arrList[0]);
                    ticket.add(this.dispenserManager.getTranslator().translate("Columna") + ":" + arrList[1]);
                    ticket.add(this.dispenserManager.getTranslator().translate("Precio") + ":" + this.getPrice() + "€");
                    ticket.add("************************");
                    dw.printTicket(ticket);
                }
            } else {
                this.reloadState(false);
            }
            this.dispenserManager.showScreen(30, new BackScreen("BackScreen",this.dispenserManager, ScreenMode.messageMode));
        }else{
            this.reloadState(false);
        }
        return ScreenResult.exitScreen;
    }
    public int getRows(){
        return areaSelection.getRows();
    }
    public int getCols(){
        return areaSelection.getCols();
    }
    public TheaterAreaState getAreaState(){
        return this.areaSelection;
    }
    public ScreenResult optionButtonPressed(DispenserHardware dw, char op){
        ScreenResult screenR = ScreenResult.exitScreen;
        if(op == 'A'){
            this.reloadState(false);
            this.dispenserManager.getDispenser().setMenuMode();
        }else if(op == 'B'){
            if(this.selected > 0){
                this.payment = new PaymentScreen(this.finalPrice(), this.dispenserManager, ScreenMode.messageMode);
                this.dispenserManager.showScreen(30, this.payment);
            }else{
                screenR = ScreenResult.continueInScreen;
            }
        }
        return screenR;
    }
    public int finalPrice(){
        return this.selected * this.getPrice();
    }
    private void reloadState(boolean update){
        for(int i = 0; i < this.areaSelection.getRows(); i++){
            for(int j = 0; j < this.areaSelection.getCols(); j++){
                if (this.areaSelection.getSeatState(i, j) == SeatState.selected){
                    if (update){
                        this.areaSelection.setSeatState(i, j,SeatState.occupied);
                        int row = i+1;
                        int col = j+1;
                        this.selectedSeats.add(row + ":" + col);
                    } else{
                        this.areaSelection.setSeatState(i, j,SeatState.free);

                    }
                }
            }
        }
    }
    public ScreenResult seatButtonPressed(DispenserHardware dw, int row,int col){
        if(this.areaSelection.getSeatState(row - 1, col - 1) == SeatState.free && this.selected < this.maxSelected){
            this.areaSelection.setSeatState(row - 1, col - 1, SeatState.selected);
            this.dispenserManager.getDispenser().markSeat(row, col, 1);
            this.selected++;
            this.setTitle("Asientos seleccionados" + ": " + this.selected);
        } else if (this.areaSelection.getSeatState(row - 1, col - 1) == SeatState.selected) {
            this.areaSelection.setSeatState(row - 1, col - 1, SeatState.free);
            this.dispenserManager.getDispenser().markSeat(row, col, 2);
            this.selected--;
            this.setTitle("Asientos seleccionados" + ": " + this.selected);
        } else if (this.areaSelection.getSeatState(row - 1, col - 1) == SeatState.occupied) {
            this.setTitle("Asiento ocupado");
        } else if (this.maxSelected >= this.selected) {
            this.setTitle("El maximo es 4 asientos");
        }
        return ScreenResult.continueInScreen;
    }
}
