package com.joelimyx.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.zip.Inflater;

/**
 * Created by Joe on 10/19/16.
 */

public class DetailViewAdapter extends RecyclerView.Adapter<DetailViewHolder> {
    LinkedList<DetailItem> mDetailList;

    public DetailViewAdapter(LinkedList<DetailItem> detailList) {
        mDetailList = detailList;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item,parent,false);
        return new DetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, final int position) {
        DetailItem item = mDetailList.get(position);
        holder.mTitleText.setText(item.getTitle());
        holder.mDetailText.setText(item.getDetail());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Remove item
                switch (v.getId()){
                    case R.id.detail_remove:
                        mDetailList.remove(position);
                        notifyItemRemoved(position);
                        break;
                }
            }
        };
        holder.mDetailRemove.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return mDetailList.size();
    }
}
