package com.ubk.casdis_tailor.ui.keyword;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.R;

public class KeywordFragment extends Fragment {

    private KeywordViewModel slideshowViewModel;

//    public static final String MyKEYWORD = "MyKeyword" ;
//    public static final String Keyword = "Keyword";
//
//    SharedPreferences sharedpreferences;
//
//    TextView keyword_submit;

    SwitchCompat switchCompat;
    TextView business, regular,textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(KeywordViewModel.class);
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);


        switchCompat = view.findViewById(R.id.switch_id);
        //business = view.findViewById(R.id.text_business_id);
        textView = view.findViewById(R.id.text_regular_id);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchCompat.isChecked()) {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("switch", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("NameOfThingToSave", true);
                    editor.commit();
                    Log.e("kljklkkk", "tttttt");
                    textView.setText("Business Active :");
                } else {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("switch", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("NameOfThingToSave", false);
                    editor.commit();
                    Log.e("kljklkkk", "jjjj");
                    textView.setText("Regular Active :");
                }
            }
        });
        SharedPreferences sharedPrefs = getContext().getSharedPreferences("switch", Context.MODE_PRIVATE);
        boolean switchState = sharedPrefs.getBoolean("NameOfThingToSave", false);
        //switchCompat.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", true));
        Log.e("jaaakllkj", "" + switchState);
        if (switchState) {
            switchCompat.setChecked(true);
        } else {
            switchCompat.setChecked(false);
            textView.setText("Regular Active :");
        }

//
//
//        EditText keyword=root.findViewById(R.id.keyword);
//        Log.e("rstegrfedwsrtgrfedwsqa","Keyword");
//        keyword_submit=root.findViewById(R.id.keyword_submit);
//
//
//
//       // EditText country_code=root.findViewById(R.id.country_code);
//
//
//        sharedpreferences = getActivity().getSharedPreferences(MyKEYWORD, Context.MODE_PRIVATE);
//
//
//        if (sharedpreferences.contains(Keyword)) {
//            // name.setText();
//
//            keyword.setText(sharedpreferences.getString(Keyword, "").toString());
//
//            //  Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));
//        }
//        else {
//            //  Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));
//
//
//        }
//
//
//
//        keyword_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (keyword.getText().toString().equals(""))
//                {
//                    Toast.makeText(getActivity(), "Enter Add Keyword",
//                            Toast.LENGTH_LONG).show();
//                }
//                else {
//
//                    Log.e("dsgdfghj","fdzsdfg"+keyword.getText().toString());
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                    editor.putString(Keyword, keyword.getText().toString());
//
//                    editor.commit();
//                    Toast.makeText(getActivity(),"Thanks",Toast.LENGTH_LONG).show();
//
//
//                }
//
//
//            }
//        });
//
//



        return view;
    }
}