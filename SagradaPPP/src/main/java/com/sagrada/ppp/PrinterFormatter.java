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
        string.append("\n|");

        for(int i = 0; i < StaticValues.PATTERN_COL; i++){
            string.append(String.format("%" + 13 + "s","|"));
        }
        string.append("\n|");

        for(int row = 0; row < StaticValues.PATTERN_ROW; row++) {
            string.append("|");
            for(int i = 0; i < 3; i++) {

                if(i == 0) {
                    for (int col = 0; col < StaticValues.PATTERN_COL; col++) {

                        string.append(String.format("%" + 13 + "s", "|"));
                    }
                    string.append("\n|");
                }
                else{
                    if(i == 1) {
                        for (int col = 0; col < StaticValues.PATTERN_COL; col++) {
                            Cell cell = panel.getCellWithPosition(row,col);
                            String h = " ";
                            if(cell.hasDiceOn()) {
                                h = "_";
                            }
                            string.append(String.format("%" + 3 + "s" + 10, h));
                        }
                        string.append("\n|");
                    }
                    else{

                    }
                }
            }
        }




        return  string.toString();
    }

    public static void main(String[] args) {
        System.out.println(printWindowPanel(new WindowPanel(0,0)));
    }

}
