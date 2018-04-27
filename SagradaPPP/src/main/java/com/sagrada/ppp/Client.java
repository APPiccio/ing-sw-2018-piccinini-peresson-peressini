package com.sagrada.ppp;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.CliView;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        try {
            registry = LocateRegistry.getRegistry(StaticValues.RMI_PORT);
            controller = (RemoteController) registry.lookup(StaticValues.REGISTRY_NAME);
            System.out.println("--> Connected!");
        } catch (Exception e) {
            System.out.println("--> Remote connection failed. Stopping application");
            e.printStackTrace();
        }


        //TO DO : remove this line and replace with the choice single-multi player
        CliView view = new CliView(controller);
        view.start();
        System.out.println("Stopping client...");
    }


    public void login(RemoteController controller){
        System.out.println("Insert username: ");


    }
}
