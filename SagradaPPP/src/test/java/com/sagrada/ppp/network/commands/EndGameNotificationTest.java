package com.sagrada.ppp.network.commands;

import com.sagrada.ppp.model.Color;
import com.sagrada.ppp.model.PlayerScore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EndGameNotificationTest {

    private Response endGameNotification;
    @Test
    public void handle() {
        ArrayList<PlayerScore> playerScores = new ArrayList<PlayerScore>(){{
            add(new PlayerScore("1",1,Color.GREEN,null,1,
                    1,1,1,1,1));
        }};
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

                assertEquals(playerScores,response.playersScore);
                assertEquals(endGameNotification, response);
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
        endGameNotification = new EndGameNotification(playerScores);
        endGameNotification.handle(responseHandler);
    }
}