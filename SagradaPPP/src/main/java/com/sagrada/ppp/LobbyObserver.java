package com.sagrada.ppp;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LobbyObserver extends UnicastRemoteObject  {
    Game game;

    protected LobbyObserver() throws RemoteException {
    }




}
