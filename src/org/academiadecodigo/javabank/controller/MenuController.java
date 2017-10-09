package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.view.View;

import java.util.Map;

public class MenuController implements Controller {

    private Map<Integer, Controller> operationsMap;
    private View view;
    private Controller nextController;

    @Override
    public void init() {
        view.show();
    }

    public void setOperationsMap(Map<Integer, Controller> operationsMap) {
        this.operationsMap = operationsMap;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void onSelectingOperation(int userInput) {
        nextController = operationsMap.get(userInput);
        nextController.init();
    }
}
