package banque.clientTest;

import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class Mail {

    public static void envoyer_email(String serveurSTMP, int portSTMP, String from, String to,
                                     String sujet, String text) throws Exception {


        final String username = "florian78.leriche@gmail.com";
        final String password = "lijygres";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", serveurSTMP);
        props.put("mail.smtp.port", portSTMP);

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse("florian.leriche@insa-rouen.fr, quentin.lerebours@insa-rouen.fr, pauline.mouches@insa-rouen.fr, kafui.atanley@insa-rouen.fr"));

            // Set Subject: header field
            message.setSubject(sujet);

            // Now set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        envoyer_email("smtp.gmail.com", 587, "florian78.leriche@gmail.com", "florian.leriche@neuf.fr", "Testance", "Envoyance de mail via java dans la fonctionnellance des familles");
    }
}