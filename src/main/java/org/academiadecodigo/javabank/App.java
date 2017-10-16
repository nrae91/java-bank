package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.dao.GenericDao;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.services.jpa.SessionManager;
import org.academiadecodigo.javabank.services.mock.MockAccountService;
import org.h2.tools.Server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        try {


            H2WebServer h2WebServer = new H2WebServer();
            h2WebServer.start();

            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);

            SessionManager sm = new JpaSessionManager(emf);

            App app = new App();
            app.bootStrap(sm);

            emf.close();
            h2WebServer.stop();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void bootStrap(SessionManager sm) {

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new JpaAccountService(sm), new AccountDao());
        bootstrap.setCustomerService(new JpaCustomerService(sm), new CustomerDao());

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

    }
}
