package pooiii.index;

import java.util.ArrayList;

public class Ranking {
    private ArrayList<String> search;
    InvertedIndex ii;
    
    public Ranking (ArrayList<String> search, InvertedIndex ii){
        this.search = search;
        this.ii = ii;
    }
}
