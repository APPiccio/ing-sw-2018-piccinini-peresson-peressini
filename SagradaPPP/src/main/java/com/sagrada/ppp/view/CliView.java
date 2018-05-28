package com.sagrada.ppp.view;
import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.cards.toolcards.ToolCard;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.client.ConnectionHandler;
import com.sagrada.ppp.network.client.ConnectionModeEnum;
import com.sagrada.ppp.utils.StaticValues;

import static com.sagrada.ppp.utils.StaticValues.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CliView extends UnicastRemoteObject implements LobbyObserver, Serializable, GameObserver {
    transient Scanner scanner;
    transient RemoteController controller;
    transient String username;
    transient int hashCode;
    transient int gameHashCode;
    transient long lobbyTimerStartTime;
    transient ArrayList<String> playersUsername;
    transient boolean waitingForPanels;
    transient ArrayList<WindowPanel> panels;
    transient boolean gameReady;
    transient WindowPanel myPanel;
    transient boolean keyboardPressed;
    transient ArrayList<Dice> draftpool;
    transient ArrayList<String> orderedPlayersUsername;
    transient boolean isGameStarted;
    transient HashMap<String, WindowPanel> playersPanel;
    transient ArrayList<Player> players;
    transient ArrayList<ToolCard> toolCards;
    transient ArrayList<PublicObjectiveCard> publicObjectiveCards;
    transient RoundTrack roundTrack;
    transient Player currentPlayer;
    transient boolean usedToolCard;
    transient boolean placedDice;
    transient boolean specialTurn;
    transient ConnectionHandler connectionHandler;
    transient ConnectionModeEnum connectionModeEnum;

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
    }


    public void start() throws RemoteException {
        System.out.println("Welcome in SAGRADA");
        System.out.println("Please enter your username! This can't be empty or with spaces.");
        username = scanner.nextLine();
        while (username.length() <= 0 || username.indexOf(" ") != -1) {
            System.out.println("Error, try again! This can't be empty or with spaces.");
            username = scanner.nextLine();
        }

        JoinGameResult joinGameResult = controller.joinGame(username, this);
        while (hashCode < 0){
            System.out.println("Join failed. Trying new attempt...");
            joinGameResult = controller.joinGame(username, this);
        }
        controller.attachGameObserver(joinGameResult.getGameHashCode(),this);

        gameHashCode = joinGameResult.getGameHashCode();
        hashCode = joinGameResult.getPlayerHashCode();
        username = joinGameResult.getUsername();
        lobbyTimerStartTime = joinGameResult.getTimerStart();
        playersUsername = joinGameResult.getPlayersUsername();
        System.out.println("Join completed. You are now identified as : " + username);

        if(lobbyTimerStartTime != 0){
            long remainingTime = ((lobbyTimerStartTime + StaticValues.getLobbyTimer()) - System.currentTimeMillis())/1000;
            System.out.println("---> The game will start in " + remainingTime + " seconds");
        }
        inLobby();
    }

    public void showCommandList(){
        System.out.println("COMMANDS:");
        System.out.println("\t" + COMMAND_QUIT + "\t" + STRING_COMMAND_QUIT);
        System.out.println("\t" + COMMAND_CREATE_GAME + "\t" + STRING_COMMAND_CREATE_GAME);
        System.out.println("\t" + COMMAND_SHOW_GAMES + "\t" + STRING_COMMAND_SHOW_GAMES);
        System.out.println("\t" + COMMAND_JOIN_GAME + "\t" + STRING_COMMAND_JOIN_GAME);
        System.out.println("\t" + COMMAND_LEAVE_GAME + "\t" + STRING_COMMAND_LEAVE_GAME);
        System.out.println("\t" + COMMAND_HELP + "\t" + STRING_COMMAND_HELP);
    }

    public void showLobbyCommandList(){
        System.out.println("\t" + StaticValues.COMMAND_QUIT + "\t" + StaticValues.STRING_COMMAND_QUIT);
        System.out.println("\t" + StaticValues.COMMAND_HELP + "\t" + StaticValues.STRING_COMMAND_HELP);
        System.out.println("\t" + StaticValues.COMMAND_LEAVE_GAME + "\t" + StaticValues.STRING_COMMAND_LEAVE_GAME);
    }

    public void showInGameCommandList(){
        System.out.println("\t" + StaticValues.COMMAND_PLACE_DICE + "\t" + StaticValues.STRING_COMMAND_PLACE_DICE);
        System.out.println("\t" + COMMAND_END_TURN + "\t" + StaticValues.STRING_COMMAND_END_TURN);
        System.out.println("\t" + StaticValues.COMMAND_USE_TOOLCARD + "\t" + StaticValues.STRING_COMMAND_USE_TOOLCARD);
        System.out.println("\t" + StaticValues.COMMAND_SHOW + "\t" + StaticValues.STRING_COMMAND_SHOW);
    }



    //TODO add show list of active players when someone join the lobby
    public void inLobby() throws RemoteException {
        System.out.println("Congratulations , you are now in lobby!");
        System.out.println("--> Game ID = " + gameHashCode);
        System.out.println("--> Your ID = " + hashCode + " as " + username + "\n");
        printPlayersUsername();
        showLobbyCommandList();
        String command = scanner.nextLine();
        while (!command.equals(COMMAND_QUIT) && !(command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3")) && !isGameStarted){
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USERNAME\t\tTOTAL\t\tTOKENS\t\tEMPTY CELLS\t\tPRIVATE\t\tPUB1\t\tPUB2\t\tPUB3\n");
        stringBuilder.append("--------------------------------------------------------------------------------------------------\n");
        for (PlayerScore playerScore : playersScore) {
            stringBuilder.append(playerScore.getUsername() + "\t\t");
            stringBuilder.append(playerScore.getTotalPoints() + "\t\t");
            stringBuilder.append(playerScore.getFavorTokenPoints() + "\t\t");
            stringBuilder.append("-" + playerScore.getEmptyCellsPoints() + "\t\t");
            stringBuilder.append(playerScore.getPrivateObjectiveCardPoints() + "\t\t");
            stringBuilder.append(playerScore.getPublicObjectiveCard1Points() + "\t\t");
            stringBuilder.append(playerScore.getPublicObjectiveCard2Points() + "\t\t");
            stringBuilder.append(playerScore.getPublicObjectiveCard3Points() + "\n\n");
        }
        System.out.println(stringBuilder.toString());
    }

    @Override
    public void onTimerChanges(long timerStart, TimerStatus timerStatus) throws RemoteException {
        long duration = ((StaticValues.getLobbyTimer() + timerStart) - System.currentTimeMillis())/1000;
        if(timerStatus.equals(TimerStatus.START)){
            System.out.println("---> Timer started! The game will start in " + duration + " seconds");
        }
        else {
            if(timerStatus.equals(TimerStatus.INTERRUPT)){
                System.out.println("---> Timer interrupted! Waiting for other players...");
            }
            else {
                if(timerStatus.equals(TimerStatus.FINISH)){
                    System.out.println("---> Countdown completed or full lobby. The game will start soon");
                    gameReady = true;
                }
            }
        }
    }

    //do stuff in game
    public void inGame(int panelIndex, String cmd) throws RemoteException {
        //do something
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
            switch (command){
                case StaticValues.COMMAND_PLACE_DICE:
                    if(!currentPlayer.getUsername().equals(username)){
                        System.out.println("Permission denied, it's not your turn!");
                        break;
                    }

                    if(placedDice){
                        System.out.println("Permission denied, you have already placed a dice in this turn!");
                    }
                    if (param.length != 4){
                        System.out.println("ERROR --> Unknown command!");
                    }
                    else {
                        PlaceDiceResult result = controller.placeDice(gameHashCode, hashCode, Integer.parseInt(param[1]), Integer.parseInt(param[2]), Integer.parseInt(param[3]));
                        if(result.status){
                            placedDice = true;
                            playersPanel.remove(username);
                            playersPanel.put(username, result.panel);
                            draftpool.remove(Integer.parseInt(param[1]));
                            System.out.println("Dice placed correctly. Panel updated :");
                            System.out.println(playersPanel.get(username));
                            System.out.println("----------------------------------------");
                            System.out.println("Draft pool: ");
                            for(int i = 0; i < draftpool.size(); i++){
                                System.out.println("ID = " + i + " ---> " + draftpool.get(i).toString());
                            }
                        }
                        else {
                            System.out.println("ERROR --> DICE NOT PLACED (" +result.status + ")");
                        }
                    }
                    break;
                case StaticValues.COMMAND_USE_TOOLCARD:
                    break;
                case StaticValues.COMMAND_END_TURN:
                    if(!currentPlayer.getUsername().equals(username)){
                        System.out.println("Permission denied, it's not your turn!");
                        break;
                    }
                    controller.endTurn(gameHashCode, hashCode);
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
                    System.out.println("Command not found : "+command);
                    break;
            }
            command = scanner.nextLine();
            param = command.split(" ");
            command = param[0];
        }

    }


    public void printPlayersUsername(){
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
        currentPlayer = gameStartMessage.players.get(0);
        isGameStarted = true;
        this.playersPanel = gameStartMessage.chosenPanels;
        this.draftpool = gameStartMessage.draftpool;
        this.publicObjectiveCards = gameStartMessage.publicObjectiveCards;
        this.toolCards = gameStartMessage.toolCards;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("GAME STARTED");
        System.out.println("----------------------------------------");
        System.out.println("ROUND 1 - TURN 1");
        System.out.println("PLAYERS AND PANELS :");
        for(String username : gameStartMessage.chosenPanels.keySet()){
            System.out.println("PLAYER :" + username);
            System.out.println(gameStartMessage.chosenPanels.get(username).toString() + "\n");
        }
        System.out.println("----------------------------------------");
        System.out.println("Draft pool: ");
        for(int i = 0; i < draftpool.size(); i++){
            System.out.println("ID = " + i + " ---> " + draftpool.get(i).toString());
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
        orderedPlayersUsername = playersUsername;
        System.out.println("----------------------------------------");
        if(gameStartMessage.playersUsername.get(0).equals(username)){
            System.out.println("It's your turn!");
        }
        else {
            System.out.println("It's " + gameStartMessage.playersUsername.get(0) + "'s turn!");
        }
    }

    @Override
    public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
        System.out.println(dicePlacedMessage.username + "placed a dice (notification)");
    }

    @Override
    public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {
        placedDice = false;
        usedToolCard = false;
        specialTurn = false;
        this.draftpool = endTurnMessage.draftpool;
        this.players = endTurnMessage.players;
        this.roundTrack = endTurnMessage.roundTrack;
        this.currentPlayer = endTurnMessage.currentPlayer;
        if(endTurnMessage.previousPlayer.getUsername().equals(username)){
            System.out.println("Your turn is ended!");
        }
        else{
            System.out.println(endTurnMessage.previousPlayer.getUsername() + "'s turn is ended! Player status:");
            //showPlayerStatus(endTurnMessage.previousPlayer);
        }
        System.out.println("----------------------------------------");
        System.out.println("NOW STARTING --> ROUND = " + endTurnMessage.roundTrack.getCurrentRound() + " - TURN = " + endTurnMessage.turn);
        showGameStatus();
        if(endTurnMessage.currentPlayer.getUsername().equals(username)) {
            System.out.println("It's your turn!");
            showInGameCommandList();
        }
        else {
            System.out.println("It's " + endTurnMessage.currentPlayer.getUsername() + "'s turn!");
        }
    }

    private void showPlayerStatus(Player player){
        if(player.getUsername() == username){
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
        for(int i = 0; i < draftpool.size(); i++){
            System.out.println("ID = " + i + " ---> " + draftpool.get(i).toString());
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
                controller.detachGameObserver(gameHashCode,this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        ConnectionHandler connectionHandler = new ConnectionHandler(connectionModeEnum);
        controller = connectionHandler.getController();
        try {
            controller.attachGameObserver(gameHashCode, this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

