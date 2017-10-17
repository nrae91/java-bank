package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.H2WebServer;
import org.academiadecodigo.javabank.persistence.dao.JpaAccountDao;
import org.academiadecodigo.javabank.persistence.dao.JpaCustomerDao;
import org.academiadecodigo.javabank.services.dao.DaoAccountService;
import org.academiadecodigo.javabank.services.dao.DaoCustomerService;
import org.academiadecodigo.javabank.services.jpa.*;
import org.academiadecodigo.javabank.services.AuthServiceImpl;

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
            TransactionManager tm = new JpaTransactionManager(sm);

            App app = new App();
            app.bootStrap(tm,sm);

            emf.close();
            h2WebServer.stop();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void bootStrap(TransactionManager tm, SessionManager sm) {

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new DaoAccountService(tm, new JpaAccountDao(sm)));
        bootstrap.setCustomerService(new DaoCustomerService(tm, new JpaCustomerDao(sm)));

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

    }
}
