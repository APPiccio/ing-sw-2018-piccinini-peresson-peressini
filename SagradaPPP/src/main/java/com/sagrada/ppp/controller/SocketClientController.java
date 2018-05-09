package com.sagrada.ppp.controller;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.LobbyObsever;
import com.sagrada.ppp.Observer;
import com.sagrada.ppp.Player;
import com.sagrada.ppp.network.commands.*;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.view.View;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SocketClientController implements RemoteController, ResponseHandler {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ArrayList<LobbyObsever> lobbyObsevers;
    private Response response;
    private ArrayList<Response> notificationQueue;
    private boolean waitingForResponse;
    private JoinGameResult joinGameResult;
    private ListeningThread notificationThread;
    private Object responseLock;

    public SocketClientController() throws IOException {
        socket = new Socket(StaticValues.SERVER_ADDRESS, StaticValues.SOCKET_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        lobbyObsevers = new ArrayList<>();
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
    public void leaveLobby(int gameHashCode, String username) throws RemoteException {

    }

    @Override
    public JoinGameResult joinGame(String username, LobbyObsever observer) throws RemoteException {
        try {
            waitingForResponse = true;
            out.writeObject(new JoinGameRequest(username));
            synchronized (responseLock) {
                while (waitingForResponse) {
                    responseLock.wait();
                }
                responseLock.notifyAll();
            }
            lobbyObsevers.add(observer);
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

    public void handle(JoinPlayerNotification response) {
        for (LobbyObsever observer : lobbyObsevers) {
            try {
                observer.onPlayerJoined(response.username, response.numOfPlayers);
            } catch (RemoteException e) {
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

