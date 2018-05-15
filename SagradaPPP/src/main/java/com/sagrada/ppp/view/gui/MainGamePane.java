package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.RoundTrack;
import com.sagrada.ppp.WindowPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainGamePane extends BorderPane {

    RoundTrack roundTrack;
    ArrayList<WindowPanel> opponentsWindowPanels;
    WindowPanel playersWindowPanel;
    Stage mainStage;
    Double height,widht = 100d;

    public MainGamePane() {
        Stage stage = new Stage();
        stage.setScene(new Scene(this));
        stage.show();
    }

    public void draw(){

        VBox opponentsWindowPanelsPane = new VBox();
        FlowPane bottomContainer = new FlowPane();
        RoundTrackPane roundTrackPane = new RoundTrackPane();
        VBox activeDiceContainer = new VBox();
        HBox centerContainer = new HBox();
        DiceButton activeDice = new DiceButton(new Dice(),70,70);
        VBox draftPoolContainer = new VBox();
        FlowPane draftPool = new FlowPane();
        activeDiceContainer.setMinSize(100,100);

        StackPane.setAlignment(activeDice,Pos.CENTER);
        Label activeDiceTitle = new Label("ActiveDice");
        activeDiceTitle.setTextFill(Color.BLACK);
        activeDiceTitle.setAlignment(Pos.CENTER);

        activeDiceContainer.setAlignment(Pos.CENTER);
        activeDiceContainer.getChildren().add(activeDiceTitle);
        activeDiceContainer.getChildren().add(activeDice);
        roundTrackPane.init();
        roundTrackPane.draw();
        this.setBackground(new Background(new BackgroundFill(Color.DARKGREY,new CornerRadii(5),new Insets(0))));


        bottomContainer.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(bottomContainer,Pos.BOTTOM_CENTER);
        bottomContainer.getChildren().addAll(activeDiceContainer,roundTrackPane);
        this.setBottom(bottomContainer);



        Label draftPoolTitle = new Label("DraftPool");
        draftPoolTitle.setTextFill(Color.BLACK);
        draftPoolTitle.setAlignment(Pos.CENTER);
        draftPoolContainer.getChildren().addAll(draftPoolTitle,draftPool);
        centerContainer.getChildren().add(draftPoolContainer);
        this.setCenter(centerContainer);


        opponentsWindowPanelsPane.setFillWidth(false);
        opponentsWindowPanelsPane.getChildren().add(new WindowPanelPane(new WindowPanel(1,1),200,300));
        opponentsWindowPanelsPane.getChildren().add(new WindowPanelPane(new WindowPanel(1,1),200,200));
        opponentsWindowPanelsPane.getChildren().add(new WindowPanelPane(new WindowPanel(1,1),200,200));
        this.setRight(opponentsWindowPanelsPane);
    }
    //TODO add start game response as a parameter to set all attributes
    public void init(){

    }
}
