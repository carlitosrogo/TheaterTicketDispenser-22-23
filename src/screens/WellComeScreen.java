package screens;

import java.util.ArrayList;
import java.util.List;
import main.Theater;
import main.DispenserManager;
import main.DispenserHardware;
import main.Screen;
import main.ScreenMode;
import main.ScreenResult;




/**
 *
 * @author carlosrodriguezgomez
 */
public class WellComeScreen extends Screen{
    
    private DateSelectionScreen dateSelection;
    private IdiomSelectionScreen idiomSelection;
    private Play p = new Play();
    private Theater theater;
    private List<String> options = new ArrayList<String>();
    
    public WellComeScreen(Theater theater, String title, DispenserManager dispenserManager, ScreenMode mode) {
        super(title, dispenserManager, mode);
        this.theater = theater;
        this.idiomSelection = new IdiomSelectionScreen("IdiomSelectionScreen",dispenserManager,ScreenMode.optionsMode);
        this.dateSelection = new DateSelectionScreen(theater,"DateSelectionScreen",dispenserManager,ScreenMode.optionsMode);
        this.setOptions(options);
    }

    public ScreenResult optionButtonPressed(DispenserHardware dw, char opt){
        ScreenResult screenR = ScreenResult.continueInScreen;
        if (opt == 'A'){
            this.dispenserManager.showScreen(30,this.idiomSelection);
        }
        if (opt == 'B'){
            this.p.getTitle();
            this.dispenserManager.showScreen(30,this.dateSelection);
        }
        return screenR;
    }
    public ScreenResult begin(DispenserHardware dw){
       return ScreenResult.continueInScreen;
    }
    public ScreenResult end(DispenserHardware dw){
        return ScreenResult.continueInScreen;
    }
    public List<String> getOptions(){
        return options;
    }
    public void setOptions(List<String> options){
        this.options.add(idiomSelection.getTitle());
        this.options.add(dateSelection.getTitle());
    }
    public String getDescription(){
        return p.getDescription();
    }
    public String getImage(){
        return p.getImage();
    }
}
