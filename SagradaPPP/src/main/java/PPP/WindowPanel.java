package PPP;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class WindowPanel {
    public static void main(String[] args) throws FileNotFoundException {
        new WindowPanel(1,StaticValues.FRONT_SIDE);

    }

    private ArrayList<Cell> windowPanel;

    //cardNumber from 1 to 12
    public WindowPanel(int cardNumber, int side) throws FileNotFoundException {

        int fileIndex = cardNumber * 2 - side;
        JSONTokener tokener = new JSONTokener(new FileReader("templates/panel"+ fileIndex + ".json"));
        JSONObject jsonObject = new JSONObject(tokener);
        JSONArray jsonArrayCells = jsonObject.getJSONArray("cells");
        windowPanel = new ArrayList<>();


        for (Object jsonArrayCell : jsonArrayCells) {
            JSONObject jsonCell = (JSONObject) jsonArrayCell;
            if (jsonCell.get("color").toString().equals(StaticValues.NULL_JSON_VALUE)) {
                //color cell
                windowPanel.add(new Cell(Color.getColor(jsonCell.get("color").toString())));

            } else if (jsonCell.get("value").toString().equals(StaticValues.NULL_JSON_VALUE)) {
                //value cell
                windowPanel.add(new Cell(jsonCell.getInt("value")));
            } else {
                //white cell
                windowPanel.add(new Cell());
            }


        }


    }
}
