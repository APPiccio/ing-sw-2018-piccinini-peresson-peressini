package PPP;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Integer turn;
    private Player activePlayer;
    private DiceBag diceBag;
    private ArrayList<WindowPanel> panels;



    public Game(){
        diceBag = new DiceBag();
        
    }


    public DiceBag getCurrentDiceBag(){
        return null;
    }

    public ArrayList<Cell> getLegalMoves(Dice dice){
        return null;
    } //Mette null dove non si possa inserire il dado, torna il riferimento diretto alle celle della plancia legali
    public ArrayList<Player> getPlayers(){
        return null;
    }
    public Player getActivePlayer(){
        return null;
    }

    public Player getPlayer(String username){
        return null;
    }
    public ArrayList<Dice> getCurrentDraftArea(){
        return null;
    }
    public RoundTrack getCurrentRoundTrack(){
        return null;
    }
    int getCurrentTurn(){
        return 0;
    }
}
