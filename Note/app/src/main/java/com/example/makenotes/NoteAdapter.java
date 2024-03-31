package com.example.makenotes;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Noteholder> {

    ArrayList<Note>notes ;
   Context context;

public NoteAdapter(ArrayList<Note> arrayList, Context context){
    notes = arrayList;
    this.context = context;
}

    @NonNull
    @Override
    public Noteholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.note_holder, parent,false);

        return new Noteholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Noteholder holder, int position) {
    holder.Title.setText(notes.get(position).getTitle());
    holder.description.setText(notes.get(position).getDescription());
    }



    @Override
    public int getItemCount() {
        return notes.size();
    }

    class Noteholder extends RecyclerView.ViewHolder {

        TextView Title;
        TextView description;
        ImageView imgedit;


        public Noteholder(@NonNull View itemView) {
            super(itemView);
            Title =itemView.findViewById(R.id.text_note_name);
            description = itemView.findViewById(R.id.text_note_description);
            imgedit = itemView.findViewById(R.id.img_edit);
        }
    }
}
