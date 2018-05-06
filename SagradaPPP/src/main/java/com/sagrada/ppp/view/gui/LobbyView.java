package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Dice;
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


        WindowPanel wp = new WindowPanel(6,0);
        wp.addDice(0,new Dice());
        wp.addDice(1,new Dice());
        wp.addDice(2,new Dice());
        wp.addDice(3,new Dice());
        wp.addDice(4,new Dice());
        wp.addDice(5,new Dice());
        wp.addDice(6,new Dice());








        AnchorPane v = new AnchorPane(new WindowPanelPane(wp,700,700));
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
