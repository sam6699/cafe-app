package cafe.sam.uz.myapplication.adapter;

import cafe.sam.uz.myapplication.repository.entities.Product;

/**
 * Created by Sam on 03.02.2019.
 */

public interface Observer {
    void getUpdate();
    void addItem(Product pr);

}
