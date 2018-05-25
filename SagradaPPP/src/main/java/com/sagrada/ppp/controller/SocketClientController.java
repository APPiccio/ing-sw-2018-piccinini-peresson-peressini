package com.sagrada.ppp.controller;

import com.sagrada.ppp.*;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.commands.*;
import com.sagrada.ppp.utils.StaticValues;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SocketClientController implements RemoteController, ResponseHandler, Serializable {

    private transient Socket socket;
    private transient ObjectOutputStream out;
    private transient ObjectInputStream in;
    private transient ArrayList<LobbyObserver> lobbyObservers;
    private transient ArrayList<GameObserver> gameObservers;
    private transient Response response;
    private transient ArrayList<Response> notificationQueue;
    private transient boolean waitingForResponse;
    private transient JoinGameResult joinGameResult;
    private transient ListeningThread notificationThread;
    private transient Object responseLock;
    private transient LeaveGameResult leaveGameResult;
    private transient boolean disconnectionResult;
    private transient PlaceDiceResult placeDiceResult;

    public SocketClientController() throws IOException {
        socket = new Socket(StaticValues.SERVER_ADDRESS, StaticValues.SOCKET_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        lobbyObservers = new ArrayList<>();
        gameObservers = new ArrayList<>();
        response = null;
        notificationQueue = new ArrayList<>();
        waitingForResponse = false;
        notificationThread = new ListeningThread(this);
        notificationThread.start();
        responseLock = new Object();
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
        return;
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
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

    class ListeningThread extends Thread {

        ResponseHandler handler;

        ListeningThread(ResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    ((Response) in.readObject()).handle(handler);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
