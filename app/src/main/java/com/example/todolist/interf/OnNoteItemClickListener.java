package com.example.todolist.interf;

import com.example.todolist.model.Note;

public interface OnNoteItemClickListener {
    void onItemClick(Note note);
    void onDeleteClick(int id);
}
