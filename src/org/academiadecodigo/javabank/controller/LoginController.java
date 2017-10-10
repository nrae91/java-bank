package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AuthenticationService;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.Set;

public class LoginController extends AbstractController {

    private Controller nextController;
    private AuthenticationService authenticationService;

    public void onLogin(int id) {

        if(authenticationService.authenticate(id)) {
            nextController.init();
            return;
        }

        init();
    }

    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
