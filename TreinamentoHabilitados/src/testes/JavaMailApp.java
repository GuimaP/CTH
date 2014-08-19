package testes;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp
{
      public static void main(String[] args) {
           
    	  Properties props = System.getProperties();
           
//            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "587");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,null);

            String username = "loperera";
            String password = "1q2w3e";
            String smtphost = "ipipi.com";
            String compression = "Include Subject";
            String from = username+"@ipipi.com";
            String to = "+5511951313869@sms.ipipi.com";
            String body = "Testando minhas sms";

            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(from)); //Remetente

                  InternetAddress[] toUser = InternetAddress //Destinat�rio(s)
                             .parse(to);  

                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject(compression);//Assunto
                  message.setText(body);
                  message.setSentDate(new Date(System.currentTimeMillis()));
                  /**M�todo para enviar a mensagem criada*/
                  
                  Transport tr = session.getTransport("smtp");
                  tr.connect(smtphost,587,username,password);
                  message.saveChanges();
                  tr.sendMessage(message, message.getAllRecipients());
                  tr.close();
                  
//                  Transport.send(message);

                  System.out.println("Feito!!!");

             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
}


