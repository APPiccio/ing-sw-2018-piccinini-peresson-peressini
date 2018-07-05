package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.CliView;
import com.sagrada.ppp.view.gui.GuiView;
import javafx.application.Application;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static java.lang.System.*;

public class Client {

    private static RemoteController controller;
    private static ConnectionModeEnum connectionModeEnum;

    /**
     * @param args
     * Client application entry point
     * @throws RemoteException
     */
    public static void main(String[] args) throws RemoteException {

        StaticValues.readConstants();
        out.println("--> Connecting...");
        controller = null;
        Scanner scanner = new Scanner(in);
        out.println("Choose connection mode: type rmi or socket");
        String connectionChoice = scanner.nextLine();
        while (!connectionChoice.equals("rmi") && !connectionChoice.equals("socket")) {
            out.println("Invalid option! Type 'rmi' or 'socket'");
            connectionChoice = scanner.nextLine();
        }

        if (connectionChoice.equals("rmi")) {
            connectionModeEnum = ConnectionModeEnum.RMI;
        }
        else {
            connectionModeEnum = ConnectionModeEnum.SOCKET;
        }

        //initializing connection
        ConnectionHandler connectionHandler = new ConnectionHandler(connectionModeEnum);
        controller = connectionHandler.getController();

        //failed to connect
        if (controller == null) {
            out.println("Unable to connect... Please try again later");
            exit(0);
        }

        out.println("Choose gui or cli!");
        String viewMode = scanner.nextLine();
        while (!viewMode.equals("gui") && !viewMode.equals("cli")) {
            out.println("Invalid option! Type 'gui' or 'cli'");
            viewMode = scanner.nextLine();
        }
        if (viewMode.equals("cli")) {
            CliView view = new CliView(controller, connectionModeEnum);
            try {
                view.init();
            } catch (ConnectException e) {
                out.println("ERROR --> SERVER CRASH DETECTED, CLOSING APPLICATION...");
                exit(0);
            }
        }
        else {
            out.println("Starting gui...");
            new Thread(() -> Application.launch(GuiView.class)).start();
        }
    }

    public static RemoteController getController() {
        return controller;
    }

    public static ConnectionModeEnum getConnectionModeEnum() { return connectionModeEnum; }

}