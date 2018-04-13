package PPP.Cards;

import PPP.StaticValues;
import PPP.WindowPanel;

//Color Diagonals: count of diagonally adjacent same color dice

public class PublicObjectiveCard9 extends PublicObjectiveCard implements PublicObjectiveCardAction {

    public PublicObjectiveCard9() {
        super(StaticValues.PUBLICOBJECTIVECARD9_NAME, 9);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        return 0;
    }

}
