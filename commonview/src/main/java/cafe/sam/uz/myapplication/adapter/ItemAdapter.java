package cafe.sam.uz.myapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import cafe.sam.uz.myapplication.R;
import cafe.sam.uz.myapplication.repository.entities.Product;

/**
 * Created by Sam on 02.02.2019.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{
    List<Product> list;

    public List<Product> getList() {
        return list;
    }

    public ItemAdapter(List<Product> ls){
        this.list=ls;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_layout,parent,false);
        ItemHolder ih = new ItemHolder(v);

        return ih;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

        holder.button.setText(list.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{
    CardView cv;
        Button button;
        public ItemHolder(View itemView) {
            super(itemView);
            cv=itemView.findViewById(R.id.cv);
            button = cv.findViewById(R.id.board);
        }
    }
}
