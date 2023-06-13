package main;

import theater.TheaterAreaState;
import sienens.TheaterTicketDispenser;
import theater.SeatState;

/**
 *
 * @author carlosrodriguezgomez
 */
public class DispenserManager {
    private TheaterTicketDispenser dispenser = new TheaterTicketDispenser();
    private TranslatorManager translator;
    private DispenserHardware dw = new DispenserHardware(dispenser);
    
    /*public DispenserManager(){
        this.dispenser = new TheaterTicketDispenser();
        this.dw = new DispenserHardware();
        this.translator = new TranslatorManager();
    }*/
    
    public void showScreen(int time, Screen screen){
        ScreenResult screenR = screen.begin(dw);
        this.setMode(screen);
        while(screenR == ScreenResult.continueInScreen){
            this.dispenser.setTitle(screen.getTitle());
            if(screen.getDescription()== null){
                this.dispenser.setDescription("");
            }else{
                this.dispenser.setDescription(screen.getDescription());
            }
            this.dispenser.setImage(screen.getImage());
            this.getOption(screen);
            char opt = 'a';
            if(!screen.getTitle().equals("Retire su tarjeta de credito")){
                opt = this.dispenser.waitEvent(time);
            }
            if(screen.getMode() == ScreenMode.optionsMode){
                if(opt != 0){
                    screenR = screen.optionButtonPressed(this.dw, opt);
                }
                if(opt != '1'){
                    screenR = screen.end(this.dw);
                }   
            } else if (screen.getMode() == ScreenMode.theaterMode){
                if(opt == 0){
                    screenR = screen.end(this.dw);
                }else if (opt == 'A' || opt == 'B'){
                    screenR = screen.optionButtonPressed(this.dw, opt);
                    if(screenR == ScreenResult.exitScreen){
                        screenR = screen.end(this.dw);
                    }
                }else if ((opt != '1') && (opt != 0)) {
                    byte col = (byte) (opt & 0xFF);
                    byte row = (byte) ((opt & 0xFF00) >> 8);
                    screenR = screen.seatButtonPressed(this.dw, row, col);
                }
            }else if (screen.getMode() == ScreenMode.messageMode){
                if (screen.getTitle().equals("Inserte la tarjeta de credito")){
                    if (opt == '1'){
                        screenR = screen.creditCardDetected(dw);
                    }
                }
                screenR = screen.end(dw);
            }
        }
    }
    public void ScreenManager(TheaterTicketDispenser dispenser){ 
    }
    public void setMode(Screen screen){
        if(screen.getMode() == ScreenMode.messageMode){
            this.dispenser.setMessageMode();
        }else if(screen.getMode() == ScreenMode.optionsMode){
            this.dispenser.setMenuMode();
        }else if(screen.getMode() == ScreenMode.theaterMode){
            this.dispenser.setTheaterMode(screen.getRows(), screen.getCols());
                this.drawArea(screen.getAreaState());

        }
    }
    
    private void drawArea(TheaterAreaState areaState){
        for (int i = 1; i <= areaState.getRows(); i++) {
            for (int j = 1; j <= areaState.getCols(); j++) {
                SeatState seat = areaState.getSeatState(i - 1, j - 1);
                if(seat == SeatState.notAvaible) {
                        this.dispenser.markSeat(i, j, 0);
                }else if(seat == SeatState.occupied){
                        this.dispenser.markSeat(i, j, 1);
                }else if (seat == SeatState.free){
                        this.dispenser.markSeat(i, j, 2);
                }

            }
        }
    }
    
    public void getOption(Screen screen){
        int i = 0;
        for(String msg: screen.getOptions()){
            this.dispenser.setOption(i, msg);
            i++;
           
        }
        if(screen.getOptions().size() < 6){
            for(int h = screen.getOptions().size(); h < 6; h++){
                this.dispenser.setOption(h, null);
            }
        }
    }

    public TheaterTicketDispenser getDispenser() {
        return dispenser;
    }

    public TranslatorManager getTranslator() {
        return translator;
    }

    public DispenserHardware getDw() {
        return dw;
    }
    

}
