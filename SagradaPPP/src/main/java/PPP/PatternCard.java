package PPP;

import java.io.FileNotFoundException;

public class PatternCard {

    private WindowPanel frontPanel;
    private WindowPanel backPanel;
    private WindowPanel activePanel;
    // 0 -> front , 1 -> back
    private Integer active;


    public PatternCard(PatternCard patternCard){
        this.frontPanel = patternCard.getFrontPanel();
        this.backPanel = patternCard.getBackPanel();
        this.activePanel = patternCard.getActivePanel();
        this.active = patternCard.getActive();
    }


    public PatternCard(int cardNumber) throws FileNotFoundException {
        frontPanel = new WindowPanel(cardNumber,1);
        backPanel = new WindowPanel(cardNumber,1);
        active = null;
        activePanel = null;
    }


    public void setActivePanel(WindowPanel activePanel) {
        this.activePanel = activePanel;
    }

    public WindowPanel getActivePanel() {
        return new WindowPanel(activePanel);
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public WindowPanel getFrontPanel() {
        return new WindowPanel(frontPanel);
    }

    public void setFrontPanel(WindowPanel frontPanel) {
        this.frontPanel = frontPanel;
    }

    public WindowPanel getBackPanel() {
        return new WindowPanel(backPanel);
    }

    public void setBackPanel(WindowPanel backPanel) {
        this.backPanel = backPanel;
    }
}
