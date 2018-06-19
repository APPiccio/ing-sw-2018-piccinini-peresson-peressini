package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Dice;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetLegalPositionRequestTest {
    private Request getLegalPositionRequest;

    @Test
    public void handle() {
        Dice dice = new Dice();
        getLegalPositionRequest = new GetLegalPositionRequest(123,456,dice);
        RequestHandler requestHandler = new RequestHandler() {
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
                assertEquals(123,request.gameHashCode);
                assertEquals(456,request.playerHashCode);
                assertEquals(dice,request.dice);
                assertEquals(getLegalPositionRequest,request);
                return null;
            }

            @Override
            public Response handle(SpecialDicePlacementRequest request) {
                return null;
            }

            @Override
            public Response handle(PutDiceInDraftPoolRequest request) {
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
        };
        getLegalPositionRequest.handle(requestHandler);
    }
}