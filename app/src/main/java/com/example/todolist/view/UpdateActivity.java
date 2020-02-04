package com.example.todolist.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.dbcontext.RealmContext;
import com.example.todolist.model.Note;
import com.example.todolist.model.NoteUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {
    EditText edtTieude, edtMota;
    Button btnSave, btnCancel;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
        addListener();
    }
    private void init() {
        edtTieude = findViewById(R.id.edt_title);
        edtMota = findViewById(R.id.edt_mota);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

         note = (Note) getIntent().getSerializableExtra("note_data");

        edtTieude.setText(note.getTieuDe());
        edtMota.setText(note.getMoTa());
    }

    private void addListener() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTieude.getText().toString();
                String MoTa = edtMota.getText().toString();
                if (title == null || title.isEmpty()) {
                    showToast("Bạn chưa Nhập tiêu đề");
                    return;

                }
                if (MoTa.isEmpty()) {
                    showToast("Bạn chưa nhập Nội dung");
                    return;
                }
                note.setTieuDe(title);
                note.setMoTa(MoTa);

//                NoteUtils.getInstance().updateNote(note.getId(),note);
                RealmContext.getInstance().upDateNote(note.getId(),note);

                showToast("Chỉnh sửa thành công!");
                finish();

            }

        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
