package com.sagrada.ppp.network.server;


import com.sagrada.ppp.utils.StaticValues;

public class Server {

    public static void main(String[] args) {

        StaticValues.readConstants();
        Service service = Service.getInstance();

    }
}
