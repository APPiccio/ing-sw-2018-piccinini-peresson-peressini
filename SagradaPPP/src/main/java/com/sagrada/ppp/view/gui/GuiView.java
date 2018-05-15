package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Client;
import com.sagrada.ppp.controller.RemoteController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.rmi.RemoteException;

public class GuiView extends Application implements BusEventHandler, Bus {

    private Stage stage;
    private RemoteController controller;
    private Lobby lobby;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;
        controller = Client.getController();
        lobby = new Lobby();
        lobby.attach(this);
        new MainGamePane().draw();
        primaryStage.setTitle("Welcome to Sagrada");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300* 1400/2500);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(lobby.getLobby(),700*1436/2156,700));
        primaryStage.show();
    }

    @Override
    public void onClose(String username) {
        try {
            PlayersLobby playersLobby = new PlayersLobby(username, controller);
            playersLobby.attach(this);
            stage.setTitle("Players Lobby");
            stage.setScene(new Scene(playersLobby.getPlayersLobby(),700*1436/2156,700));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGameExit() {
        lobby = new Lobby();
        lobby.attach(this);
        stage.setTitle("Welcome to Sagrada");
        stage.setScene(new Scene(lobby.getLobby(), 700*1436/2156,700));
    }

}