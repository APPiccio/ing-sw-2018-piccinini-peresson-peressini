package com.sagrada.ppp.model;

import com.sagrada.ppp.cards.toolcards.ToolCard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameObserver extends Remote {
    void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException;
    void onGameStart(GameStartMessage gameStartMessage) throws RemoteException;
    void onDicePlaced(DicePlacedMessage dicePlacedMessage)throws RemoteException;
    void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) throws RemoteException;
    void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException;
    void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException;
    void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException;
    void onPlayerDisconnection(Player disconnectingPlayer) throws RemoteException;
}