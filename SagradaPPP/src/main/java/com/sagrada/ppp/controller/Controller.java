package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.server.Service;
import com.sagrada.ppp.view.CliView;
import com.sagrada.ppp.view.ToolCardHandler;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller extends UnicastRemoteObject implements RemoteController {

    private Service service;
    private HashMap<Integer, ToolCardThreadController> toolCardThreads;

    public Controller(Service service) throws RemoteException {
        this.service = service;
        toolCardThreads = new HashMap<>();
    }

    public ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException{
        return service.getPlayers(gameHashCode);
    }

    public LeaveGameResult leaveLobby(int gameHashCode, String username, LobbyObserver observer) throws RemoteException{
        return service.leaveLobby(gameHashCode,username,observer,null);
    }


    public JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException{
        return service.joinGame(username, lobbyObserver,null);
    }

    public String getUsername(int playerHashCode, int gameHashCode) throws RemoteException{
        return service.getUsername(playerHashCode, gameHashCode);
    }

    @Override
    public void attachGameObserver(int gameHashCode,GameObserver gameObserver) throws RemoteException {
        service.attachGameObserver(gameHashCode, gameObserver);
    }

    @Override
    public void detachGameObserver(int gameHashCode, GameObserver gameObserver) throws RemoteException {
        service.detachGameObserver(gameHashCode, gameObserver);
    }

    @Override
    public int getNumPlayers(int gameHashCode) throws RemoteException {
        return service.getNumPlayers(gameHashCode);
    }

    @Override
    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException {
        service.choosePanel(gameHashCode, playerHashCode, panelIndex);
    }

    @Override
    public boolean disconnect(int gameHashCode, int playerHashCode, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException {
        return service.disconnect(gameHashCode, playerHashCode, lobbyObserver, gameObserver);
    }

    @Override
    public PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col) throws RemoteException {
        return service.placeDice(gameHashCode, playerHashCode, diceIndex, row, col);
    }

    @Override
    public void endTurn(int gameHashCode, int playerHashCode) throws RemoteException {
        service.endTurn(gameHashCode,playerHashCode);
    }

    @Override
    public void isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex, ToolCardHandler view) throws RemoteException {
        IsToolCardUsableResult isToolCardUsableResult = service.isToolCardUsable(gameHashCode, playerHashCode, toolCardIndex);
        ToolCardThreadController toolCardThread = new ToolCardThreadController(isToolCardUsableResult.result, view, service, gameHashCode, playerHashCode, toolCardIndex, isToolCardUsableResult.toolCardID);
        toolCardThreads.put(playerHashCode, toolCardThread);
        toolCardThread.start();
    }

    //here only to keep inheritance
    @Override
    public void closeSocket() throws RemoteException {

    }

    @Override
    public void setActionSign(int playerHashCode, int addend) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.actionSign = addend;
    }

    @Override
    public void setDraftPoolDiceIndex(int playerHashCode, int diceIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.draftPoolDiceIndex = diceIndex;
    }

    @Override
    public void setRoundTrackDiceIndex(int playerHashCode, int diceIndex, int roundIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.roundTrackDiceIndex = diceIndex;
        toolCardThreads.get(playerHashCode).toolCardParameters.roundTrackRoundIndex = roundIndex;
    }

    @Override
    public void setPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.panelDiceIndex = diceIndex;
    }

    @Override
    public void setPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.panelCellIndex = cellIndex;
    }

    class ToolCardThreadController extends Thread {

        int gameHashCode;
        boolean result;
        ToolCardHandler view;
        Service service;
        int toolCardIndex;
        ToolCardParameters toolCardParameters;
        int playerHashCode;
        int toolCardID;

        ToolCardThreadController(boolean result, ToolCardHandler view, Service service, int gameHashCode, int playerHashCode, int toolCardIndex, int toolCardID){
            this.result = result;
            this.view = view;
            this.service = service;
            this.gameHashCode = gameHashCode;
            this.toolCardIndex = toolCardIndex;
            this.toolCardParameters = new ToolCardParameters();
            this.playerHashCode = playerHashCode;
            this.toolCardID = toolCardID;
        }
        @Override
        public void run() {
            try {
                view.isToolCardUsable(result);

                switch (toolCardID) {
                    case 1:
                        toolCardParameters.reset();
                        toolCardParameters.toolCardID = toolCardID;
                        view.draftPoolDiceIndexRequired();
                        while (toolCardParameters.draftPoolDiceIndex == null) ;
                        view.actionSignRequired();
                        while (toolCardParameters.actionSign == null) ;
                        UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
                        view.notifyUsageCompleted(useToolCardResult);
                        break;
                    case 2:
                        useToolCard2();
                        break;
                    case 3:
                        useToolCard3();
                        break;
                    case 5:
                        useToolCard5();
                        break;
                    default:

                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private void useToolCard3() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard2() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard5() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            view.roundTrackDiceIndexRequired();
            while (toolCardParameters.roundTrackRoundIndex == null || toolCardParameters.roundTrackDiceIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }
    }

}