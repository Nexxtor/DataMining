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
        read.read("C:\\Users\\katie\\Desktop\\archivo.txt", data);
        
        Apriori metodo = new Apriori(data, 3);
        
        metodo.resolver();


    }

}
