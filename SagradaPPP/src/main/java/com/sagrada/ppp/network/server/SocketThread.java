package com.sagrada.ppp.network.server;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.LobbyObserver;
import com.sagrada.ppp.Service;
import com.sagrada.ppp.TimerStatus;
import com.sagrada.ppp.network.commands.*;
import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

public class SocketThread extends Thread implements LobbyObserver, RequestHandler{
    private Socket socket;
    private Service service;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Response response;

    public SocketThread(Socket socket, Service service){
        this.socket = socket;
        this.service = service;
        this.response = null;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            //DO SOCKET SERVER STUFF TO CLIENT
            try {
                System.out.println("mi metto in attesa");
                 response = ((Request) in.readObject()).handle(this);
                 if(response != null){
                     out.writeObject(response);
                 }

            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPlayerJoined(String username, int numOfPlayers) throws RemoteException {
        try {
            out.writeObject(new JoinPlayerNotification(numOfPlayers, username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTimerChanges(long timerStart, TimerStatus timerStatus){
        try {
            out.writeObject(new TimerNotification(timerStart, timerStatus));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response handle(Request request){
        return null;
    }

    public Response handle(JoinGameRequest joinGameRequest){
        JoinGameResult joinGameResult = service.joinGame(joinGameRequest.username , this);
        return new JoinGameResponse(joinGameResult);
    }

    public Response handle(LeaveGameRequest request){
        service.leaveLobby(request.gameHashCode, request.username, this);
        return null;
    }

}
