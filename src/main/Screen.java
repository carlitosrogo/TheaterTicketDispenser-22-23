package main;

import theater.TheaterAreaState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlosrodriguezgomez
 */
public class Screen {
    protected DispenserManager dispenserManager;
    protected String title;
    private String description;
    private String image;
    private String option;
    protected List<String> options;
    public ScreenMode mode;

    public Screen(String title, DispenserManager dispenseManager, ScreenMode mode) {
        this.title = title;
        this.dispenserManager = dispenseManager;
        this.mode = mode;
    }
    
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public List<String> getOptions() {
        return null;
    }

    public ScreenMode getMode() {
        return mode;
    }
    
    public ScreenResult optionButtonPressed(DispenserHardware dw, char c){
        return null;
    }
    public ScreenResult seatButtonPressed(DispenserHardware dw, int row,int col){
        return null;
    }
    public ScreenResult creditCardDetected(DispenserHardware dw){
        return null;
    }
    public TheaterAreaState getAreaState(){
        return null;
    }
    public ScreenResult begin(DispenserHardware dw){
         return null;
    }
    public ScreenResult end(DispenserHardware d){
         return null;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDispenserManager(DispenserManager dispenserManager) {
        this.dispenserManager = dispenserManager;
    }
    public void setOptions(List<String> options){
        this.options = options;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getRows(){
        return 0;
    }
    public int getCols(){
        return 0;
    }
}
