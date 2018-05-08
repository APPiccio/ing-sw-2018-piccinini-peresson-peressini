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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static com.sagrada.ppp.utils.StaticValues.*;

public class WindowPanelPane extends GridPane implements EventHandler<MouseEvent> {
    private WindowPanel panel;
    private ArrayList<Button> cells;
    private Label name,tokens;

    public WindowPanelPane(WindowPanel panel,double height,double width) {
        this.panel = panel;
        cells = new ArrayList<>();
        name = new Label(panel.getPanelName());
        tokens = new Label("Tokens:" + panel.getFavorTokens());
        double cellHeight = height/4;
        double cellWidth = width/4;
        this.setPadding(new Insets(height*.05));
        this.setHgap(height*.02);
        this.setVgap(width*.02);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: black;" +
                "-fx-background-radius: 10px;");


        int col = 0;
        int row = 0;
        for (Cell c:panel.getCells()) {
            BorderPane cell = new BorderPane();
            cell.setId(Integer.toString(col) + Integer.toString(row));
            //cell.setPadding(new Insets(1));
            cell.setPrefSize(cellWidth,cellHeight);
            cell.setMaxSize(height,width);

           if(c.hasColorRestriction()){
                cell.setBackground(
                        new Background(
                                new BackgroundImage(
                                        new Image(getAssetUri(c.getColor()),cellWidth,cellHeight,false,true),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));
            }else if(c.hasValueRestriction()){
                cell.setBackground(
                        new Background(
                                new BackgroundImage(
                                        new Image(getAssetUri(c.getValue()),cellWidth,cellHeight,false,true),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));
            }else {
                cell.setBackground(
                        new Background(
                                new BackgroundImage(
                                        new Image(FILE_URI_PREFIX + BLANK_CELL_ASSET,cellWidth,cellHeight,false,false),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));


            }
            if(c.hasDiceOn()){
                Button diceButton = new Button();
                diceButton.setPrefSize(cellWidth*.80,cellHeight*.80);
                diceButton.setAlignment(Pos.CENTER);
                diceButton.setMouseTransparent(true);
                diceButton.setBackground(
                        new Background(
                                new BackgroundImage(
                                        new Image(getAssetUri(c.getDiceOn().getColor(),c.getDiceOn().getValue()),cellWidth*.80,cellHeight*.80,true,true),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));
                cell.setCenter(diceButton);


            }


            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            this.add(cell,col,row);
            if(col < PATTERN_COL-1){
                col++;
            }else {
                col = 0;
                row++;
            }
        }

        //Adding labels on the bottom of the grid view
        name.setStyle("-fx-text-fill: white;");
        tokens.setStyle("-fx-text-fill: white;");
        GridPane.setHalignment(tokens,HPos.CENTER);
        GridPane.setHalignment(name,HPos.CENTER);
        this.add(name,1,4,3,1);
        this.add(tokens,4,4,1,1);


    }


    /**
     * @implNote handling mouse event on a cell
     */
    @Override
    public void handle(MouseEvent event) {
        System.out.println(event.getSource().toString());
    }


}
