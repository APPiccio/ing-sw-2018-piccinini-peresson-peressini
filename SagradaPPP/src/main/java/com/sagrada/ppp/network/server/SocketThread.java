package com.sagrada.ppp.network.server;

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
    private int gameHashCode;
    private int playerHashCode;
    private String username;





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

    /**
     * Start function for a the thread that listens from Client Requests and sends replies(Response).
     */
    public void run() {
        while (!isStopped) {
            //DO SOCKET SERVER STUFF TO CLIENT
            try {
                System.out.println("listening....");
                response = ((Request) in.readObject()).handle(this);
                if(response != null){
                    System.out.println("Sending response to: "+ out.toString());
                    synchronized (this) {
                        out.writeObject(response);
                        out.reset();
                        notifyAll();
                    }
                }

            }

            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Closing socket... gamehashcode = " + gameHashCode + " - playerHashCode = " + playerHashCode );
                System.out.println("DISCONNECTION RESULT = " + service.disconnect(gameHashCode, playerHashCode));
                return;
            }
            catch (ClassNotFoundException e) {
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


    public Response handle(Request request){
        return null;
    }

    @Override
    public Response handle(UseToolCardRequest request) {
        return new UseToolCardResponse(service.useToolCard(request.gameHashCode, request.playerHashCode, request.toolCardParameters));
    }

    public Response handle(JoinGameRequest joinGameRequest){
        JoinGameResult joinGameResult = service.joinGame(joinGameRequest.username , this,this);
        this.playerHashCode = joinGameResult.getPlayerHashCode();
        this.gameHashCode = joinGameResult.getGameHashCode();
        this.username = joinGameResult.getUsername();
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
        //TODO detach observer and notify game of that
        DisconnectionResponse disconnectionResponse = new DisconnectionResponse(service.disconnect(request.gameHashCode,
                request.playerHashCode));
        isStopped = true;
        return disconnectionResponse;
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
    public Response handle(DetachGameObserverRequest request) {
        service.detachAllGameObserver(request.gameHashCode, request.playerHashCode);
        isStopped = true;
        return null;
    }

    @Override
    public Response handle(IsToolCardUsableRequest request) {
        return new IsToolCardUsableResponse(service.isToolCardUsable(request.gameHashCode, request.playerHashCode, request.toolCardIndex));
    }

    @Override
    public Response handle(GetLegalPositionRequest request) {
        return new GetLegalPositionResponse(service.getLegalPositions(request.gameHashCode, request.playerHashCode, request.dice));
    }

    @Override
    public Response handle(SpecialDicePlacementRequest request) {
        return new SpecialDicePlacementResponse(service.specialDicePlacement(request.gameHashCode, request.playerHashCode, request.cellIndex, request.dice));
    }

    @Override
    public Response handle(PutDiceInDraftPoolRequest request) {
        service.putDiceInDraftPool(request.gameHashCode, request.dice);
        return null;
    }

    @Override
    public Response handle(ReconnectionRequest request) {
        return new ReconnectionResponse(service.reconnection(request.playerHashCode, request.gameHashCode,
                this));
    }

    @Override
    public Response handle(DisableAFKRequest request) {
        service.disableAFK(request.gameHashCode, request.playerHashCode);
        return null;
    }

    @Override
    public synchronized void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
        try {
            out.writeObject(new DicePlacedNotification(dicePlacedMessage));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onToolCardUsed(ToolCardNotificationMessage toolCardUsedMessage) throws RemoteException {
        System.out.println();
        try {
            out.writeObject(new UsedToolCardNotification(toolCardUsedMessage));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, Color color) throws RemoteException {
        try {
            out.writeObject(new PanelChoiceNotification(playerHashCode, panels, panelsAlreadyChosen, color));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {
        try {
            out.writeObject(new GameStartNotification(gameStartMessage));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {
        try {
            out.writeObject(new EndTurnNotification(endTurnMessage));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {
        try {
            out.writeObject(new EndGameNotification(playersScore));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public synchronized void onPlayerLeave(String username,ArrayList<String> players, int numOfPlayers) throws RemoteException {
        try {
            System.out.println("Sending notification to: "+out.toString());
            out.writeObject(new PlayerEventNotification(numOfPlayers, username,players,PlayerEventType.LEAVE));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onPlayerJoined(String username, ArrayList<String> players, int numOfPlayers) throws RemoteException {
        try {
            System.out.println("Sending notification to: " + out.toString());
            out.writeObject(new PlayerEventNotification(numOfPlayers, username,players,PlayerEventType.JOIN));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void onTimerChanges(long timerStart, TimerStatus timerStatus){
        try {
            out.writeObject(new TimerNotification(timerStart, timerStatus));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPlayerReconnection(Player reconnectingPlayer) throws RemoteException {
        try {
            out.writeObject(new PlayerReconnectionNotification(reconnectingPlayer));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPlayerDisconnection(Player disconnectingPlayer, boolean isLastPlayer) throws RemoteException {
        try {
            out.writeObject(new PlayerDisconnectionNotification(disconnectingPlayer, isLastPlayer));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPlayerAFK(Player playerAFK, boolean isLastPlayer, Player lastPlayer) throws RemoteException {
        try {
            out.writeObject(new PlayerAFKNotification(playerAFK, isLastPlayer, lastPlayer));
            out.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rmiPing() throws RemoteException {
        //do nothing here.. only to keep inheritance
    }


}