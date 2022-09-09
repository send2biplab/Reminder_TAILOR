package com.ubk.casdis_tailor.ui.tools;

import android.app.AlertDialog;
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
import com.ajts.androidmads.library.SQLiteToExcel;
import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.ubk.casdis_tailor.DbPassHandler;
import com.ubk.casdis_tailor.MainNewActivity;
import com.ubk.casdis_tailor.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ToolsFragment extends Fragment  {

    private ToolsViewModel toolsViewModel;
    //private ExportDbUtil exportDbUtil;

    TextView download;

    ArrayList arrayList_db_count2;
    DbPassHandler dbpassword;

    String passwd;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

       // SqliteToExcel sqliteToExcel = new SqliteToExcel(this, "helloworld.db");

        dbpassword = new DbPassHandler(getContext());
        arrayList_db_count2 = new ArrayList<>();
        arrayList_db_count2 = dbpassword.showAll_db();
        //uuuuser=(TextView)findViewById(R.id.uuuuser);

        Log.d("tttttttttttttttttttt199", "" + arrayList_db_count2);


        if (!arrayList_db_count2.isEmpty()) {

            HashMap<String, String> map = (HashMap) arrayList_db_count2.get(0);


            Log.d("asdfasdasdasdas", "" + map);

            passwd=map.get("password");


        }

        loginPopup();



        download=root.findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                PRDownloader.initialize(getActivity());

                SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(getActivity(), "DbLoginStatus");

                sqLiteToExcel.exportSingleTable("ALL_RECORD", "CasdisContact_TAILOR.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onCompleted(String filePath) {

                        Log.e("dsdssdsad","sdaa"+filePath);

                        Toast.makeText(getContext(), "Successfully exported database", Toast.LENGTH_LONG).show();
                         downloadFile(filePath);

                    }
                    @Override
                    public void onError(Exception e) {

                    }
                });



            }
        });


        return root;
    }


    private int downloadFile(String url){

        String fileName = url.substring(url.lastIndexOf('/')+1);
        String dirPath = getActivity().getFilesDir().getAbsolutePath()+ File.separator+"tapShare";
        Log.e("===0991=",fileName);
       // final String dirPath ="/storage/emulated/0/";
        Log.e("===099=",dirPath);
        //String dirPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"downloads";

        return  PRDownloader.download(url, dirPath, fileName)
                .build()
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.e("download complete!","dsaqwa");
                        Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();
                    }


                    @Override
                    public void onError(Error error) {
                        Log.e("onError: " +error.toString(),"efrdsz");
                    }
                });

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