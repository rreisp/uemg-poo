package pooiii.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.UncheckedIOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Rodrigo Reis Pereira
 */
public class CrawlerPensador extends Crawler {

    private String root;
    private int counter;

    private static ArrayList<String> visited;

    public CrawlerPensador() {
        if (visited == null) {
            visited = new ArrayList<>();
        }
        counter = 0;
        root = "";
    }

    @Override
    public void crawl(String url, int depth) {
        if (url.contains("https://www.pensador.com/")) {

            int pos = url.indexOf("#");
            if (pos != -1) {
                url = url.substring(0, pos);
            }
            pos = url.indexOf("?");
            if (pos != -1) {
                url = url.substring(0, pos);
            }
            pos = url.indexOf("{");
            if (pos != -1) {
                url = url.substring(0, pos);
            }

            Document doc;
            try {
                doc = Jsoup.connect(url).get();
            } catch (Exception ex) {
                return;
            } catch (UncheckedIOException ex) {
                return;
            }
            url = doc.baseUri();

            boolean skip = true;

            if (!visited.contains(url)) {
                skip = false;
                visited.add(url);
            }

            if (!skip) {
                // EXTRACT INFO
                Elements textElements = doc.getElementsByClass("thought-card");
                BufferedWriter writer = null;

                for (Element e : textElements) {
                    String text = "";
                    String autor = "";
                    try {
                        String tText = e.getElementsByClass("frase").first().html();
                        String[] lines = tText.split("<br>");
                        for (String line : lines) {
                            if (line != null) {
                                text += line.replaceAll("^\\s+", "") + "\n";
                            }
                        }
                        autor = e.getElementsByClass("autor").first().text();
                        counter++;
                        //System.out.println(">> ********************************* ");
                        //System.out.println(">> " + autor);
                        //System.out.println(">> " + text);
                        //System.out.println(">> " + depth);
                        File f = new File(root + autor + "_" + counter + ".txt");
                        FileWriter fw = new FileWriter(f);
                        writer = new BufferedWriter(fw);
                        writer.write(text);
                        writer.close();
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                    }
                }

                // FOLLOW LINKS
                if (depth + 1 <= getMaxDepth()) {
                    Elements links = doc.select("a");
                    for (Element link : links) {
                        String href = link.absUrl("href");
                        crawl(href, depth + 1);
                    }
                }
            }
        }
    }

    @Override
    public String getStartPage() {
        return "https://www.pensador.com/";
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
