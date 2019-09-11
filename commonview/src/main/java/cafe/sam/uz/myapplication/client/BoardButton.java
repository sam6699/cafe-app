package cafe.sam.uz.myapplication.client;


import java.util.HashMap;
import java.util.Map;

import cafe.sam.uz.myapplication.repository.entities.Product;

/**
 * Created by Sam on 03.01.2019.
 */

public class BoardButton {
    private Map<Integer,Product> order = new HashMap<>();
    private int ID;
    private int sum;
    private String date;
    public Map<Integer,Product> getOrder() {
        return order;
    }


    public int getID() {
        return ID;
    }

    public BoardButton(int id) {
        ID=id;
        sum=0;
    }
//    public void addItem(Product item){
//        if (order.containsKey(item)){
//            int counter = order.get(item)+1;
//            System.out.println("contains>"+item.getPrice()/(counter-1)+">"+item.getPrice());
//            order.put(item,counter);
//
//        }
//        else{
//            System.out.println("add new");
//            order.put(item,1);
//        }
//
//    }

    public void removeSum(int sum) {
        this.sum -= sum;
    }
    public void addSum(int sum){
        this.sum+=sum;
    }

    public int getSum() {
        return sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
