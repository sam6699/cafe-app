package cafe.sam.uz.myapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.DrinkHolder>{
    private List<Product> list;

    public MenuAdapter(List<Product> bevBtn) {
        this.list = bevBtn;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DrinkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_layout,parent,false);
        DrinkHolder dh = new DrinkHolder(v);

        return dh;
    }

    @Override
    public void onBindViewHolder(DrinkHolder holder, final int position) {
            holder.button.setText(list.get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("menuadpter", "onBindViewHolder: "+list.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DrinkHolder extends RecyclerView.ViewHolder{
        CardView cv;
        Button button;
        public DrinkHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            button = cv.findViewById(R.id.board);
        }
    }
}
