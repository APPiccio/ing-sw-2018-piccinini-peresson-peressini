package com.sagrada.ppp;

import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.ToolCards.ToolCard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameObserver extends Remote {
    void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color playerPrivateColor) throws RemoteException;
    void onGameStart(HashMap<String, WindowPanel> chosenPanels, ArrayList<Dice> draftpool, ArrayList<ToolCard> toolCards, ArrayList<PublicObjectiveCard> publicObjectiveCards) throws RemoteException;
}