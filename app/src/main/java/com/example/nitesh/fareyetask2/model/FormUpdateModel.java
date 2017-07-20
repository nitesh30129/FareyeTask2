package com.example.nitesh.fareyetask2.model;

/**
 * Created by nitesh on 18/7/17.
 */

public class FormUpdateModel {
    private  int attrid,formid,id;
    private String text;

    public FormUpdateModel(int id, int formid, int attrid, String text) {
        this.id=id;
        this.formid = formid;
        this.attrid = attrid;
        this.text = text;
    }
    public  int getId (){
        return id;
    }
    public  void setId(int id) {
        this.id=id;
    }
    public  void setText(String text) {
        this.text=text;
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
    public String getText(){ return text;}


}
