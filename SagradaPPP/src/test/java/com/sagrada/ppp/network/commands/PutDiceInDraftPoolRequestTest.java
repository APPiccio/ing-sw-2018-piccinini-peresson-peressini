package com.sagrada.ppp.network.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class PutDiceInDraftPoolRequestTest {

    PutDiceInDraftPoolRequest putDiceInDraftPoolRequest;

    @Test
    public void handle() {

        RequestHandler handler = new RequestHandler() {
            @Override
            public Response handle(Request request) {
                return null;
            }

            @Override
            public Response handle(JoinGameRequest request) {
                return null;
            }

            @Override
            public Response handle(LeaveGameRequest request) {
                return null;
            }

            @Override
            public Response handle(PanelChoiceRequest request) {
                return null;
            }

            @Override
            public Response handle(DisconnectionRequest request) {
                return null;
            }

            @Override
            public Response handle(PlaceDiceRequest request) {
                return null;
            }

            @Override
            public Response handle(EndTurnRequest request) {
                return null;
            }

            @Override
            public Response handle(DetachGameObserverRequest request) {
                return null;
            }

            @Override
            public Response handle(IsToolCardUsableRequest request) {
                return null;
            }

            @Override
            public Response handle(UseToolCardRequest request) {
                return null;
            }

            @Override
            public Response handle(GetLegalPositionRequest request) {
                return null;
            }

            @Override
            public Response handle(SpecialDicePlacementRequest request) {
                return null;
            }

            @Override
            public Response handle(PutDiceInDraftPoolRequest request) {
                assertEquals(putDiceInDraftPoolRequest, request);
                return null;
            }

            @Override
            public Response handle(ReconnectionRequest request) {
                return null;
            }

            @Override
            public Response handle(DisableAFKRequest request) {
                return null;
            }

            @Override
            public Response handle(ChangeConnectionRequest request) {
                return null;
            }
        };

        putDiceInDraftPoolRequest = new PutDiceInDraftPoolRequest(123, null);
        putDiceInDraftPoolRequest.handle(handler);
    }
}