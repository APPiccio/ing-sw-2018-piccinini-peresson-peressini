package PPP.Cards;

import PPP.Color;
import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.ArrayList;

//Row Color Variety: Rows with no repeated colors

public class PublicObjectiveCard1 extends PublicObjectiveCard implements PublicObjectiveCardAction {

    private ArrayList<Color> colors = new ArrayList<>();
    private int coloredLines = 0;

    public PublicObjectiveCard1() {
        super(StaticValues.PUBLICOBJECTIVECARD1_NAME, 1);
    }

    public int getScore(WindowPanel playerWindowPanel) {
        for (int i = 0; i < StaticValues.PATTERN_ROW; i++) {
            for (int j = 0; j < StaticValues.PATTERN_COL; j++) {
                Dice tempDice = playerWindowPanel.getCellWithPosition(i, j).getDiceOn();
                if(tempDice == null || colors.contains(tempDice.getColor())) {
                    break;
                }
                else {
                    colors.add(tempDice.getColor());
                }
            }
            if(colors.size() == 5) {
                coloredLines++;
            }
            colors.clear();
        }
        return coloredLines * 6;
    }

}
