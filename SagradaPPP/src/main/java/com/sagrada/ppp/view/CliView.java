package com.sagrada.ppp.view;

import com.sagrada.ppp.Player;
import com.sagrada.ppp.controller.Controller;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class CliView {
    Scanner scanner;
    RemoteController controller;
    String username;
    int hashCode;

    public CliView(RemoteController controller){
        this.scanner = new Scanner(System.in);
        this.controller = controller;

    }


    public void start() throws RemoteException {
        System.out.println("Welcome in SAGRADA\n");
        System.out.println("Please enter a command. If you need help just type :help");
        String command = scanner.nextLine();
        while(!command.equals(StaticValues.COMMAND_QUIT)){
            switch (command){

                case StaticValues.COMMAND_CREATE_GAME :
                    System.out.println("Insert lobby name");
                    String name = scanner.nextLine();
                    System.out.println("Insert your username");
                    String username = scanner.nextLine();
                    System.out.println("Insert game mode. 's' for single player mode , 'm' for multiplayer mode)");
                    String gameMode = scanner.nextLine();
                    while(!gameMode.equals("s") && !gameMode.equals("m")){
                        System.out.println("Invalid option!");
                        System.out.println("Insert game mode. 's' for single player mode , 'm' for multiplayer mode)");
                        gameMode = scanner.nextLine();
                    }

                    //TODO check on game mode
                    int gameHashCode = controller.createGame(true,name,username);
                    System.out.println("Congratulations, lobby " + name + " successfully created with GAME_ID=" + gameHashCode);
                    break;

                case StaticValues.COMMAND_SHOW_GAMES:
                    ArrayList<String> gameList = controller.getJoinableGames();
                    System.out.println("There are " + gameList.size() + " joinable games");
                    for(String string : gameList){
                        System.out.println(string);
                    }
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
    }

}
