package screens;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author carlosrodriguezgomez
 */
public class Play {
    private String title;
    private String description;
    private String image;

    public Play()  {
        this.read();
    }
    private void read(){
        try{
            Scanner r;
            r = new Scanner(new FileReader("./src/resources/play.txt"));
            while (r.hasNextLine()){
                String line = r.nextLine();
                if (line.startsWith("play_name:")){
                    this.title = cleanLine(line);
                }
                else if (line.startsWith("description:")){
                    this.description = cleanLine(line);
                }
                else if (line.startsWith("play_poster:")){
                    this.image = new File ("./src/resources/images/" + cleanLine(line)).toString();
                }
            }
            r.close();
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String cleanLine(String line) {
        return line.substring(line.indexOf(":") + 1).trim();    
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return this.image;
    }
    
    
}
