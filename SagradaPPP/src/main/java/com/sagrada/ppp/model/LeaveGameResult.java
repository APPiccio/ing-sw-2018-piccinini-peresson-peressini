package com.sagrada.ppp.model;

import java.io.Serializable;

/**
 * Container class used to package stuff needed in a leave game response
 */
public class LeaveGameResult  implements Serializable {
    private int gameHashCode;
    private LeaveGameResultStatus status;

    /**
     * @param gameHashCode game ID
     * @param status status of the leave game request handling
     */
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
