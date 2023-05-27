/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooiii.inverted.index;

import java.util.HashMap;

/**
 *
 * @author rreisp
 */
public class InvertedIndex extends HashMap<String, Occurrence> {

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
