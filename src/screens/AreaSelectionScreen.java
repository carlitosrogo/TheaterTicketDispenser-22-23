package screens;

import java.util.ArrayList;
import java.util.List;
import main.Theater;
import main.DispenserManager;
import main.DispenserHardware;
import main.Screen;
import main.ScreenMode;
import main.ScreenResult;
import theater.TheaterAreaState;
import theater.TheaterState;
/**
 *
 * @author carlosrodriguezgomez
 */
public class AreaSelectionScreen extends Screen{
    
    private TheaterAreaState areaSelect;
    private Theater theater;
    private TheaterState theaterState;
    private String day;
    private SeatSelectionScreen seatSelection;
    
    public AreaSelectionScreen(Theater theater, String day, DispenserManager dispenseManager, TheaterState theaterState, ScreenMode mode) {
        super("AreaSelectionScreen", dispenseManager, mode);
        this.theater = theater;
        this.day = day;
        this.theaterState = theaterState;
        options = new ArrayList();
        this.setOptions(options);
    }
    private void displayArea(TheaterAreaState area){
        
    }
    public String getImage(){
        return "./src/resources/images/plane.png";
    }
    public List<String> getOptions(){
        return options;
    }
    public void setOptions(List<String> options){
        for(int i = 0; i < this.theater.getArea().size(); i++){
            options.add(this.theater.getArea(i).getName());
        }
        options.add("Cancelar");
    }
    public ScreenResult begin(DispenserHardware dw){
    return ScreenResult.continueInScreen;
    }
    public ScreenResult end(DispenserHardware dw){
    return ScreenResult.exitScreen;
    }
    public ScreenResult optionButtonPressed(DispenserHardware dw, char op){
        ScreenResult screenR = ScreenResult.continueInScreen;
        int i = 0;
        if(op == 'A'){
            i = 0;
        }else if(op == 'B'){
            i = 1;
        }else if(op == 'C'){
            i = 2;
        }else if(op == 'D'){
            i = 3;
        }else if(op == 'E'){
            i = 4;
        }else if(op == 'F'){
            screenR = ScreenResult.exitScreen;
        }if(screenR == ScreenResult.continueInScreen){
            this.areaSelect = this.theaterState.getArea(i);
            this.seatSelection = new SeatSelectionScreen(this.theater, this.day,this.dispenserManager, this.areaSelect, ScreenMode.theaterMode);
            this.dispenserManager.showScreen(30, this.seatSelection);
        }
        return screenR;
    }
    
}
