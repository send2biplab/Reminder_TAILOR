package com.ubk.casdis_tailor.ui.home;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.ubk.casdis_tailor.AlermActivity;
import com.ubk.casdis_tailor.CallSendWPActivity;
import com.ubk.casdis_tailor.DatabaseHandler;
import com.ubk.casdis_tailor.MainNewActivity;
import com.ubk.casdis_tailor.R;

class CallContactAdapterNew extends BaseAdapter {

    Context context;
    String countryList[];
    String flags[];
    String status[];
    String callType[];
    String AlermTime[];
    String AlermNote[];
    LayoutInflater inflter;
    Activity activity;
    // DBManager dbManager;
    int count=1;
    long RecordID;

    SharedPreferences sharedpreferences3;
    public static final String MyCounter = "Mycounter" ;
    public static final String counter = "counter";


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;


    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";
    public static final String prefix = "prefix";
    SharedPreferences sharedpreferences2;

    public static final String MySPECIALCALL = "Myspecialcall" ;
    public static final String SPECIALICALL = "SpecialCall";
    public static final String SPECIALIMAGECall = "SpecialImgcall";


    SharedPreferences sharedpreferences4;

    public CallContactAdapterNew(Context applicationContext, Activity activity, String[] countryList, String[] flags, String[] status,String[] callType,String[] AlermTime,String[] AlermNote) {
        this.context = applicationContext;
        this.countryList = countryList;
        this.flags = flags;
        this.status = status;
        this.activity = activity;
        this.callType = callType;
        this.AlermTime = AlermTime;
        this.AlermNote = AlermNote;
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
        TextView description = (TextView) view.findViewById(R.id.description);
        ImageView thumbnail =  view.findViewById(R.id.edittt);
        ImageView callll =  view.findViewById(R.id.callll);
        ImageView alert =  view.findViewById(R.id.alert);
        RelativeLayout saasasasa=view.findViewById(R.id.saasasasa);

        Log.e("callType....","...a"+AlermTime[i]);

        if (AlermTime[i].equals("notset notset"))
        {
            description.setVisibility(View.GONE);
        }
        else {

            description.setText("Time: "+AlermTime[i]+" \nNote: "+AlermNote[i]);
            alert.setVisibility(View.GONE);
        }
        description.setText("Time: "+AlermTime[i]+" \nNote: "+AlermNote[i]);



        Log.e("callType","..."+callType[i]);

        if (callType[i].equals("INCOMING"))
        {
            country.setCompoundDrawablesWithIntrinsicBounds(R.drawable.incoming, 0, 0, 0);
        }
        else
        {

            country.setCompoundDrawablesWithIntrinsicBounds(R.drawable.outgoing, 0, 0, 0);
        }

        if (status[i].equals("True"))
        {
            country.setText(" "+countryList[i]);
        }
        else if (status[i].equals("Del"))
        {
            saasasasa.setVisibility(View.GONE);
        }
        else
        {
            country.setText(" "+countryList[i]);
            country.setTextColor(Color.RED);
        }


        saasasasa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Toast.makeText(context, "Long okkk " , Toast.LENGTH_SHORT).show();
                confiramationalert(countryList[i]);
                return true;
            }
        });

        callll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e("ssdsdsds","Remove Alerm");

                DatabaseHandler db = new DatabaseHandler(context);
                // db.deleteDeletePhoneCall("+"+number);

                db.CallstatusAlerm(flags[i],"notset", "notset","notset","0");




                Log.e("gsdfsdf","....."+countryList[i]);
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+countryList[i]));
                context.startActivity(callIntent);
            }
        });


        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///RecordID=dbManager.RowCount()+1;

                Log.e("dfgdgsdgsdgs",""+flags[i]);

                String RecordID_string=String.valueOf(flags[i]);

                Intent intent=new Intent(context, AlermActivity.class);
                intent.putExtra("titlefrom",countryList[i]);
                intent.putExtra("descriptionfrom","ignore");
                intent.putExtra("add_or_update","ADD");
                intent.putExtra("recordno",RecordID_string);
                intent.putExtra("rem_time","ignore");
                intent.putExtra("rem_date","ignore");
                // intent.putExtra("rem_date","ignore");

                context.startActivity(intent);

            }
        });



        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                country.setTextColor(Color.RED);

                notifyDataSetChanged();

             //   HomeFragment.sendddd="22";




                String phoneNo=countryList[i];


//                DatabaseHandler db = new DatabaseHandler(context);
//                db.deleteDeletePhone(phoneNo);



                sharedpreferences2 = context.getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);

                Log.e("kjhxzSfhgnv","fsDg"+String.valueOf(System.currentTimeMillis()));



                if (sharedpreferences2.contains(prefix)) {
                   // String pre = sharedpreferences2.getString(prefix, "");
                    Log.e("ffffffffffffff", "............" + phoneNo);
                    //String message="Promo Code : "+getPromoCodePromoCodeeee+" and "+"Promo Code Description : "+getPromoCodePromoCodeDesc;
                    String pre = sharedpreferences2.getString(prefix, "");

                    Log.e("erfwefwef", "sfs" + pre);




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



                    sharedpreferences4 =context.getSharedPreferences(MySPECIALCALL, Context.MODE_PRIVATE);


                    if (sharedpreferences4.contains(SPECIALICALL)) {
                        // name.setText();

                        //  special_msg.setText(sharedpreferences.getString(SPECIALIMSG, "").toString());
                        //  imgurl.setText(sharedpreferences.getString(SPECIALIMAGE, "").toString());

                        //  Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));






                        addContact(pre+""+count,countryList[i]);
//
                        //   Intent intent=new Intent(context,SendWPActivity.class);
                        Intent intent=new Intent(context,CallSendWPActivity.class);
                        intent.putExtra("number",phoneNo);
                        //intent.putExtra("number2",dashboardMobileNo);
                        //intent.putExtra("rand",iiii);
                        intent.putExtra("msg",sharedpreferences4.getString(SPECIALICALL, "").toString());
                        intent.putExtra("Imagepath",sharedpreferences4.getString(SPECIALIMAGECall, "").toString());
                        context.startActivity(intent);

                    }
                    else {
                        //  Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));

                        Toast.makeText(context," Please Add Call Files", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(context," Please Prefix Contact Number", Toast.LENGTH_SHORT).show();

                }







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



    public void confiramationalert(String number) {

        new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you sure ? ").setMessage("You want to Delete this Number?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(context, "Del "+number,Toast.LENGTH_SHORT).show();



                        DatabaseHandler db = new DatabaseHandler(context);
                        db.CallstatusUpdate3(number);

                        notifyDataSetChanged();


                        context.startActivity(new Intent(context, MainNewActivity.class));
                        activity.finish();

                    }
                }).setNegativeButton("No", null).show();

    }



}
