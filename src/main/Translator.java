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
public class Translator {
    private Map<String,String> messages;
    private final String file = "./src/resources/idioms/";

    public Translator(String translationFile) {
        this.messages = new HashMap<>();
        read(translationFile);
    }
    public String translate(String msg){
        return this.messages.get(msg);
    }
    private void read(String fileName){
        try{
            Scanner r;
        r = new Scanner(new FileReader(file + fileName));
        while (r.hasNextLine()){
            String line = r.nextLine();
                String[] arrLine = line.split(":");
                this.messages.put(arrLine[0],arrLine[1]);
            }
        r.close();
        }
    catch (FileNotFoundException ex) {
            Logger.getLogger(Theater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

