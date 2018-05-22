package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Client;
import com.sagrada.ppp.Color;
import com.sagrada.ppp.PlayerScore;
import com.sagrada.ppp.WindowPanel;
import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.PublicObjectiveCard1;
import com.sagrada.ppp.cards.PublicObjectiveCard2;
import com.sagrada.ppp.cards.PublicObjectiveCard3;
import com.sagrada.ppp.controller.RemoteController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GuiView extends Application {

    @Override
    public void start(Stage primaryStage) {

        RemoteController controller = Client.getController();
        //new Lobby(primaryStage, controller);
        try {
            ArrayList<PublicObjectiveCard> publicObjectiveCards = new ArrayList<>();
            publicObjectiveCards.add(new PublicObjectiveCard1());
            publicObjectiveCards.add(new PublicObjectiveCard2());
            publicObjectiveCards.add(new PublicObjectiveCard3());
            ArrayList<PlayerScore> playersScore = new ArrayList<>();
            playersScore.add(new PlayerScore("pere", 1, Color.BLUE,
                    new WindowPanel(1, 0), 1, 2,
                    3, 4, 5, 6));
            playersScore.add(new PlayerScore("piccio", 1, Color.RED,
                    new WindowPanel(2, 0), 2, 3,
                    4, 5, 6, 7));
            playersScore.add(new PlayerScore("tom", 1, Color.GREEN,
                    new WindowPanel(3, 0), 3, 4,
                    5, 6, 7, 8));
            new ResultPane(playersScore, publicObjectiveCards, controller, primaryStage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}