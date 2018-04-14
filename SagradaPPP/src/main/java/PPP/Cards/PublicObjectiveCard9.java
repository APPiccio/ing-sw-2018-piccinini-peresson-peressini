package PPP.Cards;


import PPP.Cell;
import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;

import java.util.ArrayList;


//Color Diagonals: count of diagonally adjacent same color dice

public class PublicObjectiveCard9 extends PublicObjectiveCard {

    public PublicObjectiveCard9() {
        super(StaticValues.PUBLICOBJECTIVECARD9_NAME, 9);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        int score = 0;
        for (int y = 0; y < StaticValues.PATTERN_COL; y++) {
            for (int x = 0; x < StaticValues.PATTERN_ROW; x++) {
                if (hasASameColourDiceOnDiagonal(x, y, playerWindowPanel)) {
                    score++;
                }


            }
        }

        return 0;
    }

    private boolean hasASameColourDiceOnDiagonal(int x, int y, WindowPanel panel) {
        boolean returnValue = false;
        Cell cell = panel.getCellWithPosition(y, x);
        if (cell.hasDiceon()) {
            Dice dice = cell.getDiceOn();
            //top-dx cell
            int xTDX = x + 1;
            int yTDX = y - 1;
            //bottom-dx cell
            int xBDX = x + 1;
            int yBDX = y + 1;
            //top-sx cell
            int xTSX = x - 1;
            int yTSX = y - 1;
            //bottom-sx cell
            int xBSX = x - 1;
            int yBSX = y + 1;


            Cell tmpCell;
            if (xTDX < 0 || xTDX > StaticValues.PATTERN_COL && yTDX < 0 || yTDX > StaticValues.PATTERN_ROW) {
                tmpCell = panel.getCellWithPosition(yTDX, xTDX);
                if (tmpCell.hasDiceon()) {
                    if (tmpCell.getDiceOn().getColor() == dice.getColor()) {
                        return true;
                    }
                }
            }
            if (xBDX < 0 || xBDX > StaticValues.PATTERN_COL && yBDX < 0 || yBDX > StaticValues.PATTERN_ROW) {
                tmpCell = panel.getCellWithPosition(yBDX, xBDX);
                if (tmpCell.hasDiceon()) {
                    if (tmpCell.getDiceOn().getColor() == dice.getColor()) {
                        return true;
                    }
                }
            }
            if (xTSX < 0 || xTSX > StaticValues.PATTERN_COL && yTSX < 0 || yTSX > StaticValues.PATTERN_ROW) {
                tmpCell = panel.getCellWithPosition(yTSX, xTSX);
                if (tmpCell.hasDiceon()) {
                    if (tmpCell.getDiceOn().getColor() == dice.getColor()) {
                        return true;
                    }
                }
            }
            if (xBSX < 0 || xBSX > StaticValues.PATTERN_COL && yBSX < 0 || yBSX > StaticValues.PATTERN_ROW) {
                tmpCell = panel.getCellWithPosition(yBSX, xBSX);
                if (tmpCell.hasDiceon()) {
                    if (tmpCell.getDiceOn().getColor() == dice.getColor()) {
                        return true;
                    }
                }
            }
            return false;
        }
        else return false;
    }
}

