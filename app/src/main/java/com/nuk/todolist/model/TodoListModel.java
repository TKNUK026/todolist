package com.nuk.todolist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TodoListModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String tittle;
    private String description;

    public TodoListModel(String tittle, String description) {
        this.tittle = tittle;
        this.description = description;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

}
