package com.ubk.casdis_tailor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DbLoginStatus";

    private static final String TABLE_CONTACTS = "loginstatus";
    private static final String TABLE_CLINIC_USER = "CLINIC_DATA";
    private static final String TABLE_ALL_RECORD = "ALL_RECORD";
    private static final String TABLE_KEYWORD_LIST = "KEYWORD_LIST";
    private static final String TABLE_KEYWORD_LIST_CALL = "KEYWORD_LIST_CALL";

    private static final String TABLE_CALL_DOC = "TblcallDoc";


    private static final String KEY_ID = "id";
    private static final String KEY_PROMO_CODE_NAME = "PromoCodeName";
    private static final String KEY_PROMO_CODE_DESCRIPTION = "PromoCodeDescription";
    private static final String KEY_PROMO_CODE_TYPE = "Messagetype";
    private static final String KEY_PROMO_DOC_PATH = "docPath";

    private static final String KEY_IDNEW = "idnew";
    private static final String KEY_MOBILE_NUMBER = "MobileNumber";
    private static final String KEY_PROMO_CODE_LIST = "PromoCodeList";
    private static final String KEY_PROMO_CODE_DESC = "PromoCodeDesc";

    private static final String KEY_IDRECORD = "id";
    private static final String KEY_RECORD_MOBILE_NUMBER = "MobileNumber";
    private static final String KEY_RECORD_DATE = "Date";
    private static final String KEY_RECORD_NAME = "Name";
    private static final String KEY_RECORD_STATUS = "Status";
    private static final String KEY_RECORD_KEY = "KeyWord";
    private static final String KEY_CALL_TYPE = "CallType";
    private static final String KEY_ALERM_DATE = "AlermDate";
    private static final String KEY_ALERM_TIME = "AlermTime";
    private static final String KEY_ALERM_NOTE = "AlermNote";
    private static final String KEY_ALERM_DATETIME = "AlermDatetime";
    private static final String NUMBER_OF_PEOPLE = "NumberOfPeople";
    private static final String KEY_TIME = "TIME";
    private static final String KEY_DAY = "DAY";

    private static final String ORDER_DATE = "ORDER_DATE";
    private static final String TRIAL_DATE = "TRIAL_DATE";
    private static final String DELIVERY_DATE = "DELIVERY_DATE";
    private static final String SALESMAN = "SALESMAN";
    private static final String REMARK = "REMARK";
    private static final String GUEST_TYPE = "GUEST_TYPE";
    private static final String DATE = "DATE";
    private static final String FOLLOWUP = "FOLLOWUP_DATE";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CLINIC_TABLE);
        db.execSQL(CREATE_ALLRECORD_TABLE);
        db.execSQL(CREATE_KEYWORD_TABLE);
        db.execSQL(CREATE_KEYWORD_TABLE_CALL);
        db.execSQL(CREATE_CALL_DOC);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLINIC_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALL_RECORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALL_DOC);
        onCreate(db);

    }


    private static  String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_PROMO_CODE_NAME + " TEXT,"
            + KEY_PROMO_CODE_DESCRIPTION + " TEXT,"
            + KEY_PROMO_CODE_TYPE + " TEXT,"
            + KEY_PROMO_DOC_PATH + " TEXT"
            + ")";


    private static  String CREATE_CLINIC_TABLE = "CREATE TABLE " + TABLE_CLINIC_USER + "("
            +KEY_IDNEW + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_MOBILE_NUMBER + " TEXT,"
            +KEY_PROMO_CODE_LIST + " TEXT,"
            +KEY_PROMO_CODE_DESC + " TEXT"
            + ")";


    private static  String CREATE_ALLRECORD_TABLE = "CREATE TABLE " + TABLE_ALL_RECORD + "("
            +KEY_IDRECORD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_RECORD_MOBILE_NUMBER + " TEXT,"
            //  +KEY_RECORD_PROMO_CODE + " TEXT,"
            +KEY_RECORD_NAME + " TEXT,"
            +ORDER_DATE + " TEXT,"
            +TRIAL_DATE + " TEXT,"
            +DELIVERY_DATE + " TEXT,"
            +FOLLOWUP + " TEXT,"
            +DATE + " TEXT,"
            +SALESMAN+ " TEXT,"
            +REMARK+ " TEXT,"
            +GUEST_TYPE+ " TEXT,"
            +KEY_RECORD_STATUS+ " TEXT"
            + ")";


    private static  String CREATE_KEYWORD_TABLE = "CREATE TABLE " + TABLE_KEYWORD_LIST + "("
            +KEY_IDRECORD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_RECORD_MOBILE_NUMBER + " TEXT,"
            +KEY_RECORD_DATE + " TEXT,"
            +KEY_RECORD_STATUS + " TEXT,"
            +KEY_RECORD_KEY + " TEXT"
            + ")";



    private static  String CREATE_KEYWORD_TABLE_CALL = "CREATE TABLE " + TABLE_KEYWORD_LIST_CALL + "("
            +KEY_IDRECORD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_RECORD_MOBILE_NUMBER + " TEXT,"
            +KEY_RECORD_DATE + " TEXT,"
            +KEY_RECORD_STATUS + " TEXT,"
            +KEY_CALL_TYPE + " TEXT,"
            +KEY_ALERM_DATE + " TEXT,"
            +KEY_ALERM_TIME + " TEXT,"
            +KEY_ALERM_NOTE + " TEXT,"
            +KEY_ALERM_DATETIME + " TEXT"
            + ")";


    private static  String CREATE_CALL_DOC = "CREATE TABLE " + TABLE_CALL_DOC + "("
            +KEY_IDRECORD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_RECORD_KEY + " TEXT,"
            +KEY_PROMO_CODE_TYPE + " TEXT,"
            +KEY_PROMO_DOC_PATH + " TEXT"
            + ")";





    public long insert(String PromoCodeName, String PromoCodeDescription,String Messagetype,String imgpath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PROMO_CODE_NAME, PromoCodeName);
        values.put(KEY_PROMO_CODE_DESCRIPTION, PromoCodeDescription);
        values.put(KEY_PROMO_CODE_TYPE, Messagetype);
        values.put(KEY_PROMO_DOC_PATH, imgpath);

        long test = db.insert(TABLE_CONTACTS, null, values);
        db.close();
        return test;
    }



    public long call_docinsert(String key, String type,String docpath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_KEY, key);

        values.put(KEY_PROMO_CODE_TYPE, type);
        values.put(KEY_PROMO_DOC_PATH, docpath);

        long test = db.insert(TABLE_CALL_DOC, null, values);
        db.close();
        return test;
    }



    public long mobileNoInsert(String MobileNumber, String PromoCodeList, String PromoCodeDesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_MOBILE_NUMBER, MobileNumber);
        values.put(KEY_PROMO_CODE_LIST, PromoCodeList);
        values.put(KEY_PROMO_CODE_DESC, PromoCodeDesc);

        long test = db.insert(TABLE_CLINIC_USER, null, values);
        db.close();
        return test;
    }




    public long allRecordInsert(String RecordMobileNumber, String name, String orderdate,String traildate,String deliverydate,String salesman,String guesttype,String remark,String date,String follow) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        values.put(KEY_RECORD_NAME, name);
        values.put(ORDER_DATE, orderdate);
        values.put(TRIAL_DATE, traildate);
        values.put(DELIVERY_DATE, deliverydate);
        values.put(SALESMAN, salesman);
        values.put(GUEST_TYPE, guesttype);
        values.put(DATE, date);
        values.put(FOLLOWUP, follow);
        values.put(REMARK, remark);
        values.put(KEY_RECORD_STATUS, "false");

        long test = db.insert(TABLE_ALL_RECORD, null, values);
        Log.e("=========112======",".."+test);
        db.close();
        return test;
    }
    public long statusUpdateData(String id,String RecordMobileNumber, String name, String orderdate,String traildate,String deliverydate,String salesman,String guesttype,String remark,String date,String follow) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

      //  values.put("id", id);
        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        values.put(KEY_RECORD_NAME, name);
        values.put(ORDER_DATE, orderdate);
        values.put(TRIAL_DATE, traildate);
        values.put(DELIVERY_DATE, deliverydate);
        values.put(SALESMAN, salesman);
        values.put(GUEST_TYPE, guesttype);
        values.put(DATE, date);
        values.put(FOLLOWUP, follow);
        values.put(REMARK, remark);
        values.put(KEY_RECORD_STATUS, "false");


        long test= db.update(TABLE_ALL_RECORD, values,"id = ?", new String[]{id});

        Log.e("dfsds","sadasdasdas"+test);
        db.close();
        return test;

    }


    public long allKeywordInsert(String RecordMobileNumber, String RecordDate,String status,String keyword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);

        values.put(KEY_RECORD_DATE, RecordDate);

        values.put(KEY_RECORD_STATUS, status);

        values.put(KEY_RECORD_KEY, keyword);


        long test = db.insert(TABLE_KEYWORD_LIST, null, values);
        db.close();
        return test;
    }


    public long allKeywordInsert_call(String RecordMobileNumber, String RecordDate ,String status,String inout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);

        values.put(KEY_RECORD_DATE, RecordDate);
        values.put(KEY_RECORD_STATUS, status);
        values.put(KEY_CALL_TYPE, inout);
        values.put(KEY_ALERM_DATE, "notset");
        values.put(KEY_ALERM_TIME, "notset");
        values.put(KEY_ALERM_NOTE, "notset");
        values.put(KEY_ALERM_DATETIME, "0");


        long test = db.insert(TABLE_KEYWORD_LIST_CALL, null, values);
        db.close();
        return test;
    }

    // TABLE_CONTACTS



    public ArrayList showAll_db_byid_call(String idd) {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_CALL_DOC +" WHERE "+ KEY_IDRECORD +" = "+idd;
        Log.e("fcgfch","...."+sqliteSelectQuery);
        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String id = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String PromoCodeName = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_KEY));


                String type = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_TYPE));

                String imgpath = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_DOC_PATH));


                map.put("id", id);
                map.put("PromoCodeName", PromoCodeName);

                map.put("type", type);
                map.put("imgpath", imgpath);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList showAll_db_byid(String idd) {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_CONTACTS +" WHERE "+ KEY_ID +" = "+idd;
        Log.e("fcgfch","...."+sqliteSelectQuery);
        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String id = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_ID));

                String PromoCodeName = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_NAME));

                String PromoCodeDescription = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_DESCRIPTION));

                String type = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_TYPE));

                String imgpath = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_DOC_PATH));


                map.put("id", id);
                map.put("PromoCodeName", PromoCodeName);
                map.put("PromoCodeDescription", PromoCodeDescription);
                map.put("type", type);
                map.put("imgpath", imgpath);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList showAll_db_call() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_CALL_DOC;
        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String id = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String PromoCodeName = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_KEY));

