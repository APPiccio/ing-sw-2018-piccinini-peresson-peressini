package com.sagrada.ppp.model;

import java.io.Serializable;

public class LeaveGameResult  implements Serializable {
    private int gameHashCode;
    private LeaveGameResultStatus status;

    public LeaveGameResult(int gameHashCode, LeaveGameResultStatus status){

        this.gameHashCode = gameHashCode;
        this.status = status;
    }



    public int getGameHashCode() {
        return gameHashCode;
    }

    public void setGameHashCode(int gameHashCode) {
        this.gameHashCode = gameHashCode;
    }

    public LeaveGameResultStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveGameResultStatus status) {
        this.status = status;
    }
}
