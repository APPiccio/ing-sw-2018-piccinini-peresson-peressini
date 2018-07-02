package com.sagrada.ppp.utils;

import com.sagrada.ppp.model.Color;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class StaticValues {
    private StaticValues(){
        super();
    }

    public static final int NUMBER_OF_DICES_PER_COLOR = 18;
    public static final int FRONT_SIDE = 1;
    static final String NULL_JSON_VALUE = "null";
    public static final int PATTERN_ROW = 4;
    public static final int PATTERN_COL = 5;
    public static final int NUMBER_OF_CELLS = 20;
    public static final int NUMBER_OF_COLORS = 5;
    public static final int NUMBER_OF_ROUNDS = 10;
    public static final int DICE_FACES = 6;
    public static final int MAX_USER_PER_GAME = 4;
    public static long TURN_DURATION = 120000;

    public static long LOBBY_TIMER = 30000;

    static String TOKEN_URL = "utils/token";
    public static final String STYLE_SHEET_URL = "file:resources/SagradaStyleSheet.css";
    public static String TEMPLATES_URL = "resources/templates/";

    public static final int COST_USED_TOOL_CARD = 2;
    public static final int COST_UNUSED_TOOL_CARD = 1;

    //Connection static value
    public static int RMI_PORT;
    public static String REGISTRY_NAME;
    public static int SOCKET_PORT;
    public static String SERVER_ADDRESS;

    //Tool Card names
    public static final String TOOL_CARD_1_NAME = "Grozing Pliers";
    public static final String TOOL_CARD_2_NAME = "Eglomise Brush";
    public static final String TOOL_CARD_3_NAME = "Copper Foil Burnisher";
    public static final String TOOL_CARD_4_NAME = "Lathekin";
    public static final String TOOL_CARD_5_NAME = "Lens Cutter";
    public static final String TOOL_CARD_6_NAME = "Flux Brush";
    public static final String TOOL_CARD_7_NAME = "Glazing Hammer";
    public static final String TOOL_CARD_8_NAME = "Running Pliers";
    public static final String TOOL_CARD_9_NAME = "Cork-backed Straightedge";
    public static final String TOOL_CARD_10_NAME = "Grinding Stone";
    public static final String TOOL_CARD_11_NAME = "Flux Remover";
    public static final String TOOL_CARD_12_NAME = "Tap Wheel";

    public static final String PUBLIC_OBJECTIVE_CARD_1_NAME = "Row Color Variety";
    public static final String PUBLIC_OBJECTIVE_CARD_2_NAME = "Column Color Variety";
    public static final String PUBLIC_OBJECTIVE_CARD_3_NAME = "Row Shade Variety";
    public static final String PUBLIC_OBJECTIVE_CARD_4_NAME = "Column Shade Variety";
    public static final String PUBLIC_OBJECTIVE_CARD_5_NAME = "Light Shades";
    public static final String PUBLIC_OBJECTIVE_CARD_6_NAME = "Medium Shades";
    public static final String PUBLIC_OBJECTIVE_CARD_7_NAME = "Deep Shades";
    public static final String PUBLIC_OBJECTIVE_CARD_8_NAME = "Shade Variety";
    public static final String PUBLIC_OBJECTIVE_CARD_9_NAME = "Color Diagonals";
    public static final String PUBLIC_OBJECTIVE_CARD_10_NAME = "Color Variety";

    //Colors for print formatter
    // Reset
    static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String RED = "\033[31m";     // RED
    public static final String GREEN = "\033[32m";   // GREEN
    public static final String YELLOW = "\033[33m";  // YELLOW
    public static final String BLUE = "\033[34m";    // BLUE
    public static final String PURPLE = "\033[35m";  // PURPLE

    // Bold High Intensity
    static final String BLACK_BOLD_BRIGHT = "\033[90m"; // BLACK
    static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    static final String RED_BACKGROUND_BRIGHT = "\033[101m";// RED
    static final String GREEN_BACKGROUND_BRIGHT = "\033[102m";// GREEN
    static final String YELLOW_BACKGROUND_BRIGHT = "\033[103m";// YELLOW
    static final String BLUE_BACKGROUND_BRIGHT = "\033[104m";// BLUE
    static final String PURPLE_BACKGROUND_BRIGHT = "\033[105m"; // PURPLE
    static final String WHITE_BACKGROUND_BRIGHT = "\033[107m";   // WHITE

    // Cli View string command
    public static final String STRING_COMMAND_QUIT = "quit";
    public static final String STRING_COMMAND_LEAVE_GAME = "leave lobby/game";
    public static final String STRING_COMMAND_HELP = "show commands list";
    public static final String STRING_COMMAND_DISABLE_AFK = "'i am active again' signal";

    public static final String STRING_COMMAND_PLACE_DICE = "<dice_index> <cell_row> <cell_col> place the chosen dice in the panel";
    public static final String STRING_COMMAND_END_TURN = "end your current turn";
    public static final String STRING_COMMAND_USE_TOOLCARD = "<tool card index> use a tool card";
    public static final String STRING_COMMAND_SHOW = "shows public objective cards, draft pool and tool cards";
    public static final String STRING_COMMAND_CONNECTION = "<rmi/socket> change connection mode";

    // Cli View command
    public static final String COMMAND_QUIT = ":q";
    public static final String COMMAND_LEAVE_GAME = ":leave";
    public static final String COMMAND_HELP = ":help";

    public static final String COMMAND_PLACE_DICE = ":dice";
    public static final String COMMAND_END_TURN = ":end";
    public static final String COMMAND_USE_TOOL_CARD = ":toolcard";
    public static final String COMMAND_SHOW = ":show";
    public static final String COMMAND_CONNECTION = ":cmode";
    public static final String COMMAND_DISABLE_AFK = ":bazinga";


    //AssetUrl
    public static final String FILE_URI_PREFIX = "file:";
    public static final String BLANK_CELL_ASSET = "resources/graphics/blank.png";
    private static final String RED_CELL_ASSET = "resources/graphics/red.png";
    private static final String BLUE_CELL_ASSET = "resources/graphics/blue.png";
    private static final String YELLOW_CELL_ASSET = "resources/graphics/yellow.png";
    private static final String GREEN_CELL_ASSET = "resources/graphics/green.png";
    private static final String PURPLE_CELL_ASSET = "resources/graphics/purple.png";
    private static final String RESTRICTION_CELL_ASSET = "resources/graphics/cell_"; //you need to add the index of the restriction
    private static final String YELLOW_DICE_ASSET = "resources/graphics/yellowDice_";
    private static final String BLUE_DICE_ASSET = "resources/graphics/blueDice_";
    private static final String RED_DICE_ASSET = "resources/graphics/redDice_";
    private static final String GREEN_DICE_ASSET = "resources/graphics/greenDice_";
    private static final String PURPLE_DICE_ASSET = "resources/graphics/purpleDice_";
    private static final String PNG_ASSET = ".png";

    public static String getAssetUri(Color color){
        switch (color){
            case GREEN:
                return FILE_URI_PREFIX + GREEN_CELL_ASSET;
            case RED:
                return  FILE_URI_PREFIX + RED_CELL_ASSET;
            case BLUE:
                return  FILE_URI_PREFIX + BLUE_CELL_ASSET;
            case YELLOW:
                return  FILE_URI_PREFIX + YELLOW_CELL_ASSET;
            case PURPLE:
                return  FILE_URI_PREFIX + PURPLE_CELL_ASSET;
            default:
                return null;
        }

    }
    public static String getAssetUri(int val){
        return FILE_URI_PREFIX +  RESTRICTION_CELL_ASSET + val + ".png";

    }

    public static String getAssetUri(Color color,int val){
        switch (color){
            case GREEN:
                return FILE_URI_PREFIX + GREEN_DICE_ASSET +val+ PNG_ASSET;
            case RED:
                return  FILE_URI_PREFIX + RED_DICE_ASSET +val+ PNG_ASSET;
            case BLUE:
                return  FILE_URI_PREFIX + BLUE_DICE_ASSET +val+ PNG_ASSET;
            case YELLOW:
                return  FILE_URI_PREFIX + YELLOW_DICE_ASSET +val+ PNG_ASSET;
            case PURPLE:
                return  FILE_URI_PREFIX + PURPLE_DICE_ASSET +val+ PNG_ASSET;
            default:
                return  FILE_URI_PREFIX + PURPLE_CELL_ASSET;
        }

    }

    public static void readConstants(){

        JSONTokener jsonTokener = null;
        try {
            jsonTokener = new JSONTokener(new FileReader("utils/config.json"));
            JSONObject jsonObject = new JSONObject(jsonTokener);
            LOBBY_TIMER = jsonObject.getInt("lobby_timer");
            TURN_DURATION = jsonObject.getLong("TURN_TIMER");
            RMI_PORT = jsonObject.getInt("RMI_PORT");
            REGISTRY_NAME = jsonObject.getString("RMI_REGISTRY_NAME");
            SOCKET_PORT = jsonObject.getInt("SOCKET_PORT");
            SERVER_ADDRESS = jsonObject.getString("SERVER_ADDRESS");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static String getPublicObjectiveCardDescription(int id) {
        switch (id) {
            case 1:
                return "6pts x Row with no repeated colors";
            case 2:
                return "5pts x Column with no repeated colors";
            case 3:
                return "5pts x Row with no repeated values";
            case 4:
                return "4pts x Column with no repeated values";
            case 5:
                return "2pts x Set of 1 & 2 values anywhere";
            case 6:
                return "2pts x Set of 3 & 4 values anywhere";
            case 7:
                return "2pts x Set of 5 & 6 values anywhere";
            case 8:
                return "5pts x Set of one of each value anywhere";
            case 9:
                return "1pt x Count of diagonally adjacent same color dice";
            case 10:
                return "4pts x Set of one of each color anywhere";
            default:
                return "CARD ID UNKNOWN";
        }
    }

    public static String getToolCardDescription(int id) {
        switch (id) {
            case 1:
                return "After drafting, increase or decrease the value of the drafted die by 1 (1 may not change to 6, or 6 to 1)";
            case 2:
                return "Move any one die in your window ignoring the color restrictions (you must obey all other placement restrictions)";
            case 3:
                return "Move any one die in your window ignoring shade restriction (you must obey all other placement restriction)";
            case 4:
                return "Move exactly two dice, obeying all placement restrictions";
            case 5:
                return "After drafting, swap the drafted die with a die from the Round Track";
            case 6:
                return "After drafting re-roll the drafted die. If it cannot be placed, return it to the Draft Pool";
            case 7:
                return "Re-roll all dice in the Draft Pool. This may only be used on your second turn before drafting";
            case 8:
                return "After your first turn, immediately draft a die. Skip your next turn this round";
            case 9:
                return "After drafting, place the die in a sport that is not adjacent to another die (you must obey all other placement restrictions)";
            case 10:
                return "After drafting, flip the die to its opposite side (6 to 1, 5 to 2, 4 to 3, etc.)";
            case 11:
                return "After drafting, return the dice to the Dice Bag and pull 1 die from the bag. Choose a value and place the new die obeying all placement restrictions, or return it to the Draft Pool";
            case 12:
                return "Move up to two dice of the same color that match the color of a die in the Round Track. You must obey all placement restrictions";
            default:
                return "CARD ID UNKNOWN";
        }
    }


}
