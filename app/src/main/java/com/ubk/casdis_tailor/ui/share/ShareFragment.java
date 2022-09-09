package com.ubk.casdis_tailor.ui.share;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.DbPassHandler;
import com.ubk.casdis_tailor.DatabaseHandler;
import com.ubk.casdis_tailor.MainNewActivity;
import com.ubk.casdis_tailor.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    DbPassHandler dbpassword;
    ArrayList arrayList_db_count2;

    String passwd;

    TextView erasedata;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        dbpassword = new DbPassHandler(getContext());

        loginPopup();

        arrayList_db_count2 = new ArrayList<>();
        arrayList_db_count2 = dbpassword.showAll_db();
        //uuuuser=(TextView)findViewById(R.id.uuuuser);

        Log.d("tttttttttttttttttttt15", "" + arrayList_db_count2);

        if (!arrayList_db_count2.isEmpty()) {

            HashMap<String, String> map = (HashMap) arrayList_db_count2.get(0);


            Log.d("asdfasdasdasdas", "" + map);

            passwd=map.get("password");


        }


        erasedata=root.findViewById(R.id.erasedata);
        erasedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure, You wanted to make Erase All data .Please Backup Data. ");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        DatabaseHandler db = new DatabaseHandler(getActivity());
                                        db.deleteall();

                                        Toast.makeText(getContext(), "Successfully delete database", Toast.LENGTH_LONG).show();


                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });




//        final TextView textView = root.findViewById(R.id.text_share);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    private void loginPopup() {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.edt_comment);

        LinearLayout button1 =  dialogView.findViewById(R.id.buttonSubmit);

        TextView number=dialogView.findViewById(R.id.number);
        number.setVisibility(View.GONE);
        TextView sdsdsdsds=dialogView.findViewById(R.id.sdsdsdsds);
        sdsdsdsds.setVisibility(View.VISIBLE);
        // number.setText("Your Registration Phone +"+mPhoneNumber);

        LinearLayout buttonCancel =  dialogView.findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  dialogBuilder.dismiss();

                Intent intent=new Intent(getContext(), MainNewActivity.class);
                startActivity(intent);

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                //   dialogBuilder.dismiss();


                if (editText.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"Please enter password",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    if (passwd.equals(editText.getText().toString().trim()))
                    {
                        dialogBuilder.dismiss();
                        Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Wrong password",Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        dialogBuilder.setCancelable(false);
        dialogBuilder.setCanceledOnTouchOutside(false);
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }
}