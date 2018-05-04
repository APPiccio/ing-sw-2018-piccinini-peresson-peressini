package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.WindowPanel;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;


public class LobbyView extends Application {

    private Scene scene;

    public static void main(String[] args) {
        new Thread(() -> Application.launch(LobbyView.class)).start();

    }
    public Scene render(){




        AnchorPane v = new AnchorPane(new WindowPanelPane(new WindowPanel(4,0),400,400));
        scene = new Scene(v,450,400);
        return scene;
    }

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(1000);
        Scene s =render();
        primaryStage.setScene(s);
        primaryStage.show();
    }

}
