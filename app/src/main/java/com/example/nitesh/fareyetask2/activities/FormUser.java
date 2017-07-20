package com.example.nitesh.fareyetask2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nitesh.fareyetask2.R;
import com.example.nitesh.fareyetask2.database.FormDb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nitesh on 18/7/17.
 */

public class FormUser extends AppCompatActivity {
    FormDb formDb;
    int id, count;
    LinearLayout linearLayout;
    TextView textview;
    RelativeLayout relativeaLayout;
    Set set;
    String name;
    // SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AddFormActivity", "in onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        Intent intent = getIntent();
        TextView formNameTextView = (TextView) findViewById(R.id.form_nameTv);
        formNameTextView.setText(intent.getStringExtra("name"));
        formDb = new FormDb(getApplicationContext());

        // ArrayList<FormAttributeModel> formAttributesArrayListModel = new FormAttrDb(this).getDataDb(intent.getIntExtra("id", 0));
        // ArrayList<FormAttributeModel> formAttributesArrayLi = new FormAttrDb(this).getDataDb(intent.getIntExtra("attrid", 0));
        linearLayout = (LinearLayout) findViewById(R.id.form_attribute_layout);
        relativeaLayout = (RelativeLayout) findViewById(R.id.relative);
        id = intent.getIntExtra("id", 0);
        //count=sharedPreferences.getInt(intent.getStringExtra("name"),0);
        // ArrayList<FormUpdateModel> formModels = new FormDb(getApplicationContext()).getDataDb(id);
        Log.d("mynameis", "" + intent.getIntExtra("id", 0));
        // Log.d("sdjchhs",""+formModels);
        set = new HashSet();
        name = intent.getStringExtra("name");
        set = new FormDb(getApplicationContext()).getuser(intent.getIntExtra("id", 0));
        Log.d("mynameis", "" + set);
        count = set.size();
        ArrayList<Integer> array = new ArrayList<>(set);
        Log.d("mynameis", "" + set.size());
        builFormView(count, array);
    }

    public void builFormView(int count, final ArrayList<Integer> array) {
        // Log.d("AddFormActivity", "in buildUserView()" + formAttributesArrayListModel.size());
        //formAttributesEditText = new EditText[formAttributesArrayListModel.size()];
        // textView = new TextView[formAttributesArrayListModel.size()];
        // attridis=new ArrayList<>();
        // formidis=new ArrayList<>();
        //textview=new TextView[count];
        for (int i = 0; i < count; i++) {
            textview = new TextView(this);
            //attridis.set(i, formAttributesArrayListModel.get(i).getId());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 30);
            //textView[i].setLayoutParams(params);
            //textview[i].setLayoutParams(params);
            textview.setText("data " + (i + 1));
            Button btn = new Button(this);
            btn.setText(" VIEW ");
            final int finalI1 = i;
            linearLayout.addView(textview);
            linearLayout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inten = new Intent(FormUser.this, FormView.class);
                    inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    inten.putExtra("id", id);
                    //intent.putExtra("attrid", formAttributeModelArrayList.get(getAdapterPosition()).getId());
                    inten.putExtra("name", name);
                    inten.putExtra("dataid", array.get(finalI1));

                    FormUser.this.startActivity(inten);
                    //Toast.makeText(context, "data inserted", Toast.LENGTH_LONG).show();
                }
                //relativeaLayout.addView(linearLayout);
            });
        }
        ;
    }


}