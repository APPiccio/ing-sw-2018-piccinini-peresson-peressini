package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.Dice;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class SelectDicePane extends GridPane implements EventHandler<MouseEvent> {

    private GuiEventBus eventBus;
    private int roundIndex;
    private GridPane gridPane;
    private Alert alertParent;

    SelectDicePane(ArrayList<Dice> dices, double diceWidth, double diceHeight,
                   GuiEventBus eventBus, int roundIndex, Alert alertParent) {
        gridPane = new GridPane();
        gridPane.setVgap(diceHeight/10);
        gridPane.setHgap(diceWidth/10);
        this.alertParent = alertParent;
        this.setPadding(new Insets(10,10,10,10));
        this.getChildren().add(gridPane);
        this.eventBus = eventBus;
        this.roundIndex = roundIndex;
        int row = 0;
        int col = 0;
        for(Dice dice: dices) {
            DiceButton diceButton = new DiceButton(dice, diceWidth,diceHeight);
            diceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            gridPane.add(diceButton,col,row);
            if(col < 4){
                col++;
            }else {
                col = 0;
                row++;
            }
        }
        Button closeButton = new Button("Close");
        closeButton.setOnMouseClicked(e ->
            alertParent.hide());

    }

    @Override
    public void handle(MouseEvent event) {
        DiceButton diceButton = (DiceButton) event.getSource();
        int diceIndex = gridPane.getChildren().indexOf(diceButton);
        System.out.println(diceButton.getDice());
        alertParent.close();

        alertParent.getButtonTypes().add(ButtonType.CANCEL);
        alertParent.hide();
        alertParent.getButtonTypes().remove(ButtonType.CANCEL);
        eventBus.onRoundTrackDiceClicked(diceIndex, roundIndex);
    }

}