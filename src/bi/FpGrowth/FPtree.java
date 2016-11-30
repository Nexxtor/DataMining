package bi.FpGrowth;

import bi.Row;
import java.util.ArrayList;

/**
 *
 * @author Nestor
 */
public class FPtree {

    private boolean root;
    private String item;
    private ArrayList<FPtree> chilsNode;
    private int cant;

    public FPtree() {
        root = false;
        item = "root";
        chilsNode = new ArrayList<>();
        cant = 1;
    }

    public void insert(Row row) {
        FPtree ins;
        ArrayList<FPtree> chilsNode = this.chilsNode;

        for (int i = 0; i < row.length(); i++) {
            if (chilsNode.isEmpty()) {
                ins = new FPtree();
                ins.setItem(row.get(i));
                chilsNode.add(ins);
                chilsNode = ins.getChilsNode();
            } else {
                boolean insertado = false;
                for (FPtree fPtree : chilsNode) {
                    if (fPtree.getItem().equals(row.get(i))) {
                        fPtree.count();
                        chilsNode = fPtree.getChilsNode();
                        insertado = true;
                    }
                }

                if (!insertado) {
                    ins = new FPtree();
                    ins.setItem(row.get(i));
                    chilsNode.add(ins);
                    chilsNode = ins.getChilsNode();
                }
            }
        }
    }

    public void print(int cantTab) {
        
        if (root == true) {
            System.out.print("Nodo inicial ");
        } else {
            
            for (int i = 1; i <cantTab -1; i++) {
                System.out.print("|\t");
            }
            System.out.print("|");
            System.out.print("Nodo: \" " + item + " \" : " + cant + " ");
        }
        System.out.println("");
        if (!chilsNode.isEmpty()) {
            
            

            for (FPtree fPtree : chilsNode) {
                
                fPtree.print(cantTab + 1);
            }
            
        }
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ArrayList<FPtree> getChilsNode() {
        return chilsNode;
    }

    public void setChilsNode(ArrayList<FPtree> chilsNode) {
        this.chilsNode = chilsNode;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void count() {
        cant++;
    }
    
    public String search(String which){
        if(item.equals(which)){
            return item +":" + cant + ";";
        }else if(chilsNode.isEmpty()){
            return "";
        }else{
            ArrayList<String> ls = new ArrayList<>();
            for (FPtree fPtree : chilsNode) {
               // System.out.println("Estoy en" + item );
                String tmp = fPtree.search(which);
                if (!tmp.equals("")){
                   // System.out.println(item+","+tmp);
                    ls.add(tmp);
                    
                }
                //System.out.println("Nuevo camino estando en " + item);
            }
            String resp = "";
            for(int i = 0; i < ls.size(); i ++){
                String[] all = ls.get(i).split(";");
                
                for (String string : all) {
                    if(root == false )
                        resp+=item+","+string+";";  
                    else if(root == true && string.contains(",")){
                        resp+=string+";";  
                    }
                }
            }
            return resp;
            //return ls.toString().replace("[", "").replace("]", "");
        }
    }

}
