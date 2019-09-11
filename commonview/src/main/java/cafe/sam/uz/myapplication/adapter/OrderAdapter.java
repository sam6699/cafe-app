package cafe.sam.uz.myapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cafe.sam.uz.myapplication.R;
import cafe.sam.uz.myapplication.repository.entities.Product;

/**
 * Created by Sam on 02.02.2019.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> implements ObservableOrder{
    private Observer ob;
    private Map<Integer,Product>  or;
    public OrderAdapter(Map<Integer,Product> map,Observer o){
        or = map;
        ob=o;
    }

    public Map<Integer,Product> getOr() {
        return or;
    }


    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout,parent,false);
        return new OrderHolder(v);
    }

    @Override
    public void onBindViewHolder(final OrderHolder holder, final int position) {
        final List<Product> itemList = new ArrayList<>(or.values());
        holder.item.setText(itemList.get(position).getName()+"  "+itemList.get(position).getDim_name());
        holder.quantity.setText(itemList.get(position).getQuan()*itemList.get(position).getDim_value()+" "+itemList.get(position).getDim_name()+"  ");
        holder.totalSum.setText(itemList.get(position).getQuan()*itemList.get(position).getPrice()+"");
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter = itemList.get(position).getQuan();
                counter++;
                itemList.get(position).setQuan(counter);

                or.put(itemList.get(position).getId(),itemList.get(position));
                notifyDataSetChanged();
                updated();


            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter = itemList.get(position).getQuan();
                Log.d("counter",counter+" ");
                counter--;
                itemList.get(position).setQuan(counter);
                or.put(itemList.get(position).getId(),itemList.get(position));

                if (counter==0){
                    or.remove(itemList.get(position));
                }
                notifyDataSetChanged();
                updated();
            }
        });
        holder.rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    or.remove(itemList.get(position).getId());
                    notifyDataSetChanged();
                    updated();
            }
        });
    }

    @Override
    public int getItemCount() {
        return or.size();
    }

    @Override
    public void addObserver(Observer o) {
        this.ob = o;
    }

    @Override
    public void updated() {
            this.ob.getUpdate();
    }

    public static class OrderHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView item;
        TextView quantity;
        TextView totalSum;
        Button add;
        Button rem;
        Button sub;
        public OrderHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv_maket);
            item = cv.findViewById(R.id.item_name);
            quantity = cv.findViewById(R.id.item_count);
            totalSum = cv.findViewById(R.id.item_price);
            add = cv.findViewById(R.id.c_add);
            rem = cv.findViewById(R.id.c_sub);
            sub = cv.findViewById(R.id.c_rem);


        }
    }


}
