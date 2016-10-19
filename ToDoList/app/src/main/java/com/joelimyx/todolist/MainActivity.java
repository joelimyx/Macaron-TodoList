package com.joelimyx.todolist;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TodoLists mTodoLists;
    FloatingActionButton mTodoFloatingActionButton;
    EditText mDialogEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        mTodoLists = TodoLists.getInstance();

        //Reference
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTodoFloatingActionButton = (FloatingActionButton) findViewById(R.id.todo_fab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        final TodoListAdapter todoListAdapter =new TodoListAdapter(mTodoLists.getMap(),this);
        mRecyclerView.setAdapter(todoListAdapter);

        //FAB On Click Listener to create dialog
        mTodoFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Inflate todolist_dialog xml
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View dialog = inflater.inflate(R.layout.todolist_dialog,null);

                mDialogEdit = (EditText) dialog.findViewById(R.id.dialog_edit);

                //Create the dialog and show
                builder.setView(dialog)
                        //Set the positive Button to add list
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = mDialogEdit.getText().toString();
                                mTodoLists.createListByName(name);
                                todoListAdapter.notifyDataSetChanged();
                            }
                        })
                        //Set the Negative Button
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });
    }
}
