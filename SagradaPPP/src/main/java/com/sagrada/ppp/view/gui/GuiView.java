package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Client;
import com.sagrada.ppp.WindowPanel;
import com.sagrada.ppp.controller.RemoteController;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiView extends Application {

    @Override
    public void start(Stage primaryStage) {

        RemoteController controller = Client.getController();
        new Lobby(primaryStage, controller);

    }

}