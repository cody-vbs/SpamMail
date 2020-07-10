
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
     * @author syd
 */
public class MainSpam {
    static Session session;
    static Config myConfig = new Config();
    static final String email = myConfig.email;
    final static String password = myConfig.password;
    static Scanner inp = new Scanner(System.in).useDelimiter("\n");;
    static Scanner inp2 = new Scanner(System.in);
    static int counter;

    public static void main(String[] args) {
        String targetEm, message;
        int count = 0;
        System.out.println("***********************************");
        System.out.println("           SPAM MAIL                 ");
        System.out.println("***********************************");
        System.out.print("Press enter to begin...");
        inp.nextLine();
        System.out.print("Target Email: ");
        targetEm = inp.next();
        inp.nextLine();
        System.out.print("Message: ");
        message = inp.next();
        inp.nextLine();
        System.out.print("Amount: ");
        count = inp2.nextInt();
        
        
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
          });

       for(int x = 0; x<count;x++){
           counter++;
           bomber(session,targetEm,message);
       }
       
        System.out.println("Thank you using the program!");
    }
    
    private static void bomber(Session session,String emailTo,String mess){
         try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailTo));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse("sydricafort@gmail.com"));
            message.setSubject(getRandString());
            message.setText(mess);
            
            Transport.send(message);
            
            System.out.println("✔ Email sent " + counter);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static String getRandString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
}
