package com.example.nitesh.fareyetask2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nitesh.fareyetask2.R;
import com.example.nitesh.fareyetask2.adapter.FetchAdapter;
import com.example.nitesh.fareyetask2.database.FormMasterDb;
import com.example.nitesh.fareyetask2.model.FormMasterModel;

import java.util.ArrayList;

/**
 * Created by nitesh on 16/7/17.
 */

public class FormMaster extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<FormMasterModel> arrayList;
    FetchAdapter fetchData;
    FormMasterDb formMasterDb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.master_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        try {
            fetchData = new FetchAdapter(getApplicationContext(), new FormMasterDb(getApplicationContext()).getDataDb());

        }catch (Exception e){e.printStackTrace();}

        recyclerView.setAdapter(fetchData);
    }

}
