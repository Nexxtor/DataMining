/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author stefa_000
 */
public class FPgrowth {

    private ArrayList<Row> data;
    private int support;
    private ArrayList<String> resp;

    public FPgrowth(ArrayList<Row> data, int support) {
        this.data = data;
        this.support = support;
        resp = new ArrayList<>();
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
        HashMap<String, Integer> pPatronesC = (HashMap<String, Integer>) pPatrones.clone();
        for (Map.Entry<String, Integer> entry : pPatronesC.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value < support) {
                pPatrones.remove(key);
            }
        }

    }

    private HashMap<String, Integer> oneItems(ArrayList<Row> data) {
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

    public void resolver() {
        resolver(this.data, "");
        System.out.println("Patrones encontrados");
        resp.sort(new Comparator<String>() {
            @Override
            public int compare(String t, String t1) {
                return t.length() - t1.length();
            }
        });
        for (String s : resp) {
            System.out.println(s );
        }
    }

    public void resolver(ArrayList<Row> data, String dado) {
        System.out.println("Inciando " + dado);
        if (data == null || data.isEmpty()) {
            System.out.println("Finalizado nada que hacer");
            return;
        }

        HashMap<String, Integer> oneItems = oneItems(data);

        System.out.println("New Transactions");
        for (Row row : data) {

            row.sort(oneItems);
            for (int i = 0; i < row.length(); i++) {
                System.out.print(row.get(i) + " ");
            }
            System.out.println("");
        }

        FPtree arbol = new FPtree();
        arbol.setRoot(true);

        for (Row row : data) {
            arbol.insert(row);
        }

        arbol.print(2);
        System.out.println("CPB " + dado + ":");
        printCPB(arbol, oneItems);
        System.out.println("---------------------");
        for (Map.Entry<String, Integer> entry : oneItems.entrySet()) {
            String key = entry.getKey();
            resolver(makeNewData(arbol, key), ((dado.isEmpty()) ? key : (key + "," + dado)));
        }

        HashMap<String, Integer> oneItemsP = (HashMap<String, Integer>) oneItems.clone();

        quitar(oneItemsP);
        if (!oneItemsP.isEmpty()) {
            System.out.println("Patrones " + dado);
        }
        for (Map.Entry<String, Integer> entry : oneItemsP.entrySet()) {
            String key = entry.getKey();
            resp.add(((dado.isEmpty()) ? key : (key + "," + dado)));
            
            System.out.println("\t" + ((dado.isEmpty()) ? key : (key + "," + dado)));
        }

        if (!oneItemsP.isEmpty()) {
            System.out.println("Finalizado " + dado);
        }
    }

    private ArrayList<Row> makeNewData(FPtree arbol, String which) {
        ArrayList<Row> data = new ArrayList<>();
        String caminos = arbol.search(which);

        if (caminos.equals("")) {
            return data;
        } else {
            String[] caminosD = caminos.split(";");
            for (String datos : caminosD) {
                String[] datosD = datos.split(":");
                String[] guardar = datosD[0].split(",");

                for (int i = 0; i < Integer.parseInt(datosD[1]); i++) {
                    Row newRow = new Row();
                    newRow.number = i + 1;
                    for (int j = 0; j < guardar.length - 1; j++) {

                        newRow.add(guardar[j]);

                    }
                    if (newRow.length() > 0) {
                        data.add(newRow);
                    }
                }
            }
            // System.out.println("----------------------------------------------"+data.size()+"----------------------------------------------");
            return data;
        }

    }

    private void printCPB(FPtree arbol, HashMap<String, Integer> which) {
        for (Map.Entry<String, Integer> entry : which.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value >= support) {
                String caminos = key + ": \n {" + arbol.search(key) + "}";
                System.out.println(caminos);
            }
        }
    }
}
