package com.example.nitesh.fareyetask2.model;

/**
 * Created by nitesh on 14/7/17.
 */

public class FormAttributeModel {
    private  int id,sequence,formid;
    private  String label,type;

    public FormAttributeModel(int formid, int id, String label, String type, int sequence) {
        this.formid = formid;
        this.id = id;
        this.label = label;
        this.type = type;
        this.sequence = sequence;
    }
    public  int getFormid() {
        return formid;
    }

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public  int getSequence() {
        return sequence;
    }

}
