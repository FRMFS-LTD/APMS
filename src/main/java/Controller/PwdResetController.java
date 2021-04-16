package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.utilisateur;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Properties;

import java.util.Random;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PwdResetController {

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
        String newPwd = String.valueOf(generatePassword(12));
        utilisateur user = getVerifiedUserMail(newPwd);
        SendMail(user,newPwd);
    }

    public void Exit_onClick(javafx.scene.input.MouseEvent mouseEvent) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public utilisateur getVerifiedUserMail(String newPwd){
        UserService us = new UserService();

        //get user
        utilisateur verifieduser =  us.getUserByEmail(emailResetField.getText(),CinResetField.getText());


        // update userpwd

        verifieduser.setPassword(newPwd);
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
            System.out.println("Sent message successfully....");
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
