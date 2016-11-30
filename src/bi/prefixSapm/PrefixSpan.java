/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi.prefixSapm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nestor
 */
public class PrefixSpan {

    private String ruta;
    private int support;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    public void resolver() {
        HashMap<String, Integer> eq = new HashMap<>();
        BufferedReader br = null;
        BufferedWriter wr = null;
        System.out.println("--- Iniciando Leyendo datos ---");
        try {
            String currentLine;

            br = new BufferedReader(new FileReader(ruta));
            wr = new BufferedWriter(new FileWriter(ruta + ".org.bin"));
            int i = 1;
            while ((currentLine = br.readLine()) != null) {
                String[] secuencias = currentLine.split(";");
                for (String string : secuencias) {
                    String[] elementos = string.split(",");
                    for (String elemento : elementos) {
                        if (!eq.containsKey(elemento)) {
                            eq.put(elemento, i);
                            i++;
                        }
                        wr.write(eq.get(elemento) + " ");
                        System.out.print(eq.get(elemento) + " ");
                    }
                    wr.write("-1 ");
                    System.out.print("-1 ");
                }
                wr.write("-2\n");
                System.out.println("-2");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                wr.close();
                System.out.println("--- Fin Leyendo datos ---");
            } catch (Exception e) {
                System.out.println("Hubo un problema al cerrar el archivo: " + e.getMessage());
            }
        }

        try {

            AlgoPrefixSpan algo = new AlgoPrefixSpan();
            String destino = ruta + ".save.bin";
            String origne = ruta + ".org.bin";
            System.out.println(support);
            algo.runAlgorithm(origne, destino, support);
            
     
            
            
            File tmp = new File(origne);

            tmp.delete();

            br = new BufferedReader(new FileReader(destino));
            String currentLine = "";
            while ((currentLine = br.readLine()) != null) {
                currentLine = currentLine.replace(" -1 ", "");
               
                String aux =  currentLine.substring(currentLine.indexOf("#SUP"));
                currentLine = currentLine.replaceAll("#SUP: [0-9]+", "");
                 //currentLine = currentLine.replace(" ", ",");
                for (Map.Entry<String, Integer> entry : eq.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    currentLine = currentLine.replace(value + "", key);
                }
                System.out.println(currentLine + " "+ aux);
            }

        } catch (IOException ex) {
            Logger.getLogger(PrefixSpan.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
