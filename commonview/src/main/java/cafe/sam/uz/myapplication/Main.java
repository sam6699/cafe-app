package cafe.sam.uz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cafe.sam.uz.myapplication.adapter.Observer;
import cafe.sam.uz.myapplication.adapter.OrderAdapter;
import cafe.sam.uz.myapplication.adapter.RVAdapter;
import cafe.sam.uz.myapplication.adapter.RecyclerItemClickListener;
import cafe.sam.uz.myapplication.client.BoardButton;
import cafe.sam.uz.myapplication.repository.dao.ProductDao;
import cafe.sam.uz.myapplication.repository.dao.SalesDao;
import cafe.sam.uz.myapplication.repository.entities.Product;
import cafe.sam.uz.myapplication.repository.entities.Sales;


public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Observer{
    RecyclerItemClickListener nest;
    public List<BoardButton> boardBtn = new ArrayList<>();
    public List<Product> foodBtn = new ArrayList<>();
    public List<Product> bevBtn = new ArrayList<>();
    public List<Product> saladsBtn = new ArrayList<>();
    public List<Product> cackesBtn = new ArrayList<>();
    public Map<Integer,Product> or = new HashMap<>();
     int starter = 10;
    public RecyclerView boards;
    public RecyclerView foods;
    public RecyclerView drinks;
    public RecyclerView salads;
                  RecyclerView order;
    public static Main instance;
    static Integer id=-1;
    public static int totalPrice=0;
    public TextView total;
    RVAdapter rvAdapter;
    OrderAdapter oa;
    TextView textOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");
        instance = this;
        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        starter = sharedPreferences.getInt("tables",10);
        DrawerLayout drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    ///////////////////////////////////////////////////////////////////////////////////////
        getMenu();

        total = (TextView) findViewById(R.id.pay_order);

        boards =  (RecyclerView) findViewById(R.id.boardView);
        order = (RecyclerView) findViewById(R.id.order);


        initBoards();
        initOrder();


        textOrder = (TextView) findViewById(R.id.order_text);

        final Button btn =(Button) findViewById(R.id.confirm_to_print);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // purchase();
                Intent intent =new Intent(btn.getContext(), cafe.sam.uz.myapplication.print.Main.class);
                intent.putExtra("startDate",boardBtn.get(id).getDate());
                startActivity(intent);
            }
        });
        final Button fb =(Button) findViewById(R.id.foodBtn);
        Button bb =(Button) findViewById(R.id.drinkBtn);
        Button sb =(Button) findViewById(R.id.saladBtn);
        Button cb = (Button)findViewById(R.id.cackeBtn);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fb.getContext(),Menu.class);
                intent.putExtra("menu",1);
                startActivity(intent);

            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fb.getContext(),Menu.class);
                intent.putExtra("menu",2);
                startActivity(intent);

            }
        });
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fb.getContext(),Menu.class);
                intent.putExtra("menu",3);
                startActivity(intent);
            }
        });
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fb.getContext(),Menu.class);
                intent.putExtra("menu",4);
                startActivity(intent);
            }
        });


    }



    private void initBoards(){
        LinearLayoutManager llm = new LinearLayoutManager(this);
        boards.setLayoutManager(llm);
        for (int i=0;i<starter;i++){
            boardBtn.add(new BoardButton(i+1));
        }

        rvAdapter = new RVAdapter(boardBtn);
        boards.setAdapter(rvAdapter);
        boards.getAdapter().notifyDataSetChanged();
        boards.addOnItemTouchListener(new RecyclerItemClickListener(this, boards ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                BoardButton bb = boardBtn.get(position);

                OrderAdapter force = new OrderAdapter(bb.getOrder(),instance);
                or = boardBtn.get(position).getOrder();
                order.setAdapter(force);
                id = position;
                textOrder.setText("Stol "+(position+1));
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy hh.mm", Locale.getDefault());
                Date d = new Date();

                    if(!or.isEmpty()){
                        bb.setDate(sdf.format(d));
                        textOrder.setText("Stol "+(position+1)+" "+bb.getDate());
                        Log.d("date", bb.getDate());
                    }
                calculate();



            }

            @Override public void onLongItemClick(View view, int position) {
                // do whatever
            }
        }));
    }




    private void initOrder(){
        LinearLayoutManager llm = new LinearLayoutManager(this);
        order.setLayoutManager(llm);
         oa = new OrderAdapter(or,instance);
        oa.addObserver(this);
        order.setAdapter(oa);
        order.getAdapter().notifyDataSetChanged();


    }
    public void calculate(){
        ArrayList<Product> val = new ArrayList<>(or.values());
        totalPrice=0;
        for (int i=0;i<val.size();i++){
            totalPrice+=val.get(i).getPrice()*val.get(i).getQuan()*val.get(i).getDim_value();
        }
        total.setText("Total:"+totalPrice);
    }


    private void getMenu(){

        bevBtn.clear();
        foodBtn.clear();
        saladsBtn.clear();
        for (Product product : ProductDao.getInstance().getProducts()){
            if(product.getCategory()!=null){
            if (product.getCategory().equals("drink")){
                bevBtn.add(product);
            }
            else if (product.getCategory().equals("food")){
                foodBtn.add(product);
            }else {
                saladsBtn.add(product);
            }
        }}
    }

    @Override
    public void onBackPressed() {
        getMenu();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            System.out.println("main");
        } else {
            System.out.println("login");
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostResume() {
        getMenu();
        System.out.println("RESUMED");
        super.onPostResume();
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.admin_menu) {

            Intent intent = new Intent(this, cafe.sam.uz.myapplication.admin.Main.class);
            startActivity(intent);
        } else if (id == R.id.tool_menu) {
            Intent intent = new Intent(this, cafe.sam.uz.myapplication.report.Report.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void getUpdate() {
        calculate();
     }

    @Override
    public void addItem(Product pr) {

    }
    private void purchase(){
        ArrayList<Sales> sales = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String date = sdf.format(new Date());
        for (Map.Entry<Integer,Product> i: or.entrySet()){
            Sales s = new Sales();

            s.setAmount(i.getValue().getPrice()*i.getValue().getQuan());
            s.setDate(date);
            s.setProduct_id(i.getValue());
            s.setQuantity(i.getValue().getQuan()*i.getValue().getDim_value());
            sales.add(s);
        }
        for (Sales s : sales){
            SalesDao.getInstance().addSale(s);
        }




    }

}
