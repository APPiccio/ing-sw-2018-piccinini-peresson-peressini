package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.commands.*;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.ToolCardHandler;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SocketClientController extends UnicastRemoteObject implements RemoteController, ResponseHandler, Serializable {

    private transient Socket socket;
    private transient ObjectOutputStream out;
    private transient ObjectInputStream in;
    private transient LobbyObserver lobbyObserver;
    private transient ArrayList<GameObserver> gameObservers;
    private transient volatile boolean waitingForResponse;
    private transient JoinGameResult joinGameResult;
    private transient ListeningThread notificationThread;
    private final transient Object responseLock;
    private transient LeaveGameResult leaveGameResult;
    private transient boolean disconnectionResult;
    private transient PlaceDiceResult placeDiceResult;
    private transient volatile ToolCardParameters toolCardParameters;
    private transient ToolCardThreadController toolCardThread;
    private transient volatile IsToolCardUsableResult isToolCardUsableResult;
    private transient volatile UseToolCardResult useToolCardResult;
    private transient volatile ArrayList<Integer> positions;
    private transient volatile PlaceDiceResult specialDicePlacementResult;
    private transient volatile ReconnectionResult reconnectionResult;

    public SocketClientController() throws IOException {
        socket = new Socket(StaticValues.SERVER_ADDRESS, StaticValues.SOCKET_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        gameObservers = new ArrayList<>();
        waitingForResponse = false;
        notificationThread = new ListeningThread(this);
        notificationThread.setName("notificationThread");
        notificationThread.start();
        responseLock = new Object();
        toolCardParameters = new ToolCardParameters();
    }





    @Override
    public LeaveGameResult leaveLobby(int gameHashCode, String username,LobbyObserver observer) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new LeaveGameRequest(username, gameHashCode));
            out.reset();
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            return leaveGameResult;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void attachGameObserver(int gameHashCode ,GameObserver gameObserver, int playerHashCode) throws RemoteException {
        gameObservers.add(gameObserver);
    }

    @Override
    public JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new JoinGameRequest(username));
            out.reset();
            this.lobbyObserver = lobbyObserver;
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            return joinGameResult;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUsername(int playerHashCode, int gameHashCode) throws RemoteException {
        return null;
    }

    @Override
    public void disableAFK(int gameHashCode, int playerHashCode) throws RemoteException {
        try {
            out.writeObject(new DisableAFKRequest(gameHashCode, playerHashCode));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle(Response response) {
        //do nothing
    }

    public void handle(JoinGameResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            joinGameResult = response.joinGameResult;
            responseLock.notifyAll();
        }

    }

    public void handle(LeaveGameResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            leaveGameResult = response.leaveGameResult;
            responseLock.notifyAll();
        }

    }

    public void handle(PlayerEventNotification response) {
            try {
                switch (response.eventType) {
                    case JOIN:
                        lobbyObserver.onPlayerJoined(response.username, response.players, response.numOfPlayers);
                        break;
                    case LEAVE:
                        lobbyObserver.onPlayerLeave(response.username, response.players, response.numOfPlayers);
                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

    }

    public void handle(TimerNotification response){
            try {
                lobbyObserver.onTimerChanges(response.timerStart, response.timerStatus);
            } catch (RemoteException e){
                e.printStackTrace();
            }

    }

    @Override
    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException {
        try {
            out.writeObject(new PanelChoiceRequest(gameHashCode,playerHashCode,panelIndex));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(PanelChoiceNotification response) {
        for(GameObserver observer : gameObservers){
            try {
                observer.onPanelChoice(response.playerHashCode, response.panels, response.panelAlreadyChosen, response.color);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(GameStartNotification response) {
        for(GameObserver observer : gameObservers){
            try {
                observer.onGameStart(response.gameStartMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(DisconnectionResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            disconnectionResult = response.disconnectionResult;
            notificationThread.interrupt();
            this.lobbyObserver = null;
            gameObservers.clear();
            responseLock.notifyAll();
        }
    }

    @Override
    public boolean disconnect(int gameHashCode, int playerHashCode) throws RemoteException {
        try{
            waitingForResponse = true;
            out.writeObject(new DisconnectionRequest(gameHashCode,playerHashCode));
            out.reset();
            notificationThread.interrupt();
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            return disconnectionResult;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PlaceDiceResult placeDice(int gameHashCode, int playerHashCode, int diceIndex, int row, int col) {
        try {
            waitingForResponse = true;
            out.writeObject(new PlaceDiceRequest(gameHashCode, playerHashCode, diceIndex, row, col));
            out.reset();
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            out.reset();
            return placeDiceResult;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handle(UseToolCardResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            useToolCardResult = response.useToolCardResult;
            responseLock.notifyAll();
        }
    }

    @Override
    public void handle(PlaceDiceResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            placeDiceResult = response.placeDiceResult;
            responseLock.notifyAll();
        }
    }

    @Override
    public void handle(DicePlacedNotification response) {
        for(GameObserver observer : gameObservers){
            try {
                observer.onDicePlaced(response.dicePlacedMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(UsedToolCardNotification response) {
        for(GameObserver gameObserver : gameObservers){
            try {
                gameObserver.onToolCardUsed(response.toolCardNotificationMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(EndTurnNotification response) {
        for(GameObserver observer : gameObservers){
            try {
                observer.onEndTurn(response.endTurnMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(EndGameNotification response) {
        for (GameObserver observer : gameObservers) {
            try {
                observer.onEndGame(response.playersScore);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(IsToolCardUsableResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            isToolCardUsableResult = response.isToolCardUsableResult;
            responseLock.notifyAll();
        }
    }

    @Override
    public void handle(GetLegalPositionResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            positions = response.positions;
            responseLock.notifyAll();
        }
    }

    @Override
    public void handle(SpecialDicePlacementResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            specialDicePlacementResult = response.result;
            responseLock.notifyAll();
        }
    }

    @Override
    public void handle(ReconnectionResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            reconnectionResult = response.reconnectionResult;
            responseLock.notifyAll();
        }
    }

    @Override
    public void handle(PlayerReconnectionNotification response) {
        for(GameObserver gameObserver : gameObservers){
            try {
                gameObserver.onPlayerReconnection(response.reconnectingPlayer);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(PlayerDisconnectionNotification response) {
        for(GameObserver gameObserver : gameObservers){
            try {
                gameObserver.onPlayerDisconnection(response.disconnectingPlayer, response.isLastPlayer);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(PlayerAFKNotification response) {
        for(GameObserver gameObserver : gameObservers){
            try {
                gameObserver.onPlayerAFK(response.player , response.isLastPlayer, response.lastPlayer);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endTurn(int gameHashCode, int playerHashCode) throws RemoteException {
        try {
            out.writeObject(new EndTurnRequest(gameHashCode, playerHashCode));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void closeConnection(){
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ReconnectionResult reconnection(int playerHashCode, int gameHashCode,
                                           GameObserver gameObserver) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new ReconnectionRequest(gameHashCode, playerHashCode, null));
            out.reset();
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            if (reconnectionResult.result) gameObservers.add(gameObserver);
            return reconnectionResult;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void detachAllGameObserver(int gameHashCode, int playerHashCode) throws RemoteException {
        gameObservers.clear();
        try {
            out.writeObject(new DetachGameObserverRequest(gameHashCode, playerHashCode));
            out.reset();
            notificationThread.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ListeningThread extends Thread {

        ResponseHandler handler;

        ListeningThread(ResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Response response = ((Response) in.readObject());
                    if (response != null) {
                        response.handle(handler);
                    }
                }catch (SocketException e){
                    if(isInterrupted()){
                        //TODO add stuff to close connection due to server crash
                        System.out.println("SERVER CRASH --> Closing notification thread");
                        closeConnection();
                        System.exit(0);
                    }
                    else{
                        e.printStackTrace();
                    }
                }catch (EOFException e){
                    System.out.println("Closing client socket due to server crash");
                    this.interrupt();
                }catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            closeConnection();
            System.exit(0);
        }
    }

    @Override
    public void isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex, ToolCardHandler view) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new IsToolCardUsableRequest(gameHashCode, playerHashCode, toolCardIndex));
            out.reset();
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            toolCardThread = new ToolCardThreadController(isToolCardUsableResult, view, gameHashCode, playerHashCode, toolCardIndex, in, out);
            toolCardThread.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDraftPoolDiceIndex(int playerHashCode, int diceIndex) throws RemoteException {
        toolCardThread.toolCardParameters.draftPoolDiceIndex = diceIndex;
    }

    @Override
    public void setRoundTrackDiceIndex(int playerHashCode, int diceIndex, int roundIndex) throws RemoteException {
        toolCardThread.toolCardParameters.roundTrackDiceIndex = diceIndex;
        toolCardThread.toolCardParameters.roundTrackRoundIndex = roundIndex;
    }

    @Override
    public void setPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException {
        toolCardThread.toolCardParameters.panelDiceIndex = diceIndex;
    }

    @Override
    public void setPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException {
        toolCardThread.toolCardParameters.panelCellIndex = cellIndex;
    }

    @Override
    public void setActionSign(int playerHashCode, int addend) throws RemoteException {
        toolCardThread.toolCardParameters.actionSign = addend;
    }

    @Override
    public void setSecondPanelCellIndex(int playerHashCode, int cellIndex) throws RemoteException {
        toolCardThread.toolCardParameters.secondPanelCellIndex = cellIndex;
    }

    @Override
    public void setSecondPanelDiceIndex(int playerHashCode, int diceIndex) throws RemoteException {
        toolCardThread.toolCardParameters.secondPanelDiceIndex = diceIndex;
    }

    @Override
    public void setDiceValue(int playerHashCode, int diceValue) throws RemoteException {
        toolCardThread.toolCardParameters.diceValue = diceValue;
    }

    @Override
    public void setTwoDiceAction(int playerHashCode, boolean choice) throws RemoteException {
        toolCardThread.toolCardParameters.twoDiceAction = choice;
    }

    class ToolCardThreadController extends Thread {

        int gameHashCode;
        boolean result;
        ToolCardHandler view;
        ObjectInputStream in;
        ObjectOutputStream out;
        int toolCardIndex;
        ToolCardParameters toolCardParameters;
        int playerHashCode;
        int toolCardID;

        ToolCardThreadController(IsToolCardUsableResult isToolCardUsableResult, ToolCardHandler view,
                                 int gameHashCode, int playerHashCode, int toolCardIndex,
                                 ObjectInputStream in, ObjectOutputStream out){
            this.result = isToolCardUsableResult.result;
            this.toolCardID = isToolCardUsableResult.toolCardID;
            this.view = view;
            this.gameHashCode = gameHashCode;
            this.toolCardIndex = toolCardIndex;
            this.toolCardParameters = new ToolCardParameters();
            this.playerHashCode = playerHashCode;
            this.in = in;
            this.out = out;
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
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        private void useToolCard1() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null) ;
            view.actionSignRequired();
            while (toolCardParameters.actionSign == null) ;
            sendToolCardRequest();
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard2and3() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            sendToolCardRequest();
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
            sendToolCardRequest();
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard5() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            view.roundTrackDiceIndexRequired();
            while (toolCardParameters.roundTrackRoundIndex == null || toolCardParameters.roundTrackDiceIndex == null);
            sendToolCardRequest();
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard6() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            sendToolCardRequest();
            if (!useToolCardResult.result) {
                view.notifyUsageCompleted(useToolCardResult);
            }
            else {
                Dice dice = useToolCardResult.dice;
                System.out.println("TOOLCARD 6 DICE = " + dice.toString());
                view.reRolledDiceActionRequired(dice);
                sendLegalDicePositionsRequest(dice);
                if(!positions.isEmpty()) {
                    do {
                        toolCardParameters.panelCellIndex = null;
                        view.panelCellIndexRequired();
                        while (toolCardParameters.panelCellIndex == null);
                    }
                    while (!positions.contains(toolCardParameters.panelCellIndex));

                    sendSpecialPlacementRequest(toolCardParameters.panelCellIndex, dice);
                    useToolCardResult.result = specialDicePlacementResult.status;
                    useToolCardResult.draftpool = specialDicePlacementResult.draftPool;
                    for(Player player : useToolCardResult.players){
                        if(player.getHashCode() == playerHashCode){
                            player.setPanel(specialDicePlacementResult.panel);
                            }
                    }
                }
                else{
                    try {
                        out.writeObject(new PutDiceInDraftPoolRequest(gameHashCode, dice));
                        out.reset();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    useToolCardResult.result = true;
                    useToolCardResult.msg = "No placement positions available, dice put back in the draft pool!";
                }
                view.notifyUsageCompleted(useToolCardResult);
            }
        }

        private void useToolCard7() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            sendToolCardRequest();
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard8and9() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            sendToolCardRequest();
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard10() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            sendToolCardRequest();
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void useToolCard11() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null);
            sendToolCardRequest();
            if(!useToolCardResult.result){
                view.notifyUsageCompleted(useToolCardResult);
            }
            else {
                //now ask to choose dice value and place it
                //dice from server, just extracted from dicebag
                Dice dice = useToolCardResult.dice;
                view.diceValueRequired(dice.getColor());
                while (toolCardParameters.diceValue == null);
                dice.setValue(toolCardParameters.diceValue);
                sendLegalDicePositionsRequest(dice);
                if(!positions.isEmpty()) {
                    do {
                        toolCardParameters.panelCellIndex = null;
                        view.panelCellIndexRequired();
                        while (toolCardParameters.panelCellIndex == null) ;
                    }
                    while (!positions.contains(toolCardParameters.panelCellIndex));

                    sendSpecialPlacementRequest(toolCardParameters.panelCellIndex, dice);
                    useToolCardResult.result = specialDicePlacementResult.status;
                    useToolCardResult.draftpool = specialDicePlacementResult.draftPool;
                    for(Player player : useToolCardResult.players){
                        if(player.getHashCode() == playerHashCode){
                            player.setPanel(specialDicePlacementResult.panel);
                        }
                    }
                }
                else{
                    try {
                        out.writeObject(new PutDiceInDraftPoolRequest(gameHashCode, dice));
                        out.reset();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    useToolCardResult.result = true;
                    useToolCardResult.msg = "No placement allowed due to game rules. The dice has been put back in the draft pool";
                }
                view.notifyUsageCompleted(useToolCardResult);
            }
        }

        private void useToolCard12() throws RemoteException {
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
            sendToolCardRequest();
            view.notifyUsageCompleted(useToolCardResult);
        }

        private void sendLegalDicePositionsRequest(Dice dice) {
            try {
                waitingForResponse = true;
                out.writeObject(new GetLegalPositionRequest(gameHashCode, playerHashCode, dice));
                out.reset();
                synchronized (responseLock) {
                    while (waitingForResponse) {
                        responseLock.wait();
                    }
                    responseLock.notifyAll();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void sendSpecialPlacementRequest(int cellIndex, Dice dice) {
            try {
                waitingForResponse = true;
                out.writeObject(new SpecialDicePlacementRequest(gameHashCode, playerHashCode, cellIndex, dice));
                out.reset();
                synchronized (responseLock) {
                    while (waitingForResponse) {
                        responseLock.wait();
                    }
                    responseLock.notifyAll();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void sendToolCardRequest(){
            try {
                waitingForResponse = true;
                out.writeObject(new UseToolCardRequest(gameHashCode, playerHashCode, toolCardParameters));
                out.reset();
                synchronized (responseLock) {
                    while (waitingForResponse) {
                        responseLock.wait();
                    }
                    responseLock.notifyAll();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}