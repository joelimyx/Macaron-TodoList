package com.joelimyx.todolist;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joe on 10/18/16.
 */

public class TodoListHolder extends RecyclerView.ViewHolder {
    public ImageView mRemoveImage;
    public TextView mTextView;

    public TodoListHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.lists_text);
        mRemoveImage = (ImageView) itemView.findViewById(R.id.removeImage);
    }
}
