package com.sagrada.ppp;

import java.io.Console;
import java.io.PrintWriter;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LobbyObserver extends UnicastRemoteObject implements Remote, Observer, Serializable {

    public LobbyObserver() throws RemoteException{
        super();
    }

    //UPDATE CODE
    // 0 --> user join the lobby
    // 1 --> user leave the lobby
    // 2 --> game started
    public void update(int updateCode,String username){
        switch (updateCode){
            case 0:
                System.out.println("--> " + username + " has joined the lobby!");
                break;
            case 1:
                System.out.println("--> " + username + " has left the lobby!");
                break;
            case 2:
                System.out.println("--> STARTING GAME...");
                break;
            default:
                break;
        }
    }
}
