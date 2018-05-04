package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.Cell;
import com.sagrada.ppp.Color;
import com.sagrada.ppp.WindowPanel;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static com.sagrada.ppp.utils.StaticValues.*;

public class WindowPanelPane extends GridPane implements EventHandler<MouseEvent> {
    WindowPanel panel;
    ArrayList<Button> cells;
    Label name,tokens;

    public WindowPanelPane(WindowPanel panel,double height,double width) {
        this.panel = panel;
        cells = new ArrayList<>();
        name = new Label(panel.getPanelName());
        tokens = new Label("Tokens:" + panel.getFavorTokens());
        double cellHeight = height/4;
        double cellWidth = width/4;
        this.setPadding(new Insets(10));
        this.setHgap(6);
        this.setVgap(6);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: black;" +
                "-fx-background-radius: 10px;");

        
        int col = 0;
        int row = 0;
        for (Cell c:panel.getCells()) {
            AnchorPane cell = new AnchorPane();
            cell.setId(Integer.toString(col) + Integer.toString(row));
            //cell.setPadding(new Insets(1));
            cell.setPrefSize(cellWidth,cellHeight);
            cell.setMaxSize(height,width);
            
            if(c.hasDiceOn()){

            }else if(c.hasColorRestriction()){
                cell.setBackground(
                        new Background(
                                new BackgroundImage(
                                        new Image(getAssetUri(c.getColor()),cellWidth,cellHeight,true,false),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));
            }else if(c.hasValueRestriction()){
                cell.setBackground(
                        new Background(
                                new BackgroundImage(
                                        new Image(getAssetUri(c.getValue()),cellWidth,cellHeight,true,false),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));
            }else {
                cell.setBackground(
                        new Background(
                                new BackgroundImage(
                                        new Image(FILE_URI_PREFIX + "graphics/blank.png",cellWidth,cellHeight,true,false),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));


            }


            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            this.add(cell,col,row);
            if(col < 4){
                col++;
            }else {
                col = 0;
                row++;
            }
        }
        name.setStyle("-fx-text-fill: white;");
        tokens.setStyle("-fx-text-fill: white;");
        GridPane.setHalignment(tokens,HPos.CENTER);
        GridPane.setHalignment(name,HPos.CENTER);
        this.add(name,1,4,3,1);
        this.add(tokens,4,4,1,1);


    }


    @Override
    public void handle(MouseEvent event) {
        System.out.println(event.getSource().toString());
    }

    private static String getAssetUri(Color color){
        switch (color){
            case GREEN:
                return FILE_URI_PREFIX + GREEN_CELL_ASSET;
            case RED:
                return  FILE_URI_PREFIX + RED_CELL_ASSET;
            case BLUE:
                return  FILE_URI_PREFIX + BLUE_CELL_ASSET;
            case YELLOW:
                return  FILE_URI_PREFIX + YELLOW_CELL_ASSET;
            case PURPLE:
                return  FILE_URI_PREFIX + PURPLE_CELL_ASSET;
            default:
                return RESET;
        }

    }
    private static String getAssetUri(int val){
        return FILE_URI_PREFIX +  RESTRICTION_CELL_ASSET + val + ".png";

    }
}
