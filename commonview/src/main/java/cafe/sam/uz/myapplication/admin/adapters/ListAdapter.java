package cafe.sam.uz.myapplication.admin.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.Objects;

import cafe.sam.uz.myapplication.R;
import cafe.sam.uz.myapplication.repository.dao.ProductDao;
import cafe.sam.uz.myapplication.repository.entities.Product;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    List<Product> ls;
    List<String> spin;
    ArrayAdapter<String> aa;
    String cat;

    public ListAdapter(List<Product> ls, List<String> spin,String categori) {
        this.ls = ls;
        this.spin = spin;
        this.cat=categori;
    }

    @Override
    public ListAdapter.ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_recycle,parent,false);
        ListHolder lh = new ListHolder(v);
        return  lh;
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ListHolder holder, final int position) {
        aa = new ArrayAdapter<String>(holder.cv.getContext(),android.R.layout.simple_spinner_item,spin);
            holder.item.setText(ls.get(position).getName());
            holder.price.setText(ls.get(position).getPrice()+"");
            holder.metr_value.setText(ls.get(position).getDim_value()+"");
            holder.metr.setAdapter(aa);
            holder.metr.setPrompt("");

        if (Objects.equals(spin.get(0), ls.get(position).getDim_name()))
        holder.metr.setSelection(0);
        else if (Objects.equals(spin.get(1), ls.get(position).getDim_name()))
            holder.metr.setSelection(1);
        else
            holder.metr.setSelection(2);
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isVailid =true;
                Product pr = new Product();
                if (holder.item.getText().toString().length()>0) {
                    pr.setName(holder.item.getText().toString());
                }
                else {
                    holder.item.setError("is empty");
                    isVailid = false;
                }

                    if (holder.metr_value.getText().toString().length()>0||!holder.item.getText().toString().equals("0.0"))
                    {
                        pr.setDim_value(Double.parseDouble(holder.metr_value.getText().toString()));
                        pr.setDim_name(holder.metr.getSelectedItem().toString());}
                    else {
                        holder.metr_value.setError("is empty");
                    isVailid = false;
                }if (holder.price.getText().toString().length()>0||!holder.price.getText().toString().equals("0"))
                pr.setPrice(Integer.parseInt((holder.price.getText()).toString()));
                else {
                    holder.price.setError("is empty");
                    isVailid = false;
                }

                if (isVailid){
                    if (ls.get(position).getId()!=0){
                        System.out.println(ls.get(position).getId()+ls.get(position).getName());
                        pr.setId(ls.get(position).getId());
                        pr.setCategory(cat);
                        ProductDao.getInstance().updateItem(pr);
                        ls.set(position,pr);
                    }else {
                        Log.d("category",cat);
                        pr.setCategory(cat);
                        pr.setId((int)ProductDao.getInstance().addItem(pr));
                        ls.add(1,pr);
                    }
                    notifyDataSetChanged();
//
                }
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProductDao.getInstance().deleteItem(ls.get(position));
                ls.remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return ls.size();
    }


    class ListHolder extends RecyclerView.ViewHolder{
        CardView cv;
        EditText item;
        EditText metr_value;
        Spinner metr;
        EditText price;
        Button up;
        Button del;
        public ListHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.ad_cv);
            item = cv.findViewById(R.id.ad_item_name);
            metr = cv.findViewById(R.id.spin_metr);
            price = cv.findViewById(R.id.ad_item_price);
            up = cv.findViewById(R.id.ad_add);
            del = cv.findViewById(R.id.ad_del);
            metr_value = cv.findViewById(R.id.metr_value);
        }
    }

}
