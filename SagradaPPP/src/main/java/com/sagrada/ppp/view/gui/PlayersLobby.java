package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.*;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlayersLobby extends UnicastRemoteObject implements LobbyObserver, EventHandler<MouseEvent> {

    private VBox vBoxPlayers;
    private VBox vBoxEventsTab;
    private JoinGameResult joinGameResult;
    private WindowsPanelSelection windowsPanelSelection;
    private transient RemoteController controller;
    private Stage stage;

    PlayersLobby(String username, RemoteController controller, Stage stage) throws RemoteException {
        vBoxPlayers = new VBox();
        vBoxEventsTab = new VBox();
        windowsPanelSelection = new WindowsPanelSelection();
        this.controller = controller;
        this.stage = stage;
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        VBox vBoxPlayersTab = new VBox();

        joinGameResult = controller.joinGame(username, this, windowsPanelSelection);
        vBoxPlayersTab.getChildren().addAll(playerID(), vBoxPlayers);
        setActivePlayers(joinGameResult.getPlayersUsername(), joinGameResult.getPlayersUsername().size());

        if (joinGameResult.getPlayersUsername().size() == 3) {
            long remainingTime = ((joinGameResult.getTimerStart() + StaticValues.getLobbyTimer()) -
                    System.currentTimeMillis())/1000;
            timerStarted(remainingTime);
        }

        Button exit = new Button("Exit");
        borderPane.setBottom(exit);
        BorderPane.setAlignment(exit, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(exit, new Insets(10));
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        Tab playersTab = new Tab("Players");
        playersTab.setClosable(false);
        vBoxPlayersTab.setSpacing(5);
        vBoxPlayersTab.setPadding(new Insets(10, 0, 0, 10));
        playersTab.setContent(vBoxPlayersTab);
        tabPane.getTabs().add(playersTab);

        Tab eventsTab = new Tab("Event Log");
        eventsTab.setClosable(false);
        vBoxEventsTab.setSpacing(5);
        vBoxEventsTab.setPadding(new Insets(10, 0, 0, 10));
        eventsTab.setContent(vBoxEventsTab);
        tabPane.getTabs().add(eventsTab);

        borderPane.setCenter(tabPane);

        stage.setScene(new Scene(borderPane, 700*1436/2156, 700));
        stage.setTitle("Players Lobby");
        stage.centerOnScreen();
        stage.show();
    }

    private Label playerID() {
        return new Label("Welcome " + joinGameResult.getUsername() + "!\n" +
                "You are participating at the game #" + joinGameResult.getGameHashCode() +
                "\nwith userID " + joinGameResult.getPlayerHashCode());
    }

    private void timerStarted(long remainingTime) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Timer");
        alert.setHeaderText(null);
        if (remainingTime == 1) {
            alert.setContentText("Your game will start in " + remainingTime + " second!");
            vBoxEventsTab.getChildren().add(
                    new Label("Timer started! Game will start in " + remainingTime + " second.")
            );
        } else {
            alert.setContentText("Your game will start in " + remainingTime + " seconds!");
            vBoxEventsTab.getChildren().add(
                    new Label("Timer started! Game will start in " + remainingTime + " seconds.")
            );
        }
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.show();
    }

    private void timerEnded() {
        windowsPanelSelection.init(controller, stage, joinGameResult);
    }

    private void timerInterrupted() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Timer");
        alert.setHeaderText(null);
        alert.setContentText("Timer interrupted. Waiting for other players...");
        vBoxEventsTab.getChildren().add(
                new Label("Timer interrupted. Waiting for other players...")
        );
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.show();
    }

    private void clearPlayers() {
        vBoxPlayers.getChildren().clear();
    }

    private void setActivePlayers(ArrayList<String> players, int numOfPlayers) {
        if (players.size() > 1) {
            vBoxPlayers.getChildren().add(new Label("There are " + numOfPlayers + " active players!"));
        }
        else {
            vBoxPlayers.getChildren().add(new Label("There is " + numOfPlayers + " active player!"));
        }
        for (String user : players) {
            vBoxPlayers.getChildren().add(new Label("Username: " + user));
        }
    }

    private void newPlayerEvent(String newPlayer) {
        vBoxEventsTab.getChildren().add(new Label(newPlayer + " has joined the game!"));
    }

    private void oldPlayerEvent(String oldPlayer) {
        vBoxEventsTab.getChildren().add(new Label(oldPlayer + " has left the game!"));
    }

    @Override
    public void onPlayerJoined(String username, ArrayList<String> players, int numOfPlayers) {
        Platform.runLater(() -> {
            clearPlayers();
            setActivePlayers(players, numOfPlayers);
            newPlayerEvent(username);
        }
        );
    }

    @Override
    public void onPlayerLeave(String username, ArrayList<String> players, int numOfPlayers) {
        Platform.runLater(() -> {
            clearPlayers();
            setActivePlayers(players, numOfPlayers);
            oldPlayerEvent(username);
        }
        );
    }

    @Override
    public void onTimerChanges(long timerStart, TimerStatus timerStatus) {
        long remainingTime = ((StaticValues.getLobbyTimer() + timerStart) - System.currentTimeMillis())/1000;
        Platform.runLater(() -> {
            if (timerStatus.equals(TimerStatus.START)) {
                timerStarted(remainingTime);
            }
            else if (timerStatus.equals(TimerStatus.FINISH)) {
                timerEnded();
            }
            else if (timerStatus.equals(TimerStatus.INTERRUPT)) {
                timerInterrupted();
            }
        }
        );
    }

    @Override
    public void handle(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Warning! You will lose your position in this game.");
        alert.setContentText("Are you sure you want to exit?");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        if (buttonType == ButtonType.OK) {
            try {
                controller.leaveLobby(joinGameResult.getGameHashCode(), joinGameResult.getUsername(), this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            new Lobby(stage, controller);
        }
    }

}