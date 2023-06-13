package screens;

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
public class ErrorScreen extends Screen{
    
    public ErrorScreen(String name, DispenserManager dispenseManager, ScreenMode mode) {
        super(name, dispenseManager, mode);
    }
    public ScreenResult end(DispenserHardware d){
         return ScreenResult.exitScreen;
    }
    
}
