package PPP.Cards;

import PPP.Color;
import PPP.StaticValues;
import PPP.WindowPanel;
import java.util.ArrayList;

//Row Color Variety: Rows with no repeated colors

public class PublicObjective_1 extends PublicObjective implements PublicObjectiveCard {

    private ArrayList<Color> colors = new ArrayList<>();
    private int rowIndex = 0;
    private int coloredLines = 0;

    public int getScore(WindowPanel playerWindowPanel) {
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
        }
        return 0;
    }

}
