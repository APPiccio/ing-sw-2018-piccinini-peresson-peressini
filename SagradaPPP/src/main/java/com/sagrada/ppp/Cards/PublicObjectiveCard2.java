package PPP.Cards;

import PPP.WindowPanel;
import PPP.Color;
import PPP.Dice;
import PPP.StaticValues;

import java.util.ArrayList;

//Column Color Variety: columns with no repeated colors

public class PublicObjectiveCard2 extends PublicObjectiveCard {

    public PublicObjectiveCard2() {
        super(StaticValues.PUBLICOBJECTIVECARD2_NAME, 2);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        ArrayList<Color> colors = new ArrayList<>();
        int coloredColumns = 0;

        for (int i = 0; i < StaticValues.PATTERN_COL; i++) {
            for (int j = 0; j < StaticValues.PATTERN_ROW; j++) {
                Dice tempDice = playerWindowPanel.getCellWithPosition(j, i).getDiceOn();
                if(tempDice == null || colors.contains(tempDice.getColor())) {
                    break;
                }
                else {
                    colors.add(tempDice.getColor());
                }
            }
            if(colors.size() == 4) {
                coloredColumns++;
            }
            colors.clear();
        }
        return coloredColumns * 5;
    }

}
