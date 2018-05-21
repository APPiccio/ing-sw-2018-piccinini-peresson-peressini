package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.Dice;
import com.sagrada.ppp.GameStartMessage;
import com.sagrada.ppp.WindowPanel;
import com.sagrada.ppp.cards.PublicObjectiveCard;
import com.sagrada.ppp.cards.ToolCards.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameStartNotification implements Response, Serializable {

    public GameStartMessage gameStartMessage;

    public GameStartNotification(GameStartMessage gameStartMessage){
        this.gameStartMessage = gameStartMessage;
    }

    @Override
    public void handle(ResponseHandler handler) {
        handler.handle(this);
    }
}
