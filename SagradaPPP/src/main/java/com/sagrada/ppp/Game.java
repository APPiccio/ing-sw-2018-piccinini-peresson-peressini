package com.sagrada.ppp;

import com.sagrada.ppp.cards.ToolCards.CommandToolCard;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.LobbyObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Game implements Serializable{
    private ArrayList<Player> players;
    private ArrayList<CommandToolCard> toolCards;
    private Player activePlayer;
    private DiceBag diceBag;
    private ArrayList<WindowPanel> panels;
    private ArrayList<Dice> draftPool;
    private RoundTrack roundTrack;
    private GameStatus gameStatus;
    private ArrayList<LobbyObserver> lobbyObservers;
    private LobbyTimer lobbyTimer;
    private long lobbyTimerStartTime;


    //TODO: Add a method that given the username string returns the desired players
    //TODO: Add overloading methods that take a Player as a parameter instead of a String

    public Game(String username){
        diceBag = new DiceBag();
        players = new ArrayList<>();
        draftPool = new ArrayList<>();
        roundTrack = new RoundTrack(StaticValues.NUMBER_OF_TURNS);
        gameStatus = GameStatus.INIT;
        toolCards = new ArrayList<>();
        if(username != null) players.add(new Player(username));
        lobbyObservers = new ArrayList<>();
    }

    public void init(){
        gameStatus = GameStatus.ACTIVE;
        roundTrack.setCurrentRound(1);
        draftPool.addAll(diceBag.extractDices(players.size() *2+1));

    }

    public ArrayList<Dice> getDraftPool(){
        ArrayList<Dice> h = new ArrayList<>();
        for(Dice dice : draftPool){
            h.add(new Dice(dice));
        }
        return h;
    }

    //TO DO check if enum should be passed as a copy and not as a reference --> private invariant
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getPlayer(String username){
        return players.stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
    }

    public void toNextTurn(){
        if (roundTrack.getCurrentRound() == 10){
            gameStatus = GameStatus.SCORE;
            System.out.println("WARNING --> 10 turns played");
            return;
        }
        else{
            roundTrack.setDicesOnTurn(roundTrack.getCurrentRound(), getDraftPool());
            roundTrack.setCurrentRound(roundTrack.getCurrentRound() + 1);
            draftPool.clear();
            draftPool.addAll(diceBag.extractDices(players.size() *2 + 1));
        }
    }

    public long getLobbyTimerStartTime(){
        return lobbyTimerStartTime;
    }

    public int joinGame(String username, LobbyObserver observer) {
        int i = 1;
        String user = username;
        while(isInMatch(user)){
            user = username + "(" + i + ")";
            i++;
        }
        Player h = new Player(user);
        players.add(h);
        notifyPlayerJoin(user,getUsernames(),players.size());
        attachLobbyObserver(observer);
        if(players.size() == 2){
            //start timer
            lobbyTimerStartTime = System.currentTimeMillis();
            lobbyTimer = new LobbyTimer(lobbyTimerStartTime, this);
            lobbyTimer.start();
            notifyTimerChanges(TimerStatus.START);
        }
        else{
            if(players.size() == 4){
                //starting game
                lobbyTimer.interrupt();
                notifyTimerChanges(TimerStatus.FINISH);
                init();

            }
        }
        return h.hashCode();
    }

    public boolean isJoinable(){
        return players.size() < StaticValues.MAX_USER_PER_GAME && gameStatus.equals(GameStatus.INIT);
    }

    public String getPlayerUsername(int hashCode){
        for(Player player : players){
            if (player.hashCode() == hashCode) return player.getUsername();
        }
        return null;
    }


    public boolean isInMatch(String username){
        for(Player player : players){
            if (player.getUsername().equals(username)) return true;
        }
        return false;
    }

    public ArrayList<Player> getPlayers(){
        ArrayList<Player> playersCopy = new ArrayList<>();
        for(Player player : players){
            playersCopy.add(new Player(player));
        }
        return playersCopy;
    }
    public int getActivePlayers(){
        return (int) players.stream().filter(x -> x.getPlayerStatus()==PlayerStatus.ACTIVE).count();
    }

    public ArrayList<String> getUsernames(){
        return this.getPlayers().stream().map(Player::getUsername).collect(Collectors.toCollection(ArrayList::new));
    }
    public int getPrivateScore(Player activePlayer) {
        int score = 0;
        for (int i = 0; i < StaticValues.NUMBER_OF_CELLS; i++) {
            Dice tempDice = activePlayer.getPanel().getCell(i).getDiceOn();
            if (tempDice != null) {
                if (tempDice.getColor() == activePlayer.getPrivateColor()) {
                    score += tempDice.getValue();
                }
            }
        }
        return score;
    }

    public void leaveLobby(String username, LobbyObserver observer){
        for(Player player : players){
            if (player.getUsername().equals(username)){
                players.remove(player);
                detachLobbyObserver(observer);
                notifyPlayerLeave(username,getUsernames(),players.size());
                if(players.size() < 2){
                    if(lobbyTimer != null) {
                        lobbyTimer.interrupt();
                    }
                    lobbyTimerStartTime = 0;
                    notifyTimerChanges(TimerStatus.INTERRUPT);
                }
                return;
            }
        }
    }

    public int getPlayerHashCode(String username){
        for(Player player : players){
            if (player.getUsername().equals(username)){
                return player.hashCode();
            }
        }
        return -1;
    }

    public void attachLobbyObserver(LobbyObserver observer){
        lobbyObservers.add(observer);
    }

    public void detachLobbyObserver(LobbyObserver observer){
        lobbyObservers.remove(observer);
    }

    public void notifyTimerChanges(TimerStatus timerStatus) {
        for (LobbyObserver observer : lobbyObservers) {
            try {
                observer.onTimerChanges(lobbyTimerStartTime, timerStatus)
                ;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public void notifyPlayerJoin (String username,ArrayList<String> players, int numOfPlayers){
            for (LobbyObserver observer: lobbyObservers) {
                try {

                    observer.onPlayerJoined(username , players,numOfPlayers);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
    }

    public void notifyPlayerLeave(String username,ArrayList<String> players,int numOfPlayers) {
            for (LobbyObserver observer: lobbyObservers) {
                try {
                    observer.onPlayerLeave(username ,players, numOfPlayers);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
}

