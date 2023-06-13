package main;

import screens.WellComeScreen;
import java.io.FileNotFoundException;
import java.util.Map;
import sienens.TheaterTicketDispenser;
/**
 *
 * @author carlosrodriguezgomez
 */
public class TheaterManager {

    public TheaterManager() {
    }
    public void run() throws FileNotFoundException{
        /*Map<String,Translator> translatorMap = null;
        String activeIdiom = null;
        TheaterTicketDispenser dispenser = new TheaterTicketDispenser();
        TranslatorManager translator = new TranslatorManager();*/
        DispenserManager disMan = new DispenserManager();
        Theater theater = new Theater();
        WellComeScreen wCS = new WellComeScreen(theater,"WellComeScreen",disMan,ScreenMode.optionsMode);
        disMan.showScreen(30, wCS);
        
    }
}
