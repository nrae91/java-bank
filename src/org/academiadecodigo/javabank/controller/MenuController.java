package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.view.View;

import java.util.Map;

public class MenuController implements Controller {

    private Map<Integer, Controller> controllerMap;
    private View view;

    @Override
    public void init() {
        view.show();
    }

    public void setControllerMap(Map<Integer, Controller> controllerMap) {
        this.controllerMap = controllerMap;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void onSelectingOperation(int userInput) {
        controllerMap.get(userInput).init();
    }
}
