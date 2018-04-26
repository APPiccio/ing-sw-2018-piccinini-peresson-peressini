package com.sagrada.ppp;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.Utils.StaticValues;
import com.sagrada.ppp.view.CliView;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class Client
{

    public static void main( String[] args ) throws RemoteException {

        System.out.println("--> Connetting...");

        Registry registry = null;
        RemoteController controller = null;
        try {
            registry = LocateRegistry.getRegistry(StaticValues.RMI_PORT);
            controller = (RemoteController) registry.lookup(StaticValues.REGISTRY_NAME);
            System.out.println("--> Connected!");
        } catch (Exception e) {
            System.out.println("--> Remote connection failed. Stopping application");
            e.printStackTrace();
        }


        //TO DO : remove this line and replace with the choice single-multi player
        controller.createGame(true);
        CliView view = new CliView(controller);
        int playerID = view.userLogin();
        view.playerInLobby(playerID);
    }


    public void login(RemoteController controller){
        System.out.println("Insert username: ");


    }
}
