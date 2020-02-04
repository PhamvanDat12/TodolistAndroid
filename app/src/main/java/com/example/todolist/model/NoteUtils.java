package com.example.todolist.model;

import java.util.ArrayList;

public class NoteUtils {

    // singleton: sử dụng khi chỉ có duy nhất 1 thực thể và 1 class ( kho )
    private ArrayList<Note> noteData;
    private static NoteUtils noteUtils;

    public NoteUtils() {
        noteData = new ArrayList<>();
    }

    public ArrayList<Note> getAll() {
        return noteData;
    }

    public static NoteUtils getInstance() {
        if (noteUtils == null) {
            noteUtils = new NoteUtils();

        }
        return noteUtils;
    }

    public void addNote(Note note) {
        if (noteData.isEmpty()) {
            note.setId(0);
        } else {

            int lastNoteId = noteData.get(noteData.size() - 1).getId();
        }
        noteData.add(note);
    }

    public void updateNote(int id, Note newNote) {
        Note oldNote = getNoteById(id);
        if (oldNote != null) {
            oldNote.setTieuDe(newNote.getTieuDe());
            oldNote.setMoTa(newNote.getMoTa());
        }


    }

    public void deleteNote(int id) {
        for (int i = 0; i < noteData.size(); i++) {
            noteData.remove(i);
            return;
        }
    }

    private Note getNoteById(int id) {
        for (Note note : noteData) {
            if (note.getId() == id) return note;
        }
        return null;
    }

}
