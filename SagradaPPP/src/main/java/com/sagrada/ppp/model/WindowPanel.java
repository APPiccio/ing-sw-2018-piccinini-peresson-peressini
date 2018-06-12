package com.sagrada.ppp.model;


import com.sagrada.ppp.utils.PrinterFormatter;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.utils.WindowPanelParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * WindowPanel is used to implement methods and attributes o
 */
public class WindowPanel implements Serializable {

    private String panelName;
    private int favorTokens;
    private int cardID;
    private ArrayList<Cell> cells;
    private static ArrayList<WindowPanel> loadedPanels = new ArrayList<>();


    /**
     * @param windowPanel window pane to be copied
     */
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

    /**
     * Intended to be used only by the WindowPanelParser!
     * @param panelName
     * @param favorTokens
     * @param cardID
     * @param cells
     */
    public WindowPanel(String panelName,int favorTokens, int cardID, ArrayList<Cell> cells){
        this.panelName = panelName;
        this.favorTokens = favorTokens;
        this.cardID = cardID;
        this.cells = cells;
    }


    /**
     * @param cardNumber is the ID of the physic card that has face up and face down, between 1 and 12
     * @param side front or rear of the card, 1 means face up, 0 means face down
     */
    public WindowPanel(int cardNumber, int side)  {
        this(getPanel(cardNumber,side));


    }
    private static WindowPanel getPanel(int cardNumber, int side){
        if(loadedPanels.size() == 0){
            try {
                loadedPanels = WindowPanelParser.getPanelsFromFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedPanels.get(((2*cardNumber)-side)-1);
    }

    public static int getNumberOfPanels() {
        if(loadedPanels.size() == 0){
            try {
                loadedPanels = WindowPanelParser.getPanelsFromFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedPanels.size()/2;
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
     * @param ignoreColor: ignore color restrictions on windowPanel
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


    /**
     * @param dice dice to be put
     * @param i cell index from 0 to 19
     * @param ignoreColor flag showing if color restriction has to be avoided
     * @param ignoreValue flag showing if color restriction has to be avoided
     * @param ignorePosition flag showing if color restriction has to be avoided
     * @return true if the placement has been done successfully
     */
    public boolean diceOk(Dice dice, int i, boolean ignoreColor, boolean ignoreValue, boolean ignorePosition) {
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

    /**
     * @param i cell index
     * @return true if the i cell has a dice in the 3x3 square around, false otherwise
     */
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

    /**
     * @param row
     * @param col
     * @return true if the position is present in the 4x5 pattern table.
     */
    private boolean validPosition(int row, int col){
        return row >= 0 && row < StaticValues.PATTERN_ROW && col >= 0 && col < StaticValues.PATTERN_COL;
    }

    /**
     * @param i cell index
     * @return true if the cell i is in the edge of the panel
     */
    private boolean borderPosition(int i){
        int row = i / StaticValues.PATTERN_COL;
        int col = i - row*StaticValues.PATTERN_COL;

        return row == 0 || row == StaticValues.PATTERN_ROW-1 || col == 0 || col == StaticValues.PATTERN_COL-1;
    }

    /**
     * @param dice dice to be put
     * @param i index of the cell
     * @return return false if there is a dice on a cell next to i that has same color OR same value of dice parameter
     */
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

    /**
     * @return true if there is no placed dices
     */
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

    /**
     * @param dice
     * @return array of the indexes where the dice can be put without breaking any rules
     */
    public ArrayList<Integer> getLegalPosition(Dice dice){
        ArrayList<Integer> h = new ArrayList<>();
        for(int i = 0; i <= 19; i++){
            if (diceOk(dice, i)){
                h.add(i);
            }
        }
        return h;
    }

    /**
     * @param dices generic subset of dices
     * @param i target index position
     * @return the indexes of "dices" that can be put in the "i" cell
     */
    public ArrayList<Integer> getLegalDicesFromSetAndCellIndex(ArrayList<Dice> dices, int i){
        ArrayList<Integer> h = new ArrayList<>();
        for(int j = 0; j < dices.size(); j++){
            if(diceOk(dices.get(j), i)) {
                h.add(j);
            }
        }
        return h;
    }

    /**
     * @param dices subset of dices
     * @return dice from a set that can match a generic position in the panel (playable dice)
     */
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


    /**
     * @param i cell index
     * @return the removed dice from the panel in the i position
     */
    public Dice removeDice(int i){
        Cell cell = cells.get(i);
        Dice dice = cell.getDiceOn();
        cell.setDiceOn(null);
        cells.set(i, cell);
        return dice;
    }

    /**
     * @param cell target cell
     * @param dice dice to bu put
     * @param ignoreColor ingore cell color restriction
     * @param ignoreValue ignore cell value restriction
     * @return true if the dice doesn't break any rules except positioning
     */
    public boolean diceOkWithRestriction(Cell cell, Dice dice, boolean ignoreColor, boolean ignoreValue) {
        if (!cell.hasColorRestriction() && !cell.hasValueRestriction()) return true;
        if (ignoreColor&&ignoreValue) return true;
        if (ignoreColor) {
            if (cell.hasValueRestriction()) {
                if (dice.getValue() == cell.getValue()) return true;
            }
            else
                return true;
        }else if(ignoreValue){
            if(cell.hasColorRestriction()){
                if (dice.getColor().equals(cell.getColor())) return true;
            }
            else
                return true;
        }else {
            if (cell.hasColorRestriction() && dice.getColor().equals(cell.getColor())) return true;
            if (cell.hasValueRestriction() && dice.getValue() == cell.getValue()) return true;
        }
        return false;
    }

    public int getEmptyCells() {
        return (int) cells.stream().filter(x -> !x.hasDiceOn()).count();
    }

    public int getPrivateScore(Color color) {
        return cells.stream().filter(Cell::hasDiceOn)
                .filter(x -> x.getDiceOn().getColor().equals(color))
                .map(x -> x.getDiceOn().getValue())
                .reduce(0,(x,y) -> x + y);
    }

}