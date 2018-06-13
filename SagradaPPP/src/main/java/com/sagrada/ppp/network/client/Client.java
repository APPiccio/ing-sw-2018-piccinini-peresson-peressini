package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.view.CliView;
import com.sagrada.ppp.view.gui.GuiView;
import javafx.application.Application;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    static RemoteController controller;
    static ConnectionModeEnum connectionModeEnum;


    public static RemoteController getController(){
        return controller;
    }

    public static void main( String[] args ) throws RemoteException {

        System.out.println("--> Connetting...");
        controller = null;
        Scanner scanner = new Scanner(System.in);
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

        System.out.println("Choose gui or cli!");
        String viewMode = scanner.nextLine();
        while (!viewMode.equals("gui") && !viewMode.equals("cli")) {
            System.out.println("Choose a valid option, type 'gui' or 'cli'.");
            viewMode = scanner.nextLine();
        }
        if(viewMode.equals("cli")){
            CliView view = new CliView(controller, connectionModeEnum);
            try {
                view.init();
            }catch (ConnectException e){
                System.out.println("ERROR --> SERVER CRASH DETECTED, CLOSING APPLICATION");
                System.exit(0);
            }
        }
        else{
            new Thread(() -> Application.launch(GuiView.class)).start();
        }

        System.out.println("Stopping client...");
    }

}