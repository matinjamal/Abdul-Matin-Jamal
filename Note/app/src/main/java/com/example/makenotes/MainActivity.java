package com.example.makenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

 ImageButton imageButton;
 ArrayList<Note> notes;
 RecyclerView recyclerView;
 NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    imageButton = findViewById(R.id.imageplus);

     //Inflator allows to create new nte using the image button. Which uses the note_input layout
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflator = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View input = inflator.inflate(R.layout.note_input,null,false);

                EditText edtTitle = input.findViewById(R.id.edt_title);
                EditText edtdescription = input.findViewById(R.id.edt_description);

                new AlertDialog.Builder(MainActivity.this).setView(input)
                        .setTitle("Add Note")
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String title = edtTitle.getText().toString();
                                String description = edtdescription.getText().toString();

                                Note note = new Note(title,description);

                                boolean isinserted = new NoteHandler (MainActivity.this).create(note);

                                if (isinserted){
                                    Toast.makeText(MainActivity.this,"Your Note was Saved",Toast.LENGTH_SHORT).show();
                                    loadnotes();}
                                else{
                                    Toast.makeText(MainActivity.this,"Can't Save your note",Toast.LENGTH_SHORT).show();
                                }
                                dialogInterface.cancel();
                            }
                        }).show();
                
            }
        });


        recyclerView = findViewById(R.id.recycler);

    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    //Detech when we switpe the notes left or right which will be attached to the recyclerview
        ItemTouchHelper.SimpleCallback itemtouchcallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                new NoteHandler(MainActivity.this).delete(notes.get(viewHolder.getAdapterPosition()).getId());
                notes.remove(viewHolder.getAdapterPosition());
                noteAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemtouchcallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    loadnotes();
    }


    public ArrayList<Note> readNotes(){
        ArrayList<Note> notes = new NoteHandler(this).readNotes();
        return notes;
    }
    //Loads/inflate notes to recyclcerview
public void  loadnotes(){
        notes = readNotes();

        noteAdapter = new NoteAdapter(notes,this);

        recyclerView.setAdapter(noteAdapter);
}

}