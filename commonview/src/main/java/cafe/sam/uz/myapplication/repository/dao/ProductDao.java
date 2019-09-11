package cafe.sam.uz.myapplication.repository.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cafe.sam.uz.myapplication.Main;
import cafe.sam.uz.myapplication.repository.DataBaseHelper;
import cafe.sam.uz.myapplication.repository.entities.Product;

/**
 * Created by Sam on 03.04.2019.
 */

public class ProductDao {
    private SQLiteDatabase sqLiteDatabase;
    private static ProductDao instance;
    public static ProductDao getInstance(){
        if (instance==null)
            instance= new ProductDao();
        return instance;
    }
    public ArrayList<Product> getProducts(){
        ArrayList<Product> list = new ArrayList<>();
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
// ******TODO For Updating Database
//        try {
//            DataBaseHelper.getInstance(Main.instance).updateDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM product",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Product product = new Product();
            product.setId(c.getInt(0));
            product.setName(c.getString(1));
            product.setPrice(c.getInt(2));
            product.setDim_name(c.getString(3));
            product.setDim_value(c.getDouble(4));
            product.setCategory(c.getString(5));
            list.add(product);
            c.moveToNext();
        }
        c.close();
        return list;
    }

    public long updateItem(Product e){

        ContentValues cv = new ContentValues();
        cv.put("name",e.getName());
        cv.put("price",e.getPrice());
        cv.put("dim_name",e.getDim_name());
        cv.put("dim_value",e.getDim_value());
        cv.put("categori",e.getCategory());
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
        String id = e.getId()+"";
        // System.out.println("iserting item:"+id);
        long index =  sqLiteDatabase.update("product",cv,"id=?",new String[]{id});
        sqLiteDatabase.close();
        return index;

    }
    public long deleteItem(Product e){
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
        long index = sqLiteDatabase.delete("product","id="+e.getId(),null);
        sqLiteDatabase.close();
        return index;
    }
    public long addItem(Product item){
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",item.getName());
        cv.put("price",item.getPrice());
        cv.put("dim_name",item.getDim_name());
        cv.put("dim_value",item.getDim_value());
        cv.put("categori",item.getCategory());
        long index = sqLiteDatabase.insert("product",null,cv);
        sqLiteDatabase.close();

        // System.out.println("inserted item id: "+index);
        return index;
    }
    public Product getProduct(int anInt) {
        System.out.println("iddd:"+anInt);
        sqLiteDatabase = DataBaseHelper.getInstance(Main.instance).getWritableDatabase();
        Cursor item = sqLiteDatabase.rawQuery("SELECT * FROM product WHERE id="+anInt,null);
        item.moveToFirst();
        Product item1 = new Product();
        item1.setId(item.getInt(0));
        item1.setName(item.getString(1));
        item1.setPrice(item.getInt(2));
        item1.setDim_name(item.getString(3));
        item1.setDim_value(item.getDouble(4));
        item1.setCategory(item.getString(5));
        sqLiteDatabase.close();
        return item1;
    }


}
