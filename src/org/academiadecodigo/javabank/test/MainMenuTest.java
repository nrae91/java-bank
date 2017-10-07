package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.application.Action;
import org.academiadecodigo.javabank.application.Deposit;
import org.academiadecodigo.javabank.application.MainMenu;

public class MainMenuTest {

    public boolean test(){

        MainMenu mainMenu = new MainMenu();

        //Should return Action corresponding to users selection
        Action action = new Deposit();
        Action action1 = mainMenu.open();

        if(!(action1.equals(action))){
            return false;
        }

        return true;
    }
}
