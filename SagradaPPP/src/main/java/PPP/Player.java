package PPP;

public class Player {
    private String username;
    private String url;
    private PlayerBoard playerBoard;

    public Player(Player player){
        this.username = player.getUsername();
    }

    public Player(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

}
