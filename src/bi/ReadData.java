/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author katie
 */
public class ReadData {

    public void read(String path, ArrayList<Row> data) {
        BufferedReader br = null;
        System.out.println("--- Iniciando Leyendo datos ---");
        try {
            String currentLine;
            
            br = new BufferedReader(new FileReader(path));

            int i = 0;
            while ((currentLine = br.readLine()) != null) {
                String[] cols = currentLine.split(",");
                Row row = new Row();
                row.number = i;
                for (String col : cols) {
                    row.add(col);
                    System.out.print(col + " ");
                }
                System.out.println("");
                data.add(i,row);
                i++;
            }
            
            
        } catch (IOException e) {
            System.out.println("Hubo un problema en la lectura de archivo: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                System.out.println("--- Fin Leyendo datos ---");
            } catch (IOException e) {
                System.out.println("Hubo un problema al cerrar el archivo: " + e.getMessage());
            }
        }
    }
}
