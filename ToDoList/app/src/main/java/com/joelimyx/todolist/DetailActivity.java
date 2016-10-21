package com.joelimyx.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView mDetailRecycler;
    FloatingActionButton mDetailFloatingActionBar;
    TextView mListName,mDetailWelcome;
    ImageView mBackButton, mDetailArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);

        //Toolbar!!!
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false);
        //Reference
        mDetailWelcome = (TextView) findViewById(R.id.detail_welcome);
        mBackButton = (ImageView) findViewById(R.id.up);
        mDetailArrow = (ImageView) findViewById(R.id.detail_arrow);

        //Intent
        Intent detailIntent = getIntent();
        String name = detailIntent.getStringExtra("detailListName");
        final LinkedList<DetailItem> detailList = TodoLists.getInstance().getDetailListByName(name);

        //Show text if empty
        if (detailList.isEmpty()){
            mDetailArrow.setVisibility(View.VISIBLE);
            mDetailWelcome.setVisibility(View.VISIBLE);
        }

        //Toolbar Title name
        mListName = (TextView) findViewById(R.id.detail_toolbar);
        mListName.setText(name+" List");

        mDetailRecycler = (RecyclerView) findViewById(R.id.detail_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mDetailRecycler.setLayoutManager(linearLayoutManager);

        final DetailViewAdapter detailViewAdapter = new DetailViewAdapter(detailList,DetailActivity.this);
        mDetailRecycler.setAdapter(detailViewAdapter);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Add Task
                switch (v.getId()) {
                    case R.id.detail_fab:

                        //Inflate dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                        View view = getLayoutInflater().inflate(R.layout.detail_dialog, null);

                        //Edit Text Reference
                        final EditText mTitleEdit = (EditText) view.findViewById(R.id.title_edit);
                        final EditText mDetailEdit = (EditText) view.findViewById(R.id.detail_edit);

                        //Create dialog and show
                        builder.setPositiveButton("Add",null).setNegativeButton("Cancel", null);
                        builder.setView(view);

                        final AlertDialog dialog = builder.create();
                        DialogInterface.OnShowListener onShowListener = new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface d) {

                                //Set Positive to persist
                                dialog.getButton(dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String title = mTitleEdit.getText().toString();
                                            String detail = mDetailEdit.getText().toString();
                                            //Check if either field is empty
                                            if (title.isEmpty()) {
                                                mTitleEdit.setError("Field cannot be blank.");
                                            } else {
                                                detailList.add(new DetailItem(title, detail));
                                                detailViewAdapter.notifyItemInserted(detailList.size() - 1);
                                                mDetailArrow.setVisibility(View.GONE);
                                                mDetailWelcome.setVisibility(View.GONE);
                                                dialog.dismiss();
                                            }
                                        }
                                        });

                                //Set negative
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

                    //Return to home
                    case R.id.up:
                        finish();
                        break;
                }
            }

        };

        mDetailFloatingActionBar = (FloatingActionButton) findViewById(R.id.detail_fab);
        mDetailFloatingActionBar.setOnClickListener(onClickListener);
    }
}
