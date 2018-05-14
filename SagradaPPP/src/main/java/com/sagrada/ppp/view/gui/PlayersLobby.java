package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.*;
import com.sagrada.ppp.controller.RemoteController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlayersLobby extends UnicastRemoteObject implements LobbyObserver, EventHandler<MouseEvent> {

    private BorderPane borderPane;
    private VBox vBoxPlayers;
    private VBox vBoxEvents;
    private Button exit;
    private String username;
    private int playerHashCode;
    private int gameHashCode;
    private RemoteController controller;
    private JoinGameResult joinGameResult;
    private ArrayList<Bus> events;
    private ArrayList<String> playersUsername;

    public PlayersLobby(String username, RemoteController controller) throws RemoteException {

        this.controller = controller;
        borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab playersTab = new Tab();
        Tab eventsTab = new Tab();
        vBoxPlayers = new VBox();
        vBoxEvents = new VBox();
        events = new ArrayList<>();

        joinGameResult = controller.joinGame(username, this);
        playersUsername = joinGameResult.getPlayersUsername();
        this.username = joinGameResult.getUsername();
        this.gameHashCode = joinGameResult.getGameHashCode();
        this.playerHashCode = joinGameResult.getPlayerHashCode();
        vBoxPlayers.getChildren().add(playerID());
        setActivePlayers();

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

    }

    public Label playerID() {
        return new Label("Welcome " + username + "!\n" +
                "You are participating at the game #" + gameHashCode +
                "\nwith userID " + playerHashCode + "\n");
    }

    public void attach(Bus bus) {
        events.add(bus);
    }

    public BorderPane getPlayersLobby() {
        return borderPane;
    }

    public void clearPlayers() {
        vBoxPlayers.getChildren().clear();
        vBoxPlayers.getChildren().add(playerID());
    }

    public void setActivePlayers() {
        int activePlayers = playersUsername.size();
        if (playersUsername.size() > 1) {
            vBoxPlayers.getChildren().add(new Label("There are " + activePlayers + " active players!"));
        } else {
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

    }

    @Override
    public void handle(MouseEvent event) {

        Button clickedBtn = (Button) event.getSource();

        if (clickedBtn.equals(exit)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Warning! You will lose your position in this game.");
            alert.setContentText("Are you sure you want to exit?");
            alert.showAndWait();
            ButtonType buttonType = alert.getResult();
            if (buttonType == ButtonType.OK) {
                try {
                    controller.leaveLobby(gameHashCode, username, this);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                for (Bus bus : events) {
                    bus.onGameExit();
                }
            }
        }
    }
}
