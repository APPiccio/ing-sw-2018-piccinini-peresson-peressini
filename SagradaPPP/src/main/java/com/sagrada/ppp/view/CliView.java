package com.sagrada.ppp.view;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.LobbyObserver;
import com.sagrada.ppp.Observer;
import com.sagrada.ppp.Player;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class CliView extends UnicastRemoteObject implements Observer{
    Scanner scanner;
    RemoteController controller;
    String username;
    int hashCode;
    int gameHashCode;
    Observer lobbyObserver;

    public CliView(RemoteController controller) throws RemoteException{
        this.scanner = new Scanner(System.in);
        this.controller = controller;

    }


    public void start() throws RemoteException {
        System.out.println("Welcome in SAGRADA");
        System.out.println("Please enter your username! This can't be empty or with spaces.");
        username = scanner.nextLine();
        while (username.length() <= 0 || username.indexOf(" ") != -1) {
            System.out.println("Error, try again! This can't be empty or with spaces.");
            username = scanner.nextLine();
        }

        JoinGameResult joinGameResult = controller.joinGame(username);
        while (hashCode < 0){
            System.out.println("Join failed. Trying new attempt...");
            joinGameResult = controller.joinGame(username);
        }

        gameHashCode = joinGameResult.getGameHashCode();
        hashCode = joinGameResult.getPlayerHashCode();

        username = joinGameResult.getUsername();
        System.out.println("Join copmleted. You are now identified as : " + username);
        inLobby();

        /*
        String[] split = command.split(" ");
        command = split[0];
        while(!command.equals(StaticValues.COMMAND_QUIT)){
            switch (command){

                case StaticValues.COMMAND_CREATE_GAME :
                    System.out.println("Insert lobby name");
                    String name = scanner.nextLine();
                    System.out.println("Insert your username");
                    username = scanner.nextLine();
                    System.out.println("Insert game mode. 's' for single player mode , 'm' for multiplayer mode)");
                    String gameMode = scanner.nextLine();
                    while(!gameMode.equals("s") && !gameMode.equals("m")){
                        System.out.println("Invalid option!");
                        System.out.println("Insert game mode. 's' for single player mode , 'm' for multiplayer mode)");
                        gameMode = scanner.nextLine();
                    }

                    //TODO check on game mode
                    //TODO block blank spaces in game name
                    gameHashCode = controller.createGame(true,name,username);
                    System.out.println("Congratulations, lobby " + name + " successfully created with GAME_ID=" + gameHashCode);
                    inLobby();
                    break;

                case StaticValues.COMMAND_SHOW_GAMES:
                    ArrayList<String> gameList = controller.getJoinableGames();
                    System.out.println("There are " + gameList.size() + " joinable games");
                    for(String string : gameList){
                        System.out.println(string);
                    }
                    break;

                case StaticValues.COMMAND_JOIN_GAME:
                    if(split.length == 3){
                        String gameName = split[1];
                        String username = split[2];
                        if(controller.joinGame(gameName,username)){
                            System.out.println("Joining game...");
                        }
                        else {
                            System.out.println("Error, unable to join this lobby.");
                        }
                    }else System.out.println("Error, wrong number of parameters");
                    break;
                case StaticValues.COMMAND_HELP:
                    showCommandList();
                    break;

                default:
                    System.out.println("Unknown command. Please retry.");
                    showCommandList();
                    break;
            }
            System.out.println("Insert command:");

            command = scanner.nextLine();
            split = command.split(" ");
            command = split[0];

        }
        */
    }


/*
    public void playerInLobby(int playerID) throws RemoteException {

        ArrayList<Player> players = controller.getPlayers();
        System.out.println("There are " + players.size() + " players in the lobby");
        for (Player player : players){
            if(player.hashCode() != playerID) {
                System.out.println("->" + player.getUsername());
            }
        }

    }
*/

    public void showCommandList(){
        System.out.println("COMMANDS:");
        System.out.println("\t" + StaticValues.COMMAND_QUIT + "\t" + StaticValues.STRING_COMMAND_QUIT);
        System.out.println("\t" + StaticValues.COMMAND_CREATE_GAME + "\t" + StaticValues.STRING_COMMAND_CREATE_GAME);
        System.out.println("\t" + StaticValues.COMMAND_SHOW_GAMES + "\t" + StaticValues.STRING_COMMAND_SHOW_GAMES);
        System.out.println("\t" + StaticValues.COMMAND_JOIN_GAME + "\t" + StaticValues.STRING_COMMAND_JOIN_GAME);
        System.out.println("\t" + StaticValues.COMMAND_LEAVE_GAME + "\t" + StaticValues.STRING_COMMAND_LEAVE_GAME);
        System.out.println("\t" + StaticValues.COMMAND_HELP + "\t" + StaticValues.STRING_COMMAND_HELP);
    }

    public void showLobbyCommandList(){
        System.out.println("LOBBY COMMANDS:");
        System.out.println("\t" + StaticValues.COMMAND_PLAYERS_IN_LOBBY + "\t" + StaticValues.STRING_COMMAND_PLAYERS_IN_LOBBY);
        System.out.println("\t" + StaticValues.COMMAND_START_GAME + "\t" + StaticValues.STRING_COMMAND_START_GAME);
        System.out.println("\t" + StaticValues.COMMAND_QUIT + "\t" + StaticValues.STRING_COMMAND_QUIT);
        System.out.println("\t" + StaticValues.COMMAND_HELP + "\t" + StaticValues.STRING_COMMAND_HELP);
    }

    public void inLobby() throws RemoteException {
        controller.attachLobbyObserver(gameHashCode, this);
        System.out.println("Congratulations , you are now in lobby!");
        System.out.println("--> Game ID = " + gameHashCode);
        System.out.println("--> Your ID = " + hashCode + " as " + username);
        showLobbyCommandList();
        String command = scanner.nextLine();
        while (!command.equals(StaticValues.COMMAND_QUIT)){
            switch(command){
                case StaticValues.COMMAND_PLAYERS_IN_LOBBY:
                    ArrayList<Player> players = controller.getPlayers(gameHashCode);
                    System.out.println("There are " + players.size() + "players in lobby");
                    for(Player player : players){
                        System.out.println("-> " + player.getUsername());
                    }
                    break;
                case StaticValues.COMMAND_LEAVE_GAME:
                    controller.leaveLobby(gameHashCode , username);
                    System.out.println("Back to main menu");
                    return;
                case StaticValues.COMMAND_START_GAME:
                    break;
                case StaticValues.COMMAND_HELP :
                    showLobbyCommandList();
                    break;
                default:
                    System.out.println("Unknown command. Please retry.");
                    showCommandList();
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
    public void update(int updateCode,String username) throws RemoteException {
        switch (updateCode){
            case 0:
                System.out.println("--> " + username + " has joined the lobby!");
                break;
            case 1:
                System.out.println("--> " + username + " has left the lobby!");
                break;
            case 2:
                System.out.println("--> STARTING GAME...");
                break;
            default:
                break;
        }
    }

}
