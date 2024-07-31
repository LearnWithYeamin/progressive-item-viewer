package com.myeamin.progressiveitemviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEXT1 = "text1";
    private static final String COLUMN_TEXT2 = "text2";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TEXT1 + " TEXT,"
            + COLUMN_TEXT2 + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert a new item with two text values
    public void insertItem(String text1, String text2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEXT1, text1);
        values.put(COLUMN_TEXT2, text2);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Method to get items with lastIndex and limit
    public ArrayList<HashMap<String, String>> getItems(int lastIndex, int limit) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Corrected query to use lastIndex and limit
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_TEXT1, COLUMN_TEXT2}, null, null, null, null, null, lastIndex + "," + limit);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(COLUMN_ID, String.valueOf(cursor.getInt(0)));
                hashMap.put(COLUMN_TEXT1, cursor.getString(1));
                hashMap.put(COLUMN_TEXT2, cursor.getString(2));
                arrayList.add(hashMap);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

}

