package com.sagrada.ppp;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class WindowPanel {

    private String panelName;
    private int favorTokens;
    private int cardID;
    private ArrayList<Cell> cells;


    public WindowPanel(WindowPanel windowPanel){
        this.panelName = windowPanel.getPanelName();
        this.favorTokens = windowPanel.getFavorTokens();
        this.cardID = windowPanel.getCardID();

        this.cells = new ArrayList<>();
        ArrayList<Cell> cells = windowPanel.getCells();

        for (Cell cell : cells){
            this.cells.add(new Cell(cell));
        }
    }


    //cardNumber from 1 to 12
    //side 1 for the front


    public WindowPanel(int cardNumber, int side)  {

        int fileIndex = cardNumber * 2 - side;
        JSONTokener tokener = null;
        try {
            tokener = new JSONTokener(new FileReader("templates/panel" + fileIndex + ".json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(tokener);
        JSONArray jsonArrayCells = jsonObject.getJSONArray("cells");
        cells = new ArrayList<>();

        //getting card name and favor token from JSON
        cardID = jsonObject.getInt("cardID");
        favorTokens = jsonObject.getInt("favorTokens");
        panelName = jsonObject.getString("name");


        String color;
        String value;

        for (Object jsonArrayCell : jsonArrayCells) {
            JSONObject jsonCell = (JSONObject) jsonArrayCell;

            color = jsonCell.get("color").toString();
            value = jsonCell.get("value").toString();

            if (!color.equals(StaticValues.NULL_JSON_VALUE)) {
                //colored cell
                cells.add(new Cell(Color.getColor(color)));

            } else if (!value.equals(StaticValues.NULL_JSON_VALUE)) {
                //value cell
                cells.add(new Cell(Integer.parseInt(value)));
            } else {
                //blank cell
                cells.add(new Cell());
            }
        }
    }

    public Cell getCellWithPosition(int row, int col) {
        if ((row < 0 || row >= StaticValues.PATTERN_ROW) || (col < 0 || col >= StaticValues.PATTERN_COL)) {
            //access denied to wrong cells
            return null;
        }
        return new Cell(cells.get(row * StaticValues.PATTERN_COL + col));
    }

    public Cell getCellWithIndex(int i) {
        if (i < 0 || i > cells.size()) return null;
        return new Cell(cells.get(i));
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public ArrayList<Cell> getCells(){
        return new ArrayList<>(cells);
    }

    public boolean addDiceOnCellWithIndex(int i, Dice dice) {
        return addDiceOnCellWithIndex(i, dice, false, false, false);
    }

    public boolean addDiceOnCellWithIndex(int i, Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignorePosition) {
        Cell currentCell = cells.get(i);
        if (currentCell == null) return false;
        if (diceOk(currentCell, dice, i, ignoreColor, ignoreValue, ignorePosition)) {
            currentCell.setDiceOn(dice);
            return true;
        }
        System.out.println("WARNING ---> CELL " + i + " - WRONG DICE PLACEMENT, IF IS NOT INTENDED FIX IT" );
        return false;
    }

    private boolean diceOk(Cell cell, Dice dice, int i) {
        return diceOk(cell, dice, i, false, false, false);
    }

    private boolean diceOk(Cell cell, Dice dice, int i, boolean ignoreColor, boolean ignoreValue, boolean ignorePosition) {
        if (cell.hasDiceOn()) {
            System.out.println("WARNING --> ANOTHER DICE IN THIS POSITION");
            return false;
        }
        if (windowIsEmpty()) {
            if (!borderPosition(i)) {
                System.out.println("WARNING --> WINDOW EMPTY AND NO BORDER POSITION");
                return false;
            }
        }
        else {
            if (!atLeastOneNear(i)) {
                System.out.println("WARNING --> NO DICE NEAR");
                return false;
            }
            if (hasSimilarDiceAttached(dice,i)) {
                System.out.println("WARNING --> SIMILAR ATTACHED");
                return false;
            }
        }
        if (diceOkWithRestriction(cell, dice, ignoreColor, ignoreValue)) return true;
        System.out.println("WARNING --> INVALID DICE FOR THIS CELL DUE TO RESTRICTION");
        return false;
    }

    private boolean atLeastOneNear(int i){
        int row = i / StaticValues.PATTERN_COL;
        int col = i - row*StaticValues.PATTERN_COL;

        row--;
        col--;

        for(int j = row; j < row + 3; j++){
            for(int k = col; k < col + 3; k++){
                if(validPosition(j,k)){
                    if(getCellWithPosition(j,k).hasDiceOn()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean validPosition(int row, int col){
        if(row >= 0 && row < StaticValues.PATTERN_ROW && col >= 0 && col < StaticValues.PATTERN_COL) return true;
        return false;
    }

    private boolean borderPosition(int i){
        int row = i / StaticValues.PATTERN_ROW;
        int col = i - row*StaticValues.PATTERN_COL;

        if(row == 0 || row == StaticValues.PATTERN_ROW || col == 0 || col == StaticValues.PATTERN_COL) return true;
        return false;
    }

    private boolean hasSimilarDiceAttached(Dice dice, int i){
        int row = i / StaticValues.PATTERN_COL;
        int col = i - row*StaticValues.PATTERN_COL;
        //testing above
        row--;
        if(cellPairSimilarity(row,col,dice)) return true;
        //testing under
        row = row + 2;
        if(cellPairSimilarity(row,col,dice)) return true;
        //testing left
        row--;
        col--;
        if(cellPairSimilarity(row,col,dice)) return true;
        //testing right
        col = col + 2;
        if(cellPairSimilarity(row,col,dice)) return true;
        return false;
    }

    public boolean cellPairSimilarity(int row, int col, Dice dice){
        if(validPosition(row,col)){
            Cell cell = getCellWithPosition(row,col);
            if(cell.hasDiceOn()){
                if(cell.getDiceOn().isSimilar(dice)) return true;
            }
        }
        return false;
    }


    public boolean addDiceOnCellWithPosition(int row, int col, Dice dice){
        int i = row * StaticValues.PATTERN_COL + col;
        Cell currentCell = cells.get(i);
        if (currentCell == null) return false;
        if(diceOk(currentCell,dice, i)){
            currentCell.setDiceOn(dice);
            return true;
        }
        return false;
    }

    private boolean windowIsEmpty(){
        for(Cell cell : cells){
            if (cell.hasDiceOn()) return false;
        }
        return true;
    }

    public boolean equals(Object object){
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof WindowPanel))return false;
        WindowPanel panel = (WindowPanel) object;
        if (this.cells.size() != panel.getCells().size()) return false;
        for(int i = 0; i < cells.size(); i++){
            if(!cells.get(i).equals(panel.getCellWithIndex(i))) return false;
        }
        return true;
     }

    public String toString(){
        StringBuilder myString = new StringBuilder();
        myString.append("PanelName = " + getPanelName().toUpperCase() + "\n" );
        myString.append("Favor tokens = " + getFavorTokens() + "\n" );
        myString.append("CardID = " + getCardID() + "\n\n" );
        Cell currentCell;

        int absIndex = 0;

        for(int k = 0; k < StaticValues.PATTERN_ROW; k++){
            for(int l = 0; l < StaticValues.PATTERN_COL; l++){
                currentCell = getCellWithIndex(absIndex);

                if(currentCell.hasColorRestriction()){
                    myString.append(currentCell.getColor().toString() + "|\t\t ");
                }
                else{
                    if(currentCell.hasValueRestriction()){
                        myString.append(currentCell.getValue() + "|\t\t ");
                    }
                    else{
                        myString.append("BLANK |\t\t");
                    }
                }
                absIndex++;
            }
            myString.append("\n____________________________________\n");
        }
        return myString.toString();

    }

    //get index of cells where the dice can be put without breaking rules
    public ArrayList<Integer> getLegalPosition(Dice dice){
        ArrayList<Integer> h = new ArrayList<>();
        for(int i = 0; i < 19; i++){
            if (diceOk(getCellWithIndex(i), dice, i)){
                h.add(i);
            }
        }
        return h;
    }

    //return the indexes of "dices" that can be put in the "i" cell
    public ArrayList<Integer> getLegalDicesFromSetAndCellIndex(ArrayList<Dice> dices, int i){
        ArrayList<Integer> h = new ArrayList<>();
        for(int j = 0; j < dices.size(); j++){
            if(diceOk(getCellWithIndex(i), dices.get(j), i)) {
                h.add(j);
            }
        }
        return h;
    }

    //return dice from a set that can match a generic position in the panel (playable dice)
    public ArrayList<Integer> getLegalDices(ArrayList<Dice> dices){
        ArrayList<Integer> h = new ArrayList<>();
        for(int j = 0; j < dices.size(); j++){
            for(int i = 0; i < cells.size(); i++){
                Cell cell = getCellWithIndex(i);
                if (diceOk(cell, dices.get(j), i)){
                    h.add(j);
                    break;
                }
            }
        }
        return h;
    }


    public Dice removeDice(int i){
        Cell cell = cells.get(i);
        Dice dice = cell.getDiceOn();
        cell.setDiceOn(null);
        cells.set(i, cell);
        return dice;
    }

    public boolean diceOkWithRestriction(Cell cell, Dice dice, boolean ignoreColor, boolean ignoreValue) {
        if (!cell.hasColorRestriction() && !cell.hasValueRestriction()) return true;
        if (!ignoreValue && cell.hasValueRestriction() && dice.getValue() == cell.getValue()) return true;
        if (!ignoreColor && cell.hasColorRestriction() && dice.getColor().equals(cell.getColor())) return true;
        return false;
    }

}