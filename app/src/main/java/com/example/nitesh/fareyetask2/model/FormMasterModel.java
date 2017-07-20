package com.example.nitesh.fareyetask2.model;

import java.util.ArrayList;

/**
 * Created by nitesh on 14/7/17.
 */

public class FormMasterModel {
    public int id;
    public String name;
    ArrayList<FormViewModel> formMaster;
    public FormMasterModel(int id, String name, ArrayList<FormViewModel> formMaster){
        this.id = id;
        this.name = name;
        this.formMaster = formMaster;
    }

    public FormMasterModel(int id, String name) {
        this.id = id;
        this.name = name;
    }




    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public ArrayList<FormViewModel> getFormMaster() {
        return formMaster;
    }

    public void setFormMaster(ArrayList<FormViewModel> formViewModel) {
        this.formMaster = formViewModel;
    }
}
