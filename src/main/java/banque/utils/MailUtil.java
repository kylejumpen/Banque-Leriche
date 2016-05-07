package banque.utils;

import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class MailUtil {

    public static void sendEmail(String serveurSTMP, int portSTMP, String from, String to,
                                     String subjet, String text) throws Exception {


        final String username = "banque.inforep@gmail.com";
        final String password = "inforepcrigolow";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", serveurSTMP);
        props.put("mail.smtp.port", portSTMP);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(to));
            message.setSubject(subjet);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
