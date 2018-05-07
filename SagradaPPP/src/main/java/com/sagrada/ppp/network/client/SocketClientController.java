package com.sagrada.ppp.network.client;

import com.sagrada.ppp.JoinGameResult;
import com.sagrada.ppp.Observer;
import com.sagrada.ppp.Player;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.network.commands.JoinGameRequest;
import com.sagrada.ppp.network.commands.JoinGameResponse;
import com.sagrada.ppp.network.commands.Response;
import com.sagrada.ppp.utils.StaticValues;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SocketClientController implements RemoteController {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public SocketClientController() throws IOException {
        socket = new Socket(StaticValues.SERVER_ADDRESS, StaticValues.SOCKET_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }


    @Override
    public ArrayList<Player> getPlayers(int gameHashCode) throws RemoteException {
        return null;
    }

    @Override
    public void leaveLobby(int gameHashCode, String username) throws RemoteException {

    }

    @Override
    public void attachLobbyObserver(int gameHashCode, Observer observer) throws RemoteException {

    }

    @Override
    public JoinGameResult joinGame(String username) throws RemoteException {
        try {
            out.writeObject(new JoinGameRequest(username));
            JoinGameResponse response = (JoinGameResponse) in.readObject();
            return response.joinGameResult;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUsername(int playerHashCode, int gameHashCode) throws RemoteException {
        return null;
    }

}
