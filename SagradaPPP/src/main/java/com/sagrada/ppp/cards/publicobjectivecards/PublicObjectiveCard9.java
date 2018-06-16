package com.sagrada.ppp.cards.publicobjectivecards;

import com.sagrada.ppp.model.Cell;
import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.model.WindowPanel;
import com.sagrada.ppp.utils.StaticValues;

/**
 *  Card description:
 *  Color Diagonals: count of diagonally adjacent same color dice
 */
public class PublicObjectiveCard9 extends PublicObjectiveCard {

    /**
     * @see PublicObjectiveCard#PublicObjectiveCard(String, int)
     */
    public PublicObjectiveCard9() {
        super(StaticValues.PUBLIC_OBJECTIVE_CARD_9_NAME, 9);
    }

    /**
     * @see PublicObjectiveCard#getScore(WindowPanel)
     */
    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        int score = 0;
        for (int y = 0; y < StaticValues.PATTERN_ROW; y++) {
            for (int x = 0; x < StaticValues.PATTERN_COL; x++) {
                if (isDiceLegal(x, y, playerWindowPanel)) {
                    score++;
                }
            }
        }
        return score;
    }

    /**
     * @param x     cell column index
     * @param y     cell row index
     * @param panel windowPanel of the current player
     * @return
     */
    private boolean isDiceLegal(int x, int y, WindowPanel panel) {
        Cell cell = panel.getCell(y, x);
        if (cell.hasDiceOn()) {
            Dice dice = cell.getDiceOn();
            Cell tmpCell;
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

            tmpCell = panel.getCell(yTDX, xTDX);
            if (tmpCell != null && tmpCell.hasDiceOn() && tmpCell.getDiceOn().getColor() == dice.getColor()) {
                return true;
            }

            tmpCell = panel.getCell(yBDX, xBDX);
            if (tmpCell != null && tmpCell.hasDiceOn() && tmpCell.getDiceOn().getColor() == dice.getColor()) {
                return true;
            }

            tmpCell = panel.getCell(yTSX, xTSX);
            if (tmpCell != null && tmpCell.hasDiceOn() && tmpCell.getDiceOn().getColor() == dice.getColor()) {
                return true;
            }

            tmpCell = panel.getCell(yBSX, xBSX);
            if (tmpCell != null && tmpCell.hasDiceOn() && tmpCell.getDiceOn().getColor() == dice.getColor()) {
                return true;
            }
            return false;
        }
        else return false;
    }

}