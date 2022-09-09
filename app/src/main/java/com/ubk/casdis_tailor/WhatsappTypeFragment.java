package com.ubk.casdis_tailor;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.ubk.casdis_tailor.R;

public class WhatsappTypeFragment extends Fragment {
    SwitchCompat switchCompat;
    TextView business, regular,textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_whatsapp_type, container, false);
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
        return view;
    }
}
