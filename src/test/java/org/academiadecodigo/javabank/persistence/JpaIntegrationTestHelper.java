package org.academiadecodigo.javabank.persistence;

import org.academiadecodigo.javabank.Config;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

public class JpaIntegrationTestHelper {

    @PersistenceContext
    protected EntityManager em;
    protected EntityManagerFactory emf;

    @Before
    public void init() {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("test");
        ctx.load(Config.SPRING_CONFIG);
        ctx.refresh();

        emf = ctx.getBean(EntityManagerFactory.class);
        em = emf.createEntityManager();

    }

    @After
    public void tearDown() {

        if (em != null) {
            em.close();
        }

        if (emf != null) {
            emf.close();
        }
    }
}
