package org.learnemail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        String message = "This is a test email";
        String subject = "Testing";
        String to = ""; // Replace with recipient's email
        String from = ""; // Replace with your Outlook.com email address
        String username = ""; // Replace with your Outlook.com email address
        String password = ""; // Replace with your Outlook.com password

        sendEmail(message, subject, to, from, username, password);
    }

    // This method is responsible for sending email
    private static void sendEmail(String message, String subject, String to, String from, String username, String password) {
        // Outlook.com SMTP server settings
        String host = "smtp-mail.outlook.com";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Outlook.com SMTP port for TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Use TLS

        // Step 1: Get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        // Step 2: Compose the message
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            // Set from email
            mimeMessage.setFrom(new InternetAddress(from));

            // Adding recipient to message
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Adding subject to message
            mimeMessage.setSubject(subject);

            // Adding text to message
            mimeMessage.setText(message);

            // Step 3: Send the message
            Transport.send(mimeMessage);

            System.out.println("Sent message successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
