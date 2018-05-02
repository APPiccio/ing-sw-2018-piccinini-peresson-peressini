package com.sagrada.ppp.view.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;


public class LobbyView extends Application {
    private Button loginButton;
    private GridPane lobbyContainer;
    private VBox loginContainer;
    private Scene scene;

    public static void main(String[] args) {
        new Thread(() -> Application.launch(LobbyView.class)).start();

    }
    public Scene render(){

        loginButton = new Button("Login");
        lobbyContainer = new GridPane();
        loginContainer = new VBox();
        Label title = new Label("Enter your username:");
        TextField username = new TextField();

        lobbyContainer.setHgap(10);
        lobbyContainer.setVgap(10);
        lobbyContainer.add(title,2,0,2,1);
        lobbyContainer.add(username,1,1,6,1);




        scene = new Scene(lobbyContainer,300,300);
        return scene;
    }

    @Override
    public void start(Stage primaryStage)  {

        primaryStage.setScene(render());
        primaryStage.show();
    }

}
