package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.view.ToolCardHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Controller exported by RMI and implemented by SocketClientController and SocketThread.
 * Defines all communication methods between View and Model
 */
public interface RemoteController extends Remote {


    /**
     * Method used to leave game lobby in a safe mode
     * @param gameHashCode
     * @param username
     * @param observer
     * @return
     * @throws RemoteException
     */
    LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer) throws RemoteException;

    /**
     * Method to enter in lobby
     * @param username
     * @param lobbyObserver
     * @return
     * @throws RemoteException
     */
    JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException;


    void attachGameObserver(int gameHashCode, GameObserver gameObserver, int playerHashCode) throws RemoteException;

    void detachAllGameObserver(int gameHashCode, int playerHashCode) throws RemoteException;

    String getUsername(int playerHashCode, int gameHashCode) throws RemoteException;


    /**
     * send panel choice to server
     * @param gameHashCode
     * @param playerHashCode
     * @param panelIndex
     * @throws RemoteException
     */
    void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException;

    /**
     * exit the game in a safe mode
     * @param gameHashCode
     * @param playerHashCode
     * @return
     * @throws RemoteException
     */
    boolean disconnect(int gameHashCode, int playerHashCode) throws RemoteException;

    PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col) throws RemoteException;

    /**
     * send request of turn end to the the server
     * @param gameHashCode
     * @param playerHashCode
     * @throws RemoteException
     */
    void endTurn(int gameHashCode, int playerHashCode) throws RemoteException;

    void isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex, ToolCardHandler view) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param diceIndex
     * @throws RemoteException
     */
    void setDraftPoolDiceIndex(int playerHashCode, int diceIndex) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param diceIndex
     * @param roundIndex
     * @throws RemoteException
     */
    void setRoundTrackDiceIndex(int playerHashCode, int diceIndex, int roundIndex) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param diceIndex
     * @throws RemoteException
     */
    void setPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param cellIndex
     * @throws RemoteException
     */
    void setPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param cellIndex
     * @throws RemoteException
     */
    void setSecondPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param diceIndex
     * @throws RemoteException
     */
    void setSecondPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException;

    /**
     *  ToolCard method
     * @param playerHashCode
     * @param addend
     * @throws RemoteException
     */
    void setActionSign(int playerHashCode, int addend) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param diceValue
     * @throws RemoteException
     */
    void setDiceValue(int playerHashCode, int diceValue) throws RemoteException;

    /**
     * ToolCard method
     * @param playerHashCode
     * @param choice
     * @throws RemoteException
     */
    void setTwoDiceAction(int playerHashCode, boolean choice) throws RemoteException;

    /**
     * Method used to reconnect to a current game
     * @param playerHashCode
     * @param gameHashCode
     * @param gameObserver
     * @return
     * @throws RemoteException
     */
    ReconnectionResult reconnection(int playerHashCode, int gameHashCode, GameObserver gameObserver) throws RemoteException;

    /**
     * Notify the server "i'm back online"
     * @param gameHashCode
     * @param playerHashCode
     * @throws RemoteException
     */
    void disableAFK(int gameHashCode, int playerHashCode) throws RemoteException;

    /**
     * Method to detect RMI disconnection
     * @throws RemoteException
     */
    void polling() throws RemoteException;
}