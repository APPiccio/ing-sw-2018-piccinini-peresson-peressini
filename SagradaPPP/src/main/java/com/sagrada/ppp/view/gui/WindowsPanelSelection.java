package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.*;
import com.sagrada.ppp.controller.RemoteController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class WindowsPanelSelection extends UnicastRemoteObject implements GameObserver, EventHandler<MouseEvent> {

    private RemoteController controller;
    private Stage stage;
    private HBox hBox1;
    private HBox hBox2;
    private VBox vBoxEvents;
    private Color privateColor;
    private JoinGameResult joinGameResult;
    private ArrayList<WindowPanel> panelAvailable;
    private ArrayList<Button> buttons;
    private static final String SELECT = "Select";

    public WindowsPanelSelection() throws RemoteException {
        super();
    }

    public void init(RemoteController controller, Stage stage, JoinGameResult joinGameResult) {

        this.controller = controller;
        this.stage = stage;
        this.joinGameResult = joinGameResult;
        this.hBox1 = new HBox();
        this.hBox2 = new HBox();
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab panelsTab = new Tab();
        Tab eventsTab = new Tab();
        VBox vBoxPanels = new VBox();
        vBoxEvents = new VBox();

        panelsTab.setText("Panels");
        panelsTab.setClosable(false);
        vBoxPanels.setSpacing(5);
        vBoxPanels.setPadding(new Insets(10, 0, 0, 10));
        vBoxPanels.getChildren().add(hBox1);
        vBoxPanels.getChildren().add(hBox2);
        panelsTab.setContent(vBoxPanels);
        tabPane.getTabs().add(panelsTab);

        eventsTab.setText("Log");
        eventsTab.setClosable(false);
        vBoxEvents.setSpacing(5);
        vBoxEvents.setPadding(new Insets(10, 0, 0, 10));
        eventsTab.setContent(vBoxEvents);
        tabPane.getTabs().add(eventsTab);

        borderPane.setCenter(tabPane);

        stage.setScene(new Scene(borderPane, 600, 700));
        stage.setTitle("Panel selection window");
        stage.show();

    }

    public void createSelection() {

        ArrayList<VBox> selectionPanels = new ArrayList<>();
        buttons = new ArrayList<>();

        for (int i = 0; i < panelAvailable.size(); i++) {
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            vBox.setPadding(new Insets(10, 0, 0, 10));
            selectionPanels.add(vBox);
            if (i == 0 || i == 1) {
                hBox1.getChildren().add(selectionPanels.get(i));
            }
            else {
                hBox2.getChildren().add(selectionPanels.get(i));
            }
            selectionPanels.get(i).getChildren().add(new WindowPanelPane(panelAvailable.get(i), 200, 200));
            buttons.add(new Button(SELECT));
            buttons.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            selectionPanels.get(i).getChildren().add(buttons.get(i));
        }

    }

    public void chosenPanels(HashMap<String, WindowPanel> panels) {
        for (String u : panels.keySet()) {
            vBoxEvents.getChildren().add(new Label(u + " has chosen panel " + panels.get(u).getPanelName() + " !"));
        }
    }

    @Override
    public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels,
                              HashMap<String, WindowPanel> panelsAlreadyChosen, Color color) {
        privateColor = color;
        panelAvailable = panels;
        Platform.runLater(() -> {
            if (panelsAlreadyChosen.size() != 0) {
                chosenPanels(panelsAlreadyChosen);
            }
            if (joinGameResult.getPlayerHashCode() == playerHashCode) {
                createSelection();
            }
        }
        );
    }

    @Override
    public void onGameStart(HashMap<String, WindowPanel> chosenPanels, ArrayList<Dice> draftpool) throws RemoteException {
        Platform.runLater(() -> {
                    MainGamePane mainGamePane = new MainGamePane();
                    mainGamePane.init(privateColor, joinGameResult, chosenPanels, draftpool, controller, stage);
        }
        );
    }

    @Override
    public void handle(MouseEvent event) {
        Button clickedBtn = (Button) event.getSource();
        for (Button button : buttons) {
            button.setDisable(true);
        }
        int gameHash = joinGameResult.getGameHashCode();
        int playerHash = joinGameResult.getPlayerHashCode();
        try {
            if (clickedBtn.equals(buttons.get(0))) {
                controller.choosePanel(gameHash, playerHash, 0);
            }
            else if (clickedBtn.equals(buttons.get(1))) {
                controller.choosePanel(gameHash, playerHash, 1);
            }
            else if (clickedBtn.equals(buttons.get(2))) {
                controller.choosePanel(gameHash, playerHash, 2);
            }
            else if (clickedBtn.equals(buttons.get(3))) {
                controller.choosePanel(gameHash, playerHash, 3);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}