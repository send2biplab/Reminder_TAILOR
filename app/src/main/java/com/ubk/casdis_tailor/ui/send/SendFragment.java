package com.ubk.casdis_tailor.ui.send;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.ui.adddefoultmessage.AddViewModel;
import com.ubk.casdis_tailor.DatabaseHandler;
import com.ubk.casdis_tailor.R;

import java.util.ArrayList;

public class SendFragment extends Fragment {

    private AddViewModel sendViewModel;

  //  String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
         //   "WebOS","Ubuntu","Windows7","Max OS X"};

    String[] messageArray2;
    DatabaseHandler db;

    ArrayList arrayList_db_count;
    SwitchCompat switch_id;
    EditText batchh;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);




         db = new DatabaseHandler(getActivity());

         arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.MobileNoShowAll_db2();
        Log.d("byjum...", ""+arrayList_db_count);


        Log.d("asdaSDASD111",""+arrayList_db_count.size());


//        if (!arrayList_db_count.isEmpty()) {
//
//            messageArray2 = new String[arrayList_db_count.size()];
//            for (int i = 0; i < arrayList_db_count.size(); i++) {
//
//                HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);
//
//                Log.d("asdaSDASD111", "...." + arrayList_db_count.get(i));
//                Log.d("asdaSDASD111", "...." + map);
//                Log.d("asdaSDASD111", "...." + map.get("PromoCodeList"));
//
//                //  Log.d("dfasdasda111", "" + message2.get(i).getPhoneNumber());
//                //  Log.d("dfasdasda2222", "" + message2.get(i).getName());
//
//                messageArray2[i] = map.get("MobileNumber");
//
//
//            }
//
//            ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
//                    R.layout.activity_listview, messageArray2);
//
//            ListView listView = (ListView) root.findViewById(R.id.istview);
//            listView.setAdapter(adapter);
//
//        }

        batchh=root.findViewById(R.id.batchh);
        switch_id=root.findViewById(R.id.switch_id);

        SharedPreferences sh = getActivity().getSharedPreferences("BatchStatus", MODE_PRIVATE);

        int s1 = sh.getInt("batch", 0);
        Log.e("=======333===","1"+s1);



        if (sh.getBoolean("status",false)) {
            switch_id.setChecked(true);
        } else {
            switch_id.setChecked(false);

        }

        batchh.setText(String.valueOf(s1));

        //  int a = sh.getInt("age", 0);

      TextView contlist_submit=root.findViewById(R.id.contlist_submit);
        contlist_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Boolean switchState = switch_id.isChecked();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("BatchStatus", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putInt("batch", Integer.parseInt(batchh.getText().toString()));
                myEdit.putBoolean("status",switch_id.isChecked());
                myEdit.apply();


                Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
                //getActivity().finish();
            }
        });


//        contlist_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (!arrayList_db_count.isEmpty()) {
//
//                    messageArray2 = new String[arrayList_db_count.size()];
//                    for (int i = 0; i < arrayList_db_count.size(); i++) {
//
//                        HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);
//
//
//                        Log.e("dfssfgdsf"+i,"aaaa"+map);
//                        int count=i+1;
//                        Log.e("dfssfgdsf"+i,"aaaa"+count);
//
//
//                        if (i==arrayList_db_count.size()-1)
//                        {
//                            Toast.makeText(getActivity(),"Success add to contact",Toast.LENGTH_LONG).show();
//                            addContact("C"+count,map.get("MobileNumber"));
//                        }
//                        {
//                            addContact("C"+count,map.get("MobileNumber"));
//                        }
//
//
//                    }
//
//
//
//                }
//
//
//            }
//        });
//
//


     //



//        final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
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
        // Put phone type value.
        contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, phoneContactType);

        // Insert new contact data into phone contact list.
        getActivity().getContentResolver().insert(addContactsUri, contentValues);

    }
}