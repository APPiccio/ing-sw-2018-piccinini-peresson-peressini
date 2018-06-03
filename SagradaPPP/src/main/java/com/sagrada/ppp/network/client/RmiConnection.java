package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.utils.StaticValues;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiConnection implements ConnectionMode {

    @Override
    public RemoteController getController() {
        try {
            RemoteController controller = (RemoteController) Naming.lookup("//" + StaticValues.SERVER_ADDRESS + "/" +StaticValues.REGISTRY_NAME);
            System.out.println("--> Connected!");
            return controller;
        } catch (Exception e) {
            System.out.println("--> Remote connection failed. Stopping application");
            e.printStackTrace();
        }
        return null;
    }
}
