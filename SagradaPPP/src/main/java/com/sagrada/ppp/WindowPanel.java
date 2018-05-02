package com.sagrada.ppp;


import com.sagrada.ppp.utils.PrinterFormatter;
import com.sagrada.ppp.utils.StaticValues;
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
        ArrayList<Cell> tempCells = windowPanel.getCells();

        for (Cell cell : tempCells){
            this.cells.add(new Cell(cell));
        }
    }

    //cardNumber from 1 to 12
    //side 1 for the front


    public WindowPanel(int cardNumber, int side)  {

        int fileIndex = cardNumber * 2 - side;
        JSONTokener jsonTokener = null;
        try {
            jsonTokener = new JSONTokener(new FileReader("templates/panel" + fileIndex + ".json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(jsonTokener);
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

    public Cell getCell(int row, int col) {
        if ((row < 0 || row >= StaticValues.PATTERN_ROW) || (col < 0 || col >= StaticValues.PATTERN_COL)) {
            //access denied to wrong cells
            return null;
        }
        return new Cell(cells.get(row * StaticValues.PATTERN_COL + col));
    }

    public Cell getCell(int i) {
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

    /**
     * @return returns a copy of the cells on the pane
     */
    public ArrayList<Cell> getCells(){
        ArrayList<Cell> h = new ArrayList<>();
        for(Cell cell : cells){
            h.add(new Cell(cell));
        }
        return h;
    }

    /**
     * overload of addDice(int,Dice,bool,bool,bool) method, all restrictions are active.
     */
    public boolean addDice(int i, Dice dice) {
        return addDice(i, dice, false, false, false);
    }

    /**
     * function that adds a dice in an empty cell.
     * @param i: index that selects the cell in witch the dice will be added
     * @param dice: dice to add
     * @param ignoreColor: ignore color restrictions on windowWpane
     * @param ignoreValue: ignore value restrictions on windowPanel
     * @param ignorePosition: ignore positioning rules.
     * @return returns true if the operation is successful
     */
    public boolean addDice(int i, Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignorePosition) {
        if (diceOk(dice, i, ignoreColor, ignoreValue, ignorePosition)) {
            cells.get(i).setDiceOn(dice);
            return true;
        }
        System.out.println("PLACING ERROR >>> CELL " + i + " - WRONG DICE PLACEMENT, IF IS NOT INTENDED FIX IT" );
        return false;
    }

    /**
     * @param dice dice subject of the control
     * @param  i index of the cell
     * @return true if the operation is successful
     */
    private boolean diceOk(Dice dice, int i) {
        return diceOk(dice, i, false, false, false);
    }

    private boolean diceOk(Dice dice, int i, boolean ignoreColor, boolean ignoreValue, boolean ignorePosition) {
        Cell cell = cells.get(i);
        if (cell.hasDiceOn()) {
            System.out.println("PLACEMENT ERROR >>> ANOTHER DICE IN THIS POSITION");
            return false;
        }
        if (windowIsEmpty()) {
            if (!borderPosition(i)) {
                System.out.println("PLACEMENT ERROR >>> WINDOW EMPTY AND NO BORDER POSITION");
                return false;
            }
        }
        else {
            if (noDiceNear(i) && !ignorePosition) {
                System.out.println("PLACEMENT ERROR >>> NO DICE NEAR");
                return false;
            }
            if (hasSimilarDiceAttached(dice,i)) {
                System.out.println("PLACEMENT ERROR >>> SIMILAR ATTACHED");
                return false;
            }
        }
        if (diceOkWithRestriction(cell, dice, ignoreColor, ignoreValue)) return true;
        System.out.println("PLACEMENT ERROR >>> INVALID DICE FOR THIS CELL DUE TO RESTRICTION");
        return false;
    }

    public boolean noDiceNear(int i){
        int row = i / StaticValues.PATTERN_COL;
        int col = i - row*StaticValues.PATTERN_COL;

        row--;
        col--;

        for(int j = row; j < row + 3; j++){
            for(int k = col; k < col + 3; k++){
                if(validPosition(j,k)){
                    if(getCell(j,k).hasDiceOn()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validPosition(int row, int col){
        return row >= 0 && row < StaticValues.PATTERN_ROW && col >= 0 && col < StaticValues.PATTERN_COL;
    }

    private boolean borderPosition(int i){
        int row = i / StaticValues.PATTERN_ROW;
        int col = i - row*StaticValues.PATTERN_COL;

        return row == 0 || row == StaticValues.PATTERN_ROW || col == 0 || col == StaticValues.PATTERN_COL;
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
            Cell cell = getCell(row,col);
            if(cell.hasDiceOn()){
                return cell.getDiceOn().isSimilar(dice);
            }
        }
        return false;
    }


    public boolean addDiceOnCellWithPosition(int row, int col, Dice dice){
        int i = row * StaticValues.PATTERN_COL + col;
        if(diceOk(dice, i)){
            cells.get(i).setDiceOn(dice);
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
            if(!cells.get(i).equals(panel.getCell(i))) return false;
        }
        return true;
     }

    public String toString(){

        return PrinterFormatter.printWindowPanelContent(this);

    }

    //get index of cells where the dice can be put without breaking rules
    public ArrayList<Integer> getLegalPosition(Dice dice){
        ArrayList<Integer> h = new ArrayList<>();
        for(int i = 0; i < 19; i++){
            if (diceOk(dice, i)){
                h.add(i);
            }
        }
        return h;
    }

    //return the indexes of "dices" that can be put in the "i" cell
    public ArrayList<Integer> getLegalDicesFromSetAndCellIndex(ArrayList<Dice> dices, int i){
        ArrayList<Integer> h = new ArrayList<>();
        for(int j = 0; j < dices.size(); j++){
            if(diceOk(dices.get(j), i)) {
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
                if (diceOk(dices.get(j), i)){
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
        if (ignoreColor || ignoreValue) return true;
        if (cell.hasColorRestriction() && dice.getColor().equals(cell.getColor())) return true;
        if (cell.hasValueRestriction() && dice.getValue() == cell.getValue()) return true;
        return false;
    }

}