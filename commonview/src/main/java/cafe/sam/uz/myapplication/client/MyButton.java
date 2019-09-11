package cafe.sam.uz.myapplication.client;

import android.content.Context;

import cafe.sam.uz.myapplication.repository.entities.Product;


/**
 * Created by Sam on 03.01.2019.
 */
public class MyButton extends android.support.v7.widget.AppCompatButton implements Cloneable{
    private Product item;

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public MyButton(Context context) {
        super(context);

    }
    @Override
    protected Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}