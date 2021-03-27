package Controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.utilisateur;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.List;

public class LogIn {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXPasswordField passwordField;




    String userName;
    String password;

    @FXML
    public void loginClick(ActionEvent event) {
        System.out.println("hello world");
        setControllerVal();
    }

    public void setControllerVal(){
        userName = usernameField.getText().toString();
        password = passwordField.getText().toString();
        System.out.println("username: "+ userName + "\npassword: " + password);
        fetch_data();
    }

    void fetch_data(){
        Configuration conf =  new Configuration().configure("utils/hibernate.cfg.xml").addAnnotatedClass(utilisateur.class);
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        String sql = "SELECT * FROM utilisateur WHERE username = :username";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(utilisateur.class);
        query.setParameter("username", "legion11");
        ArrayList<utilisateur> results = (ArrayList<utilisateur>) query.list();
        tx.commit();
        System.out.println(results.get(0).getUsername());
        System.out.println(results.get(0).getPassword());
    }




}
