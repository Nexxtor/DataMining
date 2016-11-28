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
 * @author katie
 */
public class Row {

    private HashMap<Integer, String> data;
    protected int number;
    private int cantCol;

    public Row() {
        data = new HashMap<>();
        cantCol = 0;
    }

    public void add(String data) {
        this.data.put(cantCol, data);
        cantCol++;
    }

    public String get(int key) {
        return data.get(key);
    }

    public int length() {
        return data.size();
    }

    public boolean ishere(String[] patron) {
        int count = 0;
        for (String string : patron) {
            for (Map.Entry<Integer, String> entry : data.entrySet()) {
                String value = entry.getValue();

                if (string.equals(value)) {
                    count++;
                    break;
                }
            }
        }
        return count == patron.length;
    }

}
