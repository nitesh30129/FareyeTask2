package com.example.nitesh.fareyetask2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nitesh.fareyetask2.model.FormMasterModel;

import java.util.ArrayList;

/**
 * Created by nitesh on 15/7/17.
 */

public  class FormMasterDb extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "masterDb";
    public static final String MASTER_TABLE = "formMaster";

    private static final int DATABASE_VERSION =1;
    public static final String Form_Id = "id";
    private static final String Form_Name="name";
    Context context;

    private static final String Table = "CREATE TABLE " + MASTER_TABLE + "("
            + Form_Id + " INTEGER PRIMARY KEY ,"
            + Form_Name + " TEXT " +  ")";
    public FormMasterDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table);
        Log.d("database created==", "Table1 created for album one");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS formMaster");
        onCreate(db);
    }
    public void insertMasterData(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        //FormMasterModel masterModel =new FormMasterModel();
        ContentValues values= new ContentValues();
        Log.d("datainsertedddd","number of insertion"+"DATA INSRTED");
        values.put(Form_Id,id);
        values.put(Form_Name, name);
        db.insertOrThrow(MASTER_TABLE,null,values);
        Log.d("datainsertedddd","number of insertion"+id+name);
        db.close();
    }
    public void deletdata(){
        SQLiteDatabase db= this.getReadableDatabase();
        db.execSQL("delete from formMaster");
        db.close();
    }
    public ArrayList<FormMasterModel> getDataDb(){

        ArrayList<FormMasterModel> datauniversal=new ArrayList();
        SQLiteDatabase database= this.getReadableDatabase();
        String selectQuery="SELECT * FROM formMaster";
        Log.d("query maker", selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.d("number of row in ", ""+cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                datauniversal.add(new FormMasterModel(cursor.getInt(0),cursor.getString(1)));
                Log.d("fareyetask2data=","is"+datauniversal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return datauniversal;
    }

}
