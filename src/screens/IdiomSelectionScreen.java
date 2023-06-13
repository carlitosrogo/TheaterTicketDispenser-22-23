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
public class IdiomSelectionScreen extends Screen{
    private List<String> optionsList = new ArrayList<String>();
    
    public IdiomSelectionScreen(String name, DispenserManager dispenseManager, ScreenMode mode) {
        super(name, dispenseManager, mode);
        this.setOptions(optionsList);
    }
    public ScreenResult begin(DispenserHardware dw){
       return ScreenResult.continueInScreen;
    }
    public ScreenResult end(DispenserHardware dw){
        return ScreenResult.exitScreen;
    }
    public List<String> getOptions(){
        return this.optionsList;
    }
    public void setOptions(List<String> optionsList){
        this.optionsList.add("ES");
        this.optionsList.add("CAT");
        this.optionsList.add("ING");
        this.optionsList.add("EU");

    }
    public String getDescription(){
        return "";
    }
    public String getImage(){
        return "";
    }
}
