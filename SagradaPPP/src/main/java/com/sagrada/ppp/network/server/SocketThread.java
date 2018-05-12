package com.sagrada.ppp.network.server;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.LeaveGameResult;
import com.sagrada.ppp.LobbyObsever;
import com.sagrada.ppp.Service;
import com.sagrada.ppp.network.commands.*;
import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SocketThread extends Thread implements LobbyObsever, RequestHandler{
    private Socket socket;
    private Service service;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    @Override
    public void onPlayerLeave(String username,ArrayList<String> players, int numOfPlayers) throws RemoteException {
        try {
            System.out.println("Sending notification to: "+out.toString());
            out.writeObject(new PlayerEventNotification(numOfPlayers, username,players,PlayerEventType.LEAVE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                System.out.println("listening....");
                 response = ((Request) in.readObject()).handle(this);
                 if(response != null){
                     System.out.println("Sending response to: "+out.toString());
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
    public void onPlayerJoined(String username, ArrayList<String> players, int numOfPlayers) throws RemoteException {
        try {
            System.out.println("Sending notification to: "+out.toString());
            out.writeObject(new PlayerEventNotification(numOfPlayers, username,players,PlayerEventType.JOIN));
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

    @Override
    public Response handle(LeaveGameRequest request) {
        LeaveGameResult leaveGameResult = service.leaveLobby(request.gameHashCode,request.username,this);
        return new LeaveGameResponse(leaveGameResult);
    }

}
