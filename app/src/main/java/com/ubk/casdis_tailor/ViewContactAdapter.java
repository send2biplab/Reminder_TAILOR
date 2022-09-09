package com.ubk.casdis_tailor;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ubk.casdis_tailor.R;

import java.util.ArrayList;
import java.util.List;


public class ViewContactAdapter extends BaseAdapter {
    Context context;
    String countryList[];
    String flags[];
    String status[];
    String datee[];
    LayoutInflater inflter;

    int count=1;

    SharedPreferences sharedpreferences3;
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreferences2;
   // SharedPreferences sharedpreferences4;
    public static final String MyCounter = "Mycounter" ;
    public static final String counter = "counter";


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";


    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";
    public static final String prefix = "prefix";


//    public static final String MySPECIALMSG = "Myspecial" ;
//    public static final String SPECIALIMSG = "SpecialMsg";
//    public static final String SPECIALIMAGE = "SpecialImg";

    public static final String MySPECIALMSG = "Myspecial" ;
    public static final String SPECIALIMSG = "SpecialMsg";
    public static final String SPECIALIMAGE = "SpecialImg";



    public ViewContactAdapter(Context applicationContext, String[] countryList,String[] flags,String[] status,String[] datee) {
        this.context = applicationContext;
        this.countryList = countryList;
        this.flags = flags;
        this.status = status;
        this.datee = datee;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_list_cont, null);
        TextView country = (TextView) view.findViewById(R.id.title);
        LinearLayout thumbnail =  view.findViewById(R.id.thumbnail);


        Log.e("aaaa......",",,"+status[i]);


        if (status[i].equals("True"))
        {
            country.setText("Phone No :- "+countryList[i]);
        }
        else
        {
            country.setText("Phone No :- "+countryList[i]);
            country.setTextColor(Color.RED);
        }


       // country.setText("Phone No :- "+countryList[i]);






        Log.e("xfsdfsdf","sgdsa");

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                notifyDataSetChanged();

               // HomeFragment.sendddd="22";

                sharedpreferences = context.getSharedPreferences(MyPREFERENCES,
                        Context.MODE_PRIVATE);

                // if (sharedpreferences.contains(Name)) {
                // name.setText();

                //  Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));


                //  Log.e("regfwdsa","egfdsa"+countryList[i]);
                sharedpreferences2 = context.getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);

                if (sharedpreferences2.contains(prefix))
                {
                    String pre=sharedpreferences2.getString(prefix, "");

                    Log.e("sfdsfsfsdfsd","ddddd"+pre);






                    String phoneNo=countryList[i];

                    Log.e("ffffffffffffff","............"+phoneNo);
                    //String message="Promo Code : "+getPromoCodePromoCodeeee+" and "+"Promo Code Description : "+getPromoCodePromoCodeDesc;
                    // String message=sharedpreferences.getString(Name, "");



                    //  PackageManager packageManager = context.getApplicationContext().getPackageManager();


                    sharedpreferences3 = context.getSharedPreferences(MyCounter, Context.MODE_PRIVATE);
                    if (sharedpreferences3.contains(counter)) {


                        count=sharedpreferences3.getInt(counter, 0);

                        SharedPreferences.Editor editor=sharedpreferences3.edit();
                        editor.putInt(counter,count+1);
                        editor.commit();

                        Log.e("xgzufyy1","iyfdyresrdtfhg"+count);
                    }
                    else
                    {
                        SharedPreferences.Editor editor=sharedpreferences3.edit();
                        editor.putInt(counter,1);
                        editor.commit();

                        count=1;


                        Log.e("xgzufyy2","iyfdyresrdtfhg"+count);
                    }


                    DatabaseHandler db = new DatabaseHandler(context);

                    Log.e("aaaaaaaaaa","....."+db.promoName2(phoneNo));

                    ArrayList arrayList_db_count = new ArrayList<>();
                    arrayList_db_count = db.showAll_db();
                    Log.d("byjum", ""+arrayList_db_count);

                    List<Contact2> message2 = db.getAllContactsbynumber(db.promoName2(phoneNo));

                    Log.d("asdaSDASD111",""+message2.size());
                    Log.d("asdaSDASD111",""+message2.get(0));
                    if (!arrayList_db_count.isEmpty()) {

                       // for (int i = 0; i < message2.size(); i++) {

                            Log.d("dfasdasda111", "" + message2.get(0).getPhoneNumber());
                            Log.d("dfasdasda2222", "" + message2.get(0).getName());
                            Log.d("dfasdasda2222", "" + message2.get(0).getkey_imgpath());
                            ///messageArray2[i] = "Title :- " + message2.get(i).getName() + "\n" + "Message :- " + message2.get(i).getPhoneNumber();


                        addContact(pre+""+count,countryList[i]);

                        Intent intentww=new Intent(context,SendWPActivity.class);
                        intentww.putExtra("number",phoneNo);
                        //intent.putExtra("number2",dashboardMobileNo);
                        //intent.putExtra("rand",iiii);
                        intentww.putExtra("msg",message2.get(0).getPhoneNumber());
                        intentww.putExtra("Imagepath",message2.get(0).getkey_imgpath());
                        context.startActivity(intentww);



                        //  }


                    }
                    else {
                        Toast.makeText(context," Please Add Sms Files", Toast.LENGTH_SHORT).show();
                    }


                   // sharedpreferences4 =context.getSharedPreferences(MySPECIALMSG, Context.MODE_PRIVATE);

//
//                    if (sharedpreferences4.contains(SPECIALIMSG)) {
//
//
//
//                        Log.e("kkkkkkkk","hhh");
//
//
////                    Intent intent=new Intent(context,TestActivity.class);
////                    context.startActivity(intent);
////
//
//
//
//
//
//                    }
//                    else {
//                        //  Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));
//
//                        Toast.makeText(context," Please Add Sms Files", Toast.LENGTH_SHORT).show();
//                    }


                }
                else
                {
                    Log.e("sfdsfsfsdfsd","ddddd");

                    Toast.makeText(context," Please Prefix Contact Number", Toast.LENGTH_SHORT).show();
                }










