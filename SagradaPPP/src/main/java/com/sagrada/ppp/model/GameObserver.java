package com.sagrada.ppp.model;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Used to notify the view of changes of the model.
 * Socket:
 *  Model>GameObserver ...network... GameObserver>View
 * Rmi(Using UnicastRemoteObject):
 *  Model> ...GameObserver(on network)... >View
 */
public interface GameObserver extends Remote {
    void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException;
    void onGameStart(GameStartMessage gameStartMessage) throws RemoteException;
    void onDicePlaced(DicePlacedMessage dicePlacedMessage)throws RemoteException;
    void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) throws RemoteException;
    void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException;
    void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException;
    void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException;
    void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException;
    void rmiPing() throws RemoteException;
}