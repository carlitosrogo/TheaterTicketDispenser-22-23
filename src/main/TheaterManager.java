package main;

import screens.WellComeScreen;
/**
 *
 * @author carlosrodriguezgomez
 */
public class TheaterManager {

    public TheaterManager() {
    }
    public void run(){

        DispenserManager disMan = new DispenserManager();
        Theater theater = new Theater();
        WellComeScreen wCS = new WellComeScreen(theater,"WellComeScreen",disMan,ScreenMode.optionsMode);
        disMan.showScreen(30, wCS);
        
    }
}
