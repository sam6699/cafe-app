package cafe.sam.uz.myapplication.admin;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cafe.sam.uz.myapplication.R;
import cafe.sam.uz.myapplication.admin.adapters.ListAdapter;
import cafe.sam.uz.myapplication.repository.dao.ProductDao;
import cafe.sam.uz.myapplication.repository.entities.Product;


public class Main extends AppCompatActivity {
    static ArrayList<Product> ls = new ArrayList<>();
    static ArrayList<Product> food = new ArrayList<>();
    static ArrayList<Product> drinks = new ArrayList<>();
    private List<Product> salads = new ArrayList<>();
    private List<Product> cackes = new ArrayList<>();

    private TabItem tabFood;
    private TabItem tabDrink;
    private TabItem tabSalad;
    private NestedScrollView nsv;
    private RecyclerView foodView;
    private RecyclerView drinkView;
    private RecyclerView saladView;
    private RecyclerView cackeView;

    private TabLayout tl;


    //////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);


        readFromDB();
        tabFood = (TabItem) findViewById(R.id.tabFood);
        tabDrink = (TabItem) findViewById(R.id.tabDrink);
        tabSalad= (TabItem) findViewById(R.id.tabSalad);
        nsv = (NestedScrollView) findViewById(R.id.fillView);
        tl = (TabLayout) findViewById(R.id.tabs);
        initLists();
        initTabs();


    }
    void initTabs(){
        nsv.addView(foodView);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                nsv.removeAllViews();
                System.out.println(tab.getText());
                if(tab.getText().equals("Ovqat")){
                    nsv.addView(foodView);

                }else if(tab.getText().equals("Ichimlik"))
                    nsv.addView(drinkView);
                else  if(tab.getText().equals("Salat"))
                    nsv.addView(saladView);
                else {
                    nsv.addView(cackeView);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    void initLists(){
        ArrayList<String> d = new ArrayList<>();
        d.add("ml");
        d.add("gr");
        d.add("dona");
        foodView = new RecyclerView(this);
        drinkView = new RecyclerView(this);
        saladView = new RecyclerView(this);
        cackeView = new RecyclerView(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        LinearLayoutManager llm3 = new LinearLayoutManager(this);
        LinearLayoutManager llm4 = new LinearLayoutManager(this);
        foodView.setLayoutManager(llm);
        drinkView.setLayoutManager(llm2);
        saladView.setLayoutManager(llm3);
        cackeView.setLayoutManager(llm4);
        ListAdapter la1 = new ListAdapter(food,d,"food");
        ListAdapter la2 = new ListAdapter(drinks,d,"drink");
        ListAdapter la3 = new ListAdapter(salads,d,"salad");
        ListAdapter la4 = new ListAdapter(cackes,d,"cacke");
        foodView.setAdapter(la1);
        drinkView.setAdapter(la2);
        saladView.setAdapter(la3);
        cackeView.setAdapter(la4);
        foodView.getAdapter().notifyDataSetChanged();
        drinkView.getAdapter().notifyDataSetChanged();
        saladView.getAdapter().notifyDataSetChanged();
        cackeView.getAdapter().notifyDataSetChanged();
    }
    void dataFilter(){
        food.add(new Product());
        drinks.add(new Product());
        salads.add(new Product());
        cackes.add(new Product());
        for (Product pi : ls){
            if (Objects.equals(pi.getCategory(), "food"))
                food.add(pi);
            else if (Objects.equals(pi.getCategory(), "drink"))
                drinks.add(pi);
            else if (Objects.equals(pi.getCategory(),"salad"))
                salads.add(pi);
            else if (Objects.equals(pi.getCategory(),"cacke"))
                cackes.add(pi);
        }
    }
    void readFromDB(){

        if (ls.isEmpty()){
            System.out.println("is empty");
        }else {
            ls.clear();
            drinks.clear();
            food.clear();
            cackes.clear();
        }


        ls.addAll(ProductDao.getInstance().getProducts());///??????????
         dataFilter();

    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}

