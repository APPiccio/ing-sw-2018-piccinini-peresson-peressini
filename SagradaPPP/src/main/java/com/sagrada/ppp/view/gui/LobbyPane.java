package com.sagrada.ppp.view.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class LobbyPane extends VBox implements EventHandler<MouseEvent> {

    private Button about;
    private Button play;
    private ArrayList<BusEventHandler> events;

    public LobbyPane() {

        events = new ArrayList<>();

        this.setAlignment(Pos.CENTER);
        this.setSpacing(10); //set space between nodes

        play = new Button();
        play.setText("Play");
        this.getChildren().add(play);
        play.addEventHandler(MouseEvent.MOUSE_CLICKED,this);

        about = new Button();
        about.setText("About");
        this.getChildren().add(about);
        about.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

    }

    @Override
    public void handle(MouseEvent event) {

        Button clickedBtn = (Button) event.getSource();

        if (clickedBtn.equals(about)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.setContentText("Sagrada by PPP");
            alert.showAndWait();
        } else if (clickedBtn.equals(play)) {
            for (BusEventHandler b : events) {
                b.onClose(this);
            }
        }
    }

    public void attach(BusEventHandler busEventHandler) {
        events.add(busEventHandler);
    }
}
