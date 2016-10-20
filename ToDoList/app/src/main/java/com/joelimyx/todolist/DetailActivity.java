package com.joelimyx.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView mDetailRecycler;
    FloatingActionButton mDetailFloatingActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);

        Intent detailIntent = getIntent();
        String name = detailIntent.getStringExtra("detailListName");
        final LinkedList<DetailItem> detailList = TodoLists.getInstance().getDetailListByName(name);

        mDetailRecycler = (RecyclerView) findViewById(R.id.detail_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mDetailRecycler.setLayoutManager(linearLayoutManager);

        final DetailViewAdapter detailViewAdapter = new DetailViewAdapter(detailList);
        mDetailRecycler.setAdapter(detailViewAdapter);

        mDetailFloatingActionBar = (FloatingActionButton) findViewById(R.id.detail_fab);
        mDetailFloatingActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Inflate dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
                View view = getLayoutInflater().inflate(R.layout.detail_dialog,null);

                //Edit Text Reference
                final EditText mTitleEdit = (EditText) view.findViewById(R.id.title_edit);
                final EditText mDetialEdit= (EditText) view.findViewById(R.id.detail_edit);

                //Create dialog and show
                dialog.setView(view).setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String title = mTitleEdit.getText().toString();
                        String detail = mDetialEdit.getText().toString();
                        //Check if either field is empty
                        if (title.isEmpty()
                                || detail.isEmpty()){
                            Toast.makeText(DetailActivity.this, "Either field can't be blank", Toast.LENGTH_SHORT).show();
                        }else {
                            detailList.add(new DetailItem(title,detail));
                            detailViewAdapter.notifyItemInserted(detailList.size()-1);
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
            }
        });
    }

}
