package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.server.Service;
import com.sagrada.ppp.view.ToolCardHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * RMI connection controller, for methods details
 * @see RemoteController
 */
public class Controller extends UnicastRemoteObject implements RemoteController {

    private transient Service service;
    private HashMap<Integer, ToolCardThreadController> toolCardThreads;

    public Controller(Service service) throws RemoteException {
        this.service = service;
        toolCardThreads = new HashMap<>();
    }

    @Override
    public LeaveGameResult leaveLobby(int gameHashCode, String username,
                                      LobbyObserver observer) throws RemoteException{
        return service.leaveLobby(gameHashCode,username,observer);
    }

    @Override
    public JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException{
        return service.joinGame(username, lobbyObserver,null);
    }

    @Override
    public String getUsername(int playerHashCode, int gameHashCode) throws RemoteException{
        return service.getUsername(playerHashCode, gameHashCode);
    }

    @Override
    public void attachGameObserver(int gameHashCode, GameObserver gameObserver,
                                   int playerHashCode) throws RemoteException {
        service.attachGameObserver(gameHashCode, gameObserver, playerHashCode);
    }

    @Override
    public void detachAllGameObserver(int gameHashCode, int playerHashCode) throws RemoteException {
        service.detachAllGameObserver(gameHashCode, playerHashCode);
    }

    @Override
    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException {
        service.choosePanel(gameHashCode, playerHashCode, panelIndex);
    }

    @Override
    public void disableAFK(int gameHashCode, int playerHashCode) throws RemoteException {
        service.disableAFK(gameHashCode, playerHashCode);
    }

    @Override
    public boolean disconnect(int gameHashCode, int playerHashCode) throws RemoteException {
        return service.disconnect(gameHashCode, playerHashCode);
    }

    @Override
    public PlaceDiceResult placeDice(int gameHashCode, int playerHashCode,
                                     int diceIndex, int row, int col) throws RemoteException {
        return service.placeDice(gameHashCode, playerHashCode, diceIndex, row, col);
    }

    @Override
    public void endTurn(int gameHashCode, int playerHashCode) throws RemoteException {
        service.endTurn(gameHashCode,playerHashCode);
    }

    @Override
    public ReconnectionResult reconnection(int playerHashCode, int gameHashCode,
                                           GameObserver gameObserver) throws RemoteException {
        return service.reconnection(playerHashCode, gameHashCode, gameObserver);
    }

