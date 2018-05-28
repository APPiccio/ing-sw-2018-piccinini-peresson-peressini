package com.sagrada.ppp.view.gui;

import com.sagrada.ppp.model.Dice;
import com.sagrada.ppp.cards.publicobjectivecards.PublicObjectiveCard;
import com.sagrada.ppp.cards.toolcards.ToolCard;
import com.sagrada.ppp.controller.RemoteController;
import com.sagrada.ppp.model.*;
import com.sagrada.ppp.utils.StaticValues;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class MainGamePane extends UnicastRemoteObject implements GameObserver, WindowPanelEventBus {

    private RoundTrack roundTrack;
    private double height,widht = 100d;

    private GridPane mainGamePane;
    private VBox opponentsWindowPanelsPane;
    private HBox bottomContainer;
    private RoundTrackPane roundTrackPane;
    private FlowPane centerContainer;
    private VBox draftPoolContainer;
    private FlowPane draftPoolPane;
    private WindowPanelPane playerWindowPanel;
    private GridPane toolCardsContainer;
    private GridPane publicCardsContainer;
    private HBox topContainer;
    private Insets defInset;
    private TabPane tabContainer;
    private Button skipButton;
    private Tab gameTab,settingsTab,logTab;

    private String currentPlayerUser;
    private Stage stage;
    private RemoteController controller;
    private com.sagrada.ppp.model.Color privateColor;
    private JoinGameResult joinGameResult;
    private HashMap<String, WindowPanel> panels;
    private ArrayList<Dice> draftPool;
    private ArrayList<Player> players;
    private ArrayList<DiceButton> draftPoolDiceButtons;
    private ArrayList<Button> toolCardButtons,publicCardButtons;
    private ArrayList<ToolCard> toolCards;
    private ArrayList<PublicObjectiveCard> publicObjectiveCards;
    private EventHandler<MouseEvent> draftPoolDiceEventHandler;
    private EventHandler<MouseEvent> skipButtonEventHandler;
    private Label gameStatus;

    public MainGamePane() throws RemoteException {


        defInset = new Insets(5);
        tabContainer = new TabPane();
        gameTab = new Tab();
        settingsTab = new Tab();
        logTab = new Tab();
        mainGamePane = new GridPane();
        toolCardButtons = new ArrayList<>();
        publicCardButtons = new ArrayList<>();
        opponentsWindowPanelsPane = new VBox();
        bottomContainer = new HBox();
        roundTrackPane = new RoundTrackPane();
        centerContainer = new FlowPane();
        draftPoolContainer = new VBox();
        draftPoolPane = new FlowPane();
        playerWindowPanel = new WindowPanelPane(new WindowPanel(0,0),440,400);
        toolCardsContainer = new GridPane();
        publicCardsContainer = new GridPane();
        topContainer = new HBox();
        skipButton = new Button();
        draftPoolDiceButtons = new ArrayList<>();
        gameStatus = new Label();
        //creating all Listeners
        createListeners();

    }

    public void draw(){

        Label toolCardsTitle = new Label("Tool Cards");
        Label publicObjectiveCardsTitle = new Label("Public Objective Cards");
        toolCardsContainer.setVgap(5);
        toolCardsContainer.setHgap(5);
        toolCardsContainer.setPadding(defInset);
        toolCardsContainer.add(toolCardsTitle,0,0);
        publicCardsContainer.setVgap(5);
        publicCardsContainer.setHgap(5);
        publicCardsContainer.setPadding(defInset);
        publicCardsContainer.add(publicObjectiveCardsTitle,0,0,2,1);

        skipButton.setText("Skip Turn");
        skipButton.setPadding(defInset);
        skipButton.setDisable(true);
        skipButton.addEventHandler(MouseEvent.MOUSE_CLICKED,skipButtonEventHandler);
        VBox.setMargin(skipButton,defInset);
        skipButton.setAlignment(Pos.CENTER);


        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.NEVER);
        mainGamePane.getColumnConstraints().addAll(col1,col2);
        mainGamePane.add(opponentsWindowPanelsPane,1,0,1,3);
        drawToolCards();
        drawPublicObjectiveCards();

        topContainer.getChildren().addAll(toolCardsContainer,publicCardsContainer);
        topContainer.setAlignment(Pos.CENTER);
        GridPane.setHalignment(topContainer,HPos.CENTER);
        topContainer.setPadding(defInset);
        mainGamePane.add(topContainer,0,0,1,1);
        mainGamePane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,new CornerRadii(5),new Insets(0))));

        ImageView privateCardImageView = new ImageView();
        HBox.setMargin(privateCardImageView,defInset);
        HBox.setHgrow(privateCardImageView,Priority.ALWAYS);
        privateCardImageView.setImage(new Image(StaticValues.FILE_URI_PREFIX + "graphics/PrivateCards/private_"+privateColor.toString().toLowerCase()+".png",150,204,true,true));



        HBox.setMargin(gameStatus,defInset);
        bottomContainer.setAlignment(Pos.CENTER);
        GridPane.setHalignment(bottomContainer,HPos.CENTER);
        gameStatus.setFont(Font.font("Courier New",FontWeight.BOLD,20));
        gameStatus.setAlignment(Pos.BASELINE_LEFT);
        HBox.setHgrow(roundTrackPane,Priority.ALWAYS);
        bottomContainer.getChildren().addAll(gameStatus,privateCardImageView,roundTrackPane);
        bottomContainer.setPadding(defInset);
        mainGamePane.add(bottomContainer,0,2,1,1);

        Label draftPoolTitle = new Label("DraftPool");
        draftPoolTitle.setTextFill(Color.BLACK);
        draftPoolTitle.setAlignment(Pos.CENTER);

        draftPoolContainer.setAlignment(Pos.CENTER);
        draftPoolContainer.setPadding(defInset);
        draftPoolContainer.getChildren().addAll(draftPoolTitle, draftPoolPane);

        draftPoolPane.setHgap(2);
        draftPoolPane.setVgap(2);
        draftPoolPane.setPrefWrapLength(190);

        drawDraftPool();
        centerContainer.setBackground(
                new Background(
                    new BackgroundFill(
                        Color.DARKGREEN,
                        new CornerRadii(5),
                        Insets.EMPTY)));
        centerContainer.getChildren().add(draftPoolContainer);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(defInset);
        mainGamePane.add(centerContainer,0,1,1,1);

        opponentsWindowPanelsPane.setAlignment(Pos.CENTER);
        opponentsWindowPanelsPane.setSpacing(5);

        //drawing All Window Panels
        GridPane.setFillWidth(mainGamePane,true);
        GridPane.setFillWidth(centerContainer,true);
        centerContainer.getChildren().add(playerWindowPanel);
        playerWindowPanel.setObserver(this);
        HBox.setHgrow(playerWindowPanel,Priority.ALWAYS);
        HBox.setMargin(playerWindowPanel,defInset);
        drawWindowPanels();
        opponentsWindowPanelsPane.setPadding(defInset);


        //setting up all tabs
        gameTab.setContent(mainGamePane);
        gameTab.setText("Game Tab");
        gameTab.setClosable(false);

        settingsTab.setClosable(false);
        settingsTab.setText("Settings Tab");

        logTab.setClosable(false);
        logTab.setText("LogTab");

        tabContainer.getTabs().addAll(gameTab,settingsTab,logTab);

        Scene scene = new Scene(tabContainer, 700, 1270);
        stage.setScene(scene);
        stage.setTitle("Main game");
        stage.setResizable(true);
        stage.show();
        if (currentPlayerUser.equals(joinGameResult.getUsername())){
            skipButton.setDisable(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"It's your turn!");
            alert.setHeaderText(null);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.show();
        }

    }

    public void init(com.sagrada.ppp.model.Color privateColor, JoinGameResult joinGameResult, GameStartMessage gameStartMessage, RemoteController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        this.privateColor = privateColor;
        this.joinGameResult = joinGameResult;
        this.panels = gameStartMessage.chosenPanels;
        this.draftPool = gameStartMessage.draftpool;
        this.toolCards = gameStartMessage.toolCards;
        this.players = gameStartMessage.players;
        this.publicObjectiveCards = gameStartMessage.publicObjectiveCards;
        this.currentPlayerUser = gameStartMessage.players.get(0).getUsername();
        try {
            this.controller.attachGameObserver(this.joinGameResult.getGameHashCode(),this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        draw();
    }

    private void drawWindowPanels(){
        opponentsWindowPanelsPane.getChildren().clear();
        opponentsWindowPanelsPane.getChildren().add(new Label("OpponentsPanels:"));
        for (Player player : players) {
            if (player.getUsername().equals(joinGameResult.getUsername())) {
                if(currentPlayerUser.equals(player.getUsername())) {
                    gameStatus.setText("User: " + player.getUsername() + "\n>>>Favor Tokens Remaining: " + player.getFavorTokens() + "\n>>>It's your turn!");
                }else {
                    gameStatus.setText("User: " + player.getUsername() + "\n>>>Favor Tokens Remaining: " + player.getFavorTokens() + "\n>>>Wait your turn");
                }
                if(playerWindowPanel == null) {
                    playerWindowPanel = new WindowPanelPane(player.getPanel(), 330, 300);

                }else {
                    playerWindowPanel.setPanel(player.getPanel());
                }
            }else {
                Label username = new Label("#" + players.indexOf(player) + " " + player.getUsername() +"\t Remaining Tokens : " + player.getFavorTokens() );
                username.setTextFill(Color.BLACK);
                username.setAlignment(Pos.CENTER);
                opponentsWindowPanelsPane.getChildren().add(username);
                opponentsWindowPanelsPane.getChildren().add(new WindowPanelPane(player.getPanel(),200,170));
            }
        }
        opponentsWindowPanelsPane.getChildren().add(skipButton);

    }
    private void drawDraftPool(){
        draftPoolDiceButtons.clear();
        draftPoolPane.getChildren().clear();

        int index = 0;
        for (Dice dice:draftPool) {
            DiceButton diceButton = new DiceButton(dice,70,70);
            diceButton.setIndex(index);
            FlowPane.setMargin(diceButton,new Insets(10));
            diceButton.addEventHandler(MouseEvent.MOUSE_CLICKED, draftPoolDiceEventHandler);
            draftPoolDiceButtons.add(diceButton);
            index++;
        }
        draftPoolPane.getChildren().addAll(draftPoolDiceButtons);
    }
    private void drawToolCards(){
        int count = 0;
        for(ToolCard toolCard : toolCards){
            Button toolCardButton = new Button();
            toolCardButtons.add(toolCardButton);
            toolCardButton.setId(Integer.toString(toolCard.getId()));
            toolCardButton.setMinSize(150,204);
            toolCardButton.setBackground(
                    new Background(
                            new BackgroundImage(
                                    new Image(StaticValues.FILE_URI_PREFIX + "graphics/ToolCards/tool_"+toolCard.getId()+".png",150,204,true,true),
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.CENTER,
                                    BackgroundSize.DEFAULT
                            )
                    )
            );
            toolCardsContainer.add(toolCardButton,count,1);
            count++;
        }
    }
    private void drawPublicObjectiveCards(){
        int count = 0;
        for(PublicObjectiveCard publicObjectiveCard : publicObjectiveCards){
            Button publicObjectiveButton = new Button();
            publicCardButtons.add(publicObjectiveButton);
            publicObjectiveButton.setId(Integer.toString(publicObjectiveCard.getId()));
            publicObjectiveButton.setMinSize(150,204);
            publicObjectiveButton.setBackground(
                    new Background(
                            new BackgroundImage(
                                    new Image(StaticValues.FILE_URI_PREFIX + "graphics/PublicCards/public_"+publicObjectiveCard.getId()+".png",150,204,true,true),
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.CENTER,
                                    BackgroundSize.DEFAULT
                            )
                    )
            );
            publicCardsContainer.add(publicObjectiveButton,count,1);
            count++;
        }
    }
    private void createListeners(){
        draftPoolDiceEventHandler = event -> {
            DiceButton clickedButton = ((DiceButton) event.getSource());
            if (clickedButton.isSelected()) {
                clickedButton.setSelected(false);
                clickedButton.setScaleY(1);
                clickedButton.setScaleX(1);
            }else {
                for (DiceButton diceButton : draftPoolDiceButtons) {
                    if (diceButton.isSelected()) {
                        diceButton.setSelected(false);
                        diceButton.setScaleX(1);
                        diceButton.setScaleY(1);
                    }
                }
                clickedButton.setScaleX(1.2);
                clickedButton.setScaleY(1.2);
                clickedButton.setSelected(true);
            }

        };
        skipButtonEventHandler = event -> {
            try {
                skipButton.setDisable(true);
                controller.endTurn(joinGameResult.getGameHashCode(),joinGameResult.getPlayerHashCode());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void onPanelChoice(int playerHashCode, ArrayList<WindowPanel> panels, HashMap<String, WindowPanel> panelsAlreadyChosen, com.sagrada.ppp.model.Color playerPrivateColor) throws RemoteException {
            //Do nothing here
    }





    @Override
    public void onCellClicked(int x,int y) {
        DiceButton diceButtonSelected = draftPoolDiceButtons.stream().filter(DiceButton::isSelected).findFirst().orElse(null);
        if(diceButtonSelected != null){
            Platform.runLater(()-> {
                        try {
                            PlaceDiceResult result = controller.placeDice(joinGameResult.getGameHashCode(),joinGameResult.getPlayerHashCode(),draftPoolDiceButtons.indexOf(diceButtonSelected),y,x);
                            if(result.status){
                                playerWindowPanel.setPanel(result.panel);
                                draftPool.remove(draftPoolDiceButtons.indexOf(diceButtonSelected));
                                drawDraftPool();
                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR,result.message);
                                alert.setHeaderText(null);
                                alert.initModality(Modality.APPLICATION_MODAL);
                                alert.initOwner(stage);
                                alert.showAndWait();
                            }

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
            );

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please Select a dice and THEN click on a panel cell!");
            alert.setHeaderText(null);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.showAndWait();
        }

    }

    @Override
    public void onDiceClicked(DiceButton diceButton, Dice dice) {

    }

    @Override
    public void onGameStart(GameStartMessage gameStartMessage) throws RemoteException {

    }

    @Override
    public void onDicePlaced(DicePlacedMessage dicePlacedMessage) throws RemoteException {
        Platform.runLater(()->{
            if(!dicePlacedMessage.username.equals(joinGameResult.getUsername())) {
                draftPool = dicePlacedMessage.draftPool;
                players.stream().filter(x -> x.getUsername().equals(dicePlacedMessage.username)).findFirst().orElse(null).setPanel(dicePlacedMessage.panel);
                drawDraftPool();
                drawWindowPanels();
            }
        });

    }

    @Override
    public void onEndTurn(EndTurnMessage endTurnMessage) throws RemoteException {
       Platform.runLater(()->{
            roundTrack = endTurnMessage.roundTrack;
            roundTrackPane.setRoundTrack(roundTrack);
            draftPool = endTurnMessage.draftpool;
            currentPlayerUser = endTurnMessage.currentPlayer.getUsername();
            players = endTurnMessage.players;
            drawDraftPool();
            drawWindowPanels();

            if(endTurnMessage.currentPlayer.getUsername().equals(joinGameResult.getUsername())){
                skipButton.setDisable(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"It's your turn!");
                alert.setHeaderText(null);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                alert.show();
            }else if(endTurnMessage.previousPlayer.getUsername().equals(joinGameResult.getUsername())){
                skipButton.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Your turn is ended!");
                alert.setHeaderText(null);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                alert.show();
            }
        });
    }

    @Override
    public void onEndGame(ArrayList<PlayerScore> playersScore) throws RemoteException {
        Platform.runLater(() -> {
            try {
                new ResultPane(playersScore, publicObjectiveCards, controller, stage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
