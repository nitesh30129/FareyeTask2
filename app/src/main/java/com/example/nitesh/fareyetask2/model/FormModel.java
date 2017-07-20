package com.example.nitesh.fareyetask2.model;

import android.text.Editable;

/**
 * Created by nitesh on 17/7/17.
 */

public class FormModel {
    private  int attrid,formid,id;
    private Editable text;

    public FormModel(int id,int formid, int attrid, Editable text) {
        this.id = id;
        this.formid = formid;
        this.attrid = attrid;
        this.text = text;
    }



    public  void setId(int id) {
        this.id=id;
    }
    public  int getId() {
        return id;
    }
    public  void setFormid(int formid) {
        this.formid=formid;
    }
    public  void setAttrid(int attrid) {
        this.attrid=attrid;
    }
    public  int getAttrid() {
        return attrid;
    }
    public  int getFormid() {
        return formid;
    }
    public Editable getText(){ return text;}
}
