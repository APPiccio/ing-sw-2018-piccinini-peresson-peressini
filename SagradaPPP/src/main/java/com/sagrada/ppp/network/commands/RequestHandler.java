package com.sagrada.ppp.network.commands;

public interface RequestHandler {

    Response handle(Request request);
    Response handle(JoinGameRequest request);
    Response handle(LeaveGameRequest request);
    Response handle(PanelChoiceRequest request);
    Response handle(DisconnectionRequest request);
    Response handle(PlaceDiceRequest request);
    Response handle(EndTurnRequest request);
    Response handle(DetachGameObserverRequest request);
    Response handle(IsToolCardUsableRequest request);
    Response handle(UseToolCardRequest request);
    Response handle(GetLegalPositionRequest request);
    Response handle(SpecialDicePlacementRequest request);
    Response handle(PutDiceInDraftPoolRequest request);
    Response handle(ReconnectionRequest request);
    Response handle(DisableAFKRequest request);
    Response handle(ChangeConnectionRequest request);
}
