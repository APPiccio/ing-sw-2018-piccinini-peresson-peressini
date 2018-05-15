package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.GameObserver;
import com.sagrada.ppp.RoundTrack;
import com.sagrada.ppp.WindowPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class MainGamePane extends BorderPane implements GameObserver {

    RoundTrack roundTrack;
    ArrayList<WindowPanel> opponentsWindowPanels;
    ArrayList<Dice> draftPool;
    WindowPanel playersWindowPanel;
    Double height,widht = 100d;

    VBox opponentsWindowPanelsPane;
    FlowPane bottomContainer;
    RoundTrackPane roundTrackPane;
    VBox activeDiceContainer;
    HBox centerContainer;
    DiceButton activeDice;
    VBox draftPoolContainer;
    FlowPane draftPoolPane;

    public MainGamePane() {
        try {
            UnicastRemoteObject.exportObject(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(this));
        stage.show();
        //todo remove this
        draftPool = new ArrayList<>();
        for(int i = 0; i<5;i++){
            draftPool.add(new Dice());
        }
    }

    public void draw(){

        opponentsWindowPanelsPane = new VBox();
        bottomContainer = new FlowPane();
        roundTrackPane = new RoundTrackPane();
        activeDiceContainer = new VBox();
        centerContainer = new HBox();
        activeDice = new DiceButton(new Dice(),70,70);
        draftPoolContainer = new VBox();
        draftPoolPane = new FlowPane();
        activeDiceContainer.setMinSize(100,100);

        StackPane.setAlignment(activeDice,Pos.CENTER);
        Label activeDiceTitle = new Label("ActiveDice");
        activeDiceTitle.setTextFill(Color.BLACK);
        activeDiceTitle.setAlignment(Pos.CENTER);

        activeDiceContainer.setAlignment(Pos.TOP_CENTER);
        activeDiceContainer.getChildren().add(activeDiceTitle);
        //placeholder
        activeDiceContainer.getChildren().add(activeDice);
        //todo change this with init(RoundTrack)
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

        draftPoolContainer.setAlignment(Pos.CENTER);
        draftPoolContainer.getChildren().addAll(draftPoolTitle, draftPoolPane);

        for (Dice dice:draftPool) {
            DiceButton diceButton = new DiceButton(dice,50,50);
            draftPoolPane.getChildren().addAll(diceButton);
        }
        draftPoolPane.setHgap(2);
        draftPoolPane.setVgap(2);
        draftPoolPane.setPrefWrapLength(190);
        centerContainer.getChildren().add(draftPoolContainer);
        centerContainer.setAlignment(Pos.CENTER);
        this.setCenter(centerContainer);


        opponentsWindowPanelsPane.setFillWidth(false);
        opponentsWindowPanelsPane.setAlignment(Pos.CENTER);
        opponentsWindowPanelsPane.setSpacing(5);
        opponentsWindowPanelsPane.getChildren().add(new Label("OpponentsPanels:"));
        opponentsWindowPanelsPane.getChildren().add(new WindowPanelPane(new WindowPanel(1,1),240,200));
        opponentsWindowPanelsPane.getChildren().add(new WindowPanelPane(new WindowPanel(1,0),240,200));
        opponentsWindowPanelsPane.getChildren().add(new WindowPanelPane(new WindowPanel(9,1),240,200));
        this.setRight(opponentsWindowPanelsPane);
    }
    //TODO add start game response as a parameter to set all attributes
    public void init(){

    }

    @Override
    public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen) throws RemoteException {

    }
}
