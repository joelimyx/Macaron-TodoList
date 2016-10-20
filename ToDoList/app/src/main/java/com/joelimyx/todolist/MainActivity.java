package com.joelimyx.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TodoLists mTodoLists;
    FloatingActionButton mTodoFloatingActionButton;
    EditText mDialogEdit;
    CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        mTodoLists = TodoLists.getInstance();

        //Reference
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTodoFloatingActionButton = (FloatingActionButton) findViewById(R.id.todo_fab);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);

        //Recycler View
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        final TodoListAdapter todoListAdapter =new TodoListAdapter(mTodoLists.getMap(),this,mCoordinatorLayout);
        mRecyclerView.setAdapter(todoListAdapter);

        //FAB On Click Listener to create dialog
        mTodoFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Inflate todolist_dialog xml
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.todolist_dialog,null);

                mDialogEdit = (EditText) view.findViewById(R.id.dialog_edit);
                if(mDialogEdit.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mDialogEdit, InputMethodManager.SHOW_IMPLICIT);
                }

                //Create the dialog and show
                dialog.setView(view);
                        //Set the positive Button to add list
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = mDialogEdit.getText().toString();
                        if (name.isEmpty()){
                            Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                        }else {
                            mTodoLists.createListByName(name);
                            todoListAdapter.notifyDataSetChanged();
                        }
                    }
                })
                //Set the Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.create().show();
            }
        });
    }
}
