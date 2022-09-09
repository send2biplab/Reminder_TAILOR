package com.ubk.casdis_tailor.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.DbPassHandler;
import com.ubk.casdis_tailor.MainNewActivity;
import com.ubk.casdis_tailor.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    TextView submit;

    ArrayList arrayList_db_count2;

    EditText password,newpassword,confpasswd;

      String passwd;
    DbPassHandler dbpassword;

    String iddd;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        submit=root.findViewById(R.id.submit);
         dbpassword = new DbPassHandler(getContext());

        arrayList_db_count2 = new ArrayList<>();
        arrayList_db_count2 = dbpassword.showAll_db();
        //uuuuser=(TextView)findViewById(R.id.uuuuser);

        Log.d("tttttttttttttttttttt15", "" + arrayList_db_count2);

        password=root.findViewById(R.id.password);
        newpassword=root.findViewById(R.id.newpassword);
        confpasswd=root.findViewById(R.id.confpasswd);


        if (!arrayList_db_count2.isEmpty()) {

            HashMap<String, String> map = (HashMap) arrayList_db_count2.get(0);


            Log.d("asdfasdasdasdas", "" + map);

            passwd=map.get("password");
            iddd=map.get("id");

        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (password.getText().equals(""))
                {
                    Toast.makeText(getActivity(),"Please Enter Password",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    if (password.getText().toString().equals(passwd))
                    {

                        if (newpassword.getText().equals(""))
                        {
                            Toast.makeText(getActivity(),"Please Enter new Password",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            if(newpassword.getText().toString().equals(confpasswd.getText().toString()))
                            {
                                dbpassword.update(newpassword.getText().toString(),iddd);


                                Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), MainNewActivity.class));
                                getActivity().finish();


                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Password not Match",Toast.LENGTH_SHORT).show();
                            }
                        }




                    }
                    else {
                        Toast.makeText(getActivity(),"Incorrect Password",Toast.LENGTH_SHORT).show();

                    }
                }




            }
        });

        //final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}