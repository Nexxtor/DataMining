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
 * @author Nestor
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

    public void sort(HashMap<String, Integer> condition) {
        for (int i = length() -1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (condition.get((data.get(j))) < condition.get((data.get(j + 1)))) {
                    String tmp = data.get(j + 1);

                    data.put(j + 1, data.get(j));
                    data.put(j, tmp);
                }
            }
        }
    }

}
