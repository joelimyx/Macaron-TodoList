package com.joelimyx.todolist;

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
    @BindView(R.id.removeImage) ImageView mRemoveImage;
    @BindView(R.id.lists_text) TextView mTextView;

    public TodoListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }
}
