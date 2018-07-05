package com.sagrada.ppp.network.server;


import com.sagrada.ppp.utils.StaticValues;

/**
 * Server application entry point
 */
public class Server {

    public static void main(String[] args) {

        StaticValues.readConstants();
        Service.getInstance();

    }
}
