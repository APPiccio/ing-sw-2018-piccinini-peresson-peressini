package PPP;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IllegalDiceValueException {

        DiceBag diceBag = new DiceBag();

        ArrayList<Dice> dices = diceBag.getDiceBag();

        int i = 1;
        for(Dice dice : dices){
            System.out.println(i + " - " + dice.toString());
            i++;
        }
    }
}
