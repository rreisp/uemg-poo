package pooiii.index;

import java.io.Serializable;
import java.util.HashMap;

public class InvertedIndex extends HashMap<String, Occurrence> implements Serializable {

    public InvertedIndex() {
        super();
    }

    public void add(String key, String path) {
        Occurrence oc = get(key);
        if (oc != null) {
            try {
                int counter = oc.get(path);
                oc.put(path, counter+1);
            } catch (Exception e) {
                oc.put(path, 1);
            }
        } else {
            oc = new Occurrence();
            oc.put(path, 1);
            put(key, oc);
        }
    }
}
