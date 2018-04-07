package PPP;

import java.util.Random;

public enum Color {
    GREEN,
    YELLOW,
    BLUE,
    PURPLE,
    RED;

    public static Color getRandomColor(){
        //return a random color from enum
        return values()[new Random().nextInt(values().length)];
    }

}
