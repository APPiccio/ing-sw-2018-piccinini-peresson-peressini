package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.PlaceDiceResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaceDiceResponseTest {

    PlaceDiceResponse placeDiceResponse;

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
                assertEquals(placeDiceResponse, response);
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
        placeDiceResponse = new PlaceDiceResponse(new PlaceDiceResult("ciao", true, null, null));
        placeDiceResponse.handle(handler);
    }
}