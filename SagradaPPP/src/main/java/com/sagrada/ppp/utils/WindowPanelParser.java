package com.sagrada.ppp.utils;

import com.sagrada.ppp.model.Cell;
import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.WindowPanel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Parses all files in templates's folder into WindowPanel objects.
 */
public class WindowPanelParser {

    public static ArrayList<WindowPanel> getPanelsFromFile() throws IOException {
        ArrayList<WindowPanel> panels = new ArrayList<>();
        File folder = new File(StaticValues.TEMPLATES_URL);
        File[] files = folder.listFiles();
        if(files == null){
            throw new NullPointerException("No files inside the cards folder");
        }
        List<File> fileList = Arrays.stream(files).collect(Collectors.toList());
        fileList = fileList.stream().filter(x -> x.getName().matches("(panel)(\\d+)(.json)")).collect(Collectors.toList());

        for (int i = 1; i <= fileList.size();i++) {
            File fileEntry = new File(StaticValues.TEMPLATES_URL + "panel"+i+".json");
            if(!fileEntry.exists()){
                throw new FileNotFoundException("Error during pattern cards loading, check file naming!");
            }
            if (!fileEntry.isDirectory()) {


                JSONTokener jsonTokener = null;

                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(fileEntry);
                    jsonTokener = new JSONTokener(fileReader);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject = new JSONObject(jsonTokener);
                JSONArray jsonArrayCells = jsonObject.getJSONArray("cells");
                ArrayList<Cell> cells = new ArrayList<>();

                //getting card name and favor token from JSON
                int cardID = jsonObject.getInt("cardID");
                int favorTokens = jsonObject.getInt("favorTokens");
                String panelName = jsonObject.getString("name");


                String color;
                String value;

                for (Object jsonArrayCell : jsonArrayCells) {
                    JSONObject jsonCell = (JSONObject) jsonArrayCell;

                    color = jsonCell.get("color").toString();
                    value = jsonCell.get("value").toString();

                    if (!color.equals(StaticValues.NULL_JSON_VALUE)) {
                        //colored cell
                        cells.add(new Cell(Color.getColor(color)));

                    } else if (!value.equals(StaticValues.NULL_JSON_VALUE)) {
                        //value cell
                        cells.add(new Cell(Integer.parseInt(value)));
                    } else {
                        //blank cell
                        cells.add(new Cell());
                    }
                }
                WindowPanel panel = new WindowPanel(panelName, favorTokens, cardID, cells);
                panels.add(panel);
                fileReader.close();

            }

        }
        return panels;
    }
}