//                String PromoCodeDescription = cursor_MyDatabaseHelper.getString(
//                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_DESCRIPTION));

                String type = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_TYPE));

                String imgpath = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_DOC_PATH));


                map.put("id", id);
                map.put("PromoCodeName", PromoCodeName);

                map.put("type", type);
                map.put("imgpath", imgpath);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }

    public ArrayList showAll_db() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String id = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_ID));

                String PromoCodeName = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_NAME));

                String PromoCodeDescription = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_DESCRIPTION));

                String type = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_TYPE));

                String imgpath = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_DOC_PATH));


                map.put("id", id);
                map.put("PromoCodeName", PromoCodeName);
                map.put("PromoCodeDescription", PromoCodeDescription);
                map.put("type", type);
                map.put("imgpath", imgpath);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList MobileNoShowAll_db() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER +" WHERE "+ KEY_PROMO_CODE_LIST +  " = 'Pending' GROUP BY " + KEY_MOBILE_NUMBER;

        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDNEW));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_MOBILE_NUMBER));

                String PromoCodeList = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_LIST));

                String PromoCodeDesc = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_DESC));



                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("PromoCodeList", PromoCodeList);
                map.put("PromoCodeDesc", PromoCodeDesc);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList MobileNoShowAll_db2() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER +" WHERE "+ KEY_PROMO_CODE_DESC + " = 'Pending' GROUP BY " + KEY_MOBILE_NUMBER;

        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDNEW));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_MOBILE_NUMBER));

                String PromoCodeList = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_LIST));

                String PromoCodeDesc = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_PROMO_CODE_DESC));



                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("PromoCodeList", PromoCodeList);
                map.put("PromoCodeDesc", PromoCodeDesc);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList All_Record_Show_db(String date,String type) {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_ALL_RECORD+ " where "+type +" = "+'"'+date+'"';
       // String sqliteSelectQuery = "SELECT * FROM " + TABLE_ALL_RECORD;
        Log.e("======232323=====",""+sqliteSelectQuery);
        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idrecord = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String RecordMobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String RecordnName = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_NAME));


                map.put("idrecord", idrecord);
                map.put("RecordMobileNumber", RecordMobileNumber);
                map.put("RecordnName", RecordnName);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList All_Record_Show_db(String id) {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
       String sqliteSelectQuery = "SELECT * FROM " + TABLE_ALL_RECORD+ " where "+KEY_IDRECORD +" ="+ id;
       // String sqliteSelectQuery = "SELECT * FROM " + TABLE_ALL_RECORD;
        Log.e("======232323=====",""+sqliteSelectQuery);
        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idrecord = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String RecordMobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String RecordnName = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_NAME));

                String ORDER = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(ORDER_DATE));

                String TRIAL = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(TRIAL_DATE));

                String DELIVERY = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(DELIVERY_DATE));

                String SALESMAN1 = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(SALESMAN));

                String GUEST = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(GUEST_TYPE));

                String DATEe = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(DATE));

                String FOLLOW_UP = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(FOLLOWUP));

                String REMARKk = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(REMARK));





                map.put("idrecord", idrecord);
                map.put("RecordMobileNumber", RecordMobileNumber);
                map.put("RecordnName", RecordnName);
                map.put("ORDER", ORDER);
                map.put("TRIAL", TRIAL);
                map.put("DELIVERY", DELIVERY);
                map.put("SALESMAN", SALESMAN1);
                map.put("GUEST", GUEST);
                map.put("DATEe", DATEe);
                map.put("FOLLOW_UP", FOLLOW_UP);
                map.put("REMARKk", REMARKk);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }

    public ArrayList All_Record_Show_db() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
       // String sqliteSelectQuery = "SELECT * FROM " + TABLE_ALL_RECORD+ " where "+KEY_RECORD_STATUS +" = 'false'";
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_ALL_RECORD;
        Log.e("======232323=====",""+sqliteSelectQuery);
        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);

        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idrecord = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String RecordMobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String RecordnName = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_NAME));


                map.put("idrecord", idrecord);
                map.put("RecordMobileNumber", RecordMobileNumber);
                map.put("RecordnName", RecordnName);

                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }

    public List<Contact2> getAllContactsbynumber(String key) {
        List<Contact2> contactList = new ArrayList<Contact2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS + " where "+KEY_PROMO_CODE_NAME +" = "+"'"+key + "'";

        Log.e("ddddddddddd","....."+selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact2 contact = new Contact2();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //contact.getKey_id(cursor.getString(3));
                contact.setkey_imgpath(cursor.getString(4));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list

        Log.d("dfsafs",""+contactList);
        return contactList;
    }

    public List<Contact2> getAllContacts() {
        List<Contact2> contactList = new ArrayList<Contact2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact2 contact = new Contact2();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //contact.setKey_id(cursor.getString(3));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list

        Log.d("dfsafs",""+contactList);
        return contactList;
    }

    public List<Contact2> getAllContactsCall() {
        List<Contact2> contactList = new ArrayList<Contact2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CALL_DOC;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact2 contact = new Contact2();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setKey_id(cursor.getString(3));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list

        Log.d("dfsafs",""+contactList);
        return contactList;
    }


    public long sendmsgUpdate(String RecordMobileNumber, String RecordStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        values.put(KEY_PROMO_CODE_LIST, RecordStatus);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_CLINIC_USER, values,"MobileNumber = ?", new String[]{RecordMobileNumber});
        db.close();
        return test;

    }

    public long sendmsgUpdate_byid(String iddd,String PromoCodeName, String PromoCodeDescription,String Messagetype,String imgpath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PROMO_CODE_NAME, PromoCodeName);
        values.put(KEY_PROMO_CODE_DESCRIPTION, PromoCodeDescription);
        values.put(KEY_PROMO_CODE_TYPE, Messagetype);
        values.put(KEY_PROMO_DOC_PATH, imgpath);

        long test= db.update(TABLE_CONTACTS, values,"id = ?", new String[]{iddd});
        db.close();
        return test;

    }


    public long sendmsgUpdate_byid_call(String iddd,String PromoCodeName,String Messagetype,String imgpath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_KEY, PromoCodeName);

        values.put(KEY_PROMO_CODE_TYPE, Messagetype);
        values.put(KEY_PROMO_DOC_PATH, imgpath);

        Log.e("asasasasa"+iddd,".."+values);

        long test= db.update(TABLE_CALL_DOC, values,"idrecord = ?", new String[]{iddd});
        db.close();
        return test;

    }





    public long sendmsgUpdate_byid_list(String RecordMobileNumber, String RecordStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // values.put(KEY_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        values.put(KEY_PROMO_CODE_LIST, RecordStatus);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_CLINIC_USER, values,"idnew = ?", new String[]{RecordMobileNumber});
        db.close();
        return test;

    }




    public long statusUpdate(String RecordMobileNumber, String RecordPromoCode, String RecordPromoCodeDescription, String RecordDate, String RecordStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        values.put(KEY_RECORD_DATE, RecordDate);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_ALL_RECORD, values,"RecordMobileNumber = ?", new String[]{RecordMobileNumber});
        db.close();
        return test;

    }



