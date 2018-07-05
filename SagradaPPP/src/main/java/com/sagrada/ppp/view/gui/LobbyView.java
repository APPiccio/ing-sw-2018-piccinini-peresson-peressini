package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.PlayerTokenSerializer;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import static java.lang.System.*;

public class LobbyView extends UnicastRemoteObject implements LobbyObserver, EventHandler<MouseEvent> {

    private VBox vBoxPlayers;
    private VBox vBoxEventsTab;
    private volatile JoinGameResult joinGameResult;
    private WindowPanelsSelectionView windowPanelsSelectionView;
    private transient RemoteController controller;
    private Stage stage;

    LobbyView(String username, RemoteController controller, Stage stage) throws RemoteException {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #373A3C");
        Scene scene = new Scene(borderPane, 700*1436/2156, 700);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().remove("scroll-pane");
        TabPane tabPane = new TabPane();
        VBox vBoxPlayersTab = new VBox();
        URL url = null;
        try {
            url = new URL(StaticValues.STYLE_SHEET_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            out.println("Resource not found. Aborting.");
            exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        vBoxPlayers = new VBox();
        vBoxEventsTab = new VBox();
        windowPanelsSelectionView = new WindowPanelsSelectionView();
        this.controller = controller;
        this.stage = stage;
        //lambda that controls the closing window behaviour
        this.stage.setOnCloseRequest(t -> {
            try {
                controller.disconnect(joinGameResult.getGameHashCode(),joinGameResult.getPlayerHashCode());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Platform.exit();
            exit(0);
        });

        joinGameResult = this.controller.joinGame(username, this);
        controller.attachGameObserver(joinGameResult.getGameHashCode(), windowPanelsSelectionView,
                joinGameResult.getPlayerHashCode());
        PlayerTokenSerializer.serialize(joinGameResult);
        vBoxPlayersTab.getChildren().addAll(playerID(), vBoxPlayers);
        setActivePlayers(joinGameResult.getPlayersUsername(), joinGameResult.getPlayersUsername().size());

        if (joinGameResult.getPlayersUsername().size() == 3) {
            long remainingTime = ((joinGameResult.getTimerStart() + StaticValues.LOBBY_TIMER) -
                    currentTimeMillis())/1000;
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
        eventsTab.setContent(scrollPane);
        scrollPane.setContent(vBoxEventsTab);
        tabPane.getTabs().add(eventsTab);

        borderPane.setCenter(tabPane);

        stage.setScene(scene);
        stage.setTitle("Lobby");
        stage.show();
    }

    /**
     * This method creates a label containing the information of the player
     * @return label with user information
     */
    private Label playerID() {
        return new Label("Welcome " + joinGameResult.getUsername() + "!\n" +
                "You are participating at the game #" + joinGameResult.getGameHashCode() +
                "\nwith userID " + joinGameResult.getPlayerHashCode());
    }

    /**
     * This method creates an alert when timer started or when a new player join the Lobby
     * @param remainingTime time left to before game start
     */
    private void timerStarted(long remainingTime) {
        Platform.runLater(() -> {
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
        });
    }

    /**
     * Invoking the new scene after timer end
     */
    private void timerEnded() {
        Platform.runLater(() -> windowPanelsSelectionView.init(controller, stage, joinGameResult));
    }

    /**
     * This method create an alert when the timer is interrupted due to a player disconnection
     * and when there is only one player left in the Lobby
     */
    private void timerInterrupted() {
        Platform.runLater(() -> {
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
        });
    }

    /**
     * This methods clear the active players list on the lobby main view
     */
    private void clearPlayers() {
        vBoxPlayers.getChildren().clear();
    }

    /**
     * This method updates the active players list on the Lobby main view
     * @param players active players
     * @param numOfPlayers number of active players
     */
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

    /**
     * This method updates the event tab when a new player join the game
     * @param newPlayer new active player
     */
    private void newPlayerEvent(String newPlayer) {
        vBoxEventsTab.getChildren().add(new Label(newPlayer + " has joined the game!"));
    }

    /**
     * This method updates the event tab when a player left the game
     * @param oldPlayer disconnected player
     */
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
    public void onTimerChanges(long timerStart, TimerStatus timerStatus, long duration) {
        long remainingTime = ((duration + timerStart) - currentTimeMillis()) / 1000;
        if (timerStatus.equals(TimerStatus.START)) {
            timerStarted(remainingTime);
        } else if (timerStatus.equals(TimerStatus.FINISH)) {
            new Thread(() -> {
                while(joinGameResult == null);
                timerEnded();
            }).start();
        } else if (timerStatus.equals(TimerStatus.INTERRUPT)) {
            timerInterrupted();
        }
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
            new StartGameView(stage, controller);
        }
    }

    @Override
    public void rmiPing() throws RemoteException {
        //do nothing
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}