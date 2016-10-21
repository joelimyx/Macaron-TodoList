package com.joelimyx.todolist;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.LinkedList;

/**
 * Created by Joe on 10/19/16.
 */

public class DetailViewAdapter extends RecyclerView.Adapter<DetailViewHolder> {
    LinkedList<DetailItem> mDetailList;
    private final Activity mContext;
    EditText mTitleEdit;
    EditText mDetailEdit;

    public DetailViewAdapter(LinkedList<DetailItem> detailList, Activity context) {
        mDetailList = detailList;
        mContext = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item,parent,false);
        return new DetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DetailViewHolder holder, final int position) {
        final DetailItem item = mDetailList.get(position);

        holder.mTitleText.setText(item.getTitle());
        holder.mDetailText.setText(item.getDetail());
        mTitleEdit = (EditText) mContext.findViewById(R.id.title_edit);

        //Check if done
        if (item.isDone()){
            holder.mDoneImage.setVisibility(View.VISIBLE);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Remove item
                switch (v.getId()){
                    case R.id.detail_remove:
                        mDetailList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());

                        //Snackbar
                        Snackbar snackbar = Snackbar
                                .make(v, item.getTitle()+" is deleted.", Snackbar.LENGTH_LONG)

                                //Restore
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Snackbar undo = Snackbar.make(v , item.getTitle()+" is restored", Snackbar.LENGTH_LONG);
                                        mDetailList.add(position,item);
                                        notifyItemInserted(position);
                                        undo.show();
                                    }
                                });
                        snackbar.show();
                        break;

                    //Checkbox
                    case R.id.detail_item:
                        if (item.isDone()) {
                            holder.mDoneImage.setVisibility(View.INVISIBLE);
                            item.setDone(false);
                        }else{
                            holder.mDoneImage.setVisibility(View.VISIBLE);
                            mDetailList.get(holder.getAdapterPosition()).setDone(true);
                        }
                        break;
                    case R.id.edit_image:

                        //Inflate dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        View view = mContext.getLayoutInflater().inflate(R.layout.detail_dialog,null);

                        //Retrieve and input it to
                        final String title = holder.mTitleText.getText().toString();
                        final String detail = holder.mDetailText.getText().toString();

                        mTitleEdit = (EditText) view.findViewById(R.id.title_edit);
                        mDetailEdit = (EditText) view.findViewById(R.id.detail_edit);

                        mTitleEdit.setText(title);
                        mDetailEdit.setText(detail);

                        builder.setView(view).setPositiveButton("Update", null).setNegativeButton("Cancel", null);
                        final AlertDialog dialog = builder.create();
                                String titletemp = mTitleEdit.getText().toString();
                                String detailtemp = mDetailEdit.getText().toString();
                        /*********************
                         *
                         * OnShowListener
                         ***********************/
                        DialogInterface.OnShowListener onShowListener = new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface d) {

                                //Set Positive
                                dialog.getButton(dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String titletemp = mTitleEdit.getText().toString();
                                        String detailtemp = mDetailEdit.getText().toString();

                                        //Check if either field is empty
                                        if (titletemp.isEmpty()) {
                                            mTitleEdit.setError("Field cannot be empty.");
                                        }else{
                                            item.setTitle(titletemp);
                                            item.setDetail(detailtemp);
                                            notifyItemChanged(holder.getAdapterPosition());
                                            dialog.dismiss();
                                        }//if
                                    }
                                });

                                //Set Negative
                                dialog.getButton(dialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        };

                        dialog.setOnShowListener(onShowListener);
                        dialog.show();
                        break;
                    /****************************************
                     * End of on show
                     ****************************************/
                }
            }
        };

        holder.mEditImage.setOnClickListener(onClickListener);
        holder.mDetailRelative.setOnClickListener(onClickListener);
        holder.mDetailRemove.setOnClickListener(onClickListener);
        }

    @Override
    public int getItemCount() {
        return mDetailList.size();
    }
    }

