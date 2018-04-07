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

        System.out.println(dices.get(0).toString());

        for(Dice dice : dices){
            System.out.println(dice.toString());
        }



        /* DICE TESTDRIVE
        System.out.println("random dice " + new Dice().toString());
        System.out.println("random dice " + new Dice().toString());
        System.out.println("random dice " + new Dice().toString());
        System.out.println("random dice " + new Dice().toString());
        System.out.println("random dice " + new Dice().toString());


        System.out.println("dice 5 " + new Dice(5).toString());
        System.out.println("dice green " + new Dice(Color.GREEN).toString());


        */

    }
}
