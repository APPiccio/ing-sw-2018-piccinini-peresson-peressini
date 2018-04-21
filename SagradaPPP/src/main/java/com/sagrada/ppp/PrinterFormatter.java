package com.sagrada.ppp;
public class PrinterFormatter {

    private final static String bottomCell = "------------";

    public static String printWindowPanel(WindowPanel panel){
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
    }

    public static void main(String[] args) {
        WindowPanel panel = new WindowPanel(0,0);
        panel.addDiceOnCellWithIndex(0, new Dice());
        panel.addDiceOnCellWithIndex(1, new Dice());
        System.out.println(printWindowPanel(panel));

    }

}
