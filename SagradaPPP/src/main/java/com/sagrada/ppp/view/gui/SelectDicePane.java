package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.Dice;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class SelectDicePane extends GridPane implements EventHandler<MouseEvent> {

    public SelectDicePane(ArrayList<Dice> dices, double diceWidth, double diceHeight ) {
        this.setVgap(diceHeight/10);
        this.setHgap(diceWidth/10);
        int row = 0;
        int col = 0;
        for(Dice dice: dices) {
            DiceButton diceButton = new DiceButton(dice, diceWidth,diceHeight);
            diceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            this.add(diceButton,col,row);
            if(col < 4){
                col++;
            }else {
                col = 0;
                row++;
            }
        }

    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println(((DiceButton)event.getSource()).getDice().toString());
    }
}
