package com.example.nitesh.fareyetask2.adapter;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nitesh on 19/7/17.
 */

public class session {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context ctx;
    public session (Context ctx){
        this.ctx=ctx;
        preferences=ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=preferences.edit();
    }

}
