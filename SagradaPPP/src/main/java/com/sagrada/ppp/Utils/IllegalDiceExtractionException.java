package com.sagrada.ppp.Utils;

public class IllegalDiceExtractionException extends Exception {

    public IllegalDiceExtractionException(int n) {
        super("Impossible to extract number of dices: " + n);
    }

}