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
public class Apriori {

    private ArrayList<Row> data;
    private int support;

    public Apriori(ArrayList<Row> data, int support) {
        this.data = data;
        this.support = support;
    }

    public Apriori() {
    }

    public ArrayList<Row> getData() {
        return data;
    }

    public void setData(ArrayList<Row> data) {
        this.data = data;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public ArrayList<String[]> resolver() {
        ArrayList<String[]> respuesta = new ArrayList<>();

        HashMap<String, Integer> oneItems = oneItems();

        quitar(oneItems);

        System.out.println("Patrones 1 Itemes");

        String[] pOneItems = new String[oneItems.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : oneItems.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " | " + value);
            pOneItems[i] = key;
            i++;
        }

        respuesta.add(pOneItems);

        HashMap<String, Integer> combos = (HashMap<String, Integer>) oneItems.clone();
       int p = 2;
        do {
            System.out.println("Patrones "+p +" Itemes");
            String[] o2 = new String[combos.size()];
            i = 0;
            for (Map.Entry<String, Integer> entry : combos.entrySet()) {
                String key = entry.getKey();
                //Integer value = entry.getValue();
                o2[i] = key;
                i++;
            }

            combos = makeCombos(pOneItems, o2, null);

            for (Map.Entry<String, Integer> entry : combos.entrySet()) {
                for (Row row : data) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();

                    if (row.ishere(key.split(","))) {
                        combos.put(key, value + 1);
                    }

                }

            }
            quitar(combos);
            i = 0;
            for (Map.Entry<String, Integer> entry : combos.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key + " | " + value);
                i++;
            }
            p++;
        } while (combos.size() > 0);
        return respuesta;
    }

    private HashMap<String, Integer> makeCombos(String[] origen1, String[] origen2, String[] notUse) {
        HashMap<String, Integer> res = new HashMap<>();

        for (String origen11 : origen1) {
            for (String origen21 : origen2) {

                if (!origen21.contains(origen11)) {
                    String[] ls = origen21.split(",");

                    String[] ordenados = new String[ls.length + 1];
                    int j = 0;
                    boolean ins = true;
                    for (int i = 0; i < ls.length; i++) {
                        if (ls[i].compareTo(origen11) > 0 && ins == true) {
                            ordenados[j] = origen11;
                            j++;
                            ins = false;
                        }

                        ordenados[j] = ls[i];
                        j++;
                    }

                    if (ins) {
                        ordenados[ls.length] = origen11;
                    }
                    String cadena = ordenados[0];
                    for (int i = 1; i < ordenados.length; i++) {
                        cadena += "," + ordenados[i];
                    }
                    res.put(cadena, 0);
                }
            }
        }

        return res;

    }

    private void quitar(HashMap<String, Integer> pPatrones) {
        HashMap<String, Integer> pPatronesC = (HashMap<String, Integer>) pPatrones.clone();
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
      /*  for (Map.Entry<String, Integer> entry : oneItems.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " | " + value);
        }
*/
        return oneItems;
    }
}