//
//    public long statusUpdate(String statusss) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        Log.d("htyjuku", "" + statusss);
//
//        values.put(KEY_RECORD_STATUS, statusss);
//
//        long test = db.update(TABLE_ALL_RECORD, values, "RecordMobileNumber= "+CheckPromoCodeActivity.checkPromoCodeMobileNo, null);
//
//        Log.e("dgfusdgoow", "" + test);
//
//        db.close();
//        return test;
//    }
//


    public ArrayList MobileKeyword_db_Call2() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;

        String sqliteSelectQuery = " SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " where " + KEY_RECORD_STATUS + " != 'True' AND  " + KEY_RECORD_STATUS + " != "+  "'Del'"+" ORDER BY idrecord ASC" ;
        //  String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " ORDER BY idrecord ASC";

        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);


        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String MobileStatus = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_STATUS));

                String PromoCodeDate = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_DATE));


                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("MobileStatus", MobileStatus);
                map.put("PromoCodedate", PromoCodeDate);


                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList MobileKeyword_All_SMS2() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
        // String Query = " SELECT * FROM " + TABLE_CLINIC_USER + " where " + KEY_MOBILE_NUMBER + " = " + "'" + MobileNoTitle + "'";
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " where "+ KEY_RECORD_DATE + " = " + "'"+keyyyyy + "'" +" GROUP BY " + KEY_RECORD_MOBILE_NUMBER;;

        String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST  + " ORDER BY idrecord ASC";
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " ORDER BY idrecord DESC";


        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);


        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String PromoCodeDate = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_DATE));

                String status = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_STATUS));

                String keyyyy = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_KEY));




                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("PromoCodedate", PromoCodeDate);
                map.put("status", status);
                map.put("keyword", keyyyy);


                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList MobileKeyword_db_Call21() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;

        String sqliteSelectQuery = " SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " where " + KEY_RECORD_STATUS + " == 'True' ORDER BY idrecord ASC" ;
        //  String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " ORDER BY idrecord ASC";

        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);


        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String MobileStatus = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_STATUS));

                String PromoCodeDate = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_DATE));


                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("MobileStatus", MobileStatus);
                map.put("PromoCodedate", PromoCodeDate);


                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public ArrayList MobileKeyword_db_Call(String datetime) {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
        // String Query = " SELECT * FROM " + TABLE_CLINIC_USER + " where " + KEY_MOBILE_NUMBER + " = " + "'" + MobileNoTitle + "'";
        //  String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " ORDER BY idrecord ASC";

        //  String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " where " + KEY_RECORD_STATUS + " != "+  "'Del'" + " ORDER BY idrecord ASC";

        //Set Alerm--

        String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " where " + KEY_RECORD_STATUS + " != "+  "'Del'" + " AND "+ KEY_ALERM_DATETIME + " <= "+ "'"+datetime+"'" +" ORDER BY idrecord ASC";


        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);


        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String MobileStatus = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_STATUS));

                String PromoCodeDate = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_DATE));

                String CallType = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_CALL_TYPE));


                String AlermDate = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_ALERM_DATE));

                String AlermTime = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_ALERM_TIME));

                String AlermNote = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_ALERM_NOTE));


                String AlermDateTime = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_ALERM_DATETIME));



                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("MobileStatus", MobileStatus);
                map.put("PromoCodedate", PromoCodeDate);
                map.put("callType", CallType);
                map.put("AlermDate", AlermDate);
                map.put("AlermTime", AlermTime);
                map.put("AlermNote", AlermNote);
                map.put("AlermDateTime", AlermDateTime);


                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


