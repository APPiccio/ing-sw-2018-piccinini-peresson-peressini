package com.sagrada.ppp;

import com.sagrada.ppp.cards.ToolCards.CommandToolCard;
import com.sagrada.ppp.utils.StaticValues;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Game implements Serializable{
    private ArrayList<Player> players;
    private ArrayList<CommandToolCard> toolCards;
    private Player activePlayer;
    private DiceBag diceBag;
    private ArrayList<WindowPanel> panels;
    private int numOfPlayer;
    private ArrayList<Dice> draftPool;
    private RoundTrack roundTrack;
    private GameStatus gameStatus;
    private ArrayList<Observer> observers;


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
        observers = new ArrayList<>();
    }

    public void init(){
        roundTrack.setCurrentRound(1);
        numOfPlayer = players.size();
        draftPool.addAll(diceBag.extractDices(numOfPlayer*2+1));

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
            draftPool.addAll(diceBag.extractDices(numOfPlayer*2 + 1));
        }
    }

    public int joinGame(String username) {
        int i = 1;
        String user = username;
        while(isInMatch(user)){
            user = username + "(" + i + ")";
            i++;
        }
        Player h = new Player(user);
        players.add(h);
        notifyAllObservers(0,user);
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

    public void leaveLobby(String username){
        for(Player player : players){
            if (player.getUsername().equals(username)){
                players.remove(player);
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

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifyAllObservers(int updateCode){
        notifyAllObservers(updateCode,"no_username");
    }


    public void notifyAllObservers(int updateCode, String username){
        for (Observer observer : observers) {
            try {
                observer.update(updateCode, username);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}

