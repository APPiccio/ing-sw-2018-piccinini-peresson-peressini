package com.sagrada.ppp.Utils;

public class StaticValues {

    public static final int NUMBER_OF_DICES_PER_COLOR = 18;
    public static final int FRONT_SIDE = 1;
    public static final int BACK_SIDE = 0;
    public static final String NULL_JSON_VALUE = "null";
    public static final int PATTERN_ROW = 4;
    public static final int PATTERN_COL = 5;
    public static final int NUMBER_OF_CELLS = 20;
    public static final int NUMBER_OF_COLORS = 5;
    public static final int NUMBER_OF_TURNS = 10;
    public static final int DICE_FACES = 6;

    public static final int COST_USED_TOOLCARD = 2;
    public static final int COST_UNUSED_TOOLCARD = 1;

    //Tool Card names
    public static final String TOOLCARD1_NAME = "Grozing Pliers";
    public static final String TOOLCARD2_NAME = "Eglomise Brush";
    public static final String TOOLCARD3_NAME = "Copper Foil Burnisher";
    public static final String TOOLCARD4_NAME = "Lathekin";
    public static final String TOOLCARD5_NAME = "Lens Cutter";
    public static final String TOOLCARD6_NAME = "Flux Brush";
    public static final String TOOLCARD7_NAME = "Glazing Hammer";
    public static final String TOOLCARD8_NAME = "Running Pliers";
    public static final String TOOLCARD9_NAME = "Cork-backed Straightedge";
    public static final String TOOLCARD10_NAME = "Grinding Stone";
    public static final String TOOLCARD11_NAME = "Flux Remover";
    public static final String TOOLCARD12_NAME = "Tap Wheel";

    public static final String PUBLICOBJECTIVECARD1_NAME = "Row Color Variety";
    public static final String PUBLICOBJECTIVECARD2_NAME = "Column Color Variety";
    public static final String PUBLICOBJECTIVECARD3_NAME = "Row Shade Variety";
    public static final String PUBLICOBJECTIVECARD4_NAME = "Colum Shade Variety";
    public static final String PUBLICOBJECTIVECARD5_NAME = "Light Shades";
    public static final String PUBLICOBJECTIVECARD6_NAME = "Medium Shades";
    public static final String PUBLICOBJECTIVECARD7_NAME = "Deep Shades";
    public static final String PUBLICOBJECTIVECARD8_NAME = "Shade Variety";
    public static final String PUBLICOBJECTIVECARD9_NAME = "Color Diagonals";
    public static final String PUBLICOBJECTIVECARD10_NAME = "Color Variety";

    public static final String PRIVATEOBJECTIVECARDRED_NAME = "Shades of Red";
    public static final String PRIVATEOBJECTIVECARDYELLOW_NAME = "Shades of Yellow";
    public static final String PRIVATEOBJECTIVECARDGREEN_NAME = "Shades of Green";
    public static final String PRIVATEOBJECTIVECARDBLUE_NAME = "Shades of Blue";
    public static final String PRIVATEOBJECTIVECARDPURPLE_NAME = "Shades of Purple";

    //Colors for print formatter
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

}
