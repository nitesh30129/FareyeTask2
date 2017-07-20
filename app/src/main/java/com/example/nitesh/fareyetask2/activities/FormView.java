package com.example.nitesh.fareyetask2.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nitesh.fareyetask2.R;
import com.example.nitesh.fareyetask2.database.FormAttrDb;
import com.example.nitesh.fareyetask2.database.FormDb;
import com.example.nitesh.fareyetask2.model.FormAttributeModel;
import com.example.nitesh.fareyetask2.model.FormUpdateModel;

import java.util.ArrayList;
import java.util.List;

import static android.text.InputType.TYPE_CLASS_TEXT;

/**
 * Created by nitesh on 16/7/17.
 */

public class FormView extends AppCompatActivity {
    LinearLayout linearLayout;
    EditText formAttributesEditText[];
    TextView textView;
    EditText editText[];
    ArrayList<Integer> attridis;
    ArrayList<Integer> formidis ;
    RelativeLayout relativeLayout;
    FormDb formDb;
    int id,formid;
     List<EditText> editData=new ArrayList<>();
    public java.util.ArrayList<FormAttributeModel> formAttributesArrayListModel;
   // public ArrayList<FormUpdateModel> formModels;
   // private HashMap formAttributesArrayList;
    //ArrayList<FormAttributeModel> formAttributesArrayList = new FormAttrDb(this).getDataDb(intent.getIntExtra("id", 0))

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddFormActivity", "in onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_form);
        Intent intent = getIntent();
        TextView formNameTextView = (TextView) findViewById(R.id.form_nameTv);
        //TextView formName = (TextView) findViewById(R.id.form_namev);
        formNameTextView.setText(intent.getStringExtra("name"));
        formDb = new FormDb(this);
        ArrayList<FormAttributeModel> formAttributesArrayListModel = new FormAttrDb(this).getDataDb(intent.getIntExtra("id", 0));
        ArrayList<FormUpdateModel> formModels = new FormDb(this).getDataDb(intent.getIntExtra("dataid", 0));
        Log.d("sdjchhs",""+formModels);
        id=intent.getIntExtra("dataid", 0);
        //formid=intent.getIntExtra("id", 0);
        // ArrayList<FormAttributeModel> formAttributesArrayLi = new FormAttrDb(this).getDataDb(intent.getIntExtra("attrid", 0));
        linearLayout = (LinearLayout) findViewById(R.id.form_attribute_layout);
        relativeLayout=(RelativeLayout) findViewById(R.id.relative);
        builFormView(formAttributesArrayListModel,formModels);
    }

    public void UpdateFormButton(View view) {

    }

    public void builFormView(ArrayList<FormAttributeModel> formAttributesArrayListModel,ArrayList<FormUpdateModel> formModels) {
        Log.d("AddFormActivity", "in buildUserView()" + formAttributesArrayListModel.size());
        formAttributesEditText = new EditText[formAttributesArrayListModel.size()];
       // textView = new TextView[formAttributesArrayListModel.size()];
        attridis=new ArrayList<>();
        formidis=new ArrayList<>();
        //editData=new ArrayList<>();
        for (int i = 0; i < formAttributesArrayListModel.size(); i++) {
            formAttributesEditText[i] = new EditText(this);
            textView=new TextView(this);
            //attridis.set(i, formAttributesArrayListModel.get(i).getId());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 16);
            //textView[i].setLayoutParams(params);
            textView.setPadding(10, 10, 10, 10);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setLayoutParams(params);
            textView.setText(formAttributesArrayListModel.get(i).getLabel());
            formAttributesEditText[i].setLayoutParams(params);
           // textView[i].setText(formAttributesArrayListModel.get(i).getLabel());
            formAttributesEditText[i].setText( formModels.get(i).getText());
            if (formAttributesArrayListModel.get(i).getType().equals("number")) {
                formAttributesEditText[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            } else if (formAttributesArrayListModel.get(i).getType().equals("text") || formAttributesArrayListModel.get(i).getType().equals("string")) {
                formAttributesEditText[i].setInputType(TYPE_CLASS_TEXT);
            }
            attridis.add(formAttributesArrayListModel.get(i).getId());
            formidis.add(formAttributesArrayListModel.get(i).getFormid());
           // linearLayout.addView(textView[i]);
            linearLayout.addView(textView);
            linearLayout.addView(formAttributesEditText[i]);
            Log.d("id", String.valueOf(formAttributesEditText[i]));
           // editData.add(formAttributesEditText[i]);
        }
    }
}
