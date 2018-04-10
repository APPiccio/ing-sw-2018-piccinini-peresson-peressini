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
    //Get a color giving its name >>> Input values (red,blue,purple,blue,yellow)
    public static Color getColor(String colorName){
        switch (colorName){
            case "blue":
                return Color.BLUE;
            case "yellow":
                return Color.YELLOW;
            case "red":
                return Color.RED;
            case "purple":
                return Color.PURPLE;
            case "green":
                return Color.GREEN;
        }
        //TODO add default case
        return null;
    }



}
