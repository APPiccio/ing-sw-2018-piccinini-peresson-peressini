package com.sagrada.ppp;

import com.sagrada.ppp.Utils.StaticValues;
import com.sagrada.ppp.controller.Controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Service {



    public Service(){
        try {
            Controller controller = new Controller();
            Registry registry = LocateRegistry.createRegistry(StaticValues.RMI_PORT);
            registry.rebind(StaticValues.REGISTRY_NAME, controller);
            System.out.println("--> controller exported");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