    @Override
    public void isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex,
                                 ToolCardHandler view) throws RemoteException {
        IsToolCardUsableResult isToolCardUsableResult = service.isToolCardUsable(gameHashCode,
                playerHashCode, toolCardIndex);
        ToolCardThreadController toolCardThread = new ToolCardThreadController(isToolCardUsableResult.result,
                view, service, gameHashCode, playerHashCode, toolCardIndex, isToolCardUsableResult.toolCardID);
        toolCardThreads.put(playerHashCode, toolCardThread);
        toolCardThread.start();
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
    public void setPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.panelCellIndex = cellIndex;
    }

    @Override
    public void setPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException {
        toolCardThreads.get(playerHashCode).toolCardParameters.panelDiceIndex = diceIndex;
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

    @Override
    public void polling() throws RemoteException {
        //do nothing
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

        ToolCardThreadController(boolean result, ToolCardHandler view, Service service,
                                 int gameHashCode, int playerHashCode, int toolCardIndex, int toolCardID) {
            this.result = result;
            this.view = view;
            this.service = service;
            this.gameHashCode = gameHashCode;
            this.toolCardIndex = toolCardIndex;
            this.toolCardParameters = new ToolCardParameters();
            this.playerHashCode = playerHashCode;
            this.toolCardID = toolCardID;
            this.setName("ToolCardThread" + toolCardID);
        }

        @Override
        public void run() {
            try {
                view.isToolCardUsable(result);
                if (result) {
                    switch (toolCardID) {
                        case 1:
                            useToolCard1();
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
                            useToolCard6();
                            break;
                        case 7:
                            useToolCard7();
                            break;
                        case 8:
                            useToolCard8and9();
                            break;
                        case 9:
                            useToolCard8and9();
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
                }
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private void useToolCard1() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            view.actionSignRequired();
            while (toolCardParameters.actionSign == null);
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

        private void useToolCard4() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            view.secondPanelDiceIndexRequired();
            while (toolCardParameters.secondPanelDiceIndex == null);
            view.secondPanelCellIndexRequired();
            while (toolCardParameters.secondPanelCellIndex == null);
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

        private void useToolCard6() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            if (!useToolCardResult.result) {
                view.notifyUsageCompleted(useToolCardResult);
            }
            else {
                Dice dice = useToolCardResult.dice;
                view.reRolledDiceActionRequired(dice);
                ArrayList<Integer> legalPositions = service.getLegalPositions(gameHashCode, playerHashCode, dice);
                if (!legalPositions.isEmpty()) {
                    do {
                        toolCardParameters.panelCellIndex = null;
                        view.panelCellIndexRequired();
                        while (toolCardParameters.panelCellIndex == null);
                    } while (!legalPositions.contains(toolCardParameters.panelCellIndex));
                    //useToolCardResult.result = service.specialDicePlacement(gameHashCode, playerHashCode,
                    //        toolCardParameters.panelCellIndex, dice);
                    PlaceDiceResult specialDicePlacement = service.specialDicePlacement(gameHashCode, playerHashCode, toolCardParameters.panelCellIndex, dice);
                    useToolCardResult.draftpool = specialDicePlacement.draftPool;
                    useToolCardResult.result = specialDicePlacement.status;
                    for (Player player : useToolCardResult.players) {
                        if (player.getHashCode() == playerHashCode) {
                            player.setPanel(specialDicePlacement.panel);
                        }
                    }
                }
                else {
                    service.putDiceInDraftPool(gameHashCode, dice);
                    useToolCardResult.result = true;
                    useToolCardResult.msg = "No placement positions available, dice put back in the draft pool!";
                }
                view.notifyUsageCompleted(useToolCardResult);
            }
        }

        private void useToolCard7() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard8and9() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard10() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard11() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            UseToolCardResult useToolCardResult = service.useToolCard(gameHashCode, playerHashCode, toolCardParameters);
            if(!useToolCardResult.result){
                view.notifyUsageCompleted(useToolCardResult);
            }
            else {
                //now ask to choose dice value and place it
                //dice from server, just extracted from diceBag
                Dice dice = useToolCardResult.dice;
                view.diceValueRequired(dice.getColor());
                while (toolCardParameters.diceValue == null);
                dice.setValue(toolCardParameters.diceValue);
                ArrayList<Integer> positions = service.getLegalPositions(gameHashCode, playerHashCode, dice);
                if(!positions.isEmpty()) {
                    do {
                        toolCardParameters.panelCellIndex = null;
                        view.panelCellIndexRequired();
                        while (toolCardParameters.panelCellIndex == null) ;
                    }
                    while (!positions.contains(toolCardParameters.panelCellIndex));
                    PlaceDiceResult placeDiceResult = service.specialDicePlacement(gameHashCode, playerHashCode, toolCardParameters.panelCellIndex, dice);
                    useToolCardResult.result = placeDiceResult.status;
                    useToolCardResult.draftpool = placeDiceResult.draftPool;
                    for(Player player : useToolCardResult.players){
                        if(player.getHashCode() == playerHashCode){
                            player.setPanel(placeDiceResult.panel);
                        }
                    }
                }
                else {
                    service.putDiceInDraftPool(gameHashCode, dice);
                    useToolCardResult.result = true;
                    useToolCardResult.msg = "No placement allowed due to game rules. " +
                            "The dice has been put back in the draft pool";
                }
                view.notifyUsageCompleted(useToolCardResult);
            }
        }

        private void useToolCard12() throws RemoteException{
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.roundTrackDiceIndexRequired();
            while (toolCardParameters.roundTrackDiceIndex == null || toolCardParameters.roundTrackRoundIndex == null);
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