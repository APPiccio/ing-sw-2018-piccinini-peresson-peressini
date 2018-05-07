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
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        LobbyPane lobbyPane = new LobbyPane();
        lobbyPane.attach(this);
        primaryStage.setScene(new Scene(lobbyPane,500,500));
        primaryStage.show();
    }

    @Override
    public void onClose(Pane pane) {
        stage.setScene(new Scene(new WindowPanelPane(
                new WindowPanel(6, StaticValues.FRONT_SIDE),500,500)));
    }

}