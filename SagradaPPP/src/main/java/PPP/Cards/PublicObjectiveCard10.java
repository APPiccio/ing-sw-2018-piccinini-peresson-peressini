package PPP.Cards;

import PPP.Color;
import PPP.StaticValues;
import PPP.WindowPanel;

import java.util.HashMap;

//Color Variety: sets of one of each color anywhere

public class PublicObjectiveCard10 extends PublicObjectiveCard{

    public PublicObjectiveCard10() {
        super(StaticValues.PUBLICOBJECTIVECARD10_NAME, 10);
    }

    @Override
    public int getScore(WindowPanel playerWindowPanel) {

        HashMap<Color, Integer> colorVariety = new HashMap<>();

        return 0;
    }

}