//    public ArrayList MobileKeyword_db_Call() {
//
//        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
//        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
//        // String Query = " SELECT * FROM " + TABLE_CLINIC_USER + " where " + KEY_MOBILE_NUMBER + " = " + "'" + MobileNoTitle + "'";
//        //  String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
//        String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " ORDER BY idrecord ASC";
//
//        Log.e("sertyft","fghghj"+sqliteSelectQuery);
//
//        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);
//
//
//        cursor_MyDatabaseHelper.moveToLast();
//
//        if (cursor_MyDatabaseHelper.moveToLast()) {
//            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
//                HashMap<String, String> map = new HashMap<String, String>();
//
//                String idnew = cursor_MyDatabaseHelper.getString(
//                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));
//
//                String MobileNumber = cursor_MyDatabaseHelper.getString(
//                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));
//
//                String MobileStatus = cursor_MyDatabaseHelper.getString(
//                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_STATUS));
//
//                String PromoCodeDate = cursor_MyDatabaseHelper.getString(
//                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_DATE));
//
//
//                map.put("idnew", idnew);
//                map.put("MobileNumber", MobileNumber);
//                map.put("MobileStatus", MobileStatus);
//                map.put("PromoCodedate", PromoCodeDate);
//
//
//                arrayList_MyDatabaseHelper.add(map);
//
//                cursor_MyDatabaseHelper.moveToPrevious();
//            }
//            cursor_MyDatabaseHelper.close();
//        }
//        return arrayList_MyDatabaseHelper;
//    }



    public ArrayList MobileKeyword_db(String keyyyyy) {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
        // String Query = " SELECT * FROM " + TABLE_CLINIC_USER + " where " + KEY_MOBILE_NUMBER + " = " + "'" + MobileNoTitle + "'";
        String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " where "+ KEY_RECORD_DATE + " = " + "'"+keyyyyy + "'" +" GROUP BY " + KEY_RECORD_MOBILE_NUMBER;;

        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);


        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String PromoCodeDate = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_DATE));


                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("PromoCodedate", PromoCodeDate);


                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }

    public ArrayList MobileKeyword_All_SMS() {

        ArrayList arrayList_MyDatabaseHelper = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_CLINIC_USER;
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " GROUP BY " + KEY_RECORD_MOBILE_NUMBER;
        // String Query = " SELECT * FROM " + TABLE_CLINIC_USER + " where " + KEY_MOBILE_NUMBER + " = " + "'" + MobileNoTitle + "'";
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST + " where "+ KEY_RECORD_DATE + " = " + "'"+keyyyyy + "'" +" GROUP BY " + KEY_RECORD_MOBILE_NUMBER;;

        String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST  + " ORDER BY idrecord ASC";
        // String sqliteSelectQuery = "SELECT * FROM " + TABLE_KEYWORD_LIST_CALL + " ORDER BY idrecord DESC";


        Log.e("sertyft","fghghj"+sqliteSelectQuery);

        Cursor cursor_MyDatabaseHelper = db.rawQuery(sqliteSelectQuery, null);


        cursor_MyDatabaseHelper.moveToLast();

        if (cursor_MyDatabaseHelper.moveToLast()) {
            while (cursor_MyDatabaseHelper.isBeforeFirst() == false) {
                HashMap<String, String> map = new HashMap<String, String>();

                String idnew = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_IDRECORD));

                String MobileNumber = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_MOBILE_NUMBER));

                String PromoCodeDate = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_DATE));

                String status = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_STATUS));

                String keyyyy = cursor_MyDatabaseHelper.getString(
                        cursor_MyDatabaseHelper.getColumnIndex(KEY_RECORD_KEY));




                map.put("idnew", idnew);
                map.put("MobileNumber", MobileNumber);
                map.put("PromoCodedate", PromoCodeDate);
                map.put("status", status);
                map.put("keyword", keyyyy);


                arrayList_MyDatabaseHelper.add(map);

                cursor_MyDatabaseHelper.moveToPrevious();
            }
            cursor_MyDatabaseHelper.close();
        }
        return arrayList_MyDatabaseHelper;
    }


    public boolean checkIfMyTitleExists(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_CONTACTS + " where " + KEY_PROMO_CODE_NAME + " = " + "'" + title + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();

        return true;
    }


    public boolean checkPhone(String number, String time)
    {
        SQLiteDatabase db = this.getReadableDatabase();
         String Query = "Select * from " + TABLE_KEYWORD_LIST_CALL + " where " + KEY_RECORD_MOBILE_NUMBER + " = " + "'" + number + "'"+" AND "+ KEY_RECORD_DATE + " = "+  "'"+ time+"'";
        //String Query = "Select * from " + TABLE_KEYWORD_LIST_CALL + " where " + KEY_RECORD_MOBILE_NUMBER + " = " + "'" + number + "'"+" AND "+ KEY_RECORD_STATUS+"" + " == "+  "'Del'";

        Log.e("sdfsdfsdffsd",".."+Query);
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();

        return true;
    }


    public boolean checkPhoneSms(String number)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_KEYWORD_LIST + " where " + KEY_RECORD_MOBILE_NUMBER + " = " + "'" + number + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();

        return true;
    }



    public String promoName2(String mobileNo)
    {
        String CodeName=null;

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_KEYWORD_LIST + " where " + KEY_RECORD_MOBILE_NUMBER + " = " + "'" + mobileNo + "'";

        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.moveToFirst()) {
            do {
                CodeName = cursor.getString(cursor.getColumnIndex("KeyWord"));
            } while (cursor.moveToNext());
        }

        return CodeName;

    }




    public boolean MobileNoCheckIfMyTitleExists(String MobileNoTitle) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_CLINIC_USER + " where " + KEY_MOBILE_NUMBER + " = " + "'" + MobileNoTitle + "'";
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();

        return true;
    }




    public String promoName(String mobileNo)
    {
        String CodeName=null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CLINIC_DATA WHERE MobileNumber = ?", new String[]{String.valueOf(mobileNo)}, null);
        if (cursor.moveToFirst()) {
            do {
                CodeName = cursor.getString(cursor.getColumnIndex("PromoCodeList"));
            } while (cursor.moveToNext());
        }

        return CodeName;

    }


    public String promoDescrip(String mobileNo)
    {
        String CodeDesc=null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CLINIC_DATA WHERE MobileNumber = ?", new String[]{String.valueOf(mobileNo)}, null);
        if (cursor.moveToFirst()) {
            do {
                CodeDesc = cursor.getString(cursor.getColumnIndex("PromoCodeDesc"));
            } while (cursor.moveToNext());
        }

        return CodeDesc;

    }



    public void deleteall() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_ALL_RECORD, null, null);
        db.delete(TABLE_CLINIC_USER, null, null);
        db.delete(TABLE_KEYWORD_LIST, null, null);
        db.delete(TABLE_KEYWORD_LIST_CALL, null, null);

    }


    public long CallstatusUpdate(String RecordMobileNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        values.put(KEY_RECORD_STATUS, "False");

        Log.e("dgsdf","fdasdasd"+RecordMobileNumber);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_KEYWORD_LIST_CALL, values,"RecordMobileNumber = ?", new String[]{RecordMobileNumber});

        Log.e("dfsds","sadasdasdas"+test);
        db.close();
        return test;

    }




    public void deleteDeletePhonhomee(String phone)
    {
        getWritableDatabase().delete(TABLE_ALL_RECORD, KEY_RECORD_MOBILE_NUMBER +"=?", new String[]{phone});
    }



    public long CallstatusAlerm(String Recordid,String date,String time,String desc,String milisec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        values.put(KEY_ALERM_DATE, date);
        values.put(KEY_ALERM_TIME, time);
        values.put(KEY_ALERM_NOTE, desc);
        values.put(KEY_ALERM_DATETIME, milisec);


        Log.e("dgsdf","fdasdasd"+Recordid);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_KEYWORD_LIST_CALL, values,"idrecord = ?", new String[]{Recordid});

        Log.e("dfsds","sadasdasdas....Alerm"+test);
        db.close();
        return test;

    }


    public long CallstatusUpdate2(String RecordMobileNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        values.put(KEY_RECORD_STATUS, "Done");

        Log.e("dgsdf","fdasdasd"+RecordMobileNumber);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_KEYWORD_LIST_CALL, values,"RecordMobileNumber = ?", new String[]{RecordMobileNumber});

        Log.e("dfsds","sadasdasdas"+test);
        db.close();
        return test;

    }


    public long CallstatusUpdate3(String RecordMobileNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        values.put(KEY_RECORD_STATUS, "Del");

        Log.e("dgsdf","fdasdasd"+RecordMobileNumber);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_KEYWORD_LIST_CALL, values,"RecordMobileNumber = ?", new String[]{RecordMobileNumber});

        Log.e("dfsds","sadasdasdas"+test);
        db.close();
        return test;

    }


    public void deleteData(String PromoCodeName)
    {
        getWritableDatabase().delete(TABLE_CONTACTS, KEY_PROMO_CODE_NAME +"=?", new String[]{PromoCodeName});
    }

    public void deleteDeletePhone(String phone)
    {
        getWritableDatabase().delete(TABLE_KEYWORD_LIST, KEY_RECORD_MOBILE_NUMBER +"=?", new String[]{phone});
    }


    public long SMSstatusUpdate(String RecordMobileNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_RECORD_MOBILE_NUMBER, RecordMobileNumber);
        // values.put(KEY_RECORD_PROMO_CODE, RecordPromoCode);
        // values.put(KEY_RECORD_PROMO_CODE_DESCRIPTION, RecordPromoCodeDescription);
        //values.put(KEY_RECORD_STATUS, "False");
        values.put(KEY_RECORD_STATUS, "False");

        Log.e("dgsdf","fdasdasd"+RecordMobileNumber);
        // values.put(KEY_RECORD_STATUS, RecordStatus);

        long test= db.update(TABLE_KEYWORD_LIST, values,"RecordMobileNumber = ?", new String[]{RecordMobileNumber});

        Log.e("dfsds","sadasdasdas"+test);
        db.close();
        return test;

    }


    public void deleteDeletePhoneCall(String phone)
    {
        getWritableDatabase().delete(TABLE_KEYWORD_LIST_CALL, KEY_RECORD_MOBILE_NUMBER +"=?", new String[]{phone});
    }



    public void deleteallCAllAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        // db.delete(TABLE_ALL_RECORD, null, null);
        //db.delete(TABLE_CLINIC_USER, null, null);
        //db.delete(TABLE_KEYWORD_LIST, null, null);
        db.delete(TABLE_KEYWORD_LIST_CALL, null, null);

    }

    public void deleteallCAllSMS() {
        SQLiteDatabase db = this.getReadableDatabase();
        // db.delete(TABLE_ALL_RECORD, null, null);
        //db.delete(TABLE_CLINIC_USER, null, null);
        //db.delete(TABLE_KEYWORD_LIST, null, null);
        db.delete(TABLE_KEYWORD_LIST, null, null);

    }


    public void deleteMobileWithPromo(String MobileNumber)
    {
        getWritableDatabase().delete(TABLE_CLINIC_USER, KEY_MOBILE_NUMBER +"=?", new String[]{MobileNumber});
    }


    public void deleteData_id(String id)
    {
        getWritableDatabase().delete(TABLE_CONTACTS, KEY_ID +"=?", new String[]{id});
    }


    public void deleteData_id2(String id)
    {
        getWritableDatabase().delete(TABLE_CALL_DOC, KEY_IDRECORD +"=?", new String[]{id});
    }


  /*  public boolean del(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CONTACTS, KEY_ID + "=" + id, null) > 0;
        // return true;
    }*/



}

