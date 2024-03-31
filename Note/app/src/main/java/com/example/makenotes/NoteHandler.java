package com.example.makenotes;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLData;
import java.util.ArrayList;

public class NoteHandler extends DataBaseHelper {
    public NoteHandler(Context context) {
        super(context);
    }
    // C = Create, U = upgrade, R = Read, D = Delete

    public boolean create(Note note) {
        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("description", note.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean Successful = db.insert("Note", null, values) > 0;
        db.close();
        return Successful;
    }

    public ArrayList<Note> readNotes() {
        ArrayList<Note> notes = new ArrayList<>();

        String sqlQuery = " SELECT * FROM Note ORDER BY id ASC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {

                int ci = cursor.getColumnIndex("id");
                int ti = cursor.getColumnIndex("title");
                int di = cursor.getColumnIndex("description");


                int id = Integer.parseInt(cursor.getString(ci));
                String title = cursor.getString(ti);
                String description = cursor.getString(di);

                Note note = new Note(title, description);
                note.setId(id);
                notes.add(note);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return notes;
    }

    //Read Single note

    public Note readSinglenote(int id) {
        Note note = null;
        String sqlQuery = "SELECT * FROM Note WHERE ID=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            int ci = cursor.getColumnIndex("id");
            int ti = cursor.getColumnIndex("title");
            int di = cursor.getColumnIndex("description");

            int noteid = Integer.parseInt(cursor.getString(ci));
            String title = cursor.getString(ti);
            String description = cursor.getString(di);

            note = new Note(title, description);
            note.setId(noteid);
        }

        cursor.close();
        db.close();
        return note;

    }
//Update Database
    public boolean update (Note note) {
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle());
        values.put("description",note.getDescription());
        SQLiteDatabase db =this.getWritableDatabase();
        boolean isSuccessfull = db.update("Note",values,"id"+note.getId()+"",null) > 0;
        db.close();
        return isSuccessfull;
    }
    //delte note when want to
    public boolean delete(int id){
        boolean isdeleted;
        SQLiteDatabase db = this.getWritableDatabase();
        isdeleted =db.delete("Note","id="+id+"'",null)>0;
        db.close();
        return isdeleted;
    }
}
// SQLiteDatabase Login = this.getreadabledatabase();