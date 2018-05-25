package com.sagrada.ppp.network.server;

import com.sagrada.ppp.*;
import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.ToolCards.ToolCard;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.network.commands.*;
import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class SocketThread extends Thread implements LobbyObserver, RequestHandler, GameObserver {
    private Socket socket;
    private Service service;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Response response;
    private boolean isStopped;


    @Override
    public void onPlayerLeave(String username,ArrayList<String> players, int numOfPlayers) throws RemoteException {
        try {
            System.out.println("Sending notification to: "+out.toString());
            out.writeObject(new PlayerEventNotification(numOfPlayers, username,players,PlayerEventType.LEAVE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SocketThread(Socket socket, Service service){
        this.socket = socket;
        this.service = service;
        this.response = null;
        this.isStopped = false;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true && !isStopped) {
            //DO SOCKET SERVER STUFF TO CLIENT
            try {
                System.out.println("listening....");
                response = ((Request) in.readObject()).handle(this);
                if(response != null){
                    System.out.println("Sending response to: "+out.toString());
                    out.reset();
                    out.writeObject(response);
                }

            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try{
            in.close();
            out.close();
            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
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
        JoinGameResult joinGameResult = service.joinGame(joinGameRequest.username , this,this);
        return new JoinGameResponse(joinGameResult);
    }

    @Override
    public Response handle(LeaveGameRequest request) {
        LeaveGameResult leaveGameResult = service.leaveLobby(request.gameHashCode,request.username,this,this);
        return new LeaveGameResponse(leaveGameResult);
    }

    @Override
    public Response handle(PanelChoiceRequest request) {
        service.choosePanel(request.gameHashCode, request.playerHashCode, request.panelIndex);
        return null;
    }

    @Override
    public Response handle(DisconnectionRequest request) {
        isStopped = true;
        return new DisconnectionResponse(true);
    }

    @Override
    public Response handle(PlaceDiceRequest request) {
        PlaceDiceResult result = service.placeDice(request.gameHashCode, request.playerHashCode, request.diceIndex, request.row, request.col);
        System.out.println(result.panel);
        return new PlaceDiceResponse(result);
    }

    @Override
    public Response handle(EndTurnRequest request) {
        service.endTurn(request.gameHashCode, request.playerHashCode);
        return null;
    }

    @Override
    public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
        try {
            out.writeObject(new DicePlacedNotification(dicePlacedMessage));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color color) throws RemoteException {
        try {
            out.writeObject(new PanelChoiceNotification(playerHashCode, panels, panelsAlreadyChosen, color));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {
        try {
            out.writeObject(new GameStartNotification(gameStartMessage));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {
        try {
            out.writeObject(new EndTurnNotification(endTurnMessage));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {
        try {
            out.writeObject(new EndGameNotification(playersScore));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}