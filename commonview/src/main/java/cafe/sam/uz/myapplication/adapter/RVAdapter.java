package cafe.sam.uz.myapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import cafe.sam.uz.myapplication.R;
import cafe.sam.uz.myapplication.client.BoardButton;

/**
 * Created by Sam on 01.02.2019.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BoardButtonViewHolder> {
    private List<BoardButton> list;

    public RVAdapter(List<BoardButton> list) {
        this.list = list;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BoardButtonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_layout,parent,false);
        BoardButtonViewHolder bbb = new BoardButtonViewHolder(v);
        return bbb;
    }

    @Override
    public void onBindViewHolder(final BoardButtonViewHolder holder, int position) {
            holder.button.setText(list.get(position).getID()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BoardButtonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        Button button;
        public BoardButtonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            button = (Button) cv.findViewById(R.id.board);
        }
    }



}

