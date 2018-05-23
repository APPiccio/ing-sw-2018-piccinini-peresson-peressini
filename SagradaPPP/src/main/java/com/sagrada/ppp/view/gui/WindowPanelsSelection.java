package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.*;
import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.ToolCards.ToolCard;
import com.sagrada.ppp.controller.RemoteController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class WindowPanelsSelection extends UnicastRemoteObject implements GameObserver, EventHandler<MouseEvent> {

    private transient RemoteController controller;
    private Stage stage;
    private HBox hBox1;
    private HBox hBox2;
    private VBox vBoxEvents;
    private Color privateColor;
    private static JoinGameResult joinGameResult;
    private ArrayList<WindowPanel> panelAvailable;
    private ArrayList<Button> buttons;
    private boolean receivedMyPanels;
    private boolean userHasChosen;
    private static final String SELECT = "Select";

    WindowPanelsSelection() throws RemoteException {
        this.hBox2 = new HBox();
        this.hBox1 = new HBox();
        this.vBoxEvents = new VBox();
    }

    void init(RemoteController controller, Stage stage, JoinGameResult joinGameResult) {
        receivedMyPanels = false;
        userHasChosen = false;
        this.controller = controller;
        this.stage = stage;
        WindowPanelsSelection.joinGameResult = new JoinGameResult(joinGameResult);

        hBox1.setSpacing(10);
        hBox2.setSpacing(10);
        hBox1.getChildren().add(new Label("Waiting for the other players... " +
                "You can see their choices on the \"Opponents' panels\" tab!"));

        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab panelsTab = new Tab();
        Tab eventsTab = new Tab();
        VBox vBoxPanels = new VBox();

        panelsTab.setText("Panels");
        panelsTab.setClosable(false);
        vBoxPanels.setSpacing(5);
        vBoxPanels.setPadding(new Insets(10, 10, 0, 10));
        vBoxPanels.getChildren().add(hBox1);
        vBoxPanels.getChildren().add(hBox2);
        panelsTab.setContent(vBoxPanels);
        tabPane.getTabs().add(panelsTab);

        eventsTab.setText("Opponents' panels");
        eventsTab.setClosable(false);
        vBoxEvents.setSpacing(5);
        vBoxEvents.setPadding(new Insets(10, 0, 0, 10));
        vBoxEvents.setFillWidth(false);
        vBoxEvents.setAlignment(Pos.TOP_CENTER);
        vBoxEvents.getChildren().add(new Label("Waiting for the first player's move..."));
        eventsTab.setContent(vBoxEvents);
        tabPane.getTabs().add(eventsTab);

        borderPane.setCenter(tabPane);

        this.stage.setScene(new Scene(borderPane, 625, 670));
        this.stage.setTitle("Panel selection");
        this.stage.show();
    }

    private void createSelection() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Private color");
        alert.setHeaderText(null);
        alert.setContentText("This is your Private Objective Card!\nChose your panel carefully!");
        Image image = new Image("file:graphics/PrivateCards/private_" + privateColor + ".png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(230);
        imageView.setFitHeight(313);
        imageView.setPreserveRatio(true);
        alert.setGraphic(imageView);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.show();

        hBox1.getChildren().clear();
        ArrayList<VBox> selectionPanels = new ArrayList<>();
        buttons = new ArrayList<>();

        hBox1.getChildren().clear();
        hBox2.getChildren().clear();
        for (int i = 0; i < panelAvailable.size(); i++) {
            VBox vBox = new VBox();
            vBox.setSpacing(5);
            vBox.setPadding(new Insets(10, 0, 0, 0));
            vBox.setAlignment(Pos.CENTER);
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

    private void chosenPanels(HashMap<String, WindowPanel> panels) {
        vBoxEvents.getChildren().clear();
        for (String u : panels.keySet()) {
            vBoxEvents.getChildren().add(new Label(u + " has chosen \"" + panels.get(u).getPanelName() + "\"!"));
            vBoxEvents.getChildren().add(new WindowPanelPane(new WindowPanel(panels.get(u)), 175, 175));
        }
    }

    @Override
    public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels,
                              HashMap<String, WindowPanel> panelsAlreadyChosen, Color color) {


        Platform.runLater(() -> {
            panelAvailable = panels;
            if (panelsAlreadyChosen.size() != 0) {
                chosenPanels(panelsAlreadyChosen);
            }
            if(!userHasChosen && receivedMyPanels){
                        //disable buttons due to exceeded timer
                        //auto panel assignment to panel 0
                        disableButtons();
                        showAlertTimeout();
            }
            if (joinGameResult.getPlayerHashCode() == playerHashCode) {
                receivedMyPanels = true;
                privateColor = color;
                createSelection();
            }
        }
        );
    }

    @Override
    public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {
        Platform.runLater(() -> {
                    if(!userHasChosen){
                        disableButtons();
                        showAlertTimeout();
                    }
                    MainGamePane mainGamePane = null;
                    try {
                        mainGamePane = new MainGamePane();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    mainGamePane.init(privateColor, joinGameResult, gameStartMessage, controller, stage);
        }
        );
    }

    @Override
    public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
        //do nothing
    }


    private void disableButtons(){
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    @Override
    public void handle(MouseEvent event) {
        Button clickedBtn = (Button) event.getSource();
        disableButtons();
        int gameHash = joinGameResult.getGameHashCode();
        int playerHash = joinGameResult.getPlayerHashCode();
        userHasChosen = true;
        //TODO: change this with more elegant code. use -> index of
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

    public void showAlertTimeout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Panel choice timer timeout");
        alert.setHeaderText(null);
        alert.setContentText("Timer timeout, skip choice");
        alert.show();
    }

    @Override
    public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {

    }

    @Override
    public void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {

    }
}