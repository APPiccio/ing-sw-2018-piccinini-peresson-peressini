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
import java.util.HashMap;

public class PlayersLobby extends UnicastRemoteObject implements LobbyObserver, EventHandler<MouseEvent> {

    private VBox vBoxPlayers;
    private VBox vBoxEvents;
    private Button exit;
    private String username;
    private int playerHashCode;
    private int gameHashCode;
    transient RemoteController controller;
    private ArrayList<String> playersUsername;
    private Stage stage;
    private static final String TIMER = "Timer";
    private JoinGameResult joinGameResult;
    private WindowsPanelSelection windowsPanelSelection;

    public PlayersLobby(String username, RemoteController controller, Stage stage) throws RemoteException {

        this.windowsPanelSelection = new WindowsPanelSelection();
        this.stage = stage;
        this.controller = controller;
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab playersTab = new Tab();
        Tab eventsTab = new Tab();
        vBoxPlayers = new VBox();
        vBoxEvents = new VBox();

        joinGameResult = controller.joinGame(username, this, windowsPanelSelection);
        playersUsername = joinGameResult.getPlayersUsername();
        this.username = joinGameResult.getUsername();
        this.gameHashCode = joinGameResult.getGameHashCode();
        this.playerHashCode = joinGameResult.getPlayerHashCode();
        long lobbyTimerStartTime = joinGameResult.getTimerStart();
        vBoxPlayers.getChildren().add(playerID());
        setActivePlayers();

        if (joinGameResult.getPlayersUsername().size() == 3) {
            long remainingTime = ((lobbyTimerStartTime + StaticValues.getLobbyTimer()) - System.currentTimeMillis())/1000;
            timerStarted(remainingTime);
        }

        exit = new Button();
        exit.setText("Exit");
        borderPane.setBottom(exit);
        BorderPane.setAlignment(exit, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(exit, new Insets(10));
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        playersTab.setText("Players");
        playersTab.setClosable(false);
        vBoxPlayers.setSpacing(5);
        vBoxPlayers.setPadding(new Insets(10, 0, 0, 10));
        playersTab.setContent(vBoxPlayers);
        tabPane.getTabs().add(playersTab);

        eventsTab.setText("Log");
        eventsTab.setClosable(false);
        vBoxEvents.setSpacing(5);
        vBoxEvents.setPadding(new Insets(10, 0, 0, 10));
        eventsTab.setContent(vBoxEvents);
        tabPane.getTabs().add(eventsTab);

        borderPane.setCenter(tabPane);

        stage.setScene(new Scene(borderPane, 700*1436/2156,700));
        stage.setTitle("Players Lobby");
        stage.show();

    }

    public Label playerID() {
        return new Label("Welcome " + username + "!\n" +
                "You are participating at the game #" + gameHashCode +
                "\nwith userID " + playerHashCode + "\n");
    }

    public void timerStarted(long remainingTime) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TIMER);
        alert.setHeaderText(null);
        if (remainingTime == 1) {
            alert.setContentText("Your game will start in " + remainingTime + " second!");
            vBoxEvents.getChildren().add(
                    new Label("Timer started! Game will start in " + remainingTime + " second.")
            );
        } else {
            alert.setContentText("Your game will start in " + remainingTime + " seconds!");
            vBoxEvents.getChildren().add(
                    new Label("Timer started! Game will start in " + remainingTime + " seconds.")
            );
        }
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        Stage tmpStage = (Stage) alert.getDialogPane().getScene().getWindow();
        tmpStage.setAlwaysOnTop(true);
        tmpStage.show();
    }

    public void timerEnded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(TIMER);
        alert.setHeaderText(null);
        alert.setContentText("Countdown completed or full lobby.\nYour game will start soon!");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        Stage tmpStage = (Stage) alert.getDialogPane().getScene().getWindow();
        tmpStage.setAlwaysOnTop(true);
        tmpStage.show();
        vBoxEvents.getChildren().add(
                new Label("Countdown completed or full lobby. Game will start soon.")
        );
        windowsPanelSelection.init(controller, stage, joinGameResult);
    }

    public void timerInterrupted() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(TIMER);
        alert.setHeaderText(null);
        alert.setContentText("Timer interrupted. Waiting for other players...");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        Stage tmpStage = (Stage) alert.getDialogPane().getScene().getWindow();
        tmpStage.setAlwaysOnTop(true);
        tmpStage.show();
        vBoxEvents.getChildren().add(
                new Label("Timer interrupted. Waiting for other players...")
        );
    }

    public void clearPlayers() {
        vBoxPlayers.getChildren().clear();
        vBoxPlayers.getChildren().add(playerID());
    }

    public void setActivePlayers() {
        int activePlayers = playersUsername.size();
        if (playersUsername.size() > 1) {
            vBoxPlayers.getChildren().add(new Label("There are " + activePlayers + " active players!"));
        }
        else {
            vBoxPlayers.getChildren().add(new Label("There is " + activePlayers + " active player!"));
        }
        for (String user : playersUsername) {
            vBoxPlayers.getChildren().add(new Label("Username: " + user));
        }
    }

    public void newPlayerEvent(String username) {
        vBoxEvents.getChildren().add(new Label(username + " has joined the game!"));
    }

    public void oldPlayerEvent(String username) {
        vBoxEvents.getChildren().add(new Label(username + " has left the game!"));
    }

    public void chosenPanels(HashMap<String, WindowPanel> panels) {
        for (String u : panels.keySet()) {
            vBoxEvents.getChildren().add(new Label(u + " has chosen panel " + panels.get(u).getPanelName() + " !"));
        }
    }

    @Override
    public void onPlayerJoined(String username, ArrayList<String> players, int numOfPlayers) {
        playersUsername = players;
        Platform.runLater(() -> {
            clearPlayers();
            setActivePlayers();
            newPlayerEvent(username);
        }
        );
    }

    @Override
    public void onPlayerLeave(String username, ArrayList<String> players, int numOfPlayers) {
        playersUsername = players;
        Platform.runLater(() -> {
            clearPlayers();
            setActivePlayers();
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
        });
    }

    @Override
    public void handle(MouseEvent event) {

        Button clickedBtn = (Button) event.getSource();

        if (clickedBtn.equals(exit)) {
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
                    controller.leaveLobby(gameHashCode, username, this);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                new Lobby(stage, controller);
            }
        }
    }

}