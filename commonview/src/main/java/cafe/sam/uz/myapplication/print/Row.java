package cafe.sam.uz.myapplication.print;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import cafe.sam.uz.myapplication.R;

public class Row {
    TableRow row;
    TextView t1,t2,t3,t4,t5,t6;
    public Row(Context context){
        initFields(context);
        init(context);
    }
    private void init(Context context){
        row = new TableRow(context);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
        layoutParams.width= TableRow.LayoutParams.MATCH_PARENT;
        layoutParams.height = TableRow.LayoutParams.WRAP_CONTENT;
        row.setLayoutParams(layoutParams);
        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);
        row.addView(t5);
        row.addView(t6);

    }
    private void initFields(Context context){
        t1=new TextView(context);
        t2=new TextView(context);
        t3=new TextView(context);
        t4=new TextView(context);
        t5=new TextView(context);
        t6=new TextView(context);

        t1.setBackgroundResource(R.drawable.table_cell);
        t2.setBackgroundResource(R.drawable.table_cell);
        t3.setBackgroundResource(R.drawable.table_cell);
        t4.setBackgroundResource(R.drawable.table_cell);
        t5.setBackgroundResource(R.drawable.table_cell);
        t6.setBackgroundResource(R.drawable.table_cell);

        t1.setTextColor(Color.BLACK);
        t2.setTextColor(Color.BLACK);
        t3.setTextColor(Color.BLACK);
        t4.setTextColor(Color.BLACK);
        t5.setTextColor(Color.BLACK);
        t6.setTextColor(Color.BLACK);

        t1.setTextSize(12);
        t2.setTextSize(12);
        t3.setTextSize(12);
        t4.setTextSize(12);
        t5.setTextSize(12);
        t6.setTextSize(12);

        t1.setGravity(Gravity.CENTER);
        t2.setGravity(Gravity.CENTER);
        t3.setGravity(Gravity.CENTER);
        t4.setGravity(Gravity.CENTER);
        t5.setGravity(Gravity.CENTER);
        t6.setGravity(Gravity.CENTER);
    }
    public void setData(int num,String name,String dim,double quan,int price,int amount){
        t1.setText(num+"");
        t2.setText(name);
        t3.setText(dim);
        t4.setText(quan+"");
        t5.setText(price+"");
        t6.setText(amount+"");
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(20,ViewGroup.LayoutParams.MATCH_PARENT);

        t1.setLayoutParams(layoutParams);
        layoutParams.width=100;
        layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        t2.setLayoutParams(layoutParams);
        layoutParams.width=60;
        layoutParams.height= ViewGroup.LayoutParams.MATCH_PARENT;
        t3.setLayoutParams(layoutParams);
        layoutParams.width=35;
        layoutParams.height= ViewGroup.LayoutParams.MATCH_PARENT;
        t4.setLayoutParams(layoutParams);
        layoutParams.width=80;
        layoutParams.height= ViewGroup.LayoutParams.MATCH_PARENT;
        t5.setLayoutParams(layoutParams);
        layoutParams.width=73;
        layoutParams.height= ViewGroup.LayoutParams.MATCH_PARENT;
        t6.setLayoutParams(layoutParams);


    }

    public TableRow getRow() {

        return row;
    }
}
