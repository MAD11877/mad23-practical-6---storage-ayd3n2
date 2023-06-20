package com.example.madtemp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersDB.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String COLUMN_FOLLOWED = "followed";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE "
                + TABLE_USERS
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " INTEGER" + ")";
                db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_ID,user.getId());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        int num = 2;
        if(user.getFollowed() == true) {
            num = 1;
        } else if (user.getFollowed() == false) {
            num = 0;
        }
        Log.v("Number",Integer.toString(num));
        values.put(COLUMN_FOLLOWED, num);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findUser(String username) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User usr = new User();
        if (cursor.moveToFirst()) {
            usr.setId(Integer.parseInt(cursor.getString(0)));
            usr.setName(cursor.getString(1));
            usr.setDescription(cursor.getString(2));
            Boolean fo = false;
            if(Integer.parseInt(cursor.getString(3)) == 1){
                fo = true;
            }
            usr.setFollowed(fo);
            cursor.close();
        } else {
            usr = null;
        }
        db.close();
        return usr;
    }

    public ArrayList<User> getUser(){

        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<User> userList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String i = cursor.getString(3);
                Boolean fol = false;
                if(Integer.parseInt(i) == 1){
                    fol = true;
                }
                Log.v("power",cursor.getString(0) + " " + i);
                User user = new User(cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(0)),
                        fol);
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    public void updateUser(int id,Boolean fol){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int num = 2;
        if(fol == true){
            num = 1;
        } else if (fol == false) {
            num = 0;
        }
        values.put(COLUMN_FOLLOWED,num);

        db.update(TABLE_USERS, values, "_id=?", new String[]{Integer.toString(id)});
        db.close();
    }



}
