package com.joelimyx.todolist;

import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TodoLists mTodoLists;
    FloatingActionButton mTodoFloatingActionButton;
    EditText mDialogEdit;
    TextView mWelcome;
    ImageView mMainArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        mTodoLists = TodoLists.getInstance();

        //Reference
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTodoFloatingActionButton = (FloatingActionButton) findViewById(R.id.todo_fab);

        mWelcome = (TextView) findViewById(R.id.welcome_text);
        mMainArrow = (ImageView) findViewById(R.id.main_arrow);

        if (mTodoLists.getmNameList().isEmpty()){
            mWelcome.setVisibility(View.VISIBLE);
            mMainArrow.setVisibility(View.VISIBLE);
        }

        //Recycler View
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
                View view = inflater.inflate(R.layout.todolist_dialog,null);

                mDialogEdit = (EditText) view.findViewById(R.id.dialog_edit);

                //Create the dialog and show
                builder.setView(view);
                        //Set the positive and negative Button to add list
                builder.setPositiveButton("Add", null).setNegativeButton("Cancel",null);

                final AlertDialog dialog = builder.create();


                DialogInterface.OnShowListener onShowListener = new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface d) {
                        //Set positive
                        dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String name = mDialogEdit.getText().toString();
                                if (name.isEmpty()){
                                    mDialogEdit.setError("Field cannot be empty");
                                }else {
                                    mTodoLists.createListByName(name);
                                    todoListAdapter.notifyItemInserted(mTodoLists.getmNameList().size());
                                    mWelcome.setVisibility(View.GONE);
                                    mMainArrow.setVisibility(View.GONE);
                                    dialog.dismiss();
                                }
                            }
                        });

                        //Set Negative
                        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                };
                dialog.setOnShowListener(onShowListener);
                dialog.show();
            }

        });

    }
}
