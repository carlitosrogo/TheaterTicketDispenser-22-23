package main;

import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author carlosrodriguezgomez
 */
public class DispenserHardware {
    private TheaterTicketDispenser dis;

    public DispenserHardware(TheaterTicketDispenser dis) {
        this.dis = dis;
    }
    
    
    public void printTicket(List<String> ticket){
        this.dis.print(ticket);
        
    }
    public long retainCreditCard(boolean ret){
        this.dis.retainCreditCard(ret);
        return this.dis.getCardNumber();
    }
    public void expelCreaditCard(){
        this.dis.expelCreditCard(30);
        
    }
    public long getCardNumber(){
        return this.dis.getCardNumber();
    }
    
}
