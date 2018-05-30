package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.view.ToolCardHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteController extends Remote {

    //TODO remove this
    ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException;

    LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer) throws RemoteException;

    JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException;

    void attachGameObserver(int gameHashCode, GameObserver gameObserver) throws RemoteException;

    void detachGameObserver(int gameHashCode, GameObserver gameObserver) throws RemoteException;

    String getUsername(int playerHashCode, int gameHashCode) throws RemoteException;
    //TODO remove this
    int getNumPlayers(int gameHashCode) throws RemoteException;

    void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException;

    boolean disconnect(int gameHashCode, int playerHashCode, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException;

    PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col) throws RemoteException;

    void endTurn(int gameHashCode, int playerHashCode) throws RemoteException;

    void closeSocket() throws RemoteException;

    void isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex, ToolCardHandler view) throws RemoteException;

    void setDraftPoolDiceIndex(int playerHashCode, int diceIndex) throws RemoteException;

    void setRoundTrackDiceIndex(int playerHashCode, int diceIndex, int roundIndex) throws RemoteException;

    void setPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException;

    void setPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException;

    void setSecondPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException;

    void setSecondPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException;

    void setActionSign(int playerHashCode, int addend) throws RemoteException;

    void setDiceValue(int playerHashCode, int diceValue) throws RemoteException;

    void setTwoDiceAction(int playerHashCode, boolean choice) throws RemoteException;

}