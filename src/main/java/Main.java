import dao.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import model.utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static javafx.scene.paint.Color.TRANSPARENT;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LoginForm");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOpacity(0.95);
        primaryStage.show();

        //testConnetion();
        // testHiberante();
    }

    public static void main(String[] args) {
        launch(args);
    }



    public void testConnetion(){
        Connection con = DbConnection.getConnection();
        if(con == null){
            System.out.println("Connection Failed");
        }
        else{
            System.out.println("Connection Succeeded");
            String query = "select * from utilisateur";

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String name = rs.getString("nom");
                    System.out.println(name);
                }

            } catch (SQLException throwables) {

                throwables.printStackTrace();

            }
        }


        }

        public void testHiberante(){


            utilisateur user1 = new utilisateur();
            user1.setNom("rachid");
            user1.setPrenom("boufous");
            user1.setCin("UB97460");
            user1.setTel("+212633128978");
            user1.setMail("rachidboufous32@gmail.com");
            user1.setUsername("legion11");
            user1.setPassword("123456789");
            user1.setIs_admin(true);

            saveUser(user1);



        }

        public void saveUser(utilisateur user){
            Configuration conf =  new Configuration().configure("utils/hibernate.cfg.xml").addAnnotatedClass(utilisateur.class);
            SessionFactory sf = conf.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }
}
