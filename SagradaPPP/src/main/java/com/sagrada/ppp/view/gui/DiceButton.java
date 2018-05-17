package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.utils.StaticValues;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class DiceButton extends Button {


    private boolean selected = false;
    private Dice dice;
    public DiceButton(Dice dice,double diceWidth,double diceHeight) {
        this.dice = dice;
        this.setPrefSize(diceWidth,diceHeight);
        this.setAlignment(Pos.CENTER);
        this.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image(StaticValues.getAssetUri(dice.getColor(),dice.getValue()),diceWidth,diceHeight,true,true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT)));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public Dice getDice() {
        return new Dice(dice);
    }
}

