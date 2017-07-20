package com.example.nitesh.fareyetask2.model;

/**
 * Created by nitesh on 18/7/17.
 */

public class FormViewModel {
    private  int id,sequence;
    private  String label,type;

    public FormViewModel(int id, String label, String type, int sequence) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.sequence = sequence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}
