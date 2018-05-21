package com.sagrada.ppp.view;

import com.sagrada.ppp.*;
import com.sagrada.ppp.Color;
import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.ToolCards.ToolCard;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;

import static com.sagrada.ppp.utils.StaticValues.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    transient boolean doneByRobot;
    transient ArrayList<Dice> draftpool;
    transient ArrayList<String> orderedPlayersUsername;
    transient boolean isGameStarted;
    transient HashMap<String, WindowPanel> playersPanel;

    public CliView(RemoteController controller) throws RemoteException{
        this.scanner = new Scanner(System.in);
        this.controller = controller;
        playersUsername = new ArrayList<>();
        waitingForPanels = true;
        gameReady = false;
        keyboardPressed = true;
        doneByRobot = false;
        isGameStarted = false;
    }


    public void start() throws RemoteException {
        System.out.println("Welcome in SAGRADA");
        System.out.println("Please enter your username! This can't be empty or with spaces.");
        username = scanner.nextLine();
        while (username.length() <= 0 || username.indexOf(" ") != -1) {
            System.out.println("Error, try again! This can't be empty or with spaces.");
            username = scanner.nextLine();
        }

        JoinGameResult joinGameResult = controller.joinGame(username, this, this);
        while (hashCode < 0){
            System.out.println("Join failed. Trying new attempt...");
            joinGameResult = controller.joinGame(username, this, this);
        }

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
            if(!doneByRobot) {
                keyboardPressed = true;
            }
            int panelIndex = Integer.parseInt(command);
            myPanel = panels.get(panelIndex);
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
        if(!doneByRobot) {
            controller.choosePanel(gameHashCode, hashCode, panelIndex);
        }
        System.out.println("------------> GAME STARTED! <------------");
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
                    if (param.length != 4){
                        System.out.println("ERROR --> Unknown command!");
                    }
                    else {
                        PlaceDiceResult result = controller.placeDice(gameHashCode, hashCode, Integer.parseInt(param[1]), Integer.parseInt(param[2]), Integer.parseInt(param[3]));
                        if(result.status){
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

        if(!keyboardPressed && playerHashCode != hashCode){
            fakeInput();
        }

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

        if(!keyboardPressed){
            fakeInput();
        }

        isGameStarted = true;
        this.playersPanel = gameStartMessage.chosenPanels;
        this.draftpool = gameStartMessage.draftpool;
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

    public void fakeInput(){
        return;
/*        try {
            System.out.println("fake input needed to go ahead");
            doneByRobot = true;
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_0);
            robot.keyPress(KeyEvent.VK_ENTER);
            keyboardPressed = true;
        } catch (AWTException e) {
            e.printStackTrace();
        }*/
    }
}


