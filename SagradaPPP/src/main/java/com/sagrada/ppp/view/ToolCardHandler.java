package com.sagrada.ppp.view;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.UseToolCardResult;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ToolCardHandler extends Remote {

    void isToolCardUsable(boolean result) throws RemoteException;
    void draftPoolDiceIndexRequired() throws RemoteException;
    void roundTrackDiceIndexRequired() throws RemoteException;
    void panelDiceIndexRequired() throws RemoteException;
    void panelCellIndexRequired() throws RemoteException;
    void actionSignRequired() throws RemoteException;
    void notifyUsageCompleted(UseToolCardResult useToolCardResult) throws RemoteException;

    void secondPanelDiceIndexRequired() throws RemoteException;

    void secondPanelCellIndexRequired() throws  RemoteException;

    void diceValueRequired(Color color) throws RemoteException;

    void twoDiceActionRequired() throws RemoteException;
}
