package com.sagrada.ppp.controller;

import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.commands.*;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.ToolCardHandler;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SocketClientController extends UnicastRemoteObject implements RemoteController, ResponseHandler, Serializable {

    private transient Socket socket;
    private transient ObjectOutputStream out;
    private transient ObjectInputStream in;
    private transient ArrayList<LobbyObserver> lobbyObservers;
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

    public SocketClientController() throws IOException {
        socket = new Socket(StaticValues.SERVER_ADDRESS, StaticValues.SOCKET_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        lobbyObservers = new ArrayList<>();
        gameObservers = new ArrayList<>();
        waitingForResponse = false;
        notificationThread = new ListeningThread(this);
        notificationThread.start();
        responseLock = new Object();
        toolCardParameters = new ToolCardParameters();
    }

    @Override
    public ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException {
        return null;
    }

    @Override
    public LeaveGameResult leaveLobby(int gameHashCode, String username,LobbyObserver observer) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new LeaveGameRequest(username, gameHashCode));
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            lobbyObservers.remove(observer);
            return leaveGameResult;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void attachGameObserver(int gameHashCode ,GameObserver gameObserver) throws RemoteException {
        gameObservers.add(gameObserver);
    }

    @Override
    public JoinGameResult joinGame(String username, LobbyObserver lobbyObserver) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new JoinGameRequest(username));
            lobbyObservers.add(lobbyObserver);
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
    public int getNumPlayers(int gameHashCode) throws RemoteException {
        return 0;
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
        for (LobbyObserver observer : lobbyObservers) {
            try {
                switch (response.eventType) {
                    case JOIN:
                        observer.onPlayerJoined(response.username, response.players, response.numOfPlayers);
                        break;
                    case LEAVE:
                        observer.onPlayerLeave(response.username, response.players, response.numOfPlayers);
                        break;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void handle(TimerNotification response){
        for(LobbyObserver observer : lobbyObservers){
            try {
                observer.onTimerChanges(response.timerStart, response.timerStatus);
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void choosePanel(int gameHashCode, int playerHashCode, int panelIndex) throws RemoteException {
        try {
            out.writeObject(new PanelChoiceRequest(gameHashCode,playerHashCode,panelIndex));
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
            responseLock.notifyAll();
        }
    }

    @Override
    public boolean disconnect(int gameHashCode, int playerHashCode, LobbyObserver lobbyObserver, GameObserver gameObserver) throws RemoteException {
        try{
            waitingForResponse = true;
            out.writeObject(new DisconnectionRequest(gameHashCode,playerHashCode));
            lobbyObservers.remove(lobbyObserver);
            gameObservers.remove(gameObserver);
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            closeConnection();
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
    public void endTurn(int gameHashCode, int playerHashCode) throws RemoteException {
        try {
            out.writeObject(new EndTurnRequest(gameHashCode, playerHashCode));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeSocket(){
        try {
            out.writeObject(new CloseSocketRequest());
            closeConnection();
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
    public void detachGameObserver(int gameHashCode, GameObserver gameObserver) throws RemoteException {
        gameObservers.remove(gameObserver);
        if(gameObservers.size() == 0) {
            try {
                out.writeObject(new DetachGameObserverRequest(gameHashCode));
                gameObservers.remove(gameObserver);
                notificationThread.interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                }catch (EOFException e){
                    System.out.println("Closing client socket");
                }catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    @Override
    public void isToolCardUsable(int gameHashCode, int playerHashCode, int toolCardIndex, ToolCardHandler view) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new IsToolCardUsableRequest(gameHashCode, playerHashCode, toolCardIndex));
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

        ToolCardThreadController(IsToolCardUsableResult isToolCardUsableResult, ToolCardHandler view, int gameHashCode, int playerHashCode, int toolCardIndex, ObjectInputStream in, ObjectOutputStream out){
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
                switch (toolCardID) {
                    case 1:
                        useToolCard1();
                        break;
                    case 2:
                        useToolCard2();
                        break;
                    case 3:
                        useToolCard3();
                        break;
                    case 4:
                        useToolCard4();
                        break;
                    case 9:
                        useToolCard9();
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

            sendToolCardRequest();
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
        }

        private void useToolCard1() throws RemoteException{
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.draftPoolDiceIndexRequired();
            while (toolCardParameters.draftPoolDiceIndex == null) ;
            view.actionSignRequired();
            while (toolCardParameters.actionSign == null) ;

            sendToolCardRequest();
        }

        private void useToolCard2() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            sendToolCardRequest();
        }

        private void useToolCard3() throws RemoteException {
            toolCardParameters.reset();
            toolCardParameters.toolCardID = toolCardID;
            view.panelDiceIndexRequired();
            while (toolCardParameters.panelDiceIndex == null);
            view.panelCellIndexRequired();
            while (toolCardParameters.panelCellIndex == null);
            sendToolCardRequest();
        }

        private void sendToolCardRequest(){
            try {
                waitingForResponse = true;
                out.writeObject(new UseToolCardRequest(gameHashCode, playerHashCode, toolCardParameters));
                synchronized (responseLock) {
                    while (waitingForResponse) {
                        responseLock.wait();
                    }
                    responseLock.notifyAll();
                }
                view.notifyUsageCompleted(useToolCardResult);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
