package PPP;

public class Player {

    private String username;
    private String url;
    private WindowPanel myPanel;
    private Color PrivateColor;

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

    public Color getPrivateColor() {
        return PrivateColor;
    }

    public WindowPanel getMyPanel() {
        return new WindowPanel(myPanel);
    }
}
