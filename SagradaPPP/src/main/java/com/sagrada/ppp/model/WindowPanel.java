package com.sagrada.ppp.model;

import com.sagrada.ppp.utils.PrinterFormatter;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.utils.WindowPanelParser;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class WindowPanel implements Serializable {

    private String panelName;
    private int favorTokens;
    private int cardID;
    private ArrayList<Cell> cells;

    /**
     * loadedPanels contains all the panels parsed from .json files in the 'templates' folder
     */
    private static ArrayList<WindowPanel> loadedPanels = new ArrayList<>();

    /**
     * @param windowPanel panel to be copied
     */
    public WindowPanel(WindowPanel windowPanel) {
        if (windowPanel != null) {
            panelName = windowPanel.getPanelName();
            favorTokens = windowPanel.getFavorTokens();
            cardID = windowPanel.getCardID();
            cells = windowPanel.getCells();
        }
        else {
            try {
                throw new NullPointerException("Null windowPanel received as parameter...");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Intended to be used only by the WindowPanelParser!
     * @param panelName windowPanel name
     * @param favorTokens windowPanel difficulty
     * @param cardID windowPanel ID
     * @param cells windowPanel cells
     */
    public WindowPanel(String panelName, int favorTokens, int cardID, ArrayList<Cell> cells) {
        this.panelName = panelName;
        this.favorTokens = favorTokens;
        this.cardID = cardID;
        this.cells = cells;
    }

    /**
     * @param cardNumber is the ID of the physic card that has face up and face down, between 1 and 12
     * @param side front or rear of the card, 1 means face up, 0 means face down
     */
    public WindowPanel(int cardNumber, int side) {
        this(getPanel(cardNumber, side));
    }

    /**
     * @param cardNumber is the ID of the physic card that has face up and face down, between 1 and 12
     * @param side front or rear of the card, 1 means face up, 0 means face down
     * @return the corresponding windowPanel parsed from .json file
     */
    private static WindowPanel getPanel(int cardNumber, int side) {
        if (loadedPanels.isEmpty()) {
            try {
                loadedPanels = WindowPanelParser.getPanelsFromFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedPanels.get(((2 * cardNumber) - side) - 1);
    }

    /**
     * @return the number of windowPanel to be parsed contained in the 'templates' folder
     */
    static int getNumberOfPanels() {
        if (loadedPanels.isEmpty()) {
            try {
                loadedPanels = WindowPanelParser.getPanelsFromFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedPanels.size()/2;
    }

    public String getPanelName() {
        return panelName;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    public int getCardID() {
        return cardID;
    }

    /**
     * @param i index of the cell
     * @return a copy of the cell with index i in the windowPanel
     */
    public Cell getCell(int i) {
        if (i < 0 || i > cells.size()) return null;
        return new Cell(cells.get(i));
    }

    /**
     * @param row row index of the cell
     * @param col column index of the cell
     * @return a copy of the cell with row index row and column index col in the windowPanel
     */
    public Cell getCell(int row, int col) {
        if ((row < 0 || row >= StaticValues.PATTERN_ROW) || (col < 0 || col >= StaticValues.PATTERN_COL)) {
            return null;
        }
        return new Cell(cells.get(row * StaticValues.PATTERN_COL + col));
    }

    /**
     * @return a copy of the cells in the windowPanel
     */
    public ArrayList<Cell> getCells() {
        ArrayList<Cell> h = new ArrayList<>();
        for (Cell cell : cells) {
            h.add(new Cell(cell));
        }
        return h;
    }

    /**
     * Method that adds a dice in an empty cell
     * @param i index that selects the cell in witch the dice will be added
     * @param dice dice to add
     * @param ignoreColor ignore color restrictions on windowPanel
     * @param ignoreValue ignore value restrictions on windowPanel
     * @param ignorePosition ignore positioning rules
     * @return true if the operation is successful
     */
    public boolean addDice(int i, Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignorePosition) {
        if (diceOk(dice, i, ignoreColor, ignoreValue, ignorePosition)) {
            cells.get(i).setDiceOn(dice);
            return true;
        }
        System.out.println("PLACING ERROR >>> CELL " + i + " - WRONG DICE PLACEMENT, IF IS NOT INTENDED FIX IT");
        return false;
    }

    /**
     * Overload of addDice method with all restrictions active
     */
    public boolean addDice(int i, Dice dice) {
        return addDice(i, dice,
                false, false, false);
    }

    /**
     * @param dice dice to be put
     * @param i cell index from 0 to 19
     * @param ignoreColor flag showing if color restriction has to be avoided
     * @param ignoreValue flag showing if color restriction has to be avoided
     * @param ignorePosition flag showing if color restriction has to be avoided
     * @return true if the placement has been done successfully
     */
    boolean diceOk(Dice dice, int i, boolean ignoreColor, boolean ignoreValue, boolean ignorePosition) {
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
            if (hasSimilarDiceAttached(dice, i)) {
                System.out.println("PLACEMENT ERROR >>> SIMILAR ATTACHED");
                return false;
            }
        }
        if (diceOkWithRestriction(cell, dice, ignoreColor, ignoreValue)) return true;
        System.out.println("PLACEMENT ERROR >>> INVALID DICE FOR THIS CELL DUE TO RESTRICTION");
        return false;
    }

    /**
     * @param dice dice subject of the control
     * @param i index of the cell
     * @return true if the operation is successful
     */
    private boolean diceOk(Dice dice, int i) {
        return diceOk(dice, i,
                false, false, false);
    }

    /**
     * @return true if there is no placed dices
     */
    private boolean windowIsEmpty() {
        for (Cell cell : cells) {
            if (cell.hasDiceOn()) return false;
        }
        return true;
    }

    /**
     * @param i cell index
     * @return true if the cell i is in the edge of the panel
     */
    private boolean borderPosition(int i) {
        int row = i / StaticValues.PATTERN_COL;
        int col = i - row * StaticValues.PATTERN_COL;
        return row == 0 || row == StaticValues.PATTERN_ROW-1 || col == 0 || col == StaticValues.PATTERN_COL-1;
    }

    /**
     * @param i cell index
     * @return true if the i cell has a dice in the 3x3 square around, false otherwise
     */
    public boolean noDiceNear(int i) {
        int row = i / StaticValues.PATTERN_COL;
        int col = i - row*StaticValues.PATTERN_COL;

        row--;
        col--;

        for (int j = row; j < row + 3; j++) {
            for (int k = col; k < col + 3; k++) {
                if (validPosition(j, k) && getCell(j, k).hasDiceOn()) return false;
            }
        }
        return true;
    }

    /**
     * @param row row index
     * @param col column index
     * @return true if the position is present in the 4x5 pattern table
     */
    private boolean validPosition(int row, int col) {
        return row >= 0 && row < StaticValues.PATTERN_ROW && col >= 0 && col < StaticValues.PATTERN_COL;
    }

    /**
     * @param dice dice to be put
     * @param i index of the cell
     * @return return false if there is a dice on a cell next to i that has same color OR same value of dice parameter
     */
    private boolean hasSimilarDiceAttached(Dice dice, int i) {
        int row = i / StaticValues.PATTERN_COL;
        int col = i - row*StaticValues.PATTERN_COL;
        row--;
        if(cellPairSimilarity(row, col, dice)) return true;
        row = row + 2;
        if(cellPairSimilarity(row, col, dice)) return true;
        row--;
        col--;
        if(cellPairSimilarity(row, col, dice)) return true;
        col = col + 2;
        return cellPairSimilarity(row, col, dice);
    }

    /**
     * @param cell target cell
     * @param dice dice to bu put
     * @param ignoreColor ignore cell color restriction
     * @param ignoreValue ignore cell value restriction
     * @return true if the dice doesn't break any rules except positioning
     */
    private boolean diceOkWithRestriction(Cell cell, Dice dice, boolean ignoreColor, boolean ignoreValue) {
        if (!cell.hasColorRestriction() && !cell.hasValueRestriction()) return true;
        if (ignoreColor) {
            if (cell.hasValueRestriction()) {
                return dice.getValue() == cell.getValue();
            }
            else
                return true;
        }
        else if (ignoreValue) {
            if (cell.hasColorRestriction()) {
                return dice.getColor().equals(cell.getColor());
            }
            else return true;
        }
        else {
            if (cell.hasColorRestriction() && dice.getColor().equals(cell.getColor())) return true;
            return cell.hasValueRestriction() && dice.getValue() == cell.getValue();
        }
    }

    private boolean cellPairSimilarity(int row, int col, Dice dice) {
        if (validPosition(row, col)) {
            Cell cell = getCell(row, col);
            if (cell.hasDiceOn()) {
                return cell.getDiceOn().isSimilar(dice);
            }
        }
        return false;
    }

    /**
     * @param dice to be checked
     * @return array of the indexes where the dice can be put without breaking any rules
     */
    ArrayList<Integer> getLegalPosition(Dice dice) {
        ArrayList<Integer> h = new ArrayList<>();
        for (int i = 0; i <= 19; i++) {
            if (diceOk(dice, i)) {
                h.add(i);
            }
        }
        return h;
    }

    /**
     * @param i cell index
     * @return the removed dice from the panel in the i position
     */
    public Dice removeDice(int i) {
        Cell cell = cells.get(i);
        Dice dice = cell.getDiceOn();
        cell.setDiceOn(null);
        cells.set(i, cell);
        return dice;
    }

    /**
     * @return number of empty cells in this panel
     */
    public int getEmptyCells() {
        return (int) cells.stream().filter(x -> !x.hasDiceOn()).count();
    }

    /**
     * @param color private color of the player
     * @return points associated with the Private Objective Card of the player
     */
    int getPrivateScore(Color color) {
        return cells.stream().filter(Cell::hasDiceOn)
                .filter(x -> x.getDiceOn().getColor().equals(color))
                .map(x -> x.getDiceOn().getValue())
                .reduce(0, (x, y) -> x + y);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof WindowPanel)) return false;
        WindowPanel panel = (WindowPanel) object;
        for (int i = 0; i < cells.size(); i++) {
            if (!cells.get(i).equals(panel.getCell(i))) return false;
        }
        return true;
     }

    @Override
    public String toString() {
        return PrinterFormatter.printWindowPanelContent(this);
    }

}