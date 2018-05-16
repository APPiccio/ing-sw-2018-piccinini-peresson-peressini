package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.controller.RemoteController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class Lobby implements EventHandler<MouseEvent> {

    private Button about;
    private Button play;
    private Stage stage;
    private transient RemoteController controller;

    Lobby(Stage stage, RemoteController controller) {
        this.stage = stage;
        this.controller = controller;
        BorderPane borderPane = new BorderPane();

        borderPane.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image("file:graphics/splash.png"),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(borderPane.getWidth(), borderPane.getHeight(),
                                        false,false,true,true)
                        )
                )
        );

        play = new Button("Play");
        borderPane.setCenter(play);
        play.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        about = new Button("About");
        borderPane.setBottom(about);
        BorderPane.setAlignment(about, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(about, new Insets(10));
        about.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        stage.setScene(new Scene(borderPane, 700*1436/2156, 700));
        stage.setTitle("Welcome to Sagrada");
        stage.setMinHeight(300);
        stage.setMinWidth(300*1400/2500);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void handle(MouseEvent event) {
        Button clickedBtn = (Button) event.getSource();
        if (clickedBtn.equals(about)) {
            Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
            aboutAlert.setTitle("About");
            aboutAlert.setHeaderText(null);
            aboutAlert.setContentText("Sagrada by PPP");
            aboutAlert.initModality(Modality.APPLICATION_MODAL);
            aboutAlert.initOwner(stage);
            aboutAlert.showAndWait();
        }
        else if (clickedBtn.equals(play)) {
            String username;
            do {
                TextInputDialog usernameDialog = new TextInputDialog(null);
                usernameDialog.setTitle("Username");
                usernameDialog.setHeaderText(null);
                usernameDialog.setContentText("Please enter your username!\nThis cannot be empty or contain spaces.");
                usernameDialog.initModality(Modality.APPLICATION_MODAL);
                usernameDialog.initOwner(stage);
                if (usernameDialog.showAndWait().isPresent()) {
                    username = usernameDialog.getResult();
                    if (username.contains(" ")) {
                        username = null;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText(null);
                        alert.setContentText("Error! Username cannot be empty or contain spaces.\nPlease try again!");
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.initOwner(stage);
                        alert.showAndWait();
                    }
                    else {
                        try {
                            new PlayersLobby(username, controller, stage);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    break;
                }
            } while (username == null);
        }
    }

}