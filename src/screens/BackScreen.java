
package screens;

import java.util.ArrayList;
import java.util.List;
import main.DispenserHardware;
import main.DispenserManager;
import main.Screen;
import main.ScreenMode;
import main.ScreenResult;

/**
 *
 * @author carlosrodriguezgomez
 */
public class BackScreen extends Screen{
    
    public BackScreen(String title, DispenserManager dispenseManager, ScreenMode mode) {
        super(title, dispenseManager, mode);
        options = new ArrayList();
        this.setDescription("BackScreenDes");
    }
    public List<String> getOptions(){
        return options;
    }
    public ScreenResult end(DispenserHardware dw) {
        if (!this.dispenserManager.getDispenser().expelCreditCard(30)) {
            this.dispenserManager.getDispenser().retainCreditCard(true);
        }
        this.dispenserManager.getDispenser().setMenuMode();
        return ScreenResult.exitScreen;
    }
    public ScreenResult begin(DispenserHardware dw){
        return ScreenResult.continueInScreen;
    }
}
