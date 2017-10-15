package com.example.abdallah.recyclerviewswipetodismissoredit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdallah on 10/15/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CONTACT";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "CONTACT_LIST";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_NUMBER = "NUMBER";
    private static final String KEY_ID = "ID";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME
                + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_NUMBER + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DELETE TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void addContact(model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,model.getName());
        values.put(KEY_NUMBER,model.getNumber());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<model> getAllContacts(){
        List<model> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst())
            do {
                model model = new model();
                model.setName(cursor.getString(1));
                model.setNumber(cursor.getString(2));
                list.add(model);
            }while (cursor.moveToNext());
        return list;
    }

    public void deleteContact(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_NAME+" =?",new String[]{name});
        db.close();
    }

    public void editContact(String oldName,String newName,String oldNumber,String newNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_NAME,newName);
        newValues.put(KEY_NUMBER,newNumber);
        if (oldName.equals(newName))
            db.update(TABLE_NAME,newValues,KEY_NAME+" =?",new String[]{oldName}) ;
        else
            db.update(TABLE_NAME,newValues,KEY_NUMBER+" =?",new String[]{oldNumber});
    }
}
