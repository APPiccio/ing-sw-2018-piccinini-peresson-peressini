package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.Cell;
import com.sagrada.ppp.model.WindowPanel;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.sagrada.ppp.utils.StaticValues.*;

public class WindowPanelPane extends GridPane implements EventHandler<MouseEvent> {
    private WindowPanel panel;
    private Label name;
    private Label tokens;
    private GuiEventBus eventBus;
    private double width;
    private double height;
    private static final boolean DRAW_VECTOR_CELLS = true;

    WindowPanelPane(WindowPanel panel, double height, double width) {
        this.panel = panel;

        name = new Label();
        tokens = new Label();
        this.width = width;
        this.height = height;

        this.setMinSize(width/2,height/2);
        this.setPadding(new Insets(10));
        this.setHgap(5);
        this.setVgap(5);
        this.setAlignment(Pos.CENTER);
        this.setEffect(new DropShadow(10,Color.BLACK));
        this.setBackground(new Background(
                new BackgroundFill(Color.web("#585b5d"),new CornerRadii(10), new Insets(0))
        ));
        this.autosize();

        draw();


        //Adding labels on the bottom of the grid view
        name.setStyle("-fx-text-fill: white;");
        tokens.setStyle("-fx-text-fill: white;");
        GridPane.setHalignment(tokens,HPos.CENTER);
        GridPane.setHalignment(name,HPos.CENTER);
        this.add(name,1,4,3,1);
        this.add(tokens,4,4,1,1);


    }

    private void draw(){
        if(panel == null) return;
        double cellHeight = height/4;
        double cellWidth = width/4;
        int col = 0;
        int row = 0;
        name.setText(panel.getPanelName());
        tokens.setText("Tokens:" + panel.getFavorTokens());
        for (Cell c:panel.getCells()) {
            CellPane cell = new CellPane(row,col,cellWidth,cellHeight,c);
            cell.setPrefSize(cellWidth,cellHeight);

            cell.drawCell();


            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            this.add(cell,col,row);
            if(col < PATTERN_COL-1){
                col++;
            }else {
                col = 0;
                row++;
            }
        }
    }

    public void setPanel(WindowPanel panel) {
        this.panel = panel;
        draw();
    }
    void setObserver(GuiEventBus guiEventBus){
        this.eventBus = guiEventBus;
    }

    /**
     * @implNote handling mouse event on a cell
     */
    @Override
    public void handle(MouseEvent event) {
        CellPane cell = ((CellPane) event.getSource());
        if(eventBus !=  null) {
            if(panel.getCell(cell.row,cell.col).hasDiceOn()){
                eventBus.onDiceClicked(cell.row,cell.col);
            }else {
                eventBus.onCellClicked(cell.row, cell.col);
            }
        }
    }
    public static Color getColor(com.sagrada.ppp.model.Color color){
        switch (color){
            case GREEN:
                return Color.web("589D5A");
            case RED:
                return Color.web("BE321E");
            case BLUE:
                return Color.web("67B1B8");
            case YELLOW:
                return Color.web("DAC706");
            case PURPLE:
                return Color.web("9F3D80");
            default:
                return Color.BLACK;
        }
    }

    private class CellPane extends BorderPane{

        private int row;
        private int col;
        private double cellWidth;
        private double cellHeight;
        private Cell cell;
        private static final int cornerRadius = 2;

        private CellPane(int row, int col,double cellWidth,double cellHeight,Cell cell) {
            this.row = row;
            this.col = col;
            this.cell = cell;
            this.cellWidth = cellWidth;
            this.cellHeight = cellHeight;
            this.setMinSize(cellWidth,cellHeight);
        }
        //used only to create tooltip
        private CellPane(double cellWidth,double cellHeight,Cell cell){
            cell.setDiceOn(null);
            this.cell = cell;
            this.cellWidth = cellWidth;
            this.cellHeight = cellHeight;
            this.setMinSize(cellWidth,cellHeight);
        }


        private CellPane drawCell(){
            if(cell.hasColorRestriction()){
                if (!DRAW_VECTOR_CELLS) {
                    this.setBackground(
                            new Background(
                                    new BackgroundImage(
                                            new Image(getAssetUri(cell.getColor()),cellWidth,cellHeight,true,true),
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundPosition.CENTER,
                                            BackgroundSize.DEFAULT)));
                }else {
                    this.setBackground(new Background(new BackgroundFill(getColor(cell.getColor()),new CornerRadii(cornerRadius), Insets.EMPTY)));
                }
            }else if(cell.hasValueRestriction()){
                if (!DRAW_VECTOR_CELLS) {
                    this.setBackground(
                            new Background(
                                    new BackgroundImage(
                                            new Image(getAssetUri(cell.getValue()),cellWidth,cellHeight,true,true),
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundPosition.CENTER,
                                            BackgroundSize.DEFAULT)));
                }else {
                    this.setBackground(new Background(new BackgroundFill(Color.web("95979A"),new CornerRadii(cornerRadius), Insets.EMPTY)));
                    Label text = new Label();
                    text.setText(Integer.toString(cell.getValue()));
                    text.setStyle("-fx-font-size: 32pt;\n" +
                            "    -fx-font-family: \"Segoe UI Semibold\";\n" +
                            "    -fx-text-fill: white;\n" +
                            "    -fx-opacity: 1;");
                    this.setCenter(text);
                }
            }else {
                if (!DRAW_VECTOR_CELLS) {
                    this.setBackground(
                            new Background(
                                    new BackgroundImage(
                                            new Image(FILE_URI_PREFIX + BLANK_CELL_ASSET,cellWidth,cellHeight,true,false),
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundPosition.CENTER,
                                            BackgroundSize.DEFAULT)));
                }else {
                    this.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(cornerRadius), Insets.EMPTY)));
                }


            }
            if(cell.hasDiceOn()){
                DiceButton diceButton = new DiceButton(cell.getDiceOn(),cellWidth*.80,cellHeight*.80);
                diceButton.setMouseTransparent(true);
                diceButton.setMinSize(50,50);
                Tooltip tooltip = new Tooltip();
                tooltip.setGraphic(new CellPane(cellWidth,cellHeight,cell).drawCell());
                Tooltip.install(this,tooltip);
                this.setCenter(diceButton);


            }
            return this;
        }

    }
}

