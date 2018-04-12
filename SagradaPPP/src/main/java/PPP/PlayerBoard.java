package PPP;

import java.io.FileNotFoundException;
import java.util.ArrayList;



public class PlayerBoard {

    private WindowPanel activePanel;


    public PlayerBoard(int cardNumber, int side) throws FileNotFoundException {
        activePanel = new WindowPanel(cardNumber, side);
    }

    public WindowPanel getActivePanel(){
        return new WindowPanel(activePanel);
    }


}
