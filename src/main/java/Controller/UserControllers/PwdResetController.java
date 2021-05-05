/*
 * Copyright (c) 2021.
 * programmed by Rachid Boufous.
 * for FRMFS-ltd organisation
 *
 */

package Controller.UserControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.utilisateur;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import java.util.Random;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class PwdResetController {

    @FXML
    private StackPane MainContainer;

    @FXML
    private AnchorPane win_pan;

    @FXML
    private JFXTextField emailResetField;

    @FXML
    private JFXButton ResetPwdBtn;


    @FXML
    private JFXTextField CinResetField;

    @FXML
    private FontAwesomeIconView closeBtn;



    @FXML
    void ResetPwdBtn_click(ActionEvent event) {
        String mail = emailResetField.getText();
        String cin = CinResetField.getText();

        if (!mail.isEmpty() || !cin.isEmpty()){

            try {

                String newPwd = String.valueOf(generatePassword(12));
                utilisateur user = getVerifiedUserMail(newPwd,mail,cin);
                SendMail(user,newPwd);

            }catch (IndexOutOfBoundsException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("USER does not exist");
                alert.setHeaderText("invalid Username or Password");
                alert.setContentText("try again!!");

                alert.showAndWait();
            }catch (Exception E){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(E.getCause().toString());
                alert.setHeaderText(E.getMessage().toString());
                alert.setContentText("try again!!");
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Fields Error");
            alert.setHeaderText("Mail or Cin are not supplied");
            alert.setContentText("fill in the fields and try again!!");

            alert.showAndWait();
        }




    }

    public void Exit_onClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserViews/LogIn.fxml"));
        Scene scene = ResetPwdBtn.getScene();

        root.translateYProperty().set(scene.getHeight());
        MainContainer.getChildren().add(root);

        Timeline tl = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        tl.getKeyFrames().add(kf);
        tl.setOnFinished(event1 -> {
            MainContainer.getChildren().remove(win_pan);
        });
        tl.play();

    }

    public utilisateur getVerifiedUserMail(String newPwd,String mail, String cin){


        UserService us = new UserService();

        //get user
        utilisateur verifieduser =  us.getUserByEmail(mail,cin);




        verifieduser.setPassword(newPwd);
        us.update(verifieduser);

        return verifieduser;
    }


    public void SendMail(utilisateur user,String pwd){

        // Recipient's email ID needs to be mentioned.
        String to = user.getMail();

        // Sender's email ID needs to be mentioned
        String from = "jhonvival214@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.starttls.enable", "true");


        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "eH1r9Ld6H7");

            }

        });

        SendMail(user, pwd, to, from, session);
    }

    private void SendMail(utilisateur user, String pwd, String to, String from, Session session) {
        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("APMS Password Reset!");

            // Now set the actual message
            String MailText = "Hello, " + user.getNom() + " " + user.getPrenom() +
                    ", Your password Has been set to: \"" + pwd + "\" based on a request on: " + GetCurrentTimeForamtted();
            message.setText(MailText);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("message sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    public String GetCurrentTimeForamtted(){
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return sdf3.format(timestamp);
    }
}
