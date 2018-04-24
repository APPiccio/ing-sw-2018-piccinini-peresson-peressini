package com.sagrada.ppp;

public class ToolCardTestPanels {

    public static WindowPanel panel_40() {

        WindowPanel panel = new WindowPanel(10, StaticValues.FRONT_SIDE);

        panel.addDiceOnCellWithIndex(2, new Dice(Color.GREEN, 1));
        panel.addDiceOnCellWithIndex(3, new Dice(Color.RED, 2));

        panel.addDiceOnCellWithIndex(8, new Dice(Color.BLUE, 6));

        return panel;

    }

}
