package com.appinventiv.lacture3_dummylayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PraveshSQLite.db";
    public static final String VALIDATION_TABLE_NAME = "Validation";
    public static final String VALIDATION_COLUMN_ID_1 = "id";
    public static final String VALIDATION_COLUMN_NAME_2 = "name";
    public static final String VALIDATION_COLUMN_EMAIL_3 = "email";
    public static final String VALIDATION_COLUMN_GENDER_4 = "gender";
    public static final String VALIDATION_COLUMN_HOBBIES_5 = "hobbies";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "create table " + VALIDATION_TABLE_NAME + " " +
                        "(id integer primary key AUTOINCREMENT, name text, email text, gender text,hobbies text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VALIDATION_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertValidationData(String name, String email, String gender, String hobbies) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(VALIDATION_COLUMN_NAME_2, name);
        contentValues.put(VALIDATION_COLUMN_EMAIL_3, email);
        contentValues.put(VALIDATION_COLUMN_GENDER_4, gender);
        contentValues.put(VALIDATION_COLUMN_HOBBIES_5, hobbies);

        long result = sqLiteDatabase.insert("Validation", null, contentValues);
        if (result == -1){
            return  false;

        } else{
            return true;
        }
    }

//    public Cursor getData(int id) {
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor res =  sqLiteDatabase.rawQuery( "select * from Validation where id="+id+"", null );
//        return res;
//    }
//
//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, VALIDATION_TABLE_NAME);
//        return numRows;
//    }

    public boolean updateValidationData (Integer id, String name, String email, String gender, String hobbies) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("gender", gender);
        contentValues.put("hobbies", hobbies);

        sqLiteDatabase.update("Validation", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

//    public Integer deleteValidationTable (Integer id) {
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        return sqLiteDatabase.delete("Validation",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }

    public ArrayList<String> getAllValidationData() {

        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery( "select * from Validation", null );
        if (cursor.getCount() == 0){
            Log.d("DATABASE EMPTY", "not working");
            return array_list;
        }
        cursor.moveToFirst();

        while(cursor.moveToNext()){
            array_list.add(cursor.getString(0));
            array_list.add(cursor.getString(1));
            array_list.add(cursor.getString(2));
            array_list.add(cursor.getString(3));
            array_list.add(cursor.getString(4));
        }
        return array_list;
    }
}
