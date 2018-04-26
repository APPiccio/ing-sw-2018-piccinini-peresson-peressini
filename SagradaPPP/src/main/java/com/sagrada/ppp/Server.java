package com.sagrada.ppp;


import com.sagrada.ppp.controller.Controller;
import com.sagrada.ppp.Utils.StaticValues;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        Service service = new Service();

    }
}
