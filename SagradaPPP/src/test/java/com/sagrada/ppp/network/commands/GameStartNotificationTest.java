package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.GameStartMessage;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStartNotificationTest {

    private Response gameStartNotification;
    @Test
    public void handle() {
        GameStartMessage gameStartMessage = new GameStartMessage(null,null,null,null,null,null);
        gameStartNotification = new GameStartNotification(gameStartMessage);

        ResponseHandler responseHandler = new ResponseHandler() {
            @Override
            public void handle(Response response) {

            }

            @Override
            public void handle(JoinGameResponse response) {

            }

            @Override
            public void handle(TimerNotification response) {

            }

            @Override
            public void handle(PlayerEventNotification response) {

            }

            @Override
            public void handle(LeaveGameResponse response) {

            }

            @Override
            public void handle(PanelChoiceNotification response) {

            }

            @Override
            public void handle(GameStartNotification response) {
                assertEquals(gameStartMessage,response.gameStartMessage);
                assertEquals(gameStartNotification,response);

            }

            @Override
            public void handle(DisconnectionResponse response) {

            }

            @Override
            public void handle(PlaceDiceResponse response) {

            }

            @Override
            public void handle(DicePlacedNotification response) {

            }

            @Override
            public void handle(EndTurnNotification response) {

            }

            @Override
            public void handle(EndGameNotification response) {

            }

            @Override
            public void handle(IsToolCardUsableResponse response) {

            }

            @Override
            public void handle(UseToolCardResponse response) {

            }

            @Override
            public void handle(GetLegalPositionResponse response) {

            }

            @Override
            public void handle(SpecialDicePlacementResponse response) {

            }

            @Override
            public void handle(UsedToolCardNotification response) {

            }

            @Override
            public void handle(ReconnectionResponse response) {

            }

            @Override
            public void handle(PlayerReconnectionNotification response) {

            }

            @Override
            public void handle(PlayerDisconnectionNotification response) {

            }

            @Override
            public void handle(PlayerAFKNotification response) {

            }
        };
        gameStartNotification.handle(responseHandler);
    }
}