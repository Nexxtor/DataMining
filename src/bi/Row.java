/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.data.put(cantCol,data);
        cantCol++;
    }
    
    public String get(int key){
        return data.get(key);
    }
    
    public int length(){
        return data.size();
    } 
    
    
}
