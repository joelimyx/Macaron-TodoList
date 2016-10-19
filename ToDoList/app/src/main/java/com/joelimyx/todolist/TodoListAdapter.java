package com.joelimyx.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joe on 10/18/16.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListHolder> {
    LinkedList<String> mTodoLists = new LinkedList<>();

    public TodoListAdapter(LinkedList<String> todoLists) {
        mTodoLists = todoLists;
    }

    @Override
    public TodoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists_item,parent,false);
        return new TodoListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoListHolder holder, final int position) {

        holder.mTextView.setText(mTodoLists.get(position));
        holder.mRemoveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTodoLists.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodoLists.size();
    }
}
