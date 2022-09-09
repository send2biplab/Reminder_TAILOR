package com.ubk.casdis_tailor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class DbPassHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TapShare";

    // Contacts table name
    private static final String TABLE_CONTACTS = "UserInformationTap";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TIME = "time";
    private static final String KEY_TIMESMS = "timesms";



    public DbPassHandler(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_STATUS + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_TIMESMS + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }


    public long update(String newpass ,String idd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("sadpfijA'UFD12121",""+newpass);
        values.put(KEY_PASSWORD, newpass);
        // Inserting Row
        long test= db.update(TABLE_CONTACTS, values,"id = "+idd, null);
        Log.e("dgfusdgow",""+test);
        db.close(); // Closing database connection
        return test;

    }

    public long updatetime(String time ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("sadpfijA'UFD12121",""+time);
        values.put(KEY_TIME, time);
        // Inserting Row
        long test= db.update(TABLE_CONTACTS, values,null, null);
        Log.e("dgfusdgow",""+test);
        db.close(); // Closing database connection
        return test;

    }

    public long updatetimesms(String time ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("sadpfijA'UFD12121",""+time);
        values.put(KEY_TIMESMS, time);
        // Inserting Row
        long test= db.update(TABLE_CONTACTS, values,null, null);
        Log.e("dgfusdgow",""+test);
        db.close(); // Closing database connection
        return test;

    }

    public long updateStatus(String newpass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("sadpfijA'UFD",""+newpass);
        values.put(KEY_STATUS, newpass);
        // Inserting Row
        long test= db.update(TABLE_CONTACTS, values,"id = ?", new String[]{"1"});
        db.close(); // Closing database connection
        return test;
    }

    public long insert(String colour1,String Status,String time,String time2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("sadpfijA'UFD",""+colour1);
        values.put(KEY_PASSWORD, colour1);
        values.put(KEY_STATUS, Status);
        values.put(KEY_TIME, time);
        values.put(KEY_TIMESMS, time2);
        // Inserting Row
        long test= db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
        return test;
    }




    public ArrayList showAll_db()
    {



        ArrayList arrayList_MyDatabaseHelper= new ArrayList<>();


        SQLiteDatabase db=this.getReadableDatabase();
        String sqliteSelectQuery="SELECT * FROM "+TABLE_CONTACTS;
        Cursor cursor_MyDatabaseHelper=db.rawQuery(sqliteSelectQuery,null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false)
            {


                HashMap<String, String> map = new HashMap<String, String>();



                String id =cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_ID));

                String password =cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PASSWORD));

                String status =cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_STATUS));

                String time =cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_TIME));
                String time2 =cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_TIMESMS));



                map.put("id",id);
                map.put("password",password);
                map.put("status",status);
                map.put("time",time);
                map.put("timeSms",time2);




                arrayList_MyDatabaseHelper.add(map);


                // Log.d("DATA==>",str);
                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public boolean del(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_CONTACTS, KEY_ID +  "=" + id, null) > 0;

        // return true;
    }

}
