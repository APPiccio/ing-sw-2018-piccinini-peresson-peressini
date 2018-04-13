package PPP.Cards;

import PPP.Color;

public abstract class PrivateObjectiveCard {

    private String name;
    private Color ID;

    public PrivateObjectiveCard() {
        this.name = null;
        this.ID = Color.getRandomColor(); //TODO: to be reviewed
    }

    protected PrivateObjectiveCard(String name, Color ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public Color getID() {
        return ID;
    }

}
