/*
 * Copyright (c) 2021 // programmed by Rachid Boufous
 */

package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainDao {

    private Session currentSession; // session to handle all database interaction
    private Transaction currentTransaction; // handle database action with transactions

    public MainDao() {
    }

    public Session openCurrentSession(){
        // create a session for normal database actions
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }


    public  Session openCurrentSessionWithTransaction(){
        // session for transaction database actions
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession(){
        currentSession.close();
    } // close session

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
        //close session and commit transaction to database
    }


    private static SessionFactory getSessionFactory(){
        /*
        Configuration conf =  new Configuration().configure("utils/hibernate.cfg.xml").addAnnotatedClass(utilisateur.class);
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

         */


        // this will manage the session open/close action and also create a session for each action

        Configuration configuration = new Configuration().configure("utils/hibernate.cfg.xml");
        // access the database conf (host, port, password, username)
        configuration.addAnnotatedClass(model.utilisateur.class);
        configuration.addAnnotatedClass(model.typetarif.class);
        configuration.addAnnotatedClass(model.client.class);
        configuration.addAnnotatedClass(model.abonnement.class);
        configuration.addAnnotatedClass(model.Vehicule.class);
        configuration.addAnnotatedClass(model.Stationnement.class);
        configuration.addAnnotatedClass(model.parking.class);



        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        // build the session factory to create new sessions
        return sessionFactory;

    }

    // getters and setters
    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

}
