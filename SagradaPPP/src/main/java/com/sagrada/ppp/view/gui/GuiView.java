package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Client;
import com.sagrada.ppp.WindowPanel;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class GuiView extends Application implements BusEventHandler {

    private Stage stage;
    public RemoteController controller;


    @Override
    public void start(Stage primaryStage) throws RemoteException {
        controller = Client.getController();
        stage = primaryStage;

        LobbyPane lobbyPane = new LobbyPane(200);
        lobbyPane.attach(this);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300* 1400/2500);
        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(lobbyPane,700*1436/2156,700));
        primaryStage.show();
    }

    public void attachController(RemoteController controller){
        this.controller = controller;
    }

    public void show(){
        launch();
    }

    @Override
    public void onClose(Pane pane) {
        stage.setScene(new Scene(new WindowPanelPane(
                new WindowPanel(6, StaticValues.FRONT_SIDE),500,500)));
        stage.setResizable(true);
    }

}