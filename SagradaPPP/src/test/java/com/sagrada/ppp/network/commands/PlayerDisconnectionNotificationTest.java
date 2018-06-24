package com.sagrada.ppp.network.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerDisconnectionNotificationTest {

    PlayerDisconnectionNotification playerDisconnectionNotification;

    @Test
    public void handle() {

        ResponseHandler handler = new ResponseHandler() {
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
                assertEquals(playerDisconnectionNotification, response);
            }

            @Override
            public void handle(PlayerAFKNotification response) {

            }
        };

        playerDisconnectionNotification = new PlayerDisconnectionNotification(null, true);
        playerDisconnectionNotification.handle(handler);
    }
}