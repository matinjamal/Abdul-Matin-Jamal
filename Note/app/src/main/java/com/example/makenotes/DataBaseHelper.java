package com.example.makenotes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final int  DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "NotesDatabase";

public DataBaseHelper (Context context){
    super(context,DATABASE_NAME,null,DATABASE_VERSION);

}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    String sqlQuery = "CREATE TABLE Note (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT, description TEXT)";
    sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String NewQuery = "DROP TABlE IF EXISTS note";
        sqLiteDatabase.execSQL(NewQuery);
        onCreate(sqLiteDatabase);
    }
}


// Public Class Feedbackdb extends SQllitehelper

/* private static void DATABASE_VERSION = 1;
private static void DATABASE_NAME = "feeddb"
private static void  String"CREATE_FIRST_TABLE = "create table if not exist"
          +FIRST TABLE
 */