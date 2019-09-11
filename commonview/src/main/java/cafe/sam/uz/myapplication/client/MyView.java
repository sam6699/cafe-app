package cafe.sam.uz.myapplication.client;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Sam on 03.01.2019.
 */

public class MyView extends LinearLayout {
    public ArrayList<Button> getList() {
        return list;
    }

    public void setList(ArrayList<Button> list) {
        this.list = list;
    }

    private ArrayList<Button> list;

    public MyView(Context context) {
        super(context);
        super.setOrientation(LinearLayout.VERTICAL);
        list= new ArrayList<>();
        super.setVerticalScrollBarEnabled(true);
        super.setMinimumWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        super.setMinimumHeight(ViewGroup.LayoutParams.MATCH_PARENT);

    }

    public void updateView(){

        if(!list.isEmpty()){

            Button b = getList().get(getList().size()-1);
            addView(b);



        }
    }




}