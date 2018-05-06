package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.WindowPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuiView extends Application {


    private Scene scene;

    public Scene render(){


        WindowPanel wp = new WindowPanel(6,0);
        wp.addDice(0,new Dice());
        wp.addDice(1,new Dice());
        wp.addDice(2,new Dice());
        wp.addDice(3,new Dice());
        wp.addDice(4,new Dice());
        wp.addDice(5,new Dice());
        wp.addDice(6,new Dice());








        AnchorPane v = new AnchorPane(new WindowPanelPane(wp,500,500));
        scene = new Scene(v,500,500);
        return scene;
    }

    @Override
    public void start(Stage primaryStage)  {
        Scene s =render();
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
