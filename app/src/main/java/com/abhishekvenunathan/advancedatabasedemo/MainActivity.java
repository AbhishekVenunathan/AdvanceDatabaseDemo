package com.abhishekvenunathan.advancedatabasedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase userDatabase = this.openOrCreateDatabase("Users",Context.MODE_PRIVATE,null);
            userDatabase.execSQL("CREATE TABLE IF NOT EXISTS usersNew(name VARCHAR,age int(4),id INTEGER PRIMARY KEY)");
            userDatabase.execSQL("INSERT INTO usersNew (name,age) VALUES ('Abhishek1',24)");
            userDatabase.execSQL("INSERT INTO usersNew (name,age) VALUES ('Abhishek2',20)");

            Cursor c = userDatabase.rawQuery("SELECT * from usersNew",null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while(c!=null){
                Log.i("results break","-----");
                Log.i("results name:",c.getString(nameIndex));
                Log.i("results age:",Integer.toString(c.getInt(ageIndex)));
                Log.i("results id:",Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
