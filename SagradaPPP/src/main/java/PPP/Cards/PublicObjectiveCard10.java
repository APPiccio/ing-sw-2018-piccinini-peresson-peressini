package PPP.Cards;

import PPP.StaticValues;
import PPP.WindowPanel;

//Color Variety: sets of one of each color anywhere

public class PublicObjectiveCard10 extends PublicObjectiveCard implements PublicObjectiveCardAction {

    public PublicObjectiveCard10() {
        super(StaticValues.PUBLICOBJECTIVECARD10_NAME, 10);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {
        return 0;
    }

}
