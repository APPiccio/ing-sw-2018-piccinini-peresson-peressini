package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.server.Service;
import com.sagrada.ppp.view.ToolCardHandler;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.spi.AbstractResourceBundleProvider;

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

    @Override
    public void setSecondPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.secondPanelCellIndex = cellIndex;
    }

    @Override
    public void setSecondPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.secondPanelDiceIndex = diceIndex;
    }

    @Override
    public void setDiceValue(int playerHashCode, int diceValue) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.diceValue = diceValue;
    }

    @Override
    public void setTwoDiceAction(int playerHashCode, boolean choice) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.twoDiceAction = choice;
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
                        useToolCard2and3();
                        break;
                    case 3:
                        useToolCard2and3();
                        break;
                    case 4:
                        useToolCard4();
                        break;
                    case 5:
                        useToolCard5();
                        break;
                    case 6:
                        break;
                    case 7:
                        useToolCard7();
                        break;
                    case 8:
                        break;
                    case 9:
                        useToolCard9();
                        break;
                    case 10:
                        useToolCard10();
                        break;
                    case 11:
                        useToolCard11();
                        break;
                    case 12:
                        useToolCard12();
                        break;
                    default:

                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private void useToolCard9() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);


            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard4() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;

            //first set
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);

            //second set
            view.secondPanelDiceIndexRequired();
            while (toolCardParameters.secondPanelDiceIndex == null);
            view.secondPanelCellIndexRequired();
            while (toolCardParameters.secondPanelCellIndex == null);

            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard2and3() throws RemoteException {
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


        private void useToolCard7() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard10() throws RemoteException{
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard11() throws RemoteException{
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            if(!useToolCardResult.result){
                view.notifyUsageCompleted(useToolCardResult);
            }
            else{
                //now ask to choose dice value and place it
                //dice from server, just extracted from dicebag
                Dice dice = useToolCardResult.dice;
                view.diceValueRequired(dice.getColor());
                while (toolCardParameters.diceValue == null);
                dice.setValue(toolCardParameters.diceValue);
                ArrayList<Integer> positions = service.getLegalPositions(gameHashCode, playerHashCode, dice);
                if(!positions.isEmpty()) {
                    do {
                        view.panelCellIndexRequired();
                        while (toolCardParameters.panelCellIndex == null) ;
                    }
                    while (!positions.contains(toolCardParameters.panelCellIndex));
                    useToolCardResult.result = service.specialDicePlacement(gameHashCode, playerHashCode, toolCardParameters.panelCellIndex, dice);
                    for(Player player : useToolCardResult.players){
                        if(player.hashCode() == playerHashCode){
                            WindowPanel panel = player.getPanel();
                            panel.addDice(toolCardParameters.panelCellIndex, dice);
                            player.setPanel(panel);
                        }
                    }
                    useToolCardResult.draftpool.remove(toolCardParameters.panelCellIndex);

                }
                else{
                    service.putDiceInDraftPool(gameHashCode, dice);
                    useToolCardResult.result = false;
                }
                view.notifyUsageCompleted(useToolCardResult);
            }
        }

        private void useToolCard12() throws RemoteException{
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.roundTrackDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            view.twoDiceActionRequired();
            while (toolCardParameters.twoDiceAction == null);
            if(toolCardParameters.twoDiceAction){
                view.secondPanelDiceIndexRequired();
                while (toolCardParameters.secondPanelDiceIndex == null);
                view.secondPanelCellIndexRequired();
                while (toolCardParameters.secondPanelCellIndex == null);
            }
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

    }


    }
