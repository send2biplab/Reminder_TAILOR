package com.ubk.casdis_tailor.ui.specialmsg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jaiselrahman.filepicker.model.MediaFile;
import com.ubk.casdis_tailor.R;

import java.util.ArrayList;

public class SpecialmsgFragment extends Fragment {

    private SpecialmsgViewModel specialmsgViewModel;

    TextView Addspecial_submit;

    private final static int FILE_REQUEST_CODE = 1;

    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();

    ArrayList<String> selectedImageList;

    EditText special_msg;

    public static final String TextMessage = "TextMessage" ;
    public static final String TextSms = "TextSms";
    public static final String SMSStatus = "status";


    SharedPreferences sharedpreferences;


    ToggleButton toggleButton2;;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        specialmsgViewModel =
                ViewModelProviders.of(this).get(SpecialmsgViewModel.class);
        View root = inflater.inflate(R.layout.fragment_specialmsg, container, false);


        Log.e("rstegrfedwsrtgrfedwsqa","smmmmmmmmssssss");

        selectedImageList = new ArrayList<>();



        special_msg=root.findViewById(R.id.special_msg);

        Addspecial_submit=root.findViewById(R.id.Addspecial_submit);
        toggleButton2=(ToggleButton)root.findViewById(R.id.toggleButton2);


        sharedpreferences = getActivity().getSharedPreferences(TextMessage, Context.MODE_PRIVATE);

      ///  toggleButton2.setEnabled(true);

       /// toggleButton2.setTextOn("On");

        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Log.e("fdsfsdfsdf","on");
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(SMSStatus, "On");
                    /// editor.putString(SPECIALIMAGE, imgurl.getText().toString());

                    editor.commit();

                } else {
                    // The toggle is disabled
                    Log.e("fdsfsdfsdf","offf");
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(SMSStatus, "Off");
                    /// editor.putString(SPECIALIMAGE, imgurl.getText().toString());

                    editor.commit();
                }
            }
        });



        if (sharedpreferences.contains(SMSStatus)) {



            if (sharedpreferences.getString(SMSStatus, "").toString().equals("On"))
            {
                toggleButton2.setChecked(true);
            }
            else
            {
                toggleButton2.setChecked(false);
            }

        }
        else {



        }



        if (sharedpreferences.contains(TextSms)) {
            // name.setText();

            special_msg.setText(sharedpreferences.getString(TextSms, "").toString());
           /// imgurl.setText(sharedpreferences.getString(SPECIALIMAGE, "").toString());


            //  Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));
        }
        else {
            //  Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));


        }





//


//
        Addspecial_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (special_msg.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Message empty", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(TextSms, special_msg.getText().toString());
                   /// editor.putString(SPECIALIMAGE, imgurl.getText().toString());

                    editor.commit();
                    Toast.makeText(getActivity(),"Thanks",Toast.LENGTH_LONG).show();

                }


            }
        });





        return root;
    }


}