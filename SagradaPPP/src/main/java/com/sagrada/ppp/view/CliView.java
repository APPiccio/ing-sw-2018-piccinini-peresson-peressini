package com.sagrada.ppp.view;

import com.sagrada.ppp.Player;
import com.sagrada.ppp.controller.Controller;
import com.sagrada.ppp.controller.RemoteController;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class CliView {
    Scanner scanner;
    RemoteController controller;

    public CliView(RemoteController controller){
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    public int userLogin() throws RemoteException {
        System.out.println("Insert username:");
        int loginResult = controller.login(scanner.nextLine());
        if(loginResult != -1){
            System.out.println("User logged as: " + controller.getUsername(loginResult));
        }
        return loginResult;
    }

    public void playerInLobby(int playerID) throws RemoteException {

        ArrayList<Player> players = controller.getPlayers();
        System.out.println("There are " + players.size() + " players in the lobby");
        for (Player player : players){
            if(player.hashCode() != playerID) {
                System.out.println("->" + player.getUsername());
            }
        }

    }

}
