package com.sagrada.ppp.model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameObserver extends Remote {
    void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException;
    void onGameStart(GameStartMessage gameStartMessage) throws RemoteException;
    void onDicePlaced(DicePlacedMessage dicePlacedMessage)throws RemoteException;
    void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException;
    void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException;
}