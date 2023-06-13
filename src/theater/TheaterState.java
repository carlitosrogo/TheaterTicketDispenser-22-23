package theater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import main.Theater;

/**
 *
 * @author carlosrodriguezgomez
 */
public class TheaterState implements Serializable{
    
    private String date;
    private ArrayList<TheaterAreaState> areaStateList;
    
    public TheaterState(Theater theater, String date){
        this.date = date;
        areaStateList = new ArrayList<TheaterAreaState>();
        for(TheaterArea i: theater.getArea()){
            this.areaStateList.add(new TheaterAreaState(i));
        }
        this.loadInfo();
    }

    public String getDate() {
        return date;
    }
    public ArrayList<TheaterAreaState> getAreaStateList(){
        return this.areaStateList;
    }
    public int getNumAreas(){
        return getAreaStateList().size();
    }
    public TheaterAreaState getArea(int pos){
        return getAreaStateList().get(pos);
    }
    public void saveInfo(){
        String dir = "./src/serializable/";
        File file = new File (dir);
        if (!file.exists()){
            if(file.mkdir()){
                System.out.print("No se encontro el directorio, por lo que se ha creado correctamente");
            }
        }
        try{
            String dir2 = dir + getDate() + ".TheaterState";
            FileOutputStream f = new FileOutputStream(dir2);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            out.close();
        }catch (Exception e){
            System.out.print(e);
        }
    }
    private void loadInfo(){
        try{
            String dir = "./src/serializable/" + getDate() + ".TheaterState";
            FileInputStream file = new FileInputStream(dir);
            ObjectInputStream in = new ObjectInputStream(file);
            TheaterState theaterState = (TheaterState) in.readObject();
            this.areaStateList = theaterState.areaStateList;
            this.date = theaterState.date;
            in.close();
        }catch(Exception e){
            System.out.println("No se ha encontrado el archivo, realizando carga de ficheros por defecto");
        }
    }
}
