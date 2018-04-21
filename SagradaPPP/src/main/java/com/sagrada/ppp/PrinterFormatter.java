package com.sagrada.ppp;

import java.util.ArrayList;

public class PrinterFormatter {

    private final static String bottomCell = "------------";

    /*public static String printWindowPanel(WindowPanel panel){
        StringBuilder string = new StringBuilder();

        //generating top board
        string.append("+");
        for(int i = 0; i < StaticValues.PATTERN_COL; i++){
            string.append(bottomCell+"+");
        }
        string.append("\n");

        for(int row = 0; row < StaticValues.PATTERN_ROW; row++) {
            string.append("|");
            for(int i = 0; i < 3; i++) {

                switch (i){
                    case 0:
                        for (int col = 0; col < StaticValues.PATTERN_COL; col++) {
                            Cell cell = panel.getCellWithPosition(row,col);
                            if(cell.hasDiceOn()) {
                                string.append(String.format("%" + 4 + "s", "_"));
                                string.append(String.format("%" + 9 + "s", "|"));
                            }
                            else{
                                string.append(String.format("%" + 13 + "s","|"));
                            }
                        }
                        string.append("\n|");
                        break;
                    case 1:
                        for (int col = 0; col < StaticValues.PATTERN_COL; col++) {
                            Cell cell = panel.getCellWithPosition(row,col);
                            String h;
                            if(cell.hasDiceOn()) {
                                h = "|"+cell.getDiceOn().getValue() + "| " + cell.getDiceOn().getColor();
                                string.append(String.format("%" + 3 + "s",h));

                            }

                        }
                        string.append("\n");
                        break;
                    case 2:
                        break;
                    default:
                        break;

                }

            }
        }
        return  string.toString();
    }*/


    //Modified version of APPiccio's code. It's neater
    public static String printWindowPanelContent(WindowPanel panel){
        StringBuilder result = new StringBuilder();
        if(panel == null){
            return "This WindowPanel is Null!\n";
        }else {

            result.append("Panel Name: " + panel.getPanelName() + "\n");
            ArrayList<Cell> cells = panel.getCells();
            int i = 0;
            for (Cell c:cells) {
                if(c.hasDiceOn()){
                    result.append("|"+c.getDiceOn().getColor()+"||"+c.getDiceOn().getValue()+"|\t");
                }else {
                    result.append("| NULL |\t");
                }
                if(i < StaticValues.PATTERN_COL - 1) i++;
                else {
                    result.append("\n");
                    i = 0;
                }
            }
        }
        return result.toString();
    }

    //Modified version of APPiccio's code. It's neater
    public static String printWindowPanelLayout(WindowPanel panel){
        StringBuilder result = new StringBuilder();
        if(panel == null){
            return "This WindowPanel is Null!\n";
        }else {

            result.append("Panel Name: " + panel.getPanelName() + "\n");
            ArrayList<Cell> cells = panel.getCells();
            int i = 0;
            for (Cell c:cells) {
                if(c.hasColorRestriction()){
                    result.append("| "+c.getColor()+"  |\t");
                }else if(c.hasValueRestriction()){
                    result.append("| "+c.getValue()+" |\t\t");
                }
                else {
                    result.append("| BLANK |\t");
                }
                if(i < StaticValues.PATTERN_COL - 1) i++;
                else {
                    result.append("\n");
                    i = 0;
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        WindowPanel panel = new WindowPanel(0,0);
        panel.addDiceOnCellWithIndex(0, new Dice());
        panel.addDiceOnCellWithIndex(1, new Dice());
        panel.addDiceOnCellWithIndex(2, new Dice());
        panel.addDiceOnCellWithIndex(3, new Dice());
        panel.addDiceOnCellWithIndex(4, new Dice());
        panel.addDiceOnCellWithIndex(5, new Dice());
        panel.addDiceOnCellWithIndex(6, new Dice());
        panel.addDiceOnCellWithIndex(7, new Dice());
        panel.addDiceOnCellWithIndex(8, new Dice());

        System.out.println(printWindowPanelContent(panel));


        panel = new WindowPanel(4,1);
        System.out.println(printWindowPanelLayout(panel));

    }

}
