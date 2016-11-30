/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi;

import bi.FpGrowth.FPgrowth;
import bi.Apriori;
import bi.prefixSapm.PrefixSpan;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author katie
 */
public class Bi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String entradaTeclado = "";
        do {
            System.out.println("---------------------------------Mineria de datos----------------------------");
            System.out.println("Integrantes:");
            System.out.println("---Barbara Aparicio\n---Katherine Cabrera\n---Nestor Aldana\n---Marvin Zarceño");
            System.out.println("Menu:");
            System.out.println("1) Apriori\n2) FP Growth\n3) Prefix Span\n\n\n 4) Generar Archivos \n\n\n0) salir");
            System.out.println("Introduzca un de las opciones:");

            Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
            entradaTeclado = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
            if (entradaTeclado.equals("1")) {
                ArrayList<Row> data = new ArrayList<>();

                System.out.println("Ingrese la ruta del archivo");

                String ruta = entradaEscaner.nextLine();
                System.out.println("Ingrese el soporte");
                int soporte = entradaEscaner.nextInt();
                ReadData read = new ReadData();
                read.read(ruta, data);
                Apriori m = new Apriori(data, soporte);
                m.resolver();

            }
            if (entradaTeclado.equals("2")) {
                ArrayList<Row> data = new ArrayList<>();

                System.out.println("Ingrese la ruta del archivo");

                String ruta = entradaEscaner.nextLine();
                System.out.println("Ingrese el soporte");
                int soporte = entradaEscaner.nextInt();
                ReadData read = new ReadData();
                read.read(ruta, data);
                FPgrowth metodo = new FPgrowth(data, soporte);
                metodo.resolver();

            }
            if (entradaTeclado.equals("3")) {
                ArrayList<Row> data = new ArrayList<>();
                System.out.println("Ingrese la ruta del archivo");

                String ruta = entradaEscaner.nextLine();
                System.out.println("Ingrese el soporte");
                PrefixSpan a = new PrefixSpan();
                int soporte = entradaEscaner.nextInt();
                a.setRuta(ruta);
                a.setSupport(soporte);
                a.resolver();

            }
            
            if (entradaTeclado.equals("4")) {
                Registros.generar();
            }
            if (!entradaTeclado.equals("0")) {
                entradaEscaner.nextLine();
                entradaEscaner.nextLine();
            }
        } while (!entradaTeclado.equals("0"));
        /*  ReadData read = new ReadData();
        ArrayList<Row> data = new ArrayList<>();

        Scanner readKey = new Scanner(System.in);
        read.read("C:\\Users\\Nestor\\Desktop\\archivo.txt", data);
        
        FPgrowth metodo = new FPgrowth(data, 2);
        
        metodo.resolver();

        Apriori m  = new Apriori(data,2);
        
        m.resolver();
         */

    }

}
