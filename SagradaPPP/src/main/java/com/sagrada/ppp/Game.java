package com.sagrada.ppp;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Player activePlayer;
    private DiceBag diceBag;
    private ArrayList<WindowPanel> panels;
    private int numOfPlayer = 1;

    //TODO: Add a method that given the username string returns the desired players
    //TODO: Add overloading methods that take a Player as a parameter instead of a String

    public Game(){
        diceBag = new DiceBag();
        players = new ArrayList<Player>();
    }

    public void init(){
        RoundTrack roundTrack = new RoundTrack(StaticValues.NUMBER_OF_TURNS);
        roundTrack.setCurrentRound(1);


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