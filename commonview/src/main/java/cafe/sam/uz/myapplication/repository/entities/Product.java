package cafe.sam.uz.myapplication.repository.entities;

/**
 * Created by Sam on 03.04.2019.
 */

public class Product {
    private int id;
    private String name;
    private int price;
    private String dim_name;
    private double dim_value;
    private String category;
    private int quan=1;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDim_name() {
        return dim_name;
    }

    public void setDim_name(String dim_name) {
        this.dim_name = dim_name;
    }

    public double getDim_value() {
        return dim_value;
    }

    public void setDim_value(double dim_value) {
        this.dim_value = dim_value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }
}
