package com.sagrada.ppp.view.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import java.util.ArrayList;

public class Lobby implements EventHandler<MouseEvent> {

    private BorderPane borderPane;
    private Button about;
    private Button play;
    private ArrayList<BusEventHandler> events;

    public Lobby() {
        this.borderPane = new BorderPane();
        this.events = new ArrayList<>();

        borderPane.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image("file:graphics/splash.png"),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(borderPane.getWidth(),borderPane.getHeight(),
                                        false,false,true,true)
                        )
                )
        );

        play = new Button();
        play.setText("Play");
        borderPane.setCenter(play);
        play.addEventHandler(MouseEvent.MOUSE_CLICKED,this);

        about = new Button();
        about.setText("About");
        borderPane.setBottom(about);
        BorderPane.setAlignment(about, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(about, new Insets(10));
        about.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }

    public BorderPane getLobby() {
        return borderPane;
    }

    public void attach(BusEventHandler busEventHandler) {
        events.add(busEventHandler);
    }

    @Override
    public void handle(MouseEvent event) {
        Button clickedBtn = (Button) event.getSource();

        if (clickedBtn.equals(about)) {
            Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
            aboutAlert.setTitle("About");
            aboutAlert.setHeaderText(null);
            aboutAlert.setContentText("Sagrada by PPP");
            aboutAlert.showAndWait();
        }
        else if (clickedBtn.equals(play)) {
            String username;
            do {
                TextInputDialog usernameDialog = new TextInputDialog(null);
                usernameDialog.setTitle("Username");
                usernameDialog.setHeaderText(null);
                usernameDialog.setContentText("Please enter your username!\nThis cannot be empty or contain spaces.");
                if (usernameDialog.showAndWait().isPresent()) {
                    username = usernameDialog.getResult();
                    if (username.contains(" ")) {
                        username = null;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText(null);
                        alert.setContentText("Error! Username cannot be empty or contain spaces.\nPlease try again!");
                        alert.showAndWait();
                    }
                    else {
                        for (BusEventHandler busEventHandler : events) {
                            busEventHandler.onClose(username);
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