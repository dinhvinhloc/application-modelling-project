/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.helpers;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dinhloc
 */
public final class Email {

    public Email() {
    }
    
    public static void sendEmail(String fromEmailAddress, String toEmailAddress, String subject, String messageString){
        
        String host = "localhost";
        
        Properties properties = System.getProperties();

        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);
        
//        Properties prop = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        
        
        // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("humberlibmanagement@gmail.com", "Aw3se4dr5ft^");
            }
        });

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(fromEmailAddress));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));

         // Set Subject: header field
         message.setSubject(subject);

         // Now set the actual message
         message.setText(messageString);

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
        
    }
    
    
}
