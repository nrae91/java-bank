package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private View view;
    private AuthService authService;
    private MainController mainController;

    @Before
    public void setupUp(){
        loginController = new LoginController();
        mainController = new MainController();
        view = mock(View.class);
        authService = mock(AuthService.class);
        mainController = mock(MainController.class);
        loginController.setView(view);
        loginController.setAuthService(authService);
        loginController.setNextController(mainController);
    }

    @Test
    public void initTest() {
        loginController.init();

        verify(view).show();
    }

    @Test
    public void onLoginTestSuccess() {

        int validId = 112341412;

        when(authService.authenticate(validId)).thenReturn(true);

        loginController.onLogin(validId);

        verify(authService).authenticate(validId);
        verify(mainController).init();
        verify(view,never()).show();

    }

    @Test
    public void onLoginTestFail() {

        int invalidId = 612312312;

        when(authService.authenticate(anyInt())).thenReturn(false);

        loginController.onLogin(invalidId);

        verify(authService).authenticate(invalidId);
        verify(mainController,never()).init();
        verify(view).show();

    }
}
