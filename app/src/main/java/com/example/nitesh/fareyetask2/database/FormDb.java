package com.example.nitesh.fareyetask2.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nitesh.fareyetask2.model.FormModel;
import com.example.nitesh.fareyetask2.model.FormUpdateModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by nitesh on 17/7/17.
 */

public class FormDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "formuserdb";
    private static final String FORM_USER_TABLE = "formusertable";
    private static final int DATABASE_VERSION = 1;
    private static final String Form_Master_Id = "idis";
    private static final String Attr_Id = "attrid";
    private static final String text = "seq";
    private static final String id = "id";
    private static final String Table = "CREATE TABLE " + FORM_USER_TABLE + "(" + id + " INTEGER ," + Form_Master_Id + " INTEGER ," + Attr_Id + " INTEGER ," + text + " TEXT " + " )";
    Context context;

    public FormDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table);
        Log.d("database created==", "Table2 created for album one");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Table");
        onCreate(db);

    }

    public int addDatatoformdb(ArrayList<FormModel> data) {
        SharedPreferences pref =context.getSharedPreferences("counters", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int dataId=pref.getInt("data_id",0);
        dataId++;
        editor.putInt("data_id",dataId);
        editor.apply();
        int check=1;
        //String sql="INSERT INTO " + FORM_TABLE +" (id,attrid,label,type,sequence) VALUES (?,?,?,?,?) ";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // Log.d("data inserted", "number of insertion" + "DATA INSRTED");
        try {


            for (FormModel formModel : data) {
                // Log.d("datainserted12321", "number of insertion" + "DATA INSRTED");
                values.put(id, dataId);
                Log.d("data12321", "number of insertion" + dataId);
                values.put(Form_Master_Id, formModel.getFormid());
                values.put(text, String.valueOf(formModel.getText()));
                // Log.d("datainserted1232112", "number of insertion" + formModel.getFormid() + String.valueOf(formModel.getText()));
                values.put(Attr_Id, formModel.getAttrid());
                //values.put(Type, formModel.getType());
                // Log.d("datainserted1232112", "number of insertion" + formModel.getAttrid());
                //values.put(Sequence, formModel.getSequence());
                // Log.d("datainserted12321", "number of insertion" + "DATA INSRTED");
                db.insertOrThrow(FORM_USER_TABLE, null, values);
            }
            db.close();
        }
        catch (SQLiteException e){
            check=0;
        }
        return check;
    }

    public ArrayList<FormUpdateModel> getDataDb(int id1) {

        ArrayList<FormUpdateModel> datauniversal = new ArrayList();
        SQLiteDatabase database = this.getReadableDatabase();
        //String selectQuery = "SELECT * FROM formusertable where "+Form_Master_Id+" is "+id;
        String selectQuery="SELECT * FROM "+FORM_USER_TABLE+" WHERE "+id+"="+id1;
        Log.d("query maker", selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.d("number of row in ", "" + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                datauniversal.add(new FormUpdateModel(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3)));
                Log.d("fareyetask2data=", "is" + datauniversal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return datauniversal;
    }

    public Set getuser(int id) {
        Set<Integer> datauniversa = new HashSet<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectq="SELECT id FROM "+FORM_USER_TABLE+" WHERE "+Attr_Id+"="+id;
        //String selectq = "select id from  formusertable " + " where " + Form_Master_Id + " = " + id;
        Cursor cursor = database.rawQuery(selectq, null);
        Log.d("number of row in ", "" + cursor.getCount());
        while (cursor.moveToNext()) {
            datauniversa.add(cursor.getInt(0));
            Log.d("cursor is", String.valueOf(cursor.getInt(0)));
            Log.d("fareyetask2data=", "is" + datauniversa);
        }
        cursor.close();
        database.close();
        return datauniversa;

    }
}
