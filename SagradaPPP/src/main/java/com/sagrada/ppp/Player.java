package com.sagrada.ppp;

public class Player {

    private String username;
    private String url;
    private WindowPanel panel;
    private Color privateColor;



    private Dice activeDice;

    public void setPanel(WindowPanel panel) {
        this.panel = panel;
    }

    public Player(Player player){
        this.username = player.username;
        this.privateColor = player.privateColor;
        this.activeDice = player.activeDice;
    }


    public void setActiveDice(Dice activeDice) {
        this.activeDice = activeDice;
    }

    public Dice getActiveDice() {
        if(activeDice == null){
            return null;
        }
        return new Dice(activeDice);
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
        return privateColor;
    }

    public WindowPanel getPanel() {
        return new WindowPanel(panel);
    }
}
