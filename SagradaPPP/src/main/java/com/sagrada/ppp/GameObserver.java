package com.sagrada.ppp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface GameObserver extends Remote {
    void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen) throws RemoteException;
    void onGameStart(HashMap<String, WindowPanel> chosenPanels) throws RemoteException;
}
