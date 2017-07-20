package com.example.nitesh.fareyetask2.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nitesh.fareyetask2.R;
import com.example.nitesh.fareyetask2.database.FormAttrDb;
import com.example.nitesh.fareyetask2.database.FormDb;
import com.example.nitesh.fareyetask2.database.FormMasterDb;
import com.example.nitesh.fareyetask2.model.FormAttributeModel;
import com.example.nitesh.fareyetask2.model.FormMasterModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.nitesh.fareyetask2.database.FormMasterDb.DATABASE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button go, view;
    EditText texturl;
    ArrayList<FormAttributeModel> arrayList = null;
    FormAttrDb formAttrDb;
    FormMasterDb formMasterDb;
    ArrayList<String> checkurl;
    Gson gson;
    GsonBuilder gsonBuilder;
    FormMasterModel formMasterModel;
    FormDb formDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("databaseexits","aghccgavcaaic");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = (Button) findViewById(R.id.go);
        texturl = (EditText) findViewById(R.id.texturl);
        view = (Button) findViewById(R.id.view);
        go.setOnClickListener(this);
         view.setOnClickListener(this);
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();


      /*  if(check==1){
            SQLiteDatabase sqLiteDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
            formAttrDb.onUpgrade(sqLiteDatabase,0,0);
            formDb.onUpgrade(sqLiteDatabase,0,0);
            formMasterDb.onUpgrade(sqLiteDatabase,0,0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("myCounter", 1);
            editor.apply();
        }*/
        checkurl=new ArrayList<>();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.go:
                String editText = texturl.getText().toString();
                if(checkurl.contains(editText)  ){
                    Toast.makeText(getApplicationContext(),"form already present",Toast.LENGTH_LONG).show();
                }
                else {
                    formAttrDb = new FormAttrDb(this);
                    formMasterDb = new FormMasterDb(this);
                    formDb=new FormDb(this);
                    SharedPreferences preferences = this.getSharedPreferences("myApp", this.MODE_PRIVATE);
                    int check = preferences.getInt("myCounter", 0);
                    if(check==1){
                        SQLiteDatabase sqLiteDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
                        formAttrDb.onUpgrade(sqLiteDatabase,0,0);
                        formDb.onUpgrade(sqLiteDatabase,0,0);
                        formMasterDb.onUpgrade(sqLiteDatabase,0,0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("myCounter", 1);
                        editor.apply();
                    }
                    VolleyCall(editText);
                    //checkurl.add(editText);
                }
                break;

            case R.id.view:
                Intent myIntent = new Intent(MainActivity.this, FormMaster.class);
                startActivity(myIntent);
                break;
        }
    }
/*
    public void     VolleyCall() {
        try {
            Log.d("dataaaaaaa", "fdfdfd" + 1);
            arrayList = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                Log.d("datafromserver123", "" + arrayList);
                if (i % 2 == 0) {
                    arrayList.add(new FormAttributeModel(1, i + 100, "firstname", "string", i + 1));
                    Log.d("datafromserver123", "" + arrayList);
                } else if (i % 3 == 0) {
                    arrayList.add(new FormAttributeModel(1, i + 100, "lastname", "string", i + 1));
                    Log.d("datafromserver123", "" + arrayList);
                } else {
                    arrayList.add(new FormAttributeModel(1, i + 100, "contact", "number", i + 1));
                    Log.d("datafromserver123", "" + arrayList);
                }
                Log.d("datafromserver123", "" + arrayList);
            }
            Log.d("data222222222", "fdfdfd" + 1);
            formMasterDb = new FormMasterDb(this);
            formMasterDb.insertMasterData(1, "nitesh");
            Log.d("datafromserver123456789", "" + arrayList);

            formAttrDb.insertFormAttr(arrayList);
            ArrayList<FormAttributeModel> p = new ArrayList<>();
            //p=formAttrDb.getDataDb(intent.getIntExtra(FORMID, 0));
            Log.d("aggadgad", "aa" + p);
           // q = formMasterDb.getDataDb();
            //Log.d("jsfsvfsvsdcvsjvjsv", "" + q);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
*/
   public void VolleyCall( String url) {
       Log.d("CAlllllllllllll",">>>>>>>>>>>> url" + url);
     final ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
      pDialog.setMessage("Loading...");
       pDialog.show();
       Log.d("datafromserver", "asdsadsa");
        RequestQueue queue = Volley.newRequestQueue(this);
       Log.d("datafromserver", "asdasdsad");
        final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("datafromserver", response);
                try {
                    JSONObject data = new JSONObject(response);
                    formMasterModel = gson.fromJson(data.toString(), FormMasterModel.class);
                    Log.d("formmastermodel",""+formMasterModel);
                    formMasterDb.insertMasterData(formMasterModel.getId(),formMasterModel.getName());
                    Log.d("aggadgad","aa"+formMasterModel.getId()+formMasterModel.getName()+formMasterModel.getFormMaster());
                    formAttrDb.insertFormAttr(formMasterModel.getId(),formMasterModel.getFormMaster());
                    Toast.makeText(getApplicationContext(),"data inserted",Toast.LENGTH_LONG).show();
                    //Log.d("aggadgad","aa"+formId);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"form already present",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
              pDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                VolleyLog.d("Network error12112 ", "Error: " + networkResponse);
                Toast.makeText(getApplicationContext(),"invalid url",Toast.LENGTH_LONG).show();
             pDialog.hide();
            }
        });
        queue.add(request);
    }
    }
