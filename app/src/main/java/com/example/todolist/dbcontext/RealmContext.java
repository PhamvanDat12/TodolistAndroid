package com.example.todolist.dbcontext;

import android.util.Log;

import com.example.todolist.model.Note;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RealmContext {
    private Realm realm;

    private static RealmContext realmContext;

    private RealmContext() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmContext getInstance() {
        if (realmContext == null) {
            realmContext = new RealmContext();
        }
        return realmContext;
    }

    public List<Note> getAll() {

        return realm.copyFromRealm(realm.where(Note.class).findAll());
    }

    public void addNote(Note note) {
        List<Note> noteList = getAll();
        if (noteList != null && noteList.isEmpty()) {
            note.setId(0);
        } else {
            int lastNoteId = noteList.get(noteList.size() - 1).getId();
            note.setId(lastNoteId + 1);
            Log.d("DAT", "addNote: "+lastNoteId);
        }
        realm.beginTransaction();

        Note newNote = realm.createObject(Note.class);

        newNote.coppyFrom(note);

//        newNote.setId(note.getId());
//        newNote.setTieuDe(note.getTieuDe());
//        newNote.setMoTa(note.getMoTa());
//        note.setCreateData(note.getCreateData());

        realm.copyFromRealm(newNote);

        realm.commitTransaction();
    }
  public void upDateNote(int oldId, Note newNote){

        Note oldNote = getNoteById(oldId);

        realm.beginTransaction();
        oldNote.coppyFrom(newNote);
//      oldNote.setId(newNote.getId());
//      oldNote.setTieuDe(newNote.getTieuDe());
//      oldNote.setMoTa(newNote.getMoTa());
//      oldNote.setCreateData(newNote.getCreateData());
      realm.commitTransaction();
  }
  public void deleteNote(int noteId){
        Note note = getNoteById(noteId);

        realm.beginTransaction();
        note.deleteFromRealm();
        realm.commitTransaction();
  }
  private Note getNoteById(int noteId){
        return realm.where(Note.class).equalTo("id",noteId).findFirst();
  }
}
