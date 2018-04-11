package PPP;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class WindowPanel{

    private String panelName;
    private int favorTokens;
    private int cardID;
    private ArrayList<Cell> cells;

    //cardNumber from 1 to 12
    public WindowPanel(int cardNumber, int side) throws FileNotFoundException {

        int fileIndex = cardNumber * 2 - side;
        JSONTokener tokener = new JSONTokener(new FileReader("templates/panel"+ fileIndex + ".json"));
        JSONObject jsonObject = new JSONObject(tokener);
        JSONArray jsonArrayCells = jsonObject.getJSONArray("cells");
        cells = new ArrayList<>();

        //getting card name and favor token from JSON
        cardID = jsonObject.getInt("cardID");
        favorTokens = jsonObject.getInt("favorTokens");
        panelName = jsonObject.getString("name");



        for (Object jsonArrayCell : jsonArrayCells) {
            JSONObject jsonCell = (JSONObject) jsonArrayCell;
            if (jsonCell.get("color").toString().equals(StaticValues.NULL_JSON_VALUE)) {
                //color cell
                cells.add(new Cell(Color.getColor(jsonCell.get("color").toString())));

            } else if (jsonCell.get("value").toString().equals(StaticValues.NULL_JSON_VALUE)) {
                //value cell
                cells.add(new Cell(jsonCell.getInt("value")));
            } else {
                //white cell
                cells.add(new Cell());
            }


        }


    }


    public Cell getCellWithPosition(int row, int col){
        if ((row < 0 || row > StaticValues.PATTERN_ROW) || (col < 0 || col > StaticValues.PATTERN_COL)) {
            //access denied to wrong cells
            return null;
        }
        return cells.get(row*StaticValues.PATTERN_ROW + col);
    }

    public Cell getCellWithIndex(int i){
        if (i < 0 || i > cells.size()) return null;
        return cells.get(i);
    }

}
