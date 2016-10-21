package com.joelimyx.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Joe on 10/18/16.
 */

public class TodoListHolder extends RecyclerView.ViewHolder {
    public ImageView mRemoveImage;
    public TextView mTextView;
    public RelativeLayout mRelativeLayout;

    public TodoListHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.lists_text);
        mRemoveImage = (ImageView) itemView.findViewById(R.id.removeImage);
        mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_layout);
    }
}
