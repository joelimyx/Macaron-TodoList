package com.joelimyx.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Joe on 10/19/16.
 */

public class DetailViewHolder extends RecyclerView.ViewHolder{
    TextView mTitleText, mDetailText;
    ImageView mDetailRemove, mDoneImage, mEditImage;
    RelativeLayout mDetailRelative;

    public DetailViewHolder(View itemView) {
        super(itemView);

        mTitleText = (TextView) itemView.findViewById(R.id.title_text);
        mDetailText = (TextView) itemView.findViewById(R.id.detail_text);
        mDoneImage = (ImageView) itemView.findViewById(R.id.done_image);
        mEditImage = (ImageView) itemView.findViewById(R.id.edit_image);
        mDetailRemove = (ImageView) itemView.findViewById(R.id.detail_remove);
        mDetailRelative = (RelativeLayout) itemView.findViewById(R.id.detail_item);
    }
}
