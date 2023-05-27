package pooiii.crawler;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo Reis
 */
public abstract class Crawler {

    private int maxDepth = 0;

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
    
    protected ArrayList<String> startPages;

    public abstract void crawl(String url, int depth);

    public abstract String getStartPage();

    public int getMaxDepth() {
        return maxDepth;
    }
}
