/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author stefa_000
 */
public class FPgrowth {
   
    private ArrayList<Row> data;
    private int support;

    public FPgrowth(ArrayList<Row> data, int support) {
        this.data = data;
        this.support = support;
    }

    public FPgrowth() {
    }

    public ArrayList<Row> getData() {
        return data;
    }

    public int getSupport() {
        return support;
    }

    public void setData(ArrayList<Row> data) {
        this.data = data;
    }

    public void setSupport(int support) {
        this.support = support;
    }
    
    private void quitar(HashMap<String, Integer> pPatrones) {
        HashMap<String,Integer> pPatronesC = (HashMap<String, Integer>) pPatrones.clone();
        for (Map.Entry<String, Integer> entry : pPatronesC.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value < support) {
                pPatrones.remove(key);
            }
        }

    }
    
    private HashMap<String, Integer> oneItems() {
        HashMap<String, Integer> oneItems = new HashMap<>();
        for (Row row : data) {
            for (int i = 0; i < row.length(); i++) {
                if (oneItems.containsKey(row.get(i))) {
                    int count = oneItems.get(row.get(i));
                    oneItems.put(row.get(i), count + 1);
                } else {
                    oneItems.put(row.get(i), 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : oneItems.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " | " + value);
        }

        return oneItems;
    }
    
 
    public void resolver(){
        
    }
 
}
