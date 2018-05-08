package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiView extends Application implements BusEventHandler {

    private Stage stage;

    @Override
    public void start(Stage primaryStage)  {
        stage = primaryStage;

        LobbyPane lobbyPane = new LobbyPane(200);
        lobbyPane.attach(this);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300* 1400/2500);
        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(lobbyPane,700*1436/2156,700));
        primaryStage.show();
    }

    @Override
    public void onClose(Pane pane) {
        stage.setScene(new Scene(new WindowPanelPane(
                new WindowPanel(6, StaticValues.FRONT_SIDE),500,500)));
        stage.setResizable(true);
    }

}