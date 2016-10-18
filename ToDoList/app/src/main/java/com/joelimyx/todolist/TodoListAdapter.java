package com.joelimyx.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 10/18/16.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListHolder> {
    List<TodoLists> mTodoLists = new ArrayList<>();

    public TodoListAdapter(List<TodoLists> todoLists) {
        mTodoLists = todoLists;
    }

    @Override
    public TodoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists_item,parent,false);
        return new TodoListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoListHolder holder, int position) {
        TodoLists current = mTodoLists.get(position);

    }

    @Override
    public int getItemCount() {
        return mTodoLists.size();
    }
}
