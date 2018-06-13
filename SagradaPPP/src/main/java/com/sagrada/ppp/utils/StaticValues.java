package com.sagrada.ppp.utils;

import com.sagrada.ppp.model.Color;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
    public static final int MAX_USER_PER_GAME = 4;
    public static int NUMBER_OF_CARDS = 12;
    public static final long TURN_DURATION = 10000;

    public static final String TOKEN_URL = "src/main/java/com/sagrada/ppp/utils/token.json";

    public static final int COST_USED_TOOLCARD = 2;
    public static final int COST_UNUSED_TOOLCARD = 1;
    public static int lobbyTimer = 0;

    //Connection static value
    public static final int RMI_PORT = 1099;
    public static final String REGISTRY_NAME = "SagradaRegistry";
    public static final int SOCKET_PORT = 1996;
    //public static final String SERVER_ADDRESS = "localhost";
    public static final String SERVER_ADDRESS = "192.168.43.56";

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
    public static final String PUBLICOBJECTIVECARD4_NAME = "Column Shade Variety";
    public static final String PUBLICOBJECTIVECARD5_NAME = "Light Shades";
    public static final String PUBLICOBJECTIVECARD6_NAME = "Medium Shades";
    public static final String PUBLICOBJECTIVECARD7_NAME = "Deep Shades";
    public static final String PUBLICOBJECTIVECARD8_NAME = "Shade Variety";
    public static final String PUBLICOBJECTIVECARD9_NAME = "Color Diagonals";
    public static final String PUBLICOBJECTIVECARD10_NAME = "Color Variety";

    //Colors for print formatter
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[30m";   // BLACK
    public static final String RED = "\033[31m";     // RED
    public static final String GREEN = "\033[32m";   // GREEN
    public static final String YELLOW = "\033[33m";  // YELLOW
    public static final String BLUE = "\033[34m";    // BLUE
    public static final String PURPLE = "\033[35m";  // PURPLE
    public static final String CYAN = "\033[36m";    // CYAN
    public static final String WHITE = "\033[37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[30m";  // BLACK
    public static final String RED_BOLD = "\033[31m";    // RED
    public static final String GREEN_BOLD = "\033[32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[36m";   // CYAN
    public static final String WHITE_BOLD = "\033[37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[37m";  // WHITE

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
    public static final String BLACK_BRIGHT = "\033[90m";  // BLACK
    public static final String RED_BRIGHT = "\033[91m";    // RED
    public static final String GREEN_BRIGHT = "\033[92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[107m";   // WHITE

    // Cli View string command
    public static final String STRING_COMMAND_QUIT = "quit";
    public static final String STRING_COMMAND_LOGIN = "login";
    public static final String STRING_COMMAND_SHOW_GAMES = "show joinable multiplayer games";
    public static final String STRING_COMMAND_CREATE_GAME = "create your own lobby";
    public static final String STRING_COMMAND_JOIN_GAME = "join an existing lobby (write join lobbyName myUsername)";
    public static final String STRING_COMMAND_LEAVE_GAME = "leave lobby/game";
    public static final String STRING_COMMAND_HELP = "show commands list";
    public static final String STRING_COMMAND_PLAYERS_IN_LOBBY = "show players connected to this lobby";
    public static final String STRING_COMMAND_START_GAME = "start game";
    public static final String STRING_COMMAND_DISABLE_AFK = "'i am active again' signal";

    public static final String STRING_COMMAND_PLACE_DICE = "<dice_index> <cell_row> <cell_col> place the chosen dice in the panel";
    public static final String STRING_COMMAND_END_TURN = "end your current turn";
    public static final String STRING_COMMAND_USE_TOOLCARD = "<tool card index> use a tool card";
    public static final String STRING_COMMAND_SHOW = "shows public objective cards, draft pool and tool cards";
    public static final String STRING_COMMAND_CONNECTION = "<rmi/socket> change connection mode";

    // Cli View command
    public static final String COMMAND_QUIT = ":q";
    public static final String COMMAND_LOGIN = ":login";
    public static final String COMMAND_SHOW_GAMES = ":games";
    public static final String COMMAND_CREATE_GAME = ":new";
    public static final String COMMAND_JOIN_GAME = ":join";
    public static final String COMMAND_LEAVE_GAME = ":leave";
    public static final String COMMAND_HELP = ":help";
    public static final String COMMAND_PLAYERS_IN_LOBBY = ":players";
    public static final String COMMAND_START_GAME = ":start";

    public static final String COMMAND_PLACE_DICE = ":dice";
    public static final String COMMAND_END_TURN = ":end";
    public static final String COMMAND_USE_TOOLCARD = ":toolcard";
    public static final String COMMAND_SHOW = ":show";
    public static final String COMMAND_CONNECTION = ":cmode";
    public static final String COMMAND_DISABLE_AFK = ":bazinga";


    //AssetUrl
    public static final String FILE_URI_PREFIX = "file:";
    public static final String BLANK_CELL_ASSET = "graphics/blank.png";
    public static final String RED_CELL_ASSET = "graphics/red.png";
    public static final String BLUE_CELL_ASSET = "graphics/blue.png";
    public static final String YELLOW_CELL_ASSET = "graphics/yellow.png";
    public static final String GREEN_CELL_ASSET = "graphics/green.png";
    public static final String PURPLE_CELL_ASSET = "graphics/purple.png";
    public static final String RESTRICTION_CELL_ASSET = "graphics/cell_"; //you need to add the index of the restriction
    public static final String YELLOW_DICE_ASSET = "graphics/yellowDice_";
    public static final String BLUE_DICE_ASSET = "graphics/blueDice_";
    public static final String RED_DICE_ASSET = "graphics/redDice_";
    public static final String GREEN_DICE_ASSET = "graphics/greenDice_";
    public static final String PURPLE_DICE_ASSET = "graphics/purpleDice_";
    public static final String PNG_ASSET = ".png";
    public static final String JPG_ASSET = ".jpg";



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

    public static int getLobbyTimer(){

        if(lobbyTimer > 0) return lobbyTimer;
        JSONTokener jsonTokener = null;
        try {
            jsonTokener = new JSONTokener(new FileReader("src/main/java/com/sagrada/ppp/utils/config.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(jsonTokener);
        lobbyTimer = jsonObject.getInt("lobby_timer");
        return lobbyTimer;
    }

    public static String getPublicObjectiveCardDescription(int id){
        switch (id){
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

    public static String getToolCardDescription(int id){
        switch (id){
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
