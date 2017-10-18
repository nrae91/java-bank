package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        //try {

            //H2WebServer h2WebServer = new H2WebServer();
            //h2WebServer.start();

            //EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);
            //JpaSessionManager sm = new JpaSessionManager(emf);
            //TransactionManager tx = new JpaTransactionManager(sm);

            ApplicationContext context = new ClassPathXmlApplicationContext(
           "META-INF/spring-config.xml");

            //context.getBean("loginController", LoginController.class).init();

            //App app = new App();

            //app.bootStrap(tx, sm);

            //emf.close();
            //h2WebServer.stop();

        //} catch (SQLException ex) {
        //    ex.printStackTrace();
        //}
    }

    /*private void bootStrap(TransactionManager tx, JpaSessionManager sm) {

        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.setAccountDao(new JpaAccountDao(sm));
        accountService.setTransactionManager(tx);

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.setCustomerDao(new JpaCustomerDao(sm));
        customerService.setTransactionManager(tx);

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }*/
}
