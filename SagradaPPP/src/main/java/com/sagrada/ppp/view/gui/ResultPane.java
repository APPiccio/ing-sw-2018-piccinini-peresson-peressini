package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.PlayerScore;
import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ResultPane extends UnicastRemoteObject {

    private VBox leftVBox;
    private VBox rightVBox;
    private VBox topVBox;
    private HBox publicObjectiveCardsHBox;
    private TableView<PlayerScore> tableView;
    private transient RemoteController controller;
    private Stage stage;

    ResultPane(ArrayList<PlayerScore> playersScore, ArrayList<PublicObjectiveCard> publicObjectiveCards,
               RemoteController controller, Stage stage) throws RemoteException {
        this.controller = controller;
        this.stage = stage;
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));

        leftVBox = new VBox();
        leftVBox.setSpacing(10);
        leftVBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(leftVBox, new Insets(10));

        rightVBox = new VBox();
        rightVBox.setSpacing(10);
        rightVBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(rightVBox, new Insets(10));

        topVBox = new VBox();
        topVBox.setSpacing(10);

        publicObjectiveCardsHBox = new HBox();
        publicObjectiveCardsHBox.setSpacing(10);
        publicObjectiveCardsHBox.setAlignment(Pos.TOP_CENTER);

        tableView = new TableView<>();
        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        drawWindowPanels(playersScore);
        borderPane.setLeft(leftVBox);
        borderPane.setRight(rightVBox);

        drawPublicObjectiveCards(publicObjectiveCards);
        drawResultTable(playersScore, publicObjectiveCards);
        borderPane.setCenter(topVBox);

        stage.setScene(new Scene(borderPane, 1920, 1080));
        stage.setTitle("Results");
        stage.centerOnScreen();
        stage.show();
    }

    private void drawWindowPanels(ArrayList<PlayerScore> playersScore) {
        int i = 0;
        for (PlayerScore playerScore : playersScore) {
            Image image = new Image(StaticValues.FILE_URI_PREFIX + "graphics/PrivateCards/private_" +
                    playerScore.getPrivateColor().toString().toLowerCase() + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            WindowPanelPane windowPanelPane = new WindowPanelPane(playerScore.getWindowPanel(),
                    135, 135);
            Label label = new Label(playerScore.getUsername() + "'s panel");
            if (playersScore.size() == 2) {
                if (i == 0) {
                    leftVBox.getChildren().addAll(imageView, windowPanelPane, label);
                } else {
                    rightVBox.getChildren().addAll(imageView, windowPanelPane, label);
                }
            } else {
                if (i < 2) {
                    leftVBox.getChildren().addAll(imageView, windowPanelPane, label);
                } else {
                    rightVBox.getChildren().addAll(imageView, windowPanelPane, label);
                }
            }
            i++;
        }
    }

    private void drawPublicObjectiveCards(ArrayList<PublicObjectiveCard> publicObjectiveCards) {
        for (PublicObjectiveCard publicObjectiveCard : publicObjectiveCards) {
            Image image = new Image(StaticValues.FILE_URI_PREFIX + "graphics/PublicCards/public_" +
                    publicObjectiveCard.getId() + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(180);
            imageView.setFitHeight(240);
            imageView.setPreserveRatio(true);
            publicObjectiveCardsHBox.getChildren().add(imageView);
            publicObjectiveCardsHBox.setAlignment(Pos.TOP_CENTER);
        }
        topVBox.getChildren().add(publicObjectiveCardsHBox);
    }

    private void drawResultTable(ArrayList<PlayerScore> playersScore,
                                 ArrayList<PublicObjectiveCard> publicObjectiveCards) {
        //Create column Username
        TableColumn<PlayerScore, String> usernameCol = new TableColumn<>("Username");
        //Create column TotalPoints
        TableColumn<PlayerScore, Integer> totalPointsCol = new TableColumn<>("Total Points");
        //Create column Favor Token
        TableColumn<PlayerScore, Integer> favorTokenCol = new TableColumn<>("Favor Token");
        //Create column Empty Cell
        TableColumn<PlayerScore, Integer> emptyCellsCol = new TableColumn<>("Empty Cells");
        //Create column PrivateObjectiveCard
        TableColumn<PlayerScore, Integer> privateObjectiveCardCol =
                new TableColumn<>("Private Objective Card Points");
        //Create column PublicObjectiveCard
        TableColumn<PlayerScore, Integer> publicObjectiveCardCol =
                new TableColumn<>("Public Objective Cards Points");
        //Create sub-column for PublicObjectiveCardCol (PublicObjectiveCard1)
        TableColumn<PlayerScore, Integer> publicObjectiveCard1Col =
                new TableColumn<>(publicObjectiveCards.get(0).getName());
        //Create sub-column for PublicObjectiveCardCol (PublicObjectiveCard2)
        TableColumn<PlayerScore, Integer> publicObjectiveCard2Col =
                new TableColumn<>(publicObjectiveCards.get(1).getName());
        //Create sub-column for PublicObjectiveCardCol (PublicObjectiveCard3)
        TableColumn<PlayerScore, Integer> publicObjectiveCard3Col =
                new TableColumn<>(publicObjectiveCards.get(2).getName());
        //Adding sub-columns to PublicObjectiveCardCol
        publicObjectiveCardCol.getColumns().add(publicObjectiveCard1Col);
        publicObjectiveCardCol.getColumns().add(publicObjectiveCard2Col);
        publicObjectiveCardCol.getColumns().add(publicObjectiveCard3Col);

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        totalPointsCol.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        favorTokenCol.setCellValueFactory(new PropertyValueFactory<>("favorTokenPoints"));
        emptyCellsCol.setCellValueFactory(new PropertyValueFactory<>("emptyCellsPoints"));
        privateObjectiveCardCol.setCellValueFactory(new PropertyValueFactory<>("privateObjectiveCardPoints"));
        publicObjectiveCard1Col.setCellValueFactory(new PropertyValueFactory<>("publicObjectiveCard1Points"));
        publicObjectiveCard2Col.setCellValueFactory(new PropertyValueFactory<>("publicObjectiveCard2Points"));
        publicObjectiveCard3Col.setCellValueFactory(new PropertyValueFactory<>("publicObjectiveCard3Points"));

        ObservableList<PlayerScore> list = getList(playersScore);
        tableView.setItems(list);

        tableView.getColumns().add(usernameCol);
        tableView.getColumns().add(totalPointsCol);
        tableView.getColumns().add(favorTokenCol);
        tableView.getColumns().add(emptyCellsCol);
        tableView.getColumns().add(privateObjectiveCardCol);
        tableView.getColumns().add(publicObjectiveCardCol);

        topVBox.getChildren().add(tableView);
    }

    private ObservableList<PlayerScore> getList(ArrayList<PlayerScore> playersScore) {
        return FXCollections.observableArrayList(playersScore);
    }

}