package cafe.sam.uz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cafe.sam.uz.myapplication.adapter.MenuAdapter;
import cafe.sam.uz.myapplication.adapter.RecyclerItemClickListener;
import cafe.sam.uz.myapplication.repository.entities.Product;

public class Menu extends AppCompatActivity {
    MenuAdapter menuAdapter;
    List<Product> menuList;
    RecyclerView rv;
    RecyclerItemClickListener nest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modal);

        Intent intent = getIntent();
        int menu = intent.getIntExtra("menu",0);
        if (menu == 1){
            menuList = Main.instance.foodBtn;
        }
        if (menu==2)
            menuList = Main.instance.bevBtn;
        if (menu==3)
            menuList = Main.instance.saladsBtn;
        if (menu==4)
            menuList = Main.instance.cackesBtn;
        rv = (RecyclerView) findViewById(R.id.menuList);
        initDrinks();

    }

    private void initDrinks() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        menuAdapter = new MenuAdapter(menuList);
        rv.setAdapter(menuAdapter);
        rv.getAdapter().notifyDataSetChanged();
        rv.removeOnItemTouchListener(nest);
        nest = new RecyclerItemClickListener(this,rv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (Main.id !=-1){
                    if (Main.instance.or.containsKey(menuList.get(position).getId())){
                        int count = Main.instance.or.get(menuList.get(position).getId()).getQuan();
                        count++;
                        Main.instance.or.get(menuList.get(position).getId()).setQuan(count);

//                        Main.instance.or.put(menuList.get(position),count);
                    }else {
                        CardView v = (CardView)view;
//                        ConstraintLayout b = (ConstraintLayout) v.getChildAt(0);
//                        Button btn = (Button) b.getChildAt(0);
//                        btn.setBackgroundColor(Color.GREEN);
                        Main.instance.or.put(menuList.get(position).getId(),menuList.get(position));
                    }
                }
                Main.instance.order.getAdapter().notifyDataSetChanged();
                Main.instance.calculate();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        rv.addOnItemTouchListener(nest);
}

}
