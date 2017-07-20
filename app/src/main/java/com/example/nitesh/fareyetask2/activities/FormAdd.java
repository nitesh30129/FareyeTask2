package com.example.nitesh.fareyetask2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitesh.fareyetask2.R;
import com.example.nitesh.fareyetask2.database.FormAttrDb;
import com.example.nitesh.fareyetask2.database.FormDb;
import com.example.nitesh.fareyetask2.model.FormAttributeModel;
import com.example.nitesh.fareyetask2.model.FormModel;

import java.util.ArrayList;

import static android.text.InputType.TYPE_CLASS_TEXT;

/**
 * Created by nitesh on 16/7/17.
 */

public class FormAdd extends AppCompatActivity {
    LinearLayout linearLayout;
    EditText formAttributesEditText[];
    ArrayList<Integer> attridis;
    ArrayList<Integer> formidis ;
    FormDb formDb;
    int j=0,id;
    //ArrayList<FormAttributeModel> formAttributesArrayList = new FormAttrDb(this).getDataDb(intent.getIntExtra("id", 0))

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddFormActivity", "in onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form);
        Intent intent = getIntent();
        TextView formNameTextView = (TextView) findViewById(R.id.form_nameTv);
        formNameTextView.setText(intent.getStringExtra("name"));
        formDb = new FormDb(this);

        ArrayList<FormAttributeModel> formAttributesArrayListModel = new FormAttrDb(this).getDataDb(intent.getIntExtra("id", 0));
       // ArrayList<FormAttributeModel> formAttributesArrayLi = new FormAttrDb(this).getDataDb(intent.getIntExtra("attrid", 0));
        linearLayout = (LinearLayout) findViewById(R.id.form_attribute_layout);
        id=intent.getIntExtra("ID", 0);
        buildFormView(formAttributesArrayListModel);
    }

    public void saveFormButton(View view) {
        Log.d("AddFormActivity", "in saveFormButton()");
        boolean fieldNotEmpty = true;
        ArrayList<FormModel> usermodel=new ArrayList<>();
        String temp = "";
        //formAttributesArraylist
        if ( j==0 && formAttributesEditText != null) {
            for (int i = 0; i < formAttributesEditText.length; i++)  {
                if (formAttributesEditText[i].getText().length() == 0) {
                    fieldNotEmpty = false;
                } else {
                   usermodel.add(new FormModel(id,attridis.get(i),formidis.get(i),formAttributesEditText[i].getEditableText()));
                   Log.d("dataissssss",""+usermodel);
                    temp = temp + formAttributesEditText[i].getText();
                }
            }
            if (fieldNotEmpty) {
                int check=formDb.addDatatoformdb(usermodel);
                if(check==1) {
                    Toast.makeText(this, "saved successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(FormAdd.this, FormMaster.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "data not saved", Toast.LENGTH_LONG).show();
                }

            } else {
                j=0;
                Toast.makeText(this, "One or more field is empty!", Toast.LENGTH_LONG).show();
            }
            j++;
        }
        else{
            Toast.makeText(this, "already saved!", Toast.LENGTH_LONG).show();
        }
    }

    public void buildFormView(ArrayList<FormAttributeModel> formAttributesArrayListModel) {
        Log.d("AddFormActivity", "in buildUserView()" + formAttributesArrayListModel.size());
        formAttributesEditText = new EditText[formAttributesArrayListModel.size()];
         attridis=new ArrayList<>();
         formidis=new ArrayList<>();
        for (int i = 0; i < formAttributesArrayListModel.size(); i++) {
            formAttributesEditText[i] = new EditText(this);
            //attridis.set(i, formAttributesArrayListModel.get(i).getId());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 30);
            formAttributesEditText[i].setLayoutParams(params);
            formAttributesEditText[i].setHint("Enter " + formAttributesArrayListModel.get(i).getLabel());
            if (formAttributesArrayListModel.get(i).getType().equals("number")) {
                formAttributesEditText[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            } else if (formAttributesArrayListModel.get(i).getType().equals("text") || formAttributesArrayListModel.get(i).getType().equals("string")) {
                formAttributesEditText[i].setInputType(TYPE_CLASS_TEXT);
            }
            attridis.add(formAttributesArrayListModel.get(i).getId());
            formidis.add(formAttributesArrayListModel.get(i).getFormid());
            linearLayout.addView(formAttributesEditText[i]);
        }
    }
}

