package com.ubk.casdis_tailor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.ubk.casdis_tailor.ui.edithome.EditHomeFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class EditListFragment extends Fragment {



    ListView contactlist;
    ListAdapter contactdapter1;
    EditText searchh;

    ArrayList<Customer> CustomerList = new ArrayList<Customer>();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        searchh = view.findViewById(R.id.searchh);



        contactlist=view.findViewById(R.id.contactlist);
        contactlist.removeAllViewsInLayout();
        contactlist.setTextFilterEnabled(true);

        contactlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Customer ListViewClickData = (Customer) parent.getItemAtPosition(position);

                Log.e("assdada===",""+ListViewClickData.getId());
                Log.e("assdada===",""+ListViewClickData.getId());

                EditHomeFragment fragment1 = new EditHomeFragment();
                Bundle arguments = new Bundle();
                arguments.putString("id", ListViewClickData.getId());
                fragment1.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment1);
                fragmentTransaction.commit();

                ///Toast.makeText(getContext(), ListViewClickData.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        searchh.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence stringVar, int start, int before, int count) {

                contactdapter1.getFilter().filter(stringVar.toString());
            }
        });

        getdata();


    }

    private void getdata() {


        String[] messageArray2;
        String[] messageid;
        String[] guestname;

        Customer customer;

        contactlist.removeAllViewsInLayout();

        DatabaseHandler db = new DatabaseHandler(getContext());

        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        // String typee="ORDER_DATE";
        Log.e("======21222=====",date);
        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.All_Record_Show_db();
        Log.d("byjum...", ""+arrayList_db_count);


        Log.d("asdaSDASD111",""+arrayList_db_count.size());


        if (!arrayList_db_count.isEmpty()) {

            messageArray2 = new String[arrayList_db_count.size()];
            messageid = new String[arrayList_db_count.size()];
            guestname = new String[arrayList_db_count.size()];
            for (int i = 0; i < arrayList_db_count.size(); i++) {

                HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);

                messageArray2[i] = map.get("RecordMobileNumber");
                messageid[i] = map.get("idrecord");
                guestname[i] = map.get("RecordnName");
                customer = new Customer(map.get("RecordnName"),map.get("RecordMobileNumber"),map.get("idrecord"));
                CustomerList.add(customer);

            }

            contactdapter1 = new ListAdapter(getContext(),R.layout.list_row,CustomerList);
            // contactlist.notify();
            contactlist.setAdapter(contactdapter1);



        }
        else
        {


            Toast.makeText(getContext()," No Record Found. ",
                    Toast.LENGTH_SHORT).show();
        }

    }
}