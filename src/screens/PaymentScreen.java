package screens;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import main.DispenserManager;
import main.DispenserHardware;
import main.Screen;
import main.ScreenMode;
import main.ScreenResult;
import urjc.UrjcBankServer;

/**
 *
 * @author carlosrodriguezgomez
 */
public class PaymentScreen extends Screen{
    
    private UrjcBankServer bank;
    private boolean operationDone;
    private int price;
    
    public PaymentScreen(int price, DispenserManager dispenseManager, ScreenMode mode) {
        super("PaymentScreen", dispenseManager, mode);
        this.price = price;
        this.bank = new UrjcBankServer();
        this.setDescription("El precio de su compra es de: " + price + "€");
        this.options = new ArrayList();
        this.setOptions(options);
        
    }
    public int getPrice(){
        return price;
    }
    public void setOptions(List<String> list){
        list.add("Cancelar");
    }
    public List<String> getOptions(){
        return options;
    }
    public boolean isOperationDone() {
        return operationDone;
    }
    public ScreenResult begin(DispenserHardware dw){
        if(this.bank.comunicationAvaiable()){
            return ScreenResult.continueInScreen;
        }else{
            this.dispenserManager.showScreen(30, new ErrorScreen("ErrorScreen",this.dispenserManager,ScreenMode.messageMode));
            return ScreenResult.exitScreen;
        }
    }
    public ScreenResult creditCardDetected(DispenserHardware dw){
        try{
            long creditCard = dw.retainCreditCard(false);
            this.operationDone = this.bank.doOperation(creditCard, this.getPrice());
            if(!this.operationDone){
                this.dispenserManager.showScreen(30, new ErrorScreen("ErrorScreen2", this.dispenserManager, ScreenMode.messageMode));
            }
        }catch (CommunicationException ex) {
            this.dispenserManager.showScreen(30, new ErrorScreen("ErrorScreen", this.dispenserManager, ScreenMode.messageMode));
            Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);

        }
        return ScreenResult.exitScreen;
    }
    public ScreenResult end(DispenserHardware d){
         return ScreenResult.exitScreen;
    }
}
