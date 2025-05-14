package com.example.lab1_01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MsgAdapter {

    private Context context;

    static DataBaseHelper dbHelper ;

    public MsgAdapter(Context _context){
        dbHelper = new DataBaseHelper(_context);
        context = _context;
    }


    public long insertMessage(com.example.lab1_01.Message message){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(DataBaseHelper.MESSAGE, message.getMessage());
        contentValues.put(DataBaseHelper.NUMBER, message.getMobile());

        long id = db.insert(DataBaseHelper.MY_TABLE_NAME, null , contentValues);

        return id ;
    }

    public com.example.lab1_01.Message findMessage(String mobilenumber){
        Message msg = null ;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = {mobilenumber};

        Cursor result = db.query(DataBaseHelper.MY_TABLE_NAME , null , "number = ?", args, null , null, null);
        if (result != null){
            result.moveToNext();
            msg = new Message(result.getString(0), result.getString(1));
        }

        return msg;

    }

    static class DataBaseHelper extends SQLiteOpenHelper{
        private static final int DATABASE_VERSION = 1 ;

        private static final String DATABASE_NAME = "user_msg.db" ;

        private static final String MY_TABLE_NAME = "MESSAGES" ;

        private static final String NUMBER = "number" ;

        private static final String MESSAGE = "message" ;


        private static final String CREATE_USER_MESSAGE_TABLE = "CREATE TABLE " + MY_TABLE_NAME + " (" +NUMBER + " TEXT PRIMARY KEY, "+ MESSAGE+" TEXT);";

        public DataBaseHelper(@Nullable Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_MESSAGE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
