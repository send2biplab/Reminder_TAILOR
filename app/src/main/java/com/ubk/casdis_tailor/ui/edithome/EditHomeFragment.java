package com.ubk.casdis_tailor.ui.edithome;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.DatabaseHandler;
import com.ubk.casdis_tailor.EditListFragment;
import com.ubk.casdis_tailor.NewHomeFragment;
import com.ubk.casdis_tailor.R;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class EditHomeFragment extends Fragment {

    public static String sendddd="11";
    private HomeViewModel homeViewModel;

    EditText dashboard_customer_name,getPromoCode_MobileNo,getPromoCode_Numberofpepole;
    TextView getPromoCode_Sent;

    String MySPECIALCALL = "Myspecialcall" ;
    String SPECIALICALL = "SpecialCall";
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreferences1;

    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";
    public static final String prefix = "prefix";

    public static final String MyDocument = "AllDocument" ;
    public static final String ImagePath = "ImagePath";
    SharedPreferences sharedpreferences2;

    EditText order_date,trailr_date,delivery_date,salesman_date,remark_date,date,followup_date;


    LinearLayout lldatee,lldate,llfollow,llfolloww;


    String type="CUSTOMER";

    LinearLayout llorderdate,llorderdatee,lltraill,lltrail,lldelivery,lldeliveryy;

    String idd ="0";
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             idd = bundle.getString("id", "");



            DatabaseHandler db = new DatabaseHandler(getContext());

            ArrayList arrayList_db_count = new ArrayList<>();
            arrayList_db_count = db.All_Record_Show_db(idd);
            Log.d("byjum...", ""+arrayList_db_count);
            HashMap<String, String> map = (HashMap) arrayList_db_count.get(0);

            type=map.get("GUEST");
            Log.d("byjumasas...", ""+map.get("GUEST"));

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                //  requireActivity().onBackPressed();

                EditListFragment fragment1 = new EditListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment1);
                fragmentTransaction.commit();

                Log.e("sdsdsd============","sdsds");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        dashboard_customer_name=root.findViewById(R.id.dashboard_customer_name);
        getPromoCode_MobileNo=root.findViewById(R.id.getPromoCode_MobileNo);

        getPromoCode_Sent=root.findViewById(R.id.getPromoCode_Sent);
        getPromoCode_Numberofpepole=root.findViewById(R.id.getPromoCode_Numberofpepole);

        order_date=root.findViewById(R.id.order_date);
        trailr_date=root.findViewById(R.id.trailr_date);
        delivery_date=root.findViewById(R.id.delivery_date);
        salesman_date=root.findViewById(R.id.salesman_date);
        remark_date=root.findViewById(R.id.remark_date);
            date=root.findViewById(R.id.date);
            followup_date=root.findViewById(R.id.followup_date);

            remark_date.setText(map.get("REMARKk"));
            salesman_date.setText(map.get("SALESMAN"));
            delivery_date.setText(map.get("DELIVERY"));
            trailr_date.setText(map.get("TRAIL"));
            order_date.setText(map.get("ORDER"));
            date.setText(map.get("DATEe"));
            followup_date.setText(map.get("FOLLOW_UP"));
            dashboard_customer_name.setText(map.get("RecordnName"));
            getPromoCode_MobileNo.setText(map.get("RecordMobileNumber"));

            getPromoCode_Sent.setText("Update");

            Log.d("byjumasas...", ""+map.get("idrecord"));
            lldatee=root.findViewById(R.id.lldatee);
            lldate=root.findViewById(R.id.lldate);
            llfollow=root.findViewById(R.id.llfollow);
            llfolloww=root.findViewById(R.id.llfolloww);
            llorderdate=root.findViewById(R.id.llorderdate);
            llorderdatee=root.findViewById(R.id.llorderdatee);
            lltraill=root.findViewById(R.id.lltraill);
            lltrail=root.findViewById(R.id.lltrail);
            lldelivery=root.findViewById(R.id.lldelivery);
            lldeliveryy=root.findViewById(R.id.lldeliveryy);






            if (type.equals("CUSTOMER"))
            {
                lldatee.setVisibility(View.GONE);
                lldate.setVisibility(View.GONE);
                llfollow.setVisibility(View.GONE);
                llfolloww.setVisibility(View.GONE);
                llorderdate.setVisibility(View.VISIBLE);
                llorderdatee.setVisibility(View.VISIBLE);
                lltraill.setVisibility(View.VISIBLE);
                lltrail.setVisibility(View.VISIBLE);
                lldelivery.setVisibility(View.VISIBLE);
                lldeliveryy.setVisibility(View.VISIBLE);


            }
            else
            {
                lldatee.setVisibility(View.VISIBLE);
                lldate.setVisibility(View.VISIBLE);
                llfollow.setVisibility(View.VISIBLE);
                llfolloww.setVisibility(View.VISIBLE);
                llorderdate.setVisibility(View.GONE);
                llorderdatee.setVisibility(View.GONE);
                lltraill.setVisibility(View.GONE);
                lltrail.setVisibility(View.GONE);
                lldelivery.setVisibility(View.GONE);
                lldeliveryy.setVisibility(View.GONE);
            }


        order_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();

                int  mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                String strDate = format.format(calendar.getTime());
                                    Log.e("====3333223==",""+strDate);
                                order_date.setText(strDate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                String strDate = format.format(calendar.getTime());
                                Log.e("====3333223==",""+strDate);
                                date.setText(strDate);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        followup_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                String strDate = format.format(calendar.getTime());
                                Log.e("====3333223==",""+strDate);
                                followup_date.setText(strDate);



                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        trailr_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                String strDate = format.format(calendar.getTime());
                                Log.e("====3333223==",""+strDate);
                                trailr_date.setText(strDate);



                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        delivery_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                String strDate = format.format(calendar.getTime());
                                Log.e("====3333223==",""+strDate);
                                delivery_date.setText(strDate);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        sendddd="11";

        getPromoCode_Sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Reg_Mobile=getPromoCode_MobileNo.getText().toString();
               if(Reg_Mobile.equals("") || Reg_Mobile.equals(null) || Reg_Mobile.length()< 10 ){
                  Toast.makeText(getContext(), "Mobile Number Not Valid", Toast.LENGTH_SHORT).show();
                }
               else
                {


//                    String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
//                    String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//                    String day = new SimpleDateFormat("EEEE").format(Calendar.getInstance().getTime());
//
//                    Log.e("datee----1",""+time);
//                    Log.e("datee----1",""+date);
//                    Log.e("datee----1",""+day);

                    sharedpreferences2 = getActivity().getSharedPreferences(MyDocument, MODE_PRIVATE);
                    sharedpreferences1 = getActivity().getSharedPreferences(MyCOUNTRY, MODE_PRIVATE);
                    sharedpreferences = getActivity().getSharedPreferences(MySPECIALCALL, MODE_PRIVATE);


                    if (sharedpreferences1.getString(prefix, "").equals(""))
                    {
                        Toast.makeText(getContext()," Please Add Prefix. ",
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                      //  SharedPreferences.Editor editor = getActivity().getSharedPreferences("GUEST", MODE_PRIVATE).edit();

//                        if (dashboard_customer_name.getText().toString().equals(""))
//                        {
//
//                            SharedPreferences prefs =  getActivity().getSharedPreferences("GUEST", MODE_PRIVATE);
//
//                            int idName = prefs.getInt("count", 0);
//                            int count=idName+1;
//                            addContact(sharedpreferences1.getString(prefix, "")+"_Guest "+count,Reg_Mobile);
//
//                            editor.putInt("count", count);
//                            editor.apply();
//
//
//                        }
//                        else
//                        {
//                            addContact(sharedpreferences1.getString(prefix, "")+""+dashboard_customer_name.getText().toString(),Reg_Mobile);
//
//                        }

                   //     addContact(sharedpreferences1.getString(prefix, "")+""+dashboard_customer_name.getText().toString(),Reg_Mobile);

                        DatabaseHandler db = new DatabaseHandler(getContext());
                        db.statusUpdateData(idd,Reg_Mobile,  ""+dashboard_customer_name.getText().toString(),order_date.getText().toString(),trailr_date.getText().toString(),delivery_date.getText().toString(),salesman_date.getText().toString(),type,remark_date.getText().toString(),date.getText().toString(),followup_date.getText().toString());


                        Toast.makeText(getContext()," Update Success ",
                                Toast.LENGTH_SHORT).show();
                        // Toast.makeText(getContext(), "Save Contact", Toast.LENGTH_SHORT).show();

//                        String path = sharedpreferences2.getString(ImagePath, "");
//                        String Msg =sharedpreferences.getString(SPECIALICALL, "");
//
//
//                        EditHomeFragment.sendddd="22";
//
//                        SharedPreferences sharedPrefs = getContext().getSharedPreferences("switch", MODE_PRIVATE);
//                        boolean switchState = sharedPrefs.getBoolean("NameOfThingToSave", false);
//                        //switchCompat.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", true));
//                        Log.e("jaaakllkj", "" + switchState);

//                        String message;
//                        if (dashboard_customer_name.getText().toString().equals(""))
//                        {
//                            message=sharedpreferences.getString(SPECIALICALL, "");
//                        }
//                        else
//                        {
//                            message="Hello "+dashboard_customer_name.getText().toString()+", "+sharedpreferences.getString(SPECIALICALL, "");
//                        }

//                        getPromoCode_MobileNo.setText("");
//                        dashboard_customer_name.setText("");
//                        getPromoCode_Numberofpepole.setText("");
//                        date.setText("");
//                        followup_date.setText("");
//                        order_date.setText("");
//                        trailr_date.setText("");
//                        delivery_date.setText("");
//                        remark_date.setText("");
//                        salesman_date.setText("");


//                        if (switchState) {
//
//                            sendTextBusiness(sharedpreferences1.getString(Code, "")+Reg_Mobile,message);
//
//                        } else {
//
//                            sendText(sharedpreferences1.getString(Code, "")+Reg_Mobile,message);
//
//                        }
                    }



                }


            }
        });



        Log.e("fdssdfsdfsdf..2","Misscalll....1");

        }


        return root;
    }

    private void sendImageFile(String number,String path,String msg) {

        ArrayList<Uri> files = new ArrayList<Uri>();
        Log.e("fdssdfsdfsdf..2","Misscalll....123"+path);
        String replace = path.replace("[","");
        System.out.println(replace);
        String replace1 = replace.replace("]","");
        System.out.println(replace1);
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
        System.out.println(myList.toString());

        for (int i = 0; i<myList.size();i++){
            System.out.println("suifhuire"+myList.get(i));
            String path2 = myList.get(i);
            Uri myUri = Uri.parse(path2);
            files.add(myUri);
        }

        Log.e("=====================222",number);

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.putExtra(Intent.EXTRA_STREAM, files);
            sendIntent.putExtra("jid", number + "@s.whatsapp.net");
            //sendIntent.putExtra(Intent.EXTRA_SUBJECT, "okkk");
            sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setPackage("com.whatsapp");
            sendIntent.setType("image/png");
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  //permission ok
            getActivity().startActivity(sendIntent);



    }

    private void sendText(String number,String msg) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode(msg, "UTF-8");

            intent.setPackage("com.whatsapp");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));
            startActivity(intent);
           // finish();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void sendImageFileBusiness(String number,String path,String msg) {

        ArrayList<Uri> files = new ArrayList<Uri>();
        Log.e("fdssdfsdfsdf..2","Misscalll....123"+path);
        String replace = path.replace("[","");
        System.out.println(replace);
        String replace1 = replace.replace("]","");
        System.out.println(replace1);
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
        System.out.println(myList.toString());

        for (int i = 0; i<myList.size();i++){
            System.out.println("suifhuire"+myList.get(i));
            String path2 = myList.get(i);
            Uri myUri = Uri.parse(path2);
            files.add(myUri);
        }

        Log.e("=====================222",number);

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra(Intent.EXTRA_STREAM, files);
        sendIntent.putExtra("jid", number + "@s.whatsapp.net");
        //sendIntent.putExtra(Intent.EXTRA_SUBJECT, "okkk");
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp.w4b");
        sendIntent.setType("image/png");
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  //permission ok
        getActivity().startActivity(sendIntent);



    }

    private void sendTextBusiness(String number,String msg) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode(msg, "UTF-8");

            intent.setPackage("com.whatsapp.w4b");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));
            getActivity().startActivity(intent);
            // finish();

        } catch (Exception e){
            e.printStackTrace();
        }
    }



    private void addContact(String name, String number){
        Uri addContactsUri = ContactsContract.Data.CONTENT_URI;
        long rowContactId = getRawContactId();
        String displayName = name;
        insertContactDisplayName(addContactsUri, rowContactId, displayName);
        String phoneNumber = number;
        String phoneTypeStr = "Mobile";//work,home etc
        insertContactPhoneNumber(addContactsUri, rowContactId, phoneNumber, phoneTypeStr);
    }


    private long getRawContactId()
    {
        // Inser an empty contact.
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = getActivity().getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
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

        getActivity().getContentResolver().insert(addContactsUri, contentValues);

    }

    private void insertContactPhoneNumber(Uri addContactsUri, long rawContactId, String phoneNumber, String phoneTypeStr) {
        // Create a ContentValues object.
        ContentValues contentValues = new ContentValues();

        // Each contact must has an id to avoid java.lang.IllegalArgumentException: raw_contact_id is required error.
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);

        // Each contact must has an mime type to avoid java.lang.IllegalArgumentException: mimetype is required error.
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        int phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
        if ("home".equalsIgnoreCase(phoneTypeStr)) {
            phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
        } else if ("mobile".equalsIgnoreCase(phoneTypeStr)) {
            phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
        } else if ("work".equalsIgnoreCase(phoneTypeStr)) {
            phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_WORK;
        }
        contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, phoneContactType);
        getActivity().getContentResolver().insert(addContactsUri, contentValues);

    }


    @Override
    public void onStart() {

      //  getCallDetails();
        Log.e("dszfdghj","lfdsrD");
        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("lifecycle","onPause invoked");
       // getCallDetails();
    }





}