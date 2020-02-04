package com.example.todolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.interf.OnNoteItemClickListener;
import com.example.todolist.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Note> noteData;
    private OnNoteItemClickListener listener;

    public NoteAdapter(List<Note> noteData, OnNoteItemClickListener listener) {
        this.noteData = noteData;
        this.listener = listener;
    }
    public void updateData(List<Note> notes){
        noteData.clear();
        noteData.addAll(notes);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
         holder.bindView(noteData.get(position));
    }

    @Override
    public int getItemCount() {
        return noteData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCreateDate;
        ImageView imageView;
        private Note note;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvCreateDate=itemView.findViewById(R.id.tv_create_date);
            imageView=itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(note);

                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(note.getId());
                }
            });

        }
        private void bindView(Note note){
            this.note=note;
            tvTitle.setText(note.getTieuDe());
            tvCreateDate.setText(note.getCreateData());
        }


    }
}
