package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.MenuController;

public class MenuView implements View {

    private MenuController controller;
    private Prompt prompt;

    @Override
    public void show() {
        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);
        int userInput = prompt.getUserInput(mainMenu);
        controller.onSelectingOperation(userInput);
    }

    public void setController(MenuController controller) {
        this.controller = controller;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }
}
