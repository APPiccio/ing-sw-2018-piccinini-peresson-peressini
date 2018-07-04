package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.model.JoinGameResult;
import com.sagrada.ppp.model.Player;
import com.sagrada.ppp.model.ReconnectionResult;
import com.sagrada.ppp.utils.PlayerTokenSerializer;
import com.sagrada.ppp.utils.StaticValues;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class StartGameView implements EventHandler<MouseEvent> {

    private Button about;
    private Button play;
    private Button reconnect;
    private Stage stage;
    private transient RemoteController controller;

    StartGameView(Stage stage, RemoteController controller) {
        this.stage = stage;
        this.controller = controller;
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 700*1436/2156, 700);
        URL url = null;
        try {
            url = new URL(StaticValues.STYLE_SHEET_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }

        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        borderPane.setBackground(
                new Background(
                        new BackgroundImage(
                                new Image("file:resources/graphics/splash.png"),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(borderPane.getWidth(), borderPane.getHeight(),
                                        false,false,true,true)
                        )
                )
        );
        VBox centerContainer = new VBox();
        centerContainer.setSpacing(20);
        centerContainer.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(centerContainer,Pos.CENTER);
        play = new Button("Play");
        play.getStyleClass().add("sagradabutton");
        centerContainer.getChildren().add(play);
        borderPane.setCenter(centerContainer);
        play.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        reconnect = new Button("Reconnect");
        reconnect.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
        reconnect.getStyleClass().add("sagradabutton");
        if(PlayerTokenSerializer.isTokenPresent()){
            centerContainer.getChildren().add(reconnect);
        }
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });


        about = new Button("About");

        about.getStyleClass().add("sagradabutton");
        borderPane.setBottom(about);
        BorderPane.setAlignment(about, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(about, new Insets(10));
        about.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        stage.setScene(scene);
        stage.setTitle("Welcome to Sagrada");
        stage.setMinHeight(300);
        stage.setMinWidth(300*1400/2500);
        stage.getIcons().add(new Image("file:resources/graphics/icon.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }


    /**
     * Create and display a TextInputDialog for the user's name.
     * <p>
     * This method handles the possibility of entering in the TextField
     * an empty String or a String containing whitespaces.
     */
    private void createTextInputDialog() {
        String username;
        do {
            TextInputDialog usernameDialog = new TextInputDialog(null);
            usernameDialog.setTitle("Username");
            usernameDialog.setHeaderText(null);
            usernameDialog.setContentText("Please enter your username!\nThis cannot be empty or contain spaces.");
            Button button = (Button) usernameDialog.getDialogPane().lookupButton(ButtonType.OK);
            button.getStyleClass().add("sagradabutton");
            button.disableProperty().bind(Bindings.isEmpty(usernameDialog.getEditor().textProperty()));
            usernameDialog.initModality(Modality.APPLICATION_MODAL);
            usernameDialog.initOwner(stage);
            if (usernameDialog.showAndWait().isPresent()) {
                username = usernameDialog.getResult();
                if (username.contains(" ")) {
                    username = null;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText(null);
                    alert.setContentText("Error! Username cannot contain spaces.\nPlease try again!");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(stage);
                    alert.showAndWait();
                }
                else {
                    try {
                        new LobbyView(username, controller, stage);
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

    /**
     * Handles the mouse-click
     *
     * @param event Mouse-clicked event
     */
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
        else if (clickedBtn.equals(reconnect)){
            JoinGameResult reconnectionToken = PlayerTokenSerializer.deserialize();
            MainGameView mainGameView;
            try {
                mainGameView = new MainGameView();
                ReconnectionResult reconnectionResult = controller.reconnection(reconnectionToken.getPlayerHashCode(),reconnectionToken.getGameHashCode(),mainGameView);
                if(reconnectionResult.result) {
                    Player currentPlayer = reconnectionResult.gameStartMessage.currentPlayer;
                    mainGameView.init(currentPlayer.getPrivateColor(), reconnectionToken, reconnectionResult.gameStartMessage, controller, stage,true);
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(reconnectionResult.message);
                    alert.setContentText(null);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(stage);
                    alert.show();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else if (clickedBtn.equals(play)) {
            createTextInputDialog();
        }
    }

}