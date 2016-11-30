/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefa_000
 */
public class Registros {

    /**
     * @param args the command line arguments
     */
    public static void generar() {
        int cant = 0, numRegistro =0, opcion;
        ArrayList<String> items = new ArrayList<>();
        String nombre;

        System.out.println("numero de Items a ingresar?");
        Scanner leer = new Scanner(System.in);
        cant = leer.nextInt();
        leer.nextLine();
        
        for (int i = 0; i < cant; i++) {
            System.out.println("Ingrese item " + (i + 1));
            items.add(leer.nextLine());
        }
        System.out.println("numero de registros/ transacciones?");
        numRegistro= leer.nextInt();
        leer.nextLine();
        
        System.out.println("Escriba la ruta y nombre del archivo");
        nombre= leer.nextLine();
        
        System.out.println("digite el numero 1 si desea creear un archivo de registros \nDigite el numero 2 si desea un archivo de secuencia");
        opcion= leer.nextInt();
        
        switch(opcion){
            case 1: pedirRegistros(cant, numRegistro, items, nombre);
            break;
            case 2: pedirSecuencia(cant, numRegistro, items, nombre);
            break;
        }
 
    }

    
    static void pedirRegistros(int numItems, int numRegistros, ArrayList<String> items1, String ruta){
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));

            for (int i = 0; i < numRegistros; i++) {
                ArrayList<String> itC = (ArrayList<String>) items1.clone();
                double b = Math.random() * numItems;
                for (int j = 0; j < b; j++) {
                    double c = Math.random() * itC.size();
                    if (b-j< 1) {
                        bw.write(itC.get((int) c));//System.out.print(itC.get((int) c));
                    } else {
                     
                        bw.write(itC.get((int) c) + ",");//System.out.print(itC.get((int) c) + ",");
                    }
                    itC.remove((int) c);
                }
                bw.newLine();//System.out.println("");
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    static void pedirSecuencia(int numItems, int numRegistros, ArrayList<String> items1, String ruta){
         
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));

            for (int i = 0; i < numRegistros; i++) {
                
                double p = Math.random() * 10;
                for(int k=0; k < p; k++){
                    ArrayList<String> itC = (ArrayList<String>) items1.clone();
                    double b = Math.random() * numItems;
                    for (int j = 0; j < b; j++) {
                        double c = Math.random() * itC.size();
                        if (b-j< 1) {
                            bw.write(itC.get((int) c));//System.out.print(itC.get((int) c));
                        } else {

                            bw.write(itC.get((int) c) + ",");//System.out.print(itC.get((int) c) + ",");
                        }
                        itC.remove((int) c);
                    }
                    if(p-k > 1){
                        bw.write(";");  
                    }
                    
                }
                bw.newLine();//System.out.println("");
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Registros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
