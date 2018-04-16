package PPP;

import java.util.Random;

public enum Color {
    BLUE,
    GREEN,
    PURPLE,
    RED,
    YELLOW;

    public static Color getRandomColor(){
        //return a random color from enum
        return values()[new Random().nextInt(values().length)];
    }

    //Get a color giving its name >>> Input values (red,blue,purple,blue,yellow)
    public static Color getColor(String colorName){
        switch (colorName){
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "purple":
                return Color.PURPLE;
            case "red":
                return Color.RED;
            case "yellow":
                return Color.YELLOW;
        }
        //TODO add default case
        return null;
    }

}
