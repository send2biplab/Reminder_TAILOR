package com.ubk.casdis_tailor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ubk.casdis_tailor.R;

import java.net.URLEncoder;

public class ContactAdapter extends BaseAdapter {

    Context context;
    String countryList[];
    String flags[];
    LayoutInflater inflter;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

//
    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";
    SharedPreferences sharedpreferences2;

    public ContactAdapter(Context applicationContext, String[] countryList,String[] flags) {
        this.context = applicationContext;
        this.countryList = countryList;
        this.flags = flags;
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
        LinearLayout thumbnail =  view.findViewById(R.id.thumbnail);
        country.setText("Phone No :- "+countryList[i]);
        country.setText("Name :- "+countryList[i]);

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             Log.e("asdasdada","sss"+flags[i]);
            }
        });
      //  icon.setImageResource(flags[i]);
        return view;
    }
}