//



            }
        });
        //  icon.setImageResource(flags[i]);
        return view;
    }



    private void addContact(String name, String number){
        Uri addContactsUri = ContactsContract.Data.CONTENT_URI;
        long rowContactId = getRawContactId();
        String displayName = name;
        insertContactDisplayName(addContactsUri, rowContactId, displayName);
        String phoneNumber = number;
        String phoneTypeStr = "Mobile";//work,home etc
        insertContactPhoneNumber(addContactsUri, rowContactId, phoneNumber, phoneTypeStr);

        Log.e("1111","1111");




//        Intent sendIntent = new Intent("android.intent.action.MAIN");
//        sendIntent.putExtra(Intent.EXTRA_STREAM, files);
//        sendIntent.putExtra("jid", number + "@s.whatsapp.net");
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "asdasdsad");
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.setPackage("com.whatsapp");
//        sendIntent.setType("image/png");
//        startActivity(sendIntent);


    }


    private long getRawContactId()
    {
        // Inser an empty contact.
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
        // Get the newly created contact raw id.
        long ret = ContentUris.parseId(rawContactUri);
        return ret;


    }

    private void insertContactDisplayName(Uri addContactsUri, long rawContactId, String displayName)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);

        // Put contact display name value.
        contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, displayName);

        context.getContentResolver().insert(addContactsUri, contentValues);

        Log.e("222","1111");

    }

    private void insertContactPhoneNumber(Uri addContactsUri, long rawContactId, String phoneNumber, String phoneTypeStr) {
        // Create a ContentValues object.
        ContentValues contentValues = new ContentValues();

        // Each contact must has an id to avoid java.lang.IllegalArgumentException: raw_contact_id is required error.
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);

        // Each contact must has an mime type to avoid java.lang.IllegalArgumentException: mimetype is required error.
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);

        // Put phone number value.
        contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);

        // Calculate phone type by user selection.
        int phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;

        if ("home".equalsIgnoreCase(phoneTypeStr)) {
            phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
        } else if ("mobile".equalsIgnoreCase(phoneTypeStr)) {
            phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
        } else if ("work".equalsIgnoreCase(phoneTypeStr)) {
            phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_WORK;
        }
        // Put phone type value.`
        contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, phoneContactType);

        // Insert new contact data into phone contact list.
        context.getContentResolver().insert(addContactsUri, contentValues);

        Log.e("333","1111");

    }


}
