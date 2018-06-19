package com.sagrada.ppp.view;

import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.cards.toolcards.ToolCard;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.client.ConnectionHandler;
import com.sagrada.ppp.network.client.ConnectionModeEnum;
import com.sagrada.ppp.utils.PlayerTokenSerializer;
import com.sagrada.ppp.utils.StaticValues;

import static com.sagrada.ppp.utils.StaticValues.*;

import java.io.Serializable;

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
    private transient ArrayList<WindowPanel> panels;
    private transient boolean gameReady;
    private transient WindowPanel myPanel;
    private transient boolean keyboardPressed;
    private transient ArrayList<Dice> draftPool;
    private transient ArrayList<String> orderedPlayersUsername;
    private transient boolean isGameStarted;
    private transient ArrayList<Player> players;
    private transient ArrayList<ToolCard> toolCards;
    private transient ArrayList<PublicObjectiveCard> publicObjectiveCards;
    private transient RoundTrack roundTrack;
    private transient Player currentPlayer;
    private transient boolean usedToolCard;
    private transient boolean placedDice;
    private transient boolean specialTurn;
    transient ConnectionHandler connectionHandler;
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

    public CliView(RemoteController controller, ConnectionModeEnum connectionModeEnum) throws RemoteException{
        this.scanner = new Scanner(System.in);
        this.controller = controller;
        playersUsername = new ArrayList<>();
        waitingForPanels = true;
        gameReady = false;
        keyboardPressed = true;
        isGameStarted = false;
        specialTurn = false;
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
            System.out.println("Do you want to resume the previous game? (y/n)");
            String in = scanner.nextLine();
            while(!in.equals("y") && !in.equals("n")){
                System.out.println("Invalid answer, type y (yes) or n (no)");
                in = scanner.nextLine();
            }
            if (in.equals("y")){
                JoinGameResult jgr = PlayerTokenSerializer.deserialize();
                ReconnectionResult reconnectionResult = controller.reconnection(jgr.getPlayerHashCode(),
                        jgr.getGameHashCode(), this);
                if (!reconnectionResult.result) {
                    System.out.println(reconnectionResult.message);
                    do {
                        System.out.println("Do you want to retry?");
                        in = scanner.nextLine();
                        while (!in.equals("y") && !in.equals("n")) {
                            System.out.println("Invalid answer, type y (yes) or n (no)");
                            in = scanner.nextLine();
                        }
                        if (in.equals("y")) {
                            reconnectionResult = controller.reconnection(jgr.getPlayerHashCode(),
                                    jgr.getGameHashCode(), this);
                            System.out.println(reconnectionResult.message);
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
                        inGame(0, null);
                    }
                }
                else {
                    System.out.println(reconnectionResult.message);
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
                    inGame(0, null);
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
        System.out.println("Welcome to SAGRADA");
        System.out.println("Please enter your username! This can't be empty or with spaces.");
        username = scanner.nextLine();
        while (username.length() <= 0 || username.contains(" ")) {
            System.out.println("Error, try again! This can't be empty or with spaces.");
            username = scanner.nextLine();
        }

        this.joinGameResult = controller.joinGame(username, this);
        while (hashCode < 0){
            System.out.println("Join failed. Trying new attempt...");
            joinGameResult = controller.joinGame(username, this);
        }
        controller.attachGameObserver(joinGameResult.getGameHashCode(),this, joinGameResult.getPlayerHashCode());

        gameHashCode = joinGameResult.getGameHashCode();
        hashCode = joinGameResult.getPlayerHashCode();
        username = joinGameResult.getUsername();
        long lobbyTimerStartTime = joinGameResult.getTimerStart();
        playersUsername = joinGameResult.getPlayersUsername();
        System.out.println("Join completed. You are now identified as : " + username);

        if(lobbyTimerStartTime != 0){
            long remainingTime = ((lobbyTimerStartTime + StaticValues.LOBBY_TIMER) -
                    System.currentTimeMillis())/1000;
            System.out.println("---> The game will start in " + remainingTime + " seconds");
        }
        inLobby();
    }

    private void showLobbyCommandList(){
        System.out.println("\t" + StaticValues.COMMAND_QUIT + "\t" + StaticValues.STRING_COMMAND_QUIT);
        System.out.println("\t" + StaticValues.COMMAND_HELP + "\t" + StaticValues.STRING_COMMAND_HELP);
        System.out.println("\t" + StaticValues.COMMAND_LEAVE_GAME + "\t" + StaticValues.STRING_COMMAND_LEAVE_GAME);
    }

    private void showInGameCommandList(){
        System.out.println("\t" + StaticValues.COMMAND_PLACE_DICE + "\t" + StaticValues.STRING_COMMAND_PLACE_DICE);
        System.out.println("\t" + COMMAND_END_TURN + "\t" + StaticValues.STRING_COMMAND_END_TURN);
        System.out.println("\t" + StaticValues.COMMAND_USE_TOOLCARD + "\t" + StaticValues.STRING_COMMAND_USE_TOOLCARD);
        System.out.println("\t" + StaticValues.COMMAND_SHOW + "\t" + StaticValues.STRING_COMMAND_SHOW);
        System.out.println("\t" + StaticValues.COMMAND_DISABLE_AFK + "\t" + StaticValues.STRING_COMMAND_DISABLE_AFK);
        System.out.println("\t" + StaticValues.COMMAND_CONNECTION + "\t" + StaticValues.STRING_COMMAND_CONNECTION);
        System.out.println("\t" + StaticValues.COMMAND_HELP + "\t" + StaticValues.STRING_COMMAND_HELP);

    }

    //TODO add show list of active players when someone join the lobby
    private void inLobby() throws RemoteException {
        System.out.println("Congratulations , you are now in lobby!");
        System.out.println("--> Game ID = " + gameHashCode);
        System.out.println("--> Your ID = " + hashCode + " as " + username + "\n");
        printPlayersUsername();
        showLobbyCommandList();
        String command = scanner.nextLine();
        while (!command.equals(COMMAND_QUIT) && (!(command.equals("0") || command.equals("1") ||
                command.equals("2") || command.equals("3")) || waitingForPanels) && !isGameStarted){
            switch(command){
                case StaticValues.COMMAND_LEAVE_GAME:
                    controller.leaveLobby(gameHashCode , username, this);
                    System.out.println("Back to main menu");
                    return;
                case StaticValues.COMMAND_HELP :
                    showLobbyCommandList();
                    break;
                default:
                    System.out.println("Unknown command. Please retry.");
                    showLobbyCommandList();
                    break;
            }
            System.out.println("Insert command:");
            command = scanner.nextLine();
        }
        if(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3") && !isGameStarted) {
            //TODO handle response to server and panel choice
            int panelIndex = Integer.parseInt(command);
            myPanel = panels.get(panelIndex);
            controller.choosePanel(gameHashCode, hashCode, panelIndex);
            inGame(panelIndex, null);

        }
        else{
            if(isGameStarted){
                inGame(0, command);
            }
        }
    }

    public void onPlayerJoined(String username,ArrayList<String> players ,int numOfPlayers) throws RemoteException {
        playersUsername = players;
        System.out.println(username + " has joined the game!\n");
        printPlayersUsername();
    }

    @Override
    public void onPlayerLeave(String username, ArrayList<String> players, int numOfPlayers) throws RemoteException {
        playersUsername = players;
        System.out.println(username + " has left the game!");
        printPlayersUsername();
    }

    @Override
    public void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {
        playersScore.sort(Comparator.comparingInt(PlayerScore::getTotalPoints));
        Collections.reverse(playersScore);
        String format = "| %-15s | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d | %-5d |%n";
        System.out.format("+-----------------+-------+-------+-------+-------+-------+-------+-------+%n");
        System.out.format("| USERNAME        | TOT   | TOKEN | EMPTY | PRIV  | PUB1  | PUB2  | PUB3  |%n");
        System.out.format("+-----------------+-------+-------+-------+-------+-------+-------+-------+%n");
        for(PlayerScore score : playersScore){
            System.out.format(format, score.getUsername(), score.getTotalPoints(), score.getFavorTokenPoints(), score.getEmptyCellsPoints(), score.getPrivateObjectiveCardPoints(), score.getPublicObjectiveCard1Points(), score.getPublicObjectiveCard2Points(), score.getPublicObjectiveCard3Points());
            System.out.format("+-----------------+-------+-------+-------+-------+-------+-------+-------+%n");

        }
        System.exit(0);
    }

    @Override
    public void onTimerChanges(long timerStart, TimerStatus timerStatus) throws RemoteException {
        long duration = ((StaticValues.LOBBY_TIMER + timerStart) - System.currentTimeMillis())/1000;
        if(timerStatus.equals(TimerStatus.START)){
            System.out.println("---> Timer started! The game will start in " + duration + " seconds");
        }
        else {
            if(timerStatus.equals(TimerStatus.INTERRUPT)){
                System.out.println("---> Timer interrupted! Waiting for other players...");
            }
            else {
                if(timerStatus.equals(TimerStatus.FINISH)){
                    PlayerTokenSerializer.serialize(joinGameResult);
                    System.out.println("---> Countdown completed or full lobby. The game will start soon");
                    gameReady = true;
                }
            }
        }
    }

    @Override
    public void onPlayerAFK(Player playerAFK, boolean isLastPlayer, Player lastPlayer) throws RemoteException {
        if(hashCode == playerAFK.getHashCode()){
            isAFK = true;
            System.out.println("---> You have been suspended due to inactivity. Turns will be skipped until your signal");
            if(isLastPlayer){
                System.out.println("\n\n\n\n\t\t\t\t ----> " + lastPlayer.getUsername() + " WIN <----\n\n\n\n");
                System.exit(0);
            }
        }
        else{
            if(isLastPlayer && !isAFK){
                System.out.println("\n\n\n\n\t\t\t\t ----> YOU WIN <----");
                System.exit(0);
            }
            else {
                System.out.println("\n\n\n\n\t\t\t\t ----> " + lastPlayer.getUsername() + " WIN <----\n\n\n\n");
                System.exit(0);
            }
        }
        System.out.println("--> You have been suspended due to inactivity. Turns will be skipped until your signal");
    }

    private void inGame(int panelIndex, String cmd) throws RemoteException {
        String command;
        if(cmd == null) {
            command = scanner.nextLine();
        }
        else{
            command = cmd;
        }
        while (!isGameStarted){
            System.out.println("Invalid command, waiting for other players panel choice.");
            command = scanner.nextLine();
        }
        String[] param = command.split(" ");
        command = param[0];
        while (!command.equals(StaticValues.COMMAND_QUIT)){
            switch (command) {
                case StaticValues.COMMAND_PLACE_DICE:
                    if(currentPlayer.getHashCode() != hashCode){
                        System.out.println(PERMISSION_DENIED);
                        break;
                    }

                    if(placedDice){
                        System.out.println("Permission denied, you have already placed a dice in this turn!");
                    }
                    if (param.length != 4){
                        System.out.println("ERROR --> Unknown command!");
                    }
                    else {
                        PlaceDiceResult result = controller.placeDice(gameHashCode, hashCode,
                                Integer.parseInt(param[1]), Integer.parseInt(param[2]), Integer.parseInt(param[3]));
                        if(result.status) {
                            getPlayerByHashCode(hashCode).setPanel(result.panel);
                            placedDice = true;
                            draftPool = result.draftPool;
                            System.out.println("Dice placed correctly. Panel updated :");

                            System.out.println(getPlayerByHashCode(hashCode).getPanel());
                            System.out.println("----------------------------------------");
                            System.out.println("Draft pool: ");
                            for(int i = 0; i < draftPool.size(); i++){
                                System.out.println("ID = " + i + " ---> " + draftPool.get(i).toString());
                            }
                        }
                        else {
                            System.out.println("ERROR --> DICE NOT PLACED (" + result.status + ")");
                        }
                    }
                    break;

                case StaticValues.COMMAND_USE_TOOLCARD:
                    if (!currentPlayer.getUsername().equals(username)) {
                        System.out.println(PERMISSION_DENIED);
                        break;
                    }
                    if (usedToolCard) {
                        System.out.println("Already used tool card in this turn! Unable to do it again");
                        break;
                    }
                    if(param.length == 2){
                        int toolCardIndex = -1;
                        try {
                            toolCardIndex = Integer.parseInt(param[1]);
                        } catch (NumberFormatException e){
                            toolCardIndex = -1;
                        }
                        if (toolCardIndex >= 0 && toolCardIndex <= 2) {
                            System.out.println("TOOL CARD " + toolCardIndex + " USAGE:");
                            usedToolCard = true;
                            isToolCardUsableFlag = true;
                            controller.isToolCardUsable(gameHashCode, hashCode, toolCardIndex, this);
                            while (!isEndedTurn && !isToolCardActionEnded && isToolCardUsableFlag) {

                                if (toolCardFlags.isDraftPoolDiceRequired) {
                                    toolCardFlags.isDraftPoolDiceRequired = false;
                                    do {
                                        System.out.println("Select a dice from draft pool!");
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0, draftPool.size(), command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int requiredIndex =Integer.parseInt(command.split(" ")[0]);
                                    controller.setDraftPoolDiceIndex(hashCode, requiredIndex);
                                }

                                if (toolCardFlags.isPanelCellRequired) {
                                    toolCardFlags.isPanelCellRequired = false;
                                    System.out.println("Select a cell from your panel!");
                                    do {
                                        System.out.println(INSERT_ROW);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0,StaticValues.PATTERN_ROW, command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    do {
                                        System.out.println(INSERT_COLUMN);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0, StaticValues.PATTERN_COL,command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setPanelCellIndex(hashCode,
                                            rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }

                                if (toolCardFlags.isPanelDiceRequired) {
                                    toolCardFlags.isPanelDiceRequired = false;
                                    System.out.println("Select a dice from your panel!");
                                    do {
                                        System.out.println(INSERT_ROW);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0, StaticValues.PATTERN_ROW,command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    do {
                                        System.out.println(INSERT_COLUMN);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0, StaticValues.PATTERN_COL,command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setPanelDiceIndex(hashCode,
                                            rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }

                                if (toolCardFlags.isRoundTrackDiceRequired) {
                                    toolCardFlags.isRoundTrackDiceRequired = false;
                                    do {
                                        System.out.println("Select a round from the round track!");
                                        command = scanner.nextLine();
                                        if(checkCommandRange(1, currentRound, command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int roundIndex = Integer.parseInt(command.split(" ")[0]);

                                    do {
                                        System.out.println("Select a dice from the selected round!");
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0,roundTrack.getDicesOnRound(roundIndex).size(), command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int diceIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setRoundTrackDiceIndex(hashCode, diceIndex, roundIndex);
                                }

                                if (toolCardFlags.isActionSignRequired){
                                    toolCardFlags.isActionSignRequired = false;
                                    System.out.println("You want to decrease or increase the dice value?");
                                    System.out.println("type \t'-' -> -1");
                                    System.out.println("type \t'+' -> +1");
                                    command = scanner.nextLine();
                                    while(!(command.equals("+") || command.equals("-"))){
                                        System.out.println("Unknown command, please retry");
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
                                    System.out.println("Chose a Dice from your panel!");
                                    do {
                                        System.out.println(INSERT_ROW);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0,StaticValues.PATTERN_ROW, command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    }while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    do {
                                        System.out.println(INSERT_COLUMN);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0, StaticValues.PATTERN_COL,command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setSecondPanelDiceIndex(hashCode, rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }
                                if(toolCardFlags.isSecondPanelCellRequired){
                                    toolCardFlags.isSecondPanelCellRequired = false;
                                    do {
                                        System.out.println("Chose a Cell from your panel!");
                                        System.out.println(INSERT_ROW);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0,StaticValues.PATTERN_ROW, command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }

                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int rowIndex = Integer.parseInt(command.split(" ")[0]);

                                    do {
                                        System.out.println(INSERT_COLUMN);
                                        command = scanner.nextLine();
                                        if(checkCommandRange(0,StaticValues.PATTERN_COL, command)){
                                            break;
                                        }else {
                                            System.out.println("Invalid value!");
                                        }
                                    } while (!isEndedTurn);
                                    if (isEndedTurn) break;
                                    int columnIndex = Integer.parseInt(command.split(" ")[0]);
                                    controller.setSecondPanelCellIndex(hashCode, rowIndex*(StaticValues.PATTERN_COL) + columnIndex);
                                }
                                if(toolCardFlags.isDiceValueRequired){
                                    toolCardFlags.isDiceValueRequired = false;
                                    System.out.println("You have drafted a " + toolCardFlags.colorDiceValueRequired +" dice");
                                    System.out.println("Now choose the value");
                                    String inputValue = scanner.nextLine();
                                    while(!(inputValue.equals("1") || inputValue.equals("2") || inputValue.equals("3") || inputValue.equals("4") || inputValue.equals("5") || inputValue.equals("6"))){
                                        System.out.println("Invalid choice, please retry:");
                                        inputValue = scanner.nextLine();
                                    }
                                    controller.setDiceValue(hashCode, Integer.parseInt(inputValue));
                                }

                                if (toolCardFlags.isReRolledDiceActionRequired) {
                                    toolCardFlags.isReRolledDiceActionRequired = false;
                                    System.out.println("Your new dice drafted is: " + toolCardFlags.reRolledDice.toString());
                                }

                                if(toolCardFlags.isTwoDiceActionRequired){
                                    toolCardFlags.isTwoDiceActionRequired = false;
                                    System.out.println("Do you want to place another dice? (y/n)");
                                    String inputChoice = scanner.nextLine();
                                    while (!(inputChoice.equals("y")) && !(inputChoice.equals("n"))){
                                        System.out.println("Unknown command, type y or n");
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
                                //special action on toolcard 11
                                if(useToolCardResult.result){
                                    if(useToolCardResult.msg != null){
                                        System.out.println(useToolCardResult.msg);
                                        showGameStatus();
                                    }
                                    else {
                                        System.out.println("Tool card used successfully! This is the update game status:");
                                        showGameStatus();
                                    }
                                }
                                else {
                                    System.out.println("Unable to use tool card. Game status unchanged");
                                    usedToolCard = false;
                                }
                            }
                            else {
                                System.out.println("There's a time and place for everything, but not now.");
                                usedToolCard = false;
                            }

                            //codice di ritorno alle normale istruzioni
                            //check su comandi ricevuti dal while precendete
                        }
                        else {
                            System.out.println("UNKNOWN COMMAND");
                        }
                    }
                    else {
                        System.out.println("UNKNOWN COMMAND!");
                    }
                    break;

                case StaticValues.COMMAND_END_TURN:
                    if(!(currentPlayer.getHashCode() == hashCode)){
                        System.out.println(PERMISSION_DENIED);
                        break;
                    }
                    controller.endTurn(gameHashCode, hashCode);
                    break;
                case StaticValues.COMMAND_DISABLE_AFK:
                    if(isAFK) {
                        controller.disableAFK(gameHashCode, hashCode);
                        isAFK = false;
                        System.out.println("You are now back online!");
                    }
                    else {
                        System.out.println("Nothing to do, you're not AFK!");
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
                            System.out.println("Unknown command!");
                        }
                    }
                    else{
                        System.out.println("Unknown command!");
                    }
                    break;
                default:
                    System.out.println("Command not found : " + command);
                    break;
            }
            command = scanner.nextLine();
            param = command.split(" ");
            command = param[0];
        }

        System.out.println("Disconnecting...");
        boolean disconnectionResult = controller.disconnect(gameHashCode, hashCode);
        System.out.println("Disconnection result: " + disconnectionResult);
        System.exit(0);
    }

    private Player getPlayerByHashCode(int playerHashCode) {
        for (Player player : players) {
            if (player.getHashCode() == playerHashCode) return player;
        }
        return null;

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
        System.out.println(playersUsername.size() + " ACTIVE PLAYERS IN GAME");
        for(String user : playersUsername){
            System.out.println("--->" + user);
        }
        System.out.println("\n");
    }

    @Override
    public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color color) throws RemoteException {

        if(panelsAlreadyChosen.size() != 0){
            System.out.println("ALREADY CHOSEN PANEL:");
            for(String u : panelsAlreadyChosen.keySet()){
                System.out.println("---> " +u + " panel :");
                System.out.println(panelsAlreadyChosen.get(u));
            }
        }
        if(playerHashCode == hashCode){
            keyboardPressed = false;
            System.out.println("WARNING --> Your Private Objective Color is " + color + "! Keep it in mind while choosing yout panel.");
            waitingForPanels = false;
            this.panels = panels;
            System.out.println("Please choose your pattern card! (hint: type a number between 0 and 3 to choose the panel you like)");
            for(int i = 0; i < panels.size() ; i++){
                System.out.println("---> PANEL " + i);
                System.out.println(panels.get(i).toString());
            }
            System.out.println("Enter now your choice:");
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("GAME STARTED");
        System.out.println("----------------------------------------");
        System.out.println("ROUND 1 - TURN " + currentTurn);
        System.out.println("PLAYERS AND PANELS :");
        for(Player player: players){
            System.out.println("PLAYER :" + username);
            System.out.println(player.getPanel() + "\n");
        }
        System.out.println("----------------------------------------");
        System.out.println("Draft pool: ");
        for(int i = 0; i < draftPool.size(); i++){
            System.out.println("ID = " + i + " ---> " + draftPool.get(i).toString());
        }

        System.out.println("----------------------------------------");
        System.out.println("Tool Cards:");
        for(ToolCard toolCard : gameStartMessage.toolCards){
            System.out.println(toolCard.toString());
        }
        System.out.println("----------------------------------------");
        System.out.println("Public Objective Cards:");
        for(PublicObjectiveCard publicObjectiveCard : gameStartMessage.publicObjectiveCards){
            System.out.println(publicObjectiveCard.toString());
        }
        System.out.println("----------------------------------------");
        if(currentPlayer.getHashCode() == hashCode){
            System.out.println("It's your turn!");
        }
        else {
            System.out.println("It's " + currentPlayer.getUsername() + "'s turn!");
        }
    }

    @Override
    public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
        System.out.println(dicePlacedMessage.username + " place a dice. Updating game status:");
        showGameStatus();
    }

    @Override
    public void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) throws RemoteException {
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
        System.out.println(toolCardUsedMessage.player.getUsername() + " has used toolcard #" + toolCardUsedMessage.toolCardID + ", updating game status:");
        showGameStatus();
    }

    @Override
    public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {
        placedDice = false;
        usedToolCard = false;
        specialTurn = false;
        this.draftPool = endTurnMessage.draftpool;
        this.players = endTurnMessage.players;
        this.roundTrack = endTurnMessage.roundTrack;
        this.currentPlayer = endTurnMessage.currentPlayer;
        currentTurn = endTurnMessage.turn;
        currentRound = endTurnMessage.roundTrack.getCurrentRound();
        if(endTurnMessage.previousPlayer.getUsername().equals(username)){
            System.out.println("Your turn is ended!");
        }
        else{
            System.out.println(endTurnMessage.previousPlayer.getUsername() + "'s turn is ended! Player status:");
            //showPlayerStatus(endTurnMessage.previousPlayer);
        }
        System.out.println("----------------------------------------");
        System.out.println("NOW STARTING --> ROUND = " + currentRound + " - TURN = " + currentTurn);
        showGameStatus();
        if(endTurnMessage.currentPlayer.getHashCode() == hashCode) {
            System.out.println("It's your turn!");
            showInGameCommandList();
        }
        else {
            System.out.println("It's " + endTurnMessage.currentPlayer.getUsername() + "'s turn!");
        }
    }

    @Override
    public void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException {
        System.out.println("---> " + reconnectingPlayer.getUsername() + " is back online!");
    }

    @Override
    public void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException {
        System.out.println("---> " + disconnectingPlayer.getUsername() + " is now offline! " +
                "He will automatically passed every turns until the next reconnection");
        if(isLastPlayer){
            System.out.println("You are the only active player in game!");
            System.out.println("\n\n\n\n\t\t\t\t ----> YOU WIN <----");
            System.exit(0);
        }
    }

    private void showPlayerStatus(Player player){
        if(player.getUsername().equals(username)){
            System.out.println("Your's status:");
        }
        else {
            System.out.println(player.getUsername() + "'s status:");
        }
        System.out.println(player.getPanel());
        System.out.println(player.getFavorTokens());
        System.out.println("----------------------------------------");

    }

    private void showGameStatus(){
        System.out.println("----------------------------------------");
        for(Player player : players){
            showPlayerStatus(player);
        }
        System.out.println("----------------------------------------");
        System.out.println("Draft pool: ");
        for(int i = 0; i < draftPool.size(); i++){
            System.out.println("ID = " + i + " ---> " + draftPool.get(i).toString());
        }
        showGameSecondaryStuff();
    }

    private void showGameSecondaryStuff(){
        System.out.println("----------------------------------------");
        System.out.println("Round Track: ");
        System.out.println(roundTrack.toString());
        System.out.println("----------------------------------------");
        System.out.println("Tool Cards:");
        for(ToolCard toolCard : toolCards){
            System.out.println(toolCard.toString());
        }
        System.out.println("----------------------------------------");
        System.out.println("Public Objective Cards:");
        for(PublicObjectiveCard publicObjectiveCard : publicObjectiveCards){
            System.out.println(publicObjectiveCard.toString());
        }
    }

    private void changeConnectionMode(ConnectionModeEnum connectionModeEnum){
        if(this.connectionModeEnum.equals(connectionModeEnum)) return;
        this.connectionModeEnum = connectionModeEnum;
        if(connectionModeEnum.equals(ConnectionModeEnum.RMI)){
            //if you are here, it means that you want to change socket -> rmi
            //close socket connection before changing connection mode
            try {
                controller.detachAllGameObserver(gameHashCode,hashCode);
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