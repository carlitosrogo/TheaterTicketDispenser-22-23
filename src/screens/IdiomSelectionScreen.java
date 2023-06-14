package screens;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public IdiomSelectionScreen(String name, DispenserManager dispenseManager, ScreenMode mode) {
        super(name, dispenseManager, mode);
        this.read();
    }
    public ScreenResult begin(DispenserHardware dw){
       return ScreenResult.continueInScreen;
    }
    public ScreenResult end(DispenserHardware dw){
        return ScreenResult.exitScreen;
    }
    public ScreenResult optionButtonPressed(DispenserHardware dw, char c){
        int i = 0;
        if(c == 'A'){
            i = 0;
            this.dispenserManager.getTranslator().setActiveIdiom(this.getOptions().get(i));
        }else if(c == 'B'){
            i = 1;
            this.dispenserManager.getTranslator().setActiveIdiom(this.getOptions().get(i));
        }else if(c == 'C'){
            i = 2;
            this.dispenserManager.getTranslator().setActiveIdiom(this.getOptions().get(i));
        }else if(c == 'D'){
            i = 3;
            this.dispenserManager.getTranslator().setActiveIdiom(this.getOptions().get(i));
        }
        return ScreenResult.continueInScreen;
    }
    private void read(){
        try{
            Scanner r;
        r = new Scanner(new FileReader("./src/resources/idioms/AvaibleIdioms.txt"));
        options = new ArrayList<>();
        while (r.hasNextLine()){
            String line = r.nextLine();
            String[] arrLine = line.split(":");
            this.options.add(arrLine[0]);
            }
        this.setOptions(options);
        r.close();
        }
    catch (FileNotFoundException ex) {
            Logger.getLogger(Theater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
