// Emailer using JavaMail.

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Emailer
{
  private Properties emailerProperties;
  private String messageText;

  public Emailer()
  {
    emailerProperties = new Properties();
    messageText = "";
    setProperties();
  }

  private void setProperties()
  {
    // Date.
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();

    emailerProperties.setProperty("FROM", "no-reply@inmobi.com");
    emailerProperties.setProperty("TO", "dhanasekaran.kp@inmobi.com");
    emailerProperties.setProperty("SUBJECT", "NDTV News for " + dateFormat.format(date));
    emailerProperties.setProperty("HOST", "mrelay.corp.inmobi.com");
    emailerProperties.setProperty("PROTOCOL", "mail.smtp.host");
    emailerProperties.setProperty("TYPE", "text/HTML");
  }

  public void setMessageText(String messageMarkup)
  {
    messageText = messageMarkup;
  }

  public void sendMail() throws MessagingException
  {
    Properties properties = System.getProperties();
    properties.setProperty(emailerProperties.getProperty("PROTOCOL"), emailerProperties.getProperty("HOST"));
    Session session = Session.getDefaultInstance(properties);

    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(emailerProperties.getProperty("FROM")));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailerProperties.getProperty("TO")));
    message.setSubject(emailerProperties.getProperty("SUBJECT"));
    message.setContent(messageText.toString(), emailerProperties.getProperty("TYPE"));
    Transport.send(message);
    System.out.println("Sending successful !");
  }
}
