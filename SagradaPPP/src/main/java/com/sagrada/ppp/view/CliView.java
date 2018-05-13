package com.sagrada.ppp.view;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.LobbyObserver;
import com.sagrada.ppp.TimerStatus;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;

import static com.sagrada.ppp.utils.StaticValues.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class CliView extends UnicastRemoteObject implements LobbyObserver, Serializable {
    transient Scanner scanner;
    transient RemoteController controller;
    transient String username;
    transient int hashCode;
    transient int gameHashCode;
    transient long lobbyTimerStartTime;
    transient ArrayList<String> playersUsername;

    public CliView(RemoteController controller) throws RemoteException{
        this.scanner = new Scanner(System.in);
        this.controller = controller;
        playersUsername = new ArrayList<>();
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


    //TODO add show list of active players when someone join the lobby
    public void inLobby() throws RemoteException {
        System.out.println("Congratulations , you are now in lobby!");
        System.out.println("--> Game ID = " + gameHashCode);
        System.out.println("--> Your ID = " + hashCode + " as " + username + "\n");
        printPlayersUsername();
        showLobbyCommandList();
        String command = scanner.nextLine();
        while (!command.equals(COMMAND_QUIT)){
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



    }
    //UPDATE CODE
    // 0 --> user join the lobby
    // 1 --> user leave the lobby
    // 2 --> game started
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
                    inGame();
                }
            }
        }
    }

    //do stuff in game
    private void inGame(){
        //do something
        System.out.println("------------> GAME STARTED! <------------");
        printPlayersUsername();

    }

    public void printPlayersUsername(){
        System.out.println(playersUsername.size() + " ACTIVE PLAYERS IN GAME");
        for(String user : playersUsername){
            System.out.println("--->" + user);
        }
        System.out.println("\n");
    }

}
