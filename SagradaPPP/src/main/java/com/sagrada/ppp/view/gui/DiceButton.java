package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.utils.StaticValues;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * A button that represent a dice.
 */
public class DiceButton extends Button {

    /**
     * Attribute used to indicate the selected state of a button.
     */
    private boolean selected = false;
    private int index;
    private Dice dice;

    /**
     * @param dice dice to mimic
     * @param diceWidth width of the button
     * @param diceHeight height of the button
     */
    DiceButton(Dice dice, double diceWidth, double diceHeight) {
        this.dice = dice;
        this.setPrefSize(diceWidth,diceHeight);
        this.setAlignment(Pos.CENTER);
        this.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image(StaticValues.getAssetUri(dice.getColor(), dice.getValue()),
                                        diceWidth, diceHeight, true, true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT)));
        this.getStyleClass().add("dicebutton");
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }

    boolean isSelected() {
        return selected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Dice getDice() {
        return new Dice(dice);
    }

}