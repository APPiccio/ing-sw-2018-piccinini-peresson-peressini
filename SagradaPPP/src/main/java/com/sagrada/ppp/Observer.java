package com.sagrada.ppp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
    void update(int updateCode, String username) throws RemoteException;
}
