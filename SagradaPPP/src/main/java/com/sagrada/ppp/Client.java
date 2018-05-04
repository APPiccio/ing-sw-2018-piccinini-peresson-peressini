package com.sagrada.ppp;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.network.client.ConnectionHandler;
import com.sagrada.ppp.network.client.ConnectionMode;
import com.sagrada.ppp.network.client.ConnectionModeEnum;
import com.sagrada.ppp.view.CliView;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Client
{

    public static void main( String[] args ) throws RemoteException {

        System.out.println("--> Connetting...");
        RemoteController controller = null;
        Scanner scanner = new Scanner(System.in);
        ConnectionModeEnum connectionModeEnum;
        //TODO : make server able to reach client on socket and rmi simultaneously. Until that this code is useless
        System.out.println("Choose connection mode. Type rmi or socket");
        String connectionChoice = scanner.nextLine();
        //check on invalid choice
        while(!connectionChoice.equals("rmi") && !connectionChoice.equals("socket")){
            System.out.println("Invalid option, type 'rmi' or 'socket'");
            connectionChoice = scanner.nextLine();
        }
        //from here should be a valid choice
        if(connectionChoice.equals("rmi")){
            connectionModeEnum = ConnectionModeEnum.RMI;
        }
        else{
            connectionModeEnum = ConnectionModeEnum.SOCKET;
        }

        //initializing connection
        ConnectionHandler connectionHandler = new ConnectionHandler(connectionModeEnum);
        controller = connectionHandler.getController();
        //failed to connect
        if(controller == null){
            System.out.println("Unable to connect, please try again later");
            return;
        }

        //TODO : remove this line and replace with the choice single-multi player
        CliView view = new CliView(controller);
        view.start();
        System.out.println("Stopping client...");
    }


    public void login(RemoteController controller){
        System.out.println("Insert username: ");


    }
}
