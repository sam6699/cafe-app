package cafe.sam.uz.myapplication.repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cafe.sam.uz.myapplication.Main;
import cafe.sam.uz.myapplication.repository.DataBaseHelper;
import cafe.sam.uz.myapplication.repository.entities.Sales;

/**
 * Created by Sam on 03.04.2019.
 */

public class SalesDao {
    private SQLiteDatabase sqLiteDatabase;
    private static SalesDao instance;
    public static SalesDao getInstance(){
        if (instance==null)
            instance= new SalesDao();
        return instance;
    }
    public ArrayList<Sales> getSales(String date){
        ArrayList<Sales> list = new ArrayList<>();
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM sales where sale_date='16-04-19'",null);

        c.moveToFirst();
        while (!c.isAfterLast()){
            Sales sales = new Sales();
            sales.setId(c.getInt(0));
            sales.setProduct_id(ProductDao.getInstance().getProduct(c.getInt(1)));
            sales.setAmount(c.getInt(2));
            sales.setDate(c.getString(3));
            sales.setQuantity(c.getDouble(4));
            list.add(sales);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public long updateItem(Sales e){

        ContentValues cv = new ContentValues();
        cv.put("amount",e.getAmount());
        cv.put("quantity",e.getQuantity());
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
        String id = e.getProduct_id().getId()+"";
        long index =  sqLiteDatabase.update("item",cv,"sale_date=? and product_id=?",new String[]{e.getDate(),id});
        sqLiteDatabase.close();
        return index;

    }

    public long addSale(Sales item){
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("product_id",item.getProduct_id().getId());
        cv.put("amount",item.getAmount());
        cv.put("sale_date",item.getDate());
        cv.put("quantity",item.getQuantity());
        long index = sqLiteDatabase.insert("sales",null,cv);
        sqLiteDatabase.close();

        return index;
    }


}
