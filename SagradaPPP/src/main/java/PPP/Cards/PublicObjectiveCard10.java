package PPP.Cards;

import PPP.Color;
import PPP.Dice;
import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.HashMap;

//Color Variety: sets of one of each color anywhere

public class PublicObjectiveCard10 extends PublicObjectiveCard implements PublicObjectiveCardAction {

    public PublicObjectiveCard10() {
        super(StaticValues.PUBLICOBJECTIVECARD10_NAME, 10);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        HashMap<Color, Integer> colorVariety = new HashMap<>();
        int minMapValue = Integer.MAX_VALUE;

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
        } else {
            for (Color color : colorVariety.keySet()) {
                int value = colorVariety.get(color);
                if (value < minMapValue) {
                    minMapValue = value;
                }
            }
        }
        return minMapValue * 4;
        //Alternative using Java 8
        // return colorVariety.values().stream().min(Integer::compare).get();
    }

}
