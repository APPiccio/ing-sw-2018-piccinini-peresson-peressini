package PPP;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class WindowPanel{


    private ArrayList<Cell> cells;

    //cardNumber from 1 to 12
    public WindowPanel(int cardNumber, int side) throws FileNotFoundException {

        int fileIndex = cardNumber * 2 - side;
        JSONTokener tokener = new JSONTokener(new FileReader("templates/panel"+ fileIndex + ".json"));
        JSONObject jsonObject = new JSONObject(tokener);
        JSONArray jsonArrayCells = jsonObject.getJSONArray("cells");
        cells = new ArrayList<>();


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
}
