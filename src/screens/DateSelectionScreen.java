package screens;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import main.Theater;
import main.DispenserManager;
import main.DispenserHardware;
import main.Screen;
import main.ScreenMode;
import main.ScreenResult;
import theater.TheaterState;

/**
 *
 * @author carlosrodriguezgomez
 */
public class DateSelectionScreen extends Screen{
    
    private Map<String,TheaterState> schedule;
    private AreaSelectionScreen areaSelection;
    private Theater theater;
    
    public DateSelectionScreen(Theater theater, String name, DispenserManager dispenseManager, ScreenMode mode) {
        super(name, dispenseManager, mode);
        this.theater = theater;
        this.schedule = new  HashMap();
        //this.areaSelection = new AreaSelectionScreen(theater,"AreaSelectionScreen",dispenseManager,mode);
    }    
    private List<Date> getDatesFromToday(){
        List<Date> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        int day = 0;
        while (day < 5){
            Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if(!date.toString().contains("Mon")){
                dates.add(date);
                day++;
            }
            today = today.plusDays(1);
        }
        return dates;
    }
    private void assignSchedule(List<Date> date){
        
    }
    private void loadStateFiles(){
        for(String i: this.getOptions()){
            this.schedule.put(i, new TheaterState(this.theater,i));
        }
    }
    public List<String> getOptions(){
        List<Date> days = this.getDatesFromToday();
        List<String> option = new LinkedList<>();
        for (int i =0; i < days.size(); i++){
            LocalDate date = days.get(i).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            option.add(days.get(i).toString().substring(0, 3) + ' ' + date.toString());
        }
        option.add("Cancelar");
        return option;
    }
    public ScreenResult end(DispenserHardware dw){
        return ScreenResult.exitScreen;
    }
    public ScreenResult begin(DispenserHardware dw){
        this.loadStateFiles();
        return ScreenResult.continueInScreen;
    }
    public String getTitle(){
        return this.title;
    }
    public ScreenResult optionButtonPressed(DispenserHardware dw, char op){
        ScreenResult screenR = ScreenResult.continueInScreen;
        int i = 0;
        if(op == 'A'){
            i = 0;
        }else if(op == 'B'){
            i = 1;
        }else if(op == 'C'){
            i = 2;
        }else if(op == 'D'){
            i = 3;
        }else if(op == 'E'){
            i = 4;
        }else if(op == 'F'){
            screenR = ScreenResult.exitScreen;
        }if(screenR == ScreenResult.continueInScreen){
            this.areaSelection = new AreaSelectionScreen(this.theater, this.getOptions().get(i),this.dispenserManager, this.schedule.get(getOptions().get(i)), ScreenMode.optionsMode);
            this.dispenserManager.showScreen(30, this.areaSelection);
            this.schedule.get(getOptions().get(i)).saveInfo();
        }
        return screenR;
    }
}
