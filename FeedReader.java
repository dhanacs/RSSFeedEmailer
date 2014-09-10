// Read the news feeds and their links.

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

public class FeedReader
{
  private URL feedURL;
  private XmlReader xmlReader;
  private SyndFeed feed;

  // Markup Tags.
  private String HTML_START;
  private String HTML_END;
  private String BODY_START;
  private String BODY_END;
  private String TABLE_START;
  private String TABLE_END;
  private String TABLE_ROW_START;
  private String TABLE_ROW_END;
  private String TABLE_CELL_START;
  private String TABLE_CELL_END;
  private String TABLE_HEAD_START;
  private String TABLE_HEAD_END;

  // Formatted markup tags.
  private String TABLE_START1;
  private String TABLE_ROW_START1;
  private String TABLE_HEAD_START1;
  private String TABLE_CELL_START1;

  public FeedReader()
  {
    HTML_START = "<html>";
    HTML_END = "</html>";
    BODY_START = "<body>";
    BODY_END = "</body>";
    TABLE_START = "<table>";
    TABLE_END = "</table>";
    TABLE_ROW_START = "<tr>";
    TABLE_ROW_END = "</tr>";
    TABLE_CELL_START = "<td>";
    TABLE_CELL_END = "</td>";
    TABLE_HEAD_START = "<th>";
    TABLE_HEAD_END = "</th>";

    TABLE_START1 = "<table bgcolor=\"#F0E68C\">";
    TABLE_ROW_START1 = "<tr bgcolor=\"#B8860B\">";
    TABLE_HEAD_START1 = "<th style=\"color:#FFFFFF\">";
    TABLE_CELL_START1 = "<th style=\"color:#AF1E1E\">";
  }

  public void setFeedURL(String url) throws IOException, FeedException
  {
    feedURL = new URL(url);
    xmlReader = new XmlReader(feedURL);
    feed = new SyndFeedInput().build(xmlReader);
  }

  public String getMarkupFeed()
  {
    String htmlFeed = HTML_START + BODY_START + TABLE_START1;

    // Set table heading.
    htmlFeed += TABLE_ROW_START1 +
                TABLE_HEAD_START1 + "Top News" + TABLE_HEAD_END +
                TABLE_HEAD_START1 + "Link" + TABLE_HEAD_END +
                TABLE_ROW_END;

    for(Iterator i = feed.getEntries().iterator(); i.hasNext();)
    {
      SyndEntry entry = (SyndEntry) i.next();
      htmlFeed += TABLE_ROW_START +
                  TABLE_CELL_START + entry.getTitle() + TABLE_CELL_END +
                  TABLE_CELL_START + entry.getLink() + TABLE_CELL_END +
                  TABLE_ROW_END;
    }

    htmlFeed += TABLE_END + BODY_END + HTML_END;
    return htmlFeed;
  }
}
