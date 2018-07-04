package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.view.ToolCardHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Controller exported by RMI and implemented by SocketClientController and SocketThread.
 * Defines all communication methods between View and Model
 */
public interface RemoteController extends Remote {


    LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer) throws RemoteException;

    JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException;

    void attachGameObserver(int gameHashCode, GameObserver gameObserver, int playerHashCode) throws RemoteException;

    void detachAllGameObserver(int gameHashCode, int playerHashCode) throws RemoteException;

    String getUsername(int playerHashCode, int gameHashCode) throws RemoteException;

    void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException;

    boolean disconnect(int gameHashCode, int playerHashCode) throws RemoteException;

    PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col) throws RemoteException;

    void endTurn(int gameHashCode, int playerHashCode) throws RemoteException;

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

    ReconnectionResult reconnection(int playerHashCode, int gameHashCode, GameObserver gameObserver) throws RemoteException;

    void disableAFK(int gameHashCode, int playerHashCode) throws RemoteException;

    void polling() throws RemoteException;
}