package org.example.Controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;
import org.example.Controller.SignInController;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSending {
    public static void sendEmail(String recipientEmail, String subject, String text) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("namelessthe94@gmail.com", "nxognceslogwpmup");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("namelessthe94@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Email sent successfully...");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
    public static void sendVerificationEmail(String recipientEmail) {
        String senderEmail = "namelessthe94@gmail.com"; // Update with your email
        String senderPassword = "nxognceslogwpmup"; // Update with your password

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Email Verification");
            message.setText("Click the button below to verify your email:\n\n"
                    + "<a href=\"http://yourwebsite.com/verify\">Verify Email</a>");

            Transport.send(message);
            System.out.println("Verification email sent successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send verification email: " + e.getMessage());
        }
    }
}


/*
public class EmailSending {
    public static void sendEmail(String recipientEmail, String password) {
        // Replace with your actual sender email and password
        String senderEmail = "tooyoungtobegod@gmail.com";
                String senderPassword = "youba12369";


        // Email configuration properties
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create a session with the configured properties
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Your Password Recovery");
            message.setText("Your password is: " + password);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully...");
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSending {

    public static void sendEmail(String recipientEmail, String password) {

        String senderEmail = "namelessthe94@gmail.com";
        String senderPassword = "8llk&fGR12x)>v+E";

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Your Password Recovery");

            // Now set the actual message
            message.setText("Your password is: " + password);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}


 */