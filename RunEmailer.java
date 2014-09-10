import com.sun.syndication.io.FeedException;

import javax.mail.MessagingException;
import java.io.IOException;

public class RunEmailer
{
  public static void main(String []args) throws IOException, FeedException, MessagingException
  {
    FeedReader feedReader = new FeedReader();
    feedReader.setFeedURL("http://feeds.feedburner.com/ndtv/TqgX");

    Emailer emailer = new Emailer();
    emailer.setMessageText(feedReader.getMarkupFeed());
    emailer.sendMail();
  }
}
