package com.example.todolist.model;

import java.io.Serializable;

import io.realm.RealmObject;

public class Note extends RealmObject implements Serializable {
    private int id;

    private String tieuDe;
    private String moTa;
    private String createData;



    public Note() {
    }

    public Note(String tieuDe, String moTa, String createData) {
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.createData = createData;
    }
    public void coppyFrom(Note newNote){
        this.setId(newNote.getId());
        this.setTieuDe(newNote.getTieuDe());
        this.setMoTa(newNote.getMoTa());
        this.setCreateData(newNote.getCreateData());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData;
    }
}
