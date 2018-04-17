package PPP.Cards;


import PPP.WindowPanel;
import PPP.Cell;
import PPP.Dice;
import PPP.StaticValues;


//Color Diagonals: count of diagonally adjacent same color dice
//Tested

public class PublicObjectiveCard9 extends PublicObjectiveCard {

    public PublicObjectiveCard9() {
        super(StaticValues.PUBLICOBJECTIVECARD9_NAME, 9);
    }

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

    private boolean isDiceLegal(int x, int y, WindowPanel panel) {
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
            tmpCell = panel.getCellWithPosition(yTDX, xTDX);
            if (tmpCell != null) {
                if (tmpCell.hasDiceon()) {
                    if (tmpCell.getDiceOn().getColor() == dice.getColor()) {
                        return true;
                    }
                }
            }

            tmpCell = panel.getCellWithPosition(yBDX, xBDX);
            if (tmpCell != null) {
                if (tmpCell.hasDiceon()) {
                    if (tmpCell.getDiceOn().getColor() == dice.getColor()) {
                        return true;
                    }
                }
            }

            tmpCell = panel.getCellWithPosition(yTSX, xTSX);
            if (tmpCell != null) {
                if (tmpCell.hasDiceon()) {
                    if (tmpCell.getDiceOn().getColor() == dice.getColor()) {
                        return true;
                    }
                }
            }

            tmpCell = panel.getCellWithPosition(yBSX, xBSX);
            if (tmpCell != null) {
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

