package PPP.Cards;

import PPP.Color;
import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.HashMap;

//Color Variety: sets of one of each color anywhere

public class PublicObjectiveCard10 extends PublicObjectiveCard {

    public PublicObjectiveCard10() {
        super(StaticValues.PUBLICOBJECTIVECARD10_NAME, 10);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        HashMap<Color, Integer> colorVariety = new HashMap<>();
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = playerWindowPanel.getCellWithIndex(i).getDiceOn();
            if (tempDice == null) {
                continue;
            }
            else {
                Color tempDiceColor = tempDice.getColor();
                if (colorVariety.containsKey(tempDiceColor)) {
                    colorVariety.computeIfPresent(tempDiceColor, (k, v) -> v + 1);
                }
                else {
                    colorVariety.put(tempDiceColor, 1);
                }
            }
        }
        if (colorVariety.size() != StaticValues.NUMBER_OF_COLORS) {
            return 0;
        }
        else {
            int minValue = Integer.MAX_VALUE;
            for (Color color : colorVariety.keySet()) {
                int tempValue = colorVariety.get(color);
                if (tempValue < minValue) {
                    minValue = tempValue;
                }
            }
            return minValue * 4;
        }
    }
}
