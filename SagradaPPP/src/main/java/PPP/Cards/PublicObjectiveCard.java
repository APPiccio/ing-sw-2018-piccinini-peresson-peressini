package PPP.Cards;

import PPP.WindowPanel;

public abstract class PublicObjectiveCard {

    private String name;
    private int ID;

    public PublicObjectiveCard() {
        this.name = null;
        this.ID = 0;
    }

    protected PublicObjectiveCard(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getScore(WindowPanel panel) {
        return 0;
    }
}
