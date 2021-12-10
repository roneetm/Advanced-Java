package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;

public class MailAuth {

    public static void sendEmail() throws MessagingException {

        String message = "Hello, thank you for signing up at Mike's Web App";
        String subject = "Welcome to Mike";
        String to = "michaelroneet@gmail.com";
        String from = "roneet.michael@hotwaxsystems.com";

        // Variable for Gmail Host
        String host = "smtp.gmail.com";
        // Get the system properties
        Properties properties =  System.getProperties();
        System.out.println(properties);
        // Setting important information to properties object
        // Setting Host
        properties.put("mail.smtp.host", host); // Host Key Value, Pair.
        properties.put("mail.smtp.port", 465); // Port Key Value, Pair.
        properties.put("mail.smtp.ssl.enable", true); // SSL Enable Key Value, Pair.
        properties.put("mail.smtp.auth", true); // Auth Enable Key Value, Pair.

        // 1. Getting Session Object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                ResourceBundle rd
                        = ResourceBundle.getBundle("mail");
                String userName = rd.getString("userName");
                String password = rd.getString("password");
                return new PasswordAuthentication(userName, password);
            }
        });
        session.setDebug(true); // Will help in showing if there are any errors

        // 2. Composing the email
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

        } catch (Exception e){
            e.printStackTrace();
        }

        // 3 Sending Message through Transport class
        Transport.send(mimeMessage);
        System.out.println("Sent Successfully");
    }
}
