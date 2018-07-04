package com.sagrada.ppp.view;

import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.cards.toolcards.ToolCard;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.controller.SocketClientController;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.client.ConnectionHandler;
import com.sagrada.ppp.network.client.ConnectionModeEnum;
import com.sagrada.ppp.utils.PlayerTokenSerializer;
import com.sagrada.ppp.utils.StaticValues;

import static com.sagrada.ppp.utils.StaticValues.*;
import static java.lang.System.*;


import java.io.Serializable;

import java.rmi.ConnectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CliView extends UnicastRemoteObject
        implements LobbyObserver, Serializable, GameObserver, ToolCardHandler {
    private transient Scanner scanner;
    private transient RemoteController controller;
    private transient String username;
    private transient int hashCode;
    private transient int gameHashCode;
    private transient ArrayList<String> playersUsername;
    private transient boolean waitingForPanels;
    private transient ArrayList<Dice> draftPool;
    private transient boolean isGameStarted;
    private transient ArrayList<Player> players;
    private transient ArrayList<ToolCard> toolCards;
    private transient ArrayList<PublicObjectiveCard> publicObjectiveCards;
    private transient RoundTrack roundTrack;
    private transient Player currentPlayer;
    private transient boolean usedToolCard;
    private transient boolean placedDice;
    private transient ConnectionModeEnum connectionModeEnum;
    private transient volatile boolean isToolCardActionEnded;
    private transient volatile boolean isEndedTurn;
    private transient volatile boolean isToolCardUsableFlag;
    private transient volatile ToolCardFlags toolCardFlags;
    private transient UseToolCardResult useToolCardResult;
    private static final String INSERT_ROW = "Insert row index: ";
    private static final String INSERT_COLUMN = "Insert column index: ";
    private static final String PERMISSION_DENIED = "Permission denied! It's not your turn!";
    private int currentTurn;
    private int currentRound;
    private JoinGameResult joinGameResult;
    private boolean isAFK;
    private final String TABLE_SEPARATOR = "+-----------------+-------+-------+-------+-------+-------+-------+-------+%n";
    private final String PRINT_SEPARATOR = "----------------------------------------";
    private final String INVALID_VALUE_OUTPUT = "Invalid value!";

    public CliView(RemoteController controller, ConnectionModeEnum connectionModeEnum) throws RemoteException{
        this.scanner = new Scanner(in);
        this.controller = controller;
        playersUsername = new ArrayList<>();
        waitingForPanels = true;
        isGameStarted = false;
        placedDice = false;
        usedToolCard = false;
        this.connectionModeEnum = connectionModeEnum;
        isToolCardActionEnded = false;
        isEndedTurn = false;
        isToolCardUsableFlag = true;
        toolCardFlags = new ToolCardFlags();
        isAFK = false;
    }

    public void init() throws RemoteException {
        if (PlayerTokenSerializer.isTokenPresent()) {
            out.println("Do you want to resume the previous game? (y/n)");
            String in = scanner.nextLine();
            while(!in.equals("y") && !in.equals("n")){
                out.println("Invalid answer, type y (yes) or n (no)");
                in = scanner.nextLine();
            }
            if (in.equals("y")){
                JoinGameResult jgr = PlayerTokenSerializer.deserialize();
                ReconnectionResult reconnectionResult = controller.reconnection(jgr.getPlayerHashCode(),
                        jgr.getGameHashCode(), this);
                if (!reconnectionResult.result) {
                    out.println(reconnectionResult.message);
                    do {
                        out.println("Do you want to retry?");
                        in = scanner.nextLine();
                        while (!in.equals("y") && !in.equals("n")) {
                            out.println("Invalid answer, type y (yes) or n (no)");
                            in = scanner.nextLine();
                        }
                        if (in.equals("y")) {
                            reconnectionResult = controller.reconnection(jgr.getPlayerHashCode(),
                                    jgr.getGameHashCode(), this);
                            out.println(reconnectionResult.message);
                        }
                    } while (!reconnectionResult.result && !in.equals("n"));
                    if (in.equals("n")) {
                        PlayerTokenSerializer.deleteToken();
                        start();
                    }
                    else {
                        hashCode = jgr.getPlayerHashCode();
                        gameHashCode = jgr.getGameHashCode();
                        username = jgr.getUsername();
                        draftPool = reconnectionResult.gameStartMessage.draftpool;
                        toolCards = reconnectionResult.gameStartMessage.toolCards;
                        publicObjectiveCards = reconnectionResult.gameStartMessage.publicObjectiveCards;
                        players = reconnectionResult.gameStartMessage.players;
                        currentPlayer = reconnectionResult.gameStartMessage.currentPlayer;
                        roundTrack = reconnectionResult.gameStartMessage.roundTrack;
                        isGameStarted = true;
                        showGameStatus();
                        inGame(null);
                    }
                }
                else {
                    out.println(reconnectionResult.message);
                    hashCode = jgr.getPlayerHashCode();
                    gameHashCode = jgr.getGameHashCode();
                    username = jgr.getUsername();
                    draftPool = reconnectionResult.gameStartMessage.draftpool;
                    toolCards = reconnectionResult.gameStartMessage.toolCards;
                    publicObjectiveCards = reconnectionResult.gameStartMessage.publicObjectiveCards;
                    players = reconnectionResult.gameStartMessage.players;
                    currentPlayer = reconnectionResult.gameStartMessage.currentPlayer;
                    roundTrack = reconnectionResult.gameStartMessage.roundTrack;
                    isGameStarted = true;
                    showGameStatus();
                    inGame(null);
                }

            }
            else {
                PlayerTokenSerializer.deleteToken();
                start();
            }
        }
        else {
            start();
        }
    }

    private void start() throws RemoteException {
        out.println("Welcome to SAGRADA");
        out.println("Please enter your username! This can't be empty or with spaces.");
        username = scanner.nextLine();
        while (username.length() <= 0 || username.contains(" ")) {
            out.println("Error, try again! This can't be empty or with spaces.");
            username = scanner.nextLine();
        }

        this.joinGameResult = controller.joinGame(username, this);
        while (hashCode < 0){
            out.println("Join failed. Trying new attempt...");
            joinGameResult = controller.joinGame(username, this);
        }
        controller.attachGameObserver(joinGameResult.getGameHashCode(),this, joinGameResult.getPlayerHashCode());

        gameHashCode = joinGameResult.getGameHashCode();
        hashCode = joinGameResult.getPlayerHashCode();
        username = joinGameResult.getUsername();
        long lobbyTimerStartTime = joinGameResult.getTimerStart();
        playersUsername = joinGameResult.getPlayersUsername();
        out.println("Join completed. You are now identified as : " + username);

        if(lobbyTimerStartTime != 0){
            long remainingTime = ((lobbyTimerStartTime + StaticValues.LOBBY_TIMER) -
                    currentTimeMillis())/1000;
            out.println("---> The game will start in " + remainingTime + " seconds");
        }
        inLobby();
    }

    private void showLobbyCommandList(){
        out.println("\t" + StaticValues.COMMAND_QUIT + "\t" + StaticValues.STRING_COMMAND_QUIT);
        out.println("\t" + StaticValues.COMMAND_HELP + "\t" + StaticValues.STRING_COMMAND_HELP);
        out.println("\t" + StaticValues.COMMAND_LEAVE_GAME + "\t" + StaticValues.STRING_COMMAND_LEAVE_GAME);
    }

    private void showInGameCommandList(){
        out.println("\t" + StaticValues.COMMAND_PLACE_DICE + "\t" + StaticValues.STRING_COMMAND_PLACE_DICE);
        out.println("\t" + COMMAND_END_TURN + "\t" + StaticValues.STRING_COMMAND_END_TURN);
        out.println("\t" + StaticValues.COMMAND_USE_TOOL_CARD + "\t" + StaticValues.STRING_COMMAND_USE_TOOLCARD);
        out.println("\t" + StaticValues.COMMAND_SHOW + "\t" + StaticValues.STRING_COMMAND_SHOW);
        out.println("\t" + StaticValues.COMMAND_DISABLE_AFK + "\t" + StaticValues.STRING_COMMAND_DISABLE_AFK);
        out.println("\t" + StaticValues.COMMAND_CONNECTION + "\t" + StaticValues.STRING_COMMAND_CONNECTION);
        out.println("\t" + StaticValues.COMMAND_SERVER_STATUS + "\t" + StaticValues.STRING_COMMAND_CONNECTION);
        out.println("\t" + StaticValues.COMMAND_HELP + "\t" + StaticValues.STRING_COMMAND_HELP);
        out.println("\t" + StaticValues.COMMAND_QUIT + "\t" + StaticValues.STRING_COMMAND_QUIT);

    }

    private void inLobby() throws RemoteException {
        out.println("Congratulations , you are now in lobby!");
        out.println("--> Game ID = " + gameHashCode);
        out.println("--> Your ID = " + hashCode + " as " + username + "\n");
        printPlayersUsername();
        showLobbyCommandList();
        String command = scanner.nextLine();
        while (!command.equals(COMMAND_QUIT) && (!(command.equals("0") || command.equals("1") ||
                command.equals("2") || command.equals("3")) || waitingForPanels) && !isGameStarted){
            switch(command){
                case StaticValues.COMMAND_LEAVE_GAME:
                    controller.leaveLobby(gameHashCode , username, this);
                    out.println("Back to main menu");
                    return;
                case StaticValues.COMMAND_HELP :
                    showLobbyCommandList();
                    break;
                default:
                    out.println("Unknown command. Please retry.");
                    showLobbyCommandList();
                    break;
            }
            out.println("Insert command:");
            command = scanner.nextLine();
        }
        if(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3") && !isGameStarted) {
            //TODO handle response to server and panel choice
            int panelIndex = Integer.parseInt(command);
            controller.choosePanel(gameHashCode, hashCode, panelIndex);
            inGame(null);

        }
        else{
            if(isGameStarted){
                inGame(command);
            }
        }
    }

    public void onPlayerJoined(String username,ArrayList<String> players ,int numOfPlayers) throws RemoteException {
        playersUsername = players;
        out.println(username + " has joined the game!\n");
        printPlayersUsername();
    }

    @Override
    public void onPlayerLeave(String username, ArrayList<String> players, int numOfPlayers) throws RemoteException {
        playersUsername = players;
        out.println(username + " has left the game!");
        printPlayersUsername();
    }

    @Override
    public void onEndGame(ArrayList<PlayerScore> playersScore){
        playersScore.sort(Comparator.comparingInt(PlayerScore::getTotalPoints));
        Collections.reverse(playersScore);
        String format = "| %-15s | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d |%n";
        out.format(TABLE_SEPARATOR);
        out.format("| USERNAME        | TOT   | TOKEN | EMPTY | PRIV  | PUB1  | PUB2  | PUB3  |%n");
        out.format(TABLE_SEPARATOR);
        for(PlayerScore score : playersScore){
            out.format(format, score.getUsername(), score.getTotalPoints(), score.getFavorTokenPoints(), score.getEmptyCellsPoints(), score.getPrivateObjectiveCardPoints(), score.getPublicObjectiveCard1Points(), score.getPublicObjectiveCard2Points(), score.getPublicObjectiveCard3Points());
            out.format(TABLE_SEPARATOR);

        }
        exit(0);
    }

    @Override
    public void onTimerChanges(long timerStart, TimerStatus timerStatus, long duration){
        long remainingTime = ((duration + timerStart) - currentTimeMillis())/1000;
        if(timerStatus.equals(TimerStatus.START)){
            out.println("---> Timer started! The game will start in " + remainingTime + " seconds");
        }
        else {
            if(timerStatus.equals(TimerStatus.INTERRUPT)){
                out.println("---> Timer interrupted! Waiting for other players...");
            }
            else {
                if(timerStatus.equals(TimerStatus.FINISH)){
                    PlayerTokenSerializer.serialize(joinGameResult);
                    out.println("---> Countdown completed or full lobby. The game will start soon");
                }
            }
        }
    }

    @Override
    public void onPlayerAFK(Player playerAFK, boolean isLastPlayer, Player lastPlayer) throws RemoteException {
        if(hashCode == playerAFK.getHashCode()){
            isAFK = true;
            out.println("---> You have been suspended due to inactivity. Turns will be skipped until your signal");
            if(isLastPlayer){
                out.println("\n\n\n\n\t\t\t\t ----> " + lastPlayer.getUsername() + " WIN <----\n\n\n\n");
                exit(0);
            }
        }
        else{
            if(isLastPlayer && !isAFK){
                out.println("\n\n\n\n\t\t\t\t ----> YOU WIN <----");
                exit(0);
            }
            else if(lastPlayer != null) {
                out.println("\n\n\n\n\t\t\t\t ----> " + lastPlayer.getUsername() + " WIN <----\n\n\n\n");
                exit(0);
            }
            out.println("---> " + playerAFK.getUsername() + " has been suspended due to inactivity. His turns will be skipped " +
                    "until is back online!");
        }
    }


    private void inGame(String cmd) throws RemoteException {
        String command;
        command = cmd == null ? scanner.nextLine() : cmd;
        while (!isGameStarted){
            out.println("Invalid command, waiting for other players panel choice.");
            command = scanner.nextLine();
        }
        String[] param = command.split(" ");
        command = param[0];
        while (!command.equals(StaticValues.COMMAND_QUIT)){
            switch (command) {
                case StaticValues.COMMAND_PLACE_DICE:
                    if(currentPlayer.getHashCode() != hashCode){
                        out.println(PERMISSION_DENIED);
                        break;
                    }

                    if(placedDice){
                        out.println("Permission denied, you have already placed a dice in this turn!");
                    }
                    if (param.length != 4 || invalidParams(param, 1)){
                        out.println("ERROR --> Wrong command format");
                    }
                    else {
                        PlaceDiceResult result = controller.placeDice(gameHashCode, hashCode,
                                Integer.parseInt(param[1]), Integer.parseInt(param[2]), Integer.parseInt(param[3]));
                        if(result.status) {
                            getPlayerByHashCode(hashCode).setPanel(result.panel);
                            placedDice = true;
                            draftPool = result.draftPool;
                            out.println("Dice placed correctly. Panel updated :");

                            out.println(getPlayerByHashCode(hashCode).getPanel());
                            showDraftPool();
                        }
                        else {
                            out.println("ERROR --> DICE NOT PLACED (" + false + ")");
                        }
                    }
                    break;

                case StaticValues.COMMAND_USE_TOOL_CARD:
                    if (!currentPlayer.getUsername().equals(username)) {
                        out.println(PERMISSION_DENIED);
                        break;
                    }
                    if (usedToolCard) {
                        out.println("Already used tool card in this turn! Unable to do it again");
                        break;
                    }
                    if(param.length == 2){
                        int toolCardIndex = -1;
                        try {
                            toolCardIndex = Integer.parseInt(param[1]);
                        } catch (NumberFormatException e){
                            e.printStackTrace();
                        }
                        if (toolCardIndex >= 0 && toolCardIndex <= 2) {
                            out.println("TOOL CARD " + toolCardIndex + " USAGE:");
                            usedToolCard = true;
                            isToolCardUsableFlag = true;
                            controller.isToolCardUsable(gameHashCode, hashCode, toolCardIndex, this);
                            while (!isEndedTurn && !isToolCardActionEnded && isToolCardUsableFlag) {

                                if (toolCardFlags.isDraftPoolDiceRequired) {
                                    toolCardFlags.isDraftPoolDiceRequired = false;
                                    do {
                                        out.println("Select a dice from draft pool!");
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0, draftPool.size(), command)){
                                            break;
                                        }else {
                                            out.println(INVALID_VALUE_OUTPUT);
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int requiredIndex =Integer.parseInt(command.split(" ")[0]);
                                    controller.setDraftPoolDiceIndex(hashCode, requiredIndex);
                                }

                                if (toolCardFlags.isPanelCellRequired) {
                                    toolCardFlags.isPanelCellRequired = false;
                                    out.println("Select a cell from your panel!");
                                    command = common_input_row();
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    command = common_input();
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setPanelCellIndex(hashCode,
                                            rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }

                                if (toolCardFlags.isPanelDiceRequired) {
                                    toolCardFlags.isPanelDiceRequired = false;
                                    out.println("Select a dice from your panel!");
                                    command = common_input_row();
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    command = common_input();
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setPanelDiceIndex(hashCode,
                                            rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }

                                if (toolCardFlags.isRoundTrackDiceRequired) {
                                    toolCardFlags.isRoundTrackDiceRequired = false;
                                    do {
                                        out.println("Select a round from the round track!");
                                        command = scanner.nextLine();
                                        if(checkCommandRange(1, currentRound, command)){
                                            break;
                                        }else {
                                            out.println(INVALID_VALUE_OUTPUT);
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int roundIndex = Integer.parseInt(command.split(" ")[0]);

                                    do {
                                        out.println("Select a dice from the selected round!");
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0,roundTrack.getDicesOnRound(roundIndex).size(), command)){
                                            break;
                                        }else {
                                            out.println(INVALID_VALUE_OUTPUT);
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int diceIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setRoundTrackDiceIndex(hashCode, diceIndex, roundIndex);
                                }

                                if (toolCardFlags.isActionSignRequired){
                                    toolCardFlags.isActionSignRequired = false;
                                    out.println("You want to decrease or increase the dice value?");
                                    out.println("type \t'-' -> -1");
                                    out.println("type \t'+' -> +1");
                                    command = scanner.nextLine();
                                    while(!(command.equals("+") || command.equals("-"))){
                                        out.println("Unknown command, please retry");
                                        command = scanner.nextLine();
                                    }
                                    if (isEndedTurn) break;
                                    if(command.equals("+")){
                                        controller.setActionSign(hashCode, 1);
                                    }
                                    else if(command.equals("-")){
                                        controller.setActionSign(hashCode, -1);
                                    }
                                }
                                if(toolCardFlags.isSecondPanelDiceRequired){
                                    toolCardFlags.isSecondPanelDiceRequired = false;
                                    out.println("Chose a Dice from your panel!");
                                    command = common_input_row();
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    command = common_input();
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setSecondPanelDiceIndex(hashCode, rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }
                                if(toolCardFlags.isSecondPanelCellRequired){
                                    toolCardFlags.isSecondPanelCellRequired = false;
                                    do {
                                        out.println("Chose a Cell from your panel!");
                                        out.println(INSERT_ROW);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0,StaticValues.PATTERN_ROW, command)){
                                            break;
                                        }else {
                                            out.println(INVALID_VALUE_OUTPUT);
                                        }

                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    command = common_input();
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setSecondPanelCellIndex(hashCode, rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }
                                if(toolCardFlags.isDiceValueRequired){
                                    toolCardFlags.isDiceValueRequired = false;
                                    out.println("You have drafted a " + toolCardFlags.colorDiceValueRequired +" dice");
                                    out.println("Now choose the value");
                                    String inputValue = scanner.nextLine();
                                    while(!(inputValue.equals("1") || inputValue.equals("2") || inputValue.equals("3") || inputValue.equals("4") || inputValue.equals("5") || inputValue.equals("6"))){
                                        out.println("Invalid choice, please retry:");
                                        inputValue = scanner.nextLine();
                                    }
                                    controller.setDiceValue(hashCode, Integer.parseInt(inputValue));
                                }

                                if (toolCardFlags.isReRolledDiceActionRequired) {
                                    toolCardFlags.isReRolledDiceActionRequired = false;
                                    out.println("Your new dice drafted is: " + toolCardFlags.reRolledDice.toString());
                                }

                                if(toolCardFlags.isTwoDiceActionRequired){
                                    toolCardFlags.isTwoDiceActionRequired = false;
                                    out.println("Do you want to place another dice? (y/n)");
                                    String inputChoice = scanner.nextLine();
                                    while (!(inputChoice.equals("y")) && !(inputChoice.equals("n"))){
                                        out.println("Unknown command, type y or n");
                                        inputChoice = scanner.nextLine();
                                    }
                                    if(inputChoice.equals("y")){
                                        controller.setTwoDiceAction(hashCode, true);
                                    }
                                    else{
                                        controller.setTwoDiceAction(hashCode, false);
                                    }
                                }
                            }

                            if (isToolCardActionEnded) {
                                isToolCardActionEnded = false;
                                isToolCardUsableFlag = false;
                                //special action on tool card 11
                                if(useToolCardResult.result){
                                    if(useToolCardResult.msg != null){
                                        out.println(useToolCardResult.msg);
                                        showGameStatus();
                                    }
                                    else {
                                        out.println("Tool card used successfully! This is the update game status:");
                                        showGameStatus();
                                    }
                                }
                                else {
                                    out.println("Unable to use tool card. Game status unchanged");
                                    usedToolCard = false;
                                }
                            }
                            else {
                                out.println("There's a time and place for everything, but not now.");
                                usedToolCard = false;
                            }

                            //codice di ritorno alle normale istruzioni
                            //check su comandi ricevuti dal while precendete
                        }
                        else {
                            out.println("UNKNOWN COMMAND");
                        }
                    }
                    else {
                        out.println("UNKNOWN COMMAND!");
                    }
                    break;

                case StaticValues.COMMAND_END_TURN:
                    if(currentPlayer.getHashCode() != hashCode){
                        out.println(PERMISSION_DENIED);
                        break;
                    }
                    controller.endTurn(gameHashCode, hashCode);
                    break;
                case StaticValues.COMMAND_DISABLE_AFK:
                    if(isAFK) {
                        controller.disableAFK(gameHashCode, hashCode);
                        isAFK = false;
                        out.println("You are now back online!");
                    }
                    else {
                        out.println("Nothing to do, you're not AFK!");
                    }
                    break;
                case StaticValues.COMMAND_SHOW:
                    showGameSecondaryStuff();
                    break;
                case StaticValues.COMMAND_CONNECTION:
                    if(param.length == 2){
                        if(param[1].equals("rmi")){
                            changeConnectionMode(ConnectionModeEnum.RMI);
                        }
                        else if(param[1].equals("socket")){
                            changeConnectionMode(ConnectionModeEnum.SOCKET);
                        }
                        else{
                            out.println("Unknown command!");
                        }
                    }
                    else{
                        out.println("Unknown command!");
                    }
                    break;
                case StaticValues.COMMAND_SERVER_STATUS:
                    controller.polling();
                    out.println("--> SERVER STATUS : ACTIVE");
                    break;
                case StaticValues.COMMAND_HELP:
                    showInGameCommandList();
                    break;
                default:
                    out.println("Command not found : " + command);
                    break;
            }
            command = scanner.nextLine();
            param = command.split(" ");
            command = param[0];
        }

        out.println("Disconnecting...");
        boolean disconnectionResult = controller.disconnect(gameHashCode, hashCode);
        out.println("Disconnection result: " + disconnectionResult);
        exit(0);
    }

    private String common_input_row() {
        String command;
        do {
            out.println(INSERT_ROW);
            command = scanner.nextLine();
            if(checkCommandRange(0, StaticValues.PATTERN_ROW,command)){
                break;
            }else {
                out.println(INVALID_VALUE_OUTPUT);
            }
        } while (!isEndedTurn);
        return command;
    }

    private boolean invalidParams(String[] params, int startingIndex){
        ArrayList<String> availableStrings = new ArrayList<>();
        availableStrings.add("0");
        availableStrings.add("1");
        availableStrings.add("2");
        availableStrings.add("3");
        availableStrings.add("4");
        availableStrings.add("5");
        availableStrings.add("6");
        availableStrings.add("7");
        availableStrings.add("8");
        for(int i = startingIndex; i < params.length; i++){
            if (!availableStrings.contains(params[i])) return true;
        }
        return false;
    }

    private String common_input() {
        String command;
        do {
            out.println(INSERT_COLUMN);
            command = scanner.nextLine();
            if(checkCommandRange(0, StaticValues.PATTERN_COL,command)){
                break;
            }else {
                out.println(INVALID_VALUE_OUTPUT);
            }
        } while (!isEndedTurn);
        return command;
    }

    private Player getPlayerByHashCode(int playerHashCode) {
        for (Player player : players) {
            if (player.getHashCode() == playerHashCode) return player;
        }
        throw new IllegalArgumentException("Player Hash code not valid!");

    }
    //min included, max excluded
    private boolean checkCommandRange(int min, int max, String command) {
        int index;
        try {
            index = Integer.parseInt(command.split(" ")[0]);
        } catch (NumberFormatException e){
            return false;
        }
        if (command.split(" ").length != 1 || !(index >= min && index < max)) {
            return false;
        }
        return true;
    }

    private void printPlayersUsername(){
        out.println(playersUsername.size() + " ACTIVE PLAYERS IN GAME");
        for(String user : playersUsername){
            out.println("---> " + user);
        }
        out.println("\n");
    }

    @Override
    public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color color) throws RemoteException {

        if(panelsAlreadyChosen.size() != 0){
            out.println("ALREADY CHOSEN PANEL:");
            for (Iterator<String> iterator = panelsAlreadyChosen.keySet().iterator(); iterator.hasNext(); ) {
                String u = iterator.next();
                out.println("---> " + u + " panel :");
                out.println(panelsAlreadyChosen.get(u));
            }
        }
        if(playerHashCode == hashCode){
            out.println("WARNING --> Your Private Objective Color is " + color + "! Keep it in mind while choosing yout panel.");
            waitingForPanels = false;
            out.println("Please choose your pattern card! (hint: type a number between 0 and 3 to choose the panel you like)");
            for(int i = 0; i < panels.size() ; i++){
                out.println("---> PANEL " + i);
                out.println(panels.get(i).toString());
            }
            out.println("Enter now your choice:");
        }
    }

    @Override
    public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {
        currentTurn = 1;
        currentRound = 1;
        players = gameStartMessage.players;
        currentPlayer = gameStartMessage.players.get(0);
        isGameStarted = true;
        this.draftPool = gameStartMessage.draftpool;
        this.publicObjectiveCards = gameStartMessage.publicObjectiveCards;
        this.toolCards = gameStartMessage.toolCards;
        out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        out.println("GAME STARTED");
        out.println(PRINT_SEPARATOR);
        out.println("ROUND 1 - TURN " + currentTurn);
        out.println("PLAYERS AND PANELS :");
        for(Player player: players){
            out.println("PLAYER :" + player.getUsername());
            out.println(player.getPanel() + "\n");
        }
        out.println(PRINT_SEPARATOR);
        out.println("Draft pool: ");
        for(int i = 0; i < draftPool.size(); i++){
            out.println("ID = " + i + " ---> " + draftPool.get(i).toString());
        }

        out.println(PRINT_SEPARATOR);
        out.println("Tool Cards:");
        for(ToolCard toolCard : gameStartMessage.toolCards){
            out.println(toolCard.toString());
        }
        out.println(PRINT_SEPARATOR);
        out.println("Public Objective Cards:");
        for(PublicObjectiveCard publicObjectiveCard : gameStartMessage.publicObjectiveCards){
            out.println(publicObjectiveCard.toString());
        }
        out.println(PRINT_SEPARATOR);
        if(currentPlayer.getHashCode() == hashCode){
            out.println("It's your turn!");
        }
        else {
            out.println("It's " + currentPlayer.getUsername() + "'s turn!");
        }
    }

    @Override
    public void onDicePlaced(DicePlacedMessage dicePlacedMessage) {
        if(username.equals(dicePlacedMessage.username)) return;
        this.draftPool = dicePlacedMessage.draftPool;
        Player player = players.stream().filter(x -> x.getUsername().equals(dicePlacedMessage.username))
                .findFirst().orElse(null);
        if(player != null) {
            player.setPanel(dicePlacedMessage.panel);
            out.println(dicePlacedMessage.username + " place a dice. Updating game status:");
            showPlayerStatus(player);
        }
        showDraftPool();
    }

    @Override
    public void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) {
        //If it's my toolcard notification, discard it. you arleady know what you have done
        if(toolCardUsedMessage.player.getHashCode() == hashCode) return;
        this.draftPool = toolCardUsedMessage.draftPool;
        this.roundTrack = toolCardUsedMessage.roundTrack;
        //updating toolcard cost
        for(ToolCard toolCard : toolCards){
            if(toolCard.getId() == toolCardUsedMessage.toolCardID){
                toolCard.setUsed();
            }
        }
        //updating players stuff
        for(Player x : players){
            if (x.getHashCode() == toolCardUsedMessage.player.getHashCode()) players.set(players.indexOf(x),toolCardUsedMessage.player);
        }
        out.println(toolCardUsedMessage.player.getUsername() + " has used toolcard #" + toolCardUsedMessage.toolCardID + ", updating game status:");
        showPlayerStatus(toolCardUsedMessage.player);
        showDraftPool();
    }

    @Override
    public void onEndTurn(EndTurnMessage endTurnMessage) {
        placedDice = false;
        usedToolCard = false;
        this.draftPool = endTurnMessage.draftpool;
        this.players = endTurnMessage.players;
        this.roundTrack = endTurnMessage.roundTrack;
        this.currentPlayer = endTurnMessage.currentPlayer;
        currentTurn = endTurnMessage.turn;
        currentRound = endTurnMessage.roundTrack.getCurrentRound();
        if(endTurnMessage.previousPlayer.getUsername().equals(username)){
            out.println("Your turn is ended!");
        }
        else{
            out.println(endTurnMessage.previousPlayer.getUsername() + "'s turn is ended!");
        }
        out.println(PRINT_SEPARATOR);
        out.println("NOW STARTING --> ROUND = " + currentRound + " - TURN = " + currentTurn);
        showGameStatus();
        if(endTurnMessage.currentPlayer.getHashCode() == hashCode) {
            out.println("It's your turn!");
            showInGameCommandList();
        }
        else {
            out.println("It's " + endTurnMessage.currentPlayer.getUsername() + "'s turn!");
        }
    }

    @Override
    public void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException {
        out.println("---> " + reconnectingPlayer.getUsername() + " is back online!");
    }

    @Override
    public void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException {
        out.println("---> " + disconnectingPlayer.getUsername() + " is now offline! " +
                "He will automatically passed every turns until the next reconnection");
        if(isLastPlayer){
            out.println("You are the only active player in game!");
            out.println("\n\n\n\n\t\t\t\t ----> YOU WIN <----");
            exit(0);
        }
    }

    private void showPlayerStatus(Player player){
        if(player.getUsername().equals(username)){
            out.println("Your's status:");
        }
        else {
            out.println(player.getUsername() + "'s status:");
        }
        out.println(player.getPanel());
        out.println(player.getFavorTokens());
        out.println(PRINT_SEPARATOR);

    }

    private void showGameStatus(){
        out.println(PRINT_SEPARATOR);
        for(Player player : players){
            showPlayerStatus(player);
        }
        showDraftPool();
        showGameSecondaryStuff();
    }

    private void showDraftPool(){
        out.println(PRINT_SEPARATOR);
        out.println("Draft pool: ");
        for(int i = 0; i < draftPool.size(); i++){
            out.println("ID = " + i + " ---> " + draftPool.get(i).toString());
        }
    }

    private void showGameSecondaryStuff(){
        if(roundTrack != null && roundTrack.getCurrentRound() != 1) {
            out.println(PRINT_SEPARATOR);
            out.println("Round Track: ");
            out.println(roundTrack.toString());
        }
        out.println(PRINT_SEPARATOR);
        out.println("Tool Cards:");
        for(ToolCard toolCard : toolCards){
            out.println(toolCard.toString());
        }
        out.println(PRINT_SEPARATOR);
        out.println("Public Objective Cards:");
        for(PublicObjectiveCard publicObjectiveCard : publicObjectiveCards){
            out.println(publicObjectiveCard.toString());
        }
    }

    private void changeConnectionMode(ConnectionModeEnum connectionModeEnum){
        if(this.connectionModeEnum.equals(connectionModeEnum)) {
            out.println("This is already your connection mode. Nothing to be done.");
            out.println("Enter a new command: ");
            return;
        }
        this.connectionModeEnum = connectionModeEnum;
        if(connectionModeEnum.equals(ConnectionModeEnum.RMI)){
            //if you are here, it means that you want to change socket -> rmi
            //close socket connection before changing connection mode
            SocketClientController socketClientController = (SocketClientController) controller;
            socketClientController.isChangingConnection(gameHashCode, hashCode);
        }
        else{
            try {
                controller.detachAllGameObserver(gameHashCode, hashCode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        ConnectionHandler connectionHandler = new ConnectionHandler(connectionModeEnum);
        controller = connectionHandler.getController();
        try {
            controller.attachGameObserver(gameHashCode, this, hashCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void isToolCardUsable(boolean result) {
        isToolCardUsableFlag = result;
    }

    @Override
    public void draftPoolDiceIndexRequired() {
        toolCardFlags.reset();
        toolCardFlags.isDraftPoolDiceRequired = true;
    }

    @Override
    public void roundTrackDiceIndexRequired() {
        toolCardFlags.reset();
        toolCardFlags.isRoundTrackDiceRequired = true;
    }

    @Override
    public void panelDiceIndexRequired() {
        toolCardFlags.reset();
        toolCardFlags.isPanelDiceRequired = true;
    }

    @Override
    public void panelCellIndexRequired() {
        toolCardFlags.reset();
        toolCardFlags.isPanelCellRequired = true;
    }

    @Override
    public void actionSignRequired() {
        toolCardFlags.reset();
        toolCardFlags.isActionSignRequired = true;
    }

    @Override
    public void notifyUsageCompleted(UseToolCardResult useToolCardResult) {
        this.useToolCardResult = useToolCardResult;
        this.players = useToolCardResult.players;
        this.draftPool = useToolCardResult.draftpool;
        this.roundTrack = useToolCardResult.roundTrack;
        toolCardFlags.reset();
        isToolCardActionEnded = true;
        this.usedToolCard = useToolCardResult.result;
        if(this.useToolCardResult.result){
            for(ToolCard toolCard : toolCards){
                if (toolCard.getId() == useToolCardResult.toolCardId){
                    toolCard.setUsed();
                }
            }
        }
    }

    @Override
    public void reRolledDiceActionRequired(Dice dice) throws RemoteException {
        toolCardFlags.reset();
        toolCardFlags.reRolledDice = dice;
        toolCardFlags.isReRolledDiceActionRequired = true;

    }

    @Override
    public void secondPanelDiceIndexRequired() throws RemoteException {
        toolCardFlags.reset();
        toolCardFlags.isSecondPanelDiceRequired = true;
    }

    @Override
    public void secondPanelCellIndexRequired() throws RemoteException {
        toolCardFlags.reset();
        toolCardFlags.isSecondPanelCellRequired = true;
    }

    @Override
    public void diceValueRequired(Color color) throws RemoteException {
        toolCardFlags.reset();
        toolCardFlags.colorDiceValueRequired = color;
        toolCardFlags.isDiceValueRequired = true;

    }

    @Override
    public void twoDiceActionRequired() throws RemoteException {
        toolCardFlags.reset();
        toolCardFlags.isTwoDiceActionRequired = true;
    }

    @Override
    public void rmiPing() throws RemoteException {
        //do nothing here
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}