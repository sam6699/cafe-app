package cafe.sam.uz.myapplication.adapter;

import java.util.Observable;

/**
 * Created by Sam on 03.02.2019.
 */

interface ObservableOrder {
    void addObserver(Observer o);
    void updated();
}
