package com.sagrada.ppp.network.client;

import com.sagrada.ppp.controller.Controller;
import com.sagrada.ppp.controller.RemoteController;

public interface ConnectionMode {
    RemoteController getController();
}
