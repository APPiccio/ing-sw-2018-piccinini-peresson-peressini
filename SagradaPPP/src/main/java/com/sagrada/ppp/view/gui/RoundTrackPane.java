package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.RoundTrack;

import com.sagrada.ppp.utils.StaticValues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RoundTrackPane extends BorderPane {


    private RoundTrack roundTrack;
    private FlowPane mainPane;
    private double width,height;
    public RoundTrackPane(){

    }
    public RoundTrackPane(RoundTrack roundTrack,double width,double height) {
        this.roundTrack = roundTrack;
        this.width = width;
        this.height = height;
    }

    //TODO delete this, this is here only for testing purpose
    public void init(){
        height = 70;
        width = 70;
        RoundTrack r = new RoundTrack();
        for(int i = 1;i<=5;i++){
            for(int j = 0;j<9;j++){
                if(!(i == 5 && j ==3 ))r.addDice(i,new Dice());
            }
        }
        roundTrack = r;
        draw();
    }
    //sets all the layout parameters, call only after init or using the "complete" constructor
    private void draw(){
        mainPane = new FlowPane();
        this.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(5),new Insets(0))));
        this.setPadding(new Insets(10));

        for (int i = 1; i <= 10; i++) {
            Button roundIndicator = new Button();
            roundIndicator.setId(Integer.toString(i));
            FlowPane.setMargin(roundIndicator,new Insets(1,5,1,5));
            roundIndicator.setMinSize(width,height);
            roundIndicator.setBackground(
                    new Background(
                            new BackgroundImage(
                                    new Image(StaticValues.FILE_URI_PREFIX+"graphics/roundTrack_"+i+".png",width,height,false,true),
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.CENTER,
                                    BackgroundSize.DEFAULT)));

            if (i < roundTrack.getCurrentRound()) {
                roundIndicator.setDisable(true);
                Tooltip roundToolTip = new Tooltip();
                roundToolTip.setGraphic(new SelectDicePane(roundTrack.getDicesOnRound(i),width/1.5,height/1.5));
                roundIndicator.setTooltip(roundToolTip);
            }


            mainPane.getChildren().add(roundIndicator);
            Label title = new Label("RoundTrack");
            title.setTextFill(Color.WHITE);
            title.setPadding(new Insets(0,0,5,0));
            this.setTop(title);
            this.setBottom(mainPane);

        }
    }
    public void setRoundTrack(RoundTrack roundTrack) {
        this.roundTrack = roundTrack;
        draw();
    }
}
