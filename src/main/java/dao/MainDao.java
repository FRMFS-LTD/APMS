package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainDao {

    private Session currentSession;
    private Transaction currentTransaction;

    public MainDao() {
    }

    public Session openCurrentSession(){µ
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }


    public Session openCurrentSessionWithTransaction(){
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;

    }

    public void closeCurrentSession(){
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }


    private static SessionFactory getSessionFactory(){
        /*
        Configuration conf =  new Configuration().configure("utils/hibernate.cfg.xml").addAnnotatedClass(utilisateur.class);
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

         */

        Configuration configuration = new Configuration().configure("utils/hibernate.cfg.xml");
        configuration.addAnnotatedClass(model.utilisateur.class);
        configuration.addAnnotatedClass(model.typetarif.class);



        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

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
