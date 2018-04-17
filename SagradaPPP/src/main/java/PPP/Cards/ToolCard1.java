package PPP.Cards;


import PPP.Color;
import PPP.Dice;
import PPP.Game;
import PPP.StaticValues;

import java.util.ArrayList;

public class ToolCard1 extends ToolCard {

    public ToolCard1() {
        super(StaticValues.TOOLCARD1_NAME,1, Color.PURPLE);
    }

    @Override
    public Object activate(Game game)  {
        //TODO: Ask what dice needs to be modified and if it needs to be increased or decreased

        Dice dice = new Dice();
        int diceValue = dice.getValue();
        ArrayList<Integer> legalValues = new ArrayList<>();


        if(diceValue == 6) legalValues.add(5);
        else if(diceValue == 1) legalValues.add(2);
        else {
            legalValues.add(diceValue + 1);
            legalValues.add(diceValue - 1);
        }



        setUsed();
        return null;
    }
}
