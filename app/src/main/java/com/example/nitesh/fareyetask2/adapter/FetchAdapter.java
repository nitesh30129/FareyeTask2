package com.example.nitesh.fareyetask2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitesh.fareyetask2.R;
import com.example.nitesh.fareyetask2.activities.FormAdd;
import com.example.nitesh.fareyetask2.activities.FormUser;
import com.example.nitesh.fareyetask2.database.FormMasterDb;
import com.example.nitesh.fareyetask2.model.FormAttributeModel;
import com.example.nitesh.fareyetask2.model.FormMasterModel;

import java.util.ArrayList;

/**
 * Created by nitesh on 16/7/17.
 */

public class FetchAdapter extends RecyclerView.Adapter<FetchAdapter.ViewHolder>{
    Context context;
    int i=0,m=1;
    public ArrayList<FormMasterModel> arrayList;
    public ArrayList<FormAttributeModel> formAttributeModelArrayList;
    FormMasterDb db;
    public static final String FORMNAME = "form_name";
    public static final String FORMID = "form_id";
    //SharedPreferences sharedPreferences=getSharedPreferences("mydata",MODE_PRIVATE);
    //SharedPreferences.Editor editor=sharedPreferences.edit();
    public FetchAdapter(Context context, ArrayList<FormMasterModel> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public FetchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.form_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FetchAdapter.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        FormMasterModel data=arrayList.get(position);
        holder.setData(data);
        //Picasso.with(context).load(data.getThumbnail()).error(R.mipmap.ic_launcher).into(holder.imageView);
        holder.text.setText(""+data.getName());
        //holder.title.setText(data.getTitle());
    }
    @Override
    public int getItemCount() {
        Log.d("count data is",""+arrayList.size());
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        FormMasterModel data;
        Button formshow,add1;
        public ViewHolder(View itemView) {
            super(itemView);
            formshow=(Button)itemView.findViewById(R.id.formview);
            text=(TextView)itemView.findViewById(R.id.text);
            add1=(Button) itemView.findViewById(R.id.add);
            formshow.setOnClickListener(this);
            add1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add:
                    if(m==0){
                        Toast.makeText(context," no data inserted",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent inten = new Intent(context, FormUser.class);
                        inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        inten.putExtra("id", arrayList.get(getAdapterPosition()).getId());
                        //intent.putExtra("attrid", formAttributeModelArrayList.get(getAdapterPosition()).getId());
                        inten.putExtra("name", arrayList.get(getAdapterPosition()).getName());

                        context.startActivity(inten);
                        //Toast.makeText(context, "data inserted", Toast.LENGTH_LONG).show();
                    }
                    Log.d("agdaudd","");
                    break;

                case R.id.formview:
                   // Toast.makeText(context,"data view",Toast.LENGTH_LONG).show();
                    //if(i==0) {
                        Intent intent = new Intent(context, FormAdd.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("id", arrayList.get(getAdapterPosition()).getId());
                        //intent.putExtra("ID", m++);
                        //intent.putExtra("attrid", formAttributeModelArrayList.get(getAdapterPosition()).getId());
                    //editor.putInt((arrayList.get(getAdapterPosition()).getName()),m);
                        intent.putExtra("name", arrayList.get(getAdapterPosition()).getName());
                        context.startActivity(intent);

                    break;
            }
        }
        public void setData(FormMasterModel data) {
            this.data = data;
        }
    }

    }
