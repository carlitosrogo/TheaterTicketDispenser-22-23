package main;

import java.util.Map;

/**
 *
 * @author carlosrodriguezgomez
 */
public class TranslatorManager {
    private Map<String,Translator> traslatorMap;
    private String activeIdiom;

    public TranslatorManager() {
    }
    public String traslate(String msg){
       return msg; 
    }
    
}
