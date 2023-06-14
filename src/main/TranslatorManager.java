package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosrodriguezgomez
 */
public class TranslatorManager {
    private Map<String,Translator> translatorMap;
    private String activeIdiom;
    private String dir = "./src/resources/idioms/";

    public TranslatorManager() {
        this.activeIdiom = "Castellano";
        this.translatorMap = new HashMap<>();
        readIdioms("AvaibleIdioms.txt");
    }
    public String translate(String msg){
        Translator translator = this.translatorMap.get(this.activeIdiom);
       return translator.translate(msg); 
    }
    public void setActiveIdiom(String activeIdiom){
        this.activeIdiom = activeIdiom;
    }
    private void readIdioms(String fileName){
        try{
            Scanner r;
        r = new Scanner(new FileReader(dir + fileName));
        //lee hasta que no haya siguiente linea
        while (r.hasNextLine()){
            String line = r.nextLine();
            String[] arrLine = line.split(":");
            Translator translator = new Translator(arrLine[1]);
            this.translatorMap.put(arrLine[0],translator);
            }
        r.close();
        }
    catch (FileNotFoundException ex) {
            Logger.getLogger(Theater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
