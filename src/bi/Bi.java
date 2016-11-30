/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi;

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
        ReadData read = new ReadData();
        ArrayList<Row> data = new ArrayList<>();

        Scanner readKey = new Scanner(System.in);
        read.read("C:\\Users\\Nestor\\Desktop\\archivo.txt", data);
        
        FPgrowth metodo = new FPgrowth(data, 2);
        
        metodo.resolver();

        Apriori m  = new Apriori(data,2);
        
        m.resolver();

    }

}
