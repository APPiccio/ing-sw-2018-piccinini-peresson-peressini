package com.sagrada.ppp;

import com.sagrada.ppp.cards.ToolCards.CommandToolCard;
import com.sagrada.ppp.utils.StaticValues;
import com.sagrada.ppp.LobbyObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
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
    public volatile boolean waitingForPanelChoice;
    private HashMap<Integer, WindowPanel> playersPanel;
    public volatile boolean panelChoiceTimerExpired;
    public Integer chosenPanelIndex;
    public ArrayList<GameObserver> gameObservers;

    /*
    TODO: Add a method that given the username string returns the desired players
    TODO: Add overloading methods that take a Player as a parameter instead of a String
    */

    public Game(String username){
        diceBag = new DiceBag();
        players = new ArrayList<>();
        draftPool = new ArrayList<>();
        roundTrack = new RoundTrack(StaticValues.NUMBER_OF_TURNS);
        gameStatus = GameStatus.INIT;
        toolCards = new ArrayList<>();
        if(username != null) players.add(new Player(username));
        lobbyObservers = new ArrayList<>();
        waitingForPanelChoice = false;
        panelChoiceTimerExpired = false;
        playersPanel = new HashMap<>();
        chosenPanelIndex = null;
        gameObservers = new ArrayList<>();
    }

    public void init(){
        gameStatus = GameStatus.ACTIVE;
        HashMap<Integer, ArrayList<WindowPanel>> panels = extractPanels();
        for(int playerHashCode : panels.keySet()){
            waitingForPanelChoice = true;
            panelChoiceTimerExpired = false;
            System.out.println("Sending panels to " + playerHashCode);
            HashMap<String, WindowPanel> usernameToPanelHashMap = new HashMap<>();
            for(Integer i : playersPanel.keySet()){
                usernameToPanelHashMap.put(getPlayerUsername(i), playersPanel.get(i));
            }
            notifyPanelChoice(playerHashCode, panels.get(playerHashCode),usernameToPanelHashMap);
            System.out.println("end of notify");
            PanelChoiceTimer panelChoiceTimer = new PanelChoiceTimer(System.currentTimeMillis(), this);
            panelChoiceTimer.start();
            while(waitingForPanelChoice && !panelChoiceTimerExpired){
            }
            System.out.println("Received choise from user");
            if(chosenPanelIndex != null){
                playersPanel.put(playerHashCode, panels.get(playerHashCode).get(chosenPanelIndex));
            }
            else{
                playersPanel.put(playerHashCode, panels.get(playerHashCode).get(0));
            }
        }
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

    public int joinGame(String username, LobbyObserver lobbyObserver, GameObserver gameObserver) {
        int i = 1;
        String user = username;
        while(isInMatch(user)){
            user = username + "(" + i + ")";
            i++;
        }
        Player h = new Player(user);
        players.add(h);
        notifyPlayerJoin(user,getUsernames(),players.size());
        attachLobbyObserver(lobbyObserver);
        attachGameObserver(gameObserver);
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
                Runnable myrunnable = () -> {
                    init();
                };
                new Thread(myrunnable).start();

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


    public void attachGameObserver(GameObserver observer){
        if(observer == null) return;
        gameObservers.add(observer);
    }

    public void detachGameObserver(GameObserver observer){
        lobbyObservers.remove(observer);
    }

    public void attachLobbyObserver(LobbyObserver observer){
        if(observer == null) return;
        lobbyObservers.add(observer);
    }

    public void detachLobbyObserver(LobbyObserver observer){
        lobbyObservers.remove(observer);
    }

    public void notifyPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen){
        for(GameObserver observer : gameObservers){
            try {
                observer.onPanelChoice(playerHashCode, panels, panelsAlreadyChosen);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }



    public void notifyTimerChanges(TimerStatus timerStatus) {
        for (LobbyObserver observer : lobbyObservers) {
            try {
                System.out.println("I'm notifying a new LobbyTimerStatus = " + timerStatus);
                observer.onTimerChanges(lobbyTimerStartTime, timerStatus);
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

    public HashMap<Integer, ArrayList<WindowPanel>> extractPanels() {
        HashMap<Integer, ArrayList<WindowPanel>> temp = new HashMap<>();
        ArrayList<Integer> notUsedPanel = new ArrayList<>();
        for(int i = 1; i <= StaticValues.NUMBER_OF_CARDS; i++ ){
            notUsedPanel.add(i);
        }
        for(Player player : players){
            int randomNum = ThreadLocalRandom.current().nextInt(0, notUsedPanel.size());
            ArrayList<WindowPanel> panels = new ArrayList<>();
            panels.add(new WindowPanel(notUsedPanel.get(randomNum), 1));
            panels.add(new WindowPanel(notUsedPanel.get(randomNum), 0));
            notUsedPanel.remove(randomNum);
            randomNum = ThreadLocalRandom.current().nextInt(0, notUsedPanel.size());
            panels.add(new WindowPanel(notUsedPanel.get(randomNum), 1));
            panels.add(new WindowPanel(notUsedPanel.get(randomNum), 0));
            notUsedPanel.remove(randomNum);
            temp.put(player.hashCode() , panels);
        }
        return temp;
    }


    public void pairPanelToPlayer(int playerHashCode, int panelIndex) {
        chosenPanelIndex = panelIndex;
    }

}

