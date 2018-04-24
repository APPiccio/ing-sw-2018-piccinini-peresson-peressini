package com.sagrada.ppp;

import com.sagrada.ppp.Cards.CommandToolCard;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<CommandToolCard> toolCards;
    private Player activePlayer;
    private DiceBag diceBag;
    private ArrayList<WindowPanel> panels;
    private int numOfPlayer;
    private ArrayList<Dice> draftPool;
    private RoundTrack roundTrack;
    private GameStatus gameStatus;

    //TODO: Add a method that given the username string returns the desired players
    //TODO: Add overloading methods that take a Player as a parameter instead of a String

    public Game(){
        diceBag = new DiceBag();
        players = new ArrayList<>();
        draftPool = new ArrayList<>();
        roundTrack = new RoundTrack(StaticValues.NUMBER_OF_TURNS);
        gameStatus = GameStatus.INIT;
        toolCards = new ArrayList<>();
    }

    public void init(){
        roundTrack.setCurrentRound(1);
        draftPool.addAll(diceBag.extractDices(numOfPlayer*2+1));
        numOfPlayer = players.size();
    }

    public ArrayList<Dice> getDraftPool(){
        ArrayList<Dice> h = new ArrayList<>();
        for(Dice dice : draftPool){
            h.add(new Dice(dice));
        }
        return h;
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

    public void joinGame(String username) {
        int i = 1;
        String user = username;
        while(isInMatch(user)){
            user = username + "(" + i + ")";
            i++;
        }
        players.add(new Player(user));
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
            Dice tempDice = activePlayer.getMyPanel().getCellWithIndex(i).getDiceOn();
            if (tempDice == null) {
                continue;
            }
            else {
                if (tempDice.getColor() == activePlayer.getPrivateColor()) {
                    score += tempDice.getValue();
                }
            }
        }
        return score;
    }
}
