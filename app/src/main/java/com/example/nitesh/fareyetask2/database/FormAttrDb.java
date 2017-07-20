package com.example.nitesh.fareyetask2.database;

/**
 * Created by nitesh on 13/7/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nitesh.fareyetask2.model.FormAttributeModel;
import com.example.nitesh.fareyetask2.model.FormViewModel;

import java.util.ArrayList;

import static com.example.nitesh.fareyetask2.database.FormMasterDb.Form_Id;
import static com.example.nitesh.fareyetask2.database.FormMasterDb.MASTER_TABLE;


public class FormAttrDb extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "formmasterdb";
        public static final String FORM_TABLE = "formAttr";
        private static final int DATABASE_VERSION =1;
        private static final String Form_Master_Id = "id";
        private static final String Attr_Id = "attrid";
        private static final String  Sequence = "sequence";
        private static final String Lable = "label";
        private static final String Type = "type";
        Context context;



   private static final   String Table = "CREATE TABLE " + FORM_TABLE + "("
            + Form_Master_Id + " INTEGER ,"
            + Attr_Id + " INTEGER PRIMARY KEY ,"
            + Lable + " TEXT ,"
            + Type + " TEXT ,"
            + Sequence + " INTEGER ,"
            + "FOREIGN KEY(" + Form_Master_Id + ") REFERENCES "
            + MASTER_TABLE + " ( "+Form_Id +" ) "+ ")";

        public FormAttrDb(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Method is called during creation of the database
        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(Table);
            Log.d("database created==", "Table2 created for album one");

        }

        // Method is called during an upgrade of the database, e.g. if you increase
        // the database version
        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion,
                              int newVersion) {
            database.execSQL("DROP TABLE IF EXISTS "+FORM_TABLE);
            onCreate(database);
        }
   public void insertFormAttr(int formid, ArrayList<FormViewModel> data1){
   // public void insertFormAttr( ArrayList<FormAttributeModel> data1){
        //String sql="INSERT INTO " + FORM_TABLE +" (id,attFrid,label,type,sequence) VALUES (?,?,?,?,?) ";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        Log.d("datainserted11111","number of insertion"+ values.size());
        for (FormViewModel formAttributeModel : data1){
            Log.d("datainserted12321","number of insertion"+formAttributeModel.getId()+formAttributeModel.getLabel());
            values.put(Attr_Id, formAttributeModel.getId());
            values.put(Form_Master_Id, formid);
            values.put(Lable, formAttributeModel.getLabel());
            values.put(Type, formAttributeModel.getType());
            Log.d("datainserted12321","number of insertion"+"DATA INSRTED");
            values.put(Sequence, formAttributeModel.getSequence());
            Log.d("datainserted12321","number of insertion"+"DATA INSRTED");
            db.insertOrThrow(FORM_TABLE,null,values);
        }
        db.close();
    }
    public void deletdata(){
        SQLiteDatabase db= this.getReadableDatabase();
        db.execSQL("delete from formAttr");
        //db=this.getWritableDatabase();
        db.close();
    }

    public ArrayList<FormAttributeModel> getDataDb(int id){

        ArrayList<FormAttributeModel> datauniversal=new ArrayList();
        SQLiteDatabase database= this.getReadableDatabase();
        String selectQuery="SELECT * FROM formAttr where "+Form_Master_Id+" = "+id ;
        Log.d("query maker", selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        switch (Log.d("number of row in ", "" + cursor.getCount())) {
        }
        if (cursor.moveToFirst()) {
            do {
                datauniversal.add(new FormAttributeModel(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4)));
                Log.d("fareyetask2data","is"+datauniversal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return datauniversal;
    }

}
