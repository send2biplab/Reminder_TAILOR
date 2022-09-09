package com.ubk.casdis_tailor.ui.country;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.R;

public class CountryFragment extends Fragment {

    private CountryViewModel peemissionViewModel;
    Switch sw;


    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";
    public static final String prefix = "prefix";
    SharedPreferences sharedpreferences;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        peemissionViewModel =
                ViewModelProviders.of(this).get(CountryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_country, container, false);

        Log.e("fhgdfdsaSDFBGB", "Permission ");

        EditText country_code=root.findViewById(R.id.country_code);
        EditText contact_prefix=root.findViewById(R.id.contact_prefix);


        TextView Addcountry_submit=root.findViewById(R.id.Addcountry_submit);

        sharedpreferences = getActivity().getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);


        if (sharedpreferences.contains(Code)) {
            // name.setText();

            country_code.setText(sharedpreferences.getString(Code, "").toString());
            contact_prefix.setText(sharedpreferences.getString(prefix, "").toString());


            //  Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));
        }
        else {
            //  Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));


        }



        Addcountry_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (country_code.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Enter Add Message",
                            Toast.LENGTH_LONG).show();
                }
                else if (contact_prefix.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Enter Prefix Contact Name",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    Log.e("dsgdfghj","fdzsdfg"+country_code.getText().toString());
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Code, country_code.getText().toString());
                    editor.putString(prefix, contact_prefix.getText().toString());

                    editor.commit();
                    Toast.makeText(getActivity(),"Thanks",Toast.LENGTH_LONG).show();


                }


            }
        });







        return root;
    }



}