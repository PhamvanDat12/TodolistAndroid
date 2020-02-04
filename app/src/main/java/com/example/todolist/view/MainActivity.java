package com.example.todolist.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.adapter.NoteAdapter;
import com.example.todolist.dbcontext.RealmContext;
import com.example.todolist.interf.OnNoteItemClickListener;
import com.example.todolist.model.Note;
import com.example.todolist.model.NoteUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnNoteItemClickListener {

    FloatingActionButton fabb;

   List<Note> noteData;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        noteAdapter.updateData(RealmContext.getInstance().getAll());
//        noteData.clear();
//        noteData.addAll(RealmContext.getInstance().getAll());
//        noteAdapter.notifyDataSetChanged();

    }

    private void init() {
        recyclerView = findViewById(R.id.rv_notes);
        fabb = findViewById(R.id.fab_add);

        noteData = RealmContext.getInstance().getAll();

        //Chưa hiểu về singleton nên kiểm tra lại
        noteData = NoteUtils.getInstance().getAll();
        // khởi tạo adapter
        noteAdapter = new NoteAdapter(noteData,this);
        recyclerView.setAdapter(noteAdapter);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
        //tạo mỗi hàng có 2 ptu
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }
//    private void dummyData() {
//        noteData = new ArrayList<>();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        for (int i = 0; i < 30; i++) {
//            noteData.add(new Note("Ghi Chú" + i, "Đây là mô tả" + i, dateFormat.format(new Date())));
//        }
//    }

    private void addListener() {
        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAddActivity();
            }
        });
    }

    private void gotoAddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
    private void gotUpdateActivity(Note note) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("note_data",note);
        startActivity(intent);
    }

    @Override
    public void onItemClick(Note note) {
        gotUpdateActivity(note);

    }

    @Override
    public void onDeleteClick(final int id) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa!")
                .setMessage("Bạn có chắc chắn muốn xóa ghi chú này k?")
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        NoteUtils.getInstance().deleteNote(id);
                        RealmContext.getInstance().deleteNote(id);
//                        noteAdapter.notifyDataSetChanged();
                        noteAdapter.updateData(RealmContext.getInstance().getAll());

                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
