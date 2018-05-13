package com.sagrada.ppp.controller;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.LobbyObserver;
import com.sagrada.ppp.LeaveGameResult;
import com.sagrada.ppp.Player;
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
    private transient Response response;
    private transient ArrayList<Response> notificationQueue;
    private transient boolean waitingForResponse;
    private transient JoinGameResult joinGameResult;
    private transient ListeningThread notificationThread;
    private transient Object responseLock;
    private transient LeaveGameResult leaveGameResult;

    public SocketClientController() throws IOException {
        socket = new Socket(StaticValues.SERVER_ADDRESS, StaticValues.SOCKET_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        lobbyObservers = new ArrayList<>();
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
    public JoinGameResult joinGame(String username, LobbyObserver observer) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new JoinGameRequest(username));
            lobbyObservers.add(observer);
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
            responseLock.notifyAll();
        }
        joinGameResult = response.joinGameResult;
    }

    public void handle(LeaveGameResponse response) {
        synchronized (responseLock) {
            waitingForResponse = false;
            responseLock.notifyAll();
        }
        leaveGameResult = response.leaveGameResult;
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

