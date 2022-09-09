package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ubk.casdis_tailor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class SendWPActivity extends AppCompatActivity {

    ArrayList<Uri> files = new ArrayList<Uri>();

    Button ddddddd;

    String getDate;

    public static final String MySPECIALMSG = "Myspecial" ;
    public static final String SPECIALIMSG = "SpecialMsg";
    public static final String SPECIALIMAGE = "SpecialImg";

    SharedPreferences sharedpreferences2;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_wp);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        number = b.getString("number");
        // String number2 = b.getString("number2");
        // String rand = b.getString("rand");

        String msg = b.getString("msg");
        String Imagepath = b.getString("Imagepath");




        String replace = Imagepath.replace("[","");
        System.out.println(replace);
        String replace1 = replace.replace("]","");
        System.out.println(replace1);
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
        System.out.println(myList.toString());



        for (int i = 0; i<myList.size();i++){
            System.out.println("suifhuire"+myList.get(i));



            String path = myList.get(i);

//
            Uri myUri = Uri.parse(path);
            files.add(myUri);
        }


//
//        Uri myUri = Uri.parse(Imagepath);
//        files.add(myUri);


        number = number.replace("+", "");


        Log.e("dfgsdsxws","number"+number);
        //  Log.e("dfgsdsxws","number"+msg);

        ddddddd=findViewById(R.id.AddPromoCode_submit);
        ddddddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                getDate = simpledateformat.format(calendar.getTime());



             //   HomeFragment.sendddd="22";
                DatabaseHandler db = new DatabaseHandler(SendWPActivity.this);


                //  db.allRecordInsert("+"+number, "","",getDate,"False","SMS");

                // db.deleteDeletePhone("+"+number);
                db.SMSstatusUpdate("+"+number);
                //db.deleteDeletePhoneCall("+"+number);


                Log.e("fxdhgfg","yg"+number);

//                DbPassHandler dbpassword = new DbPassHandler(SendWPActivity.this);
//                dbpassword.updatetime(String.valueOf(System.currentTimeMillis()));


                // db.HomePageDataInsert(number2,rand,getDate,Imagepath,"False");

                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra(Intent.EXTRA_STREAM, files);
                sendIntent.putExtra("jid", number + "@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp.w4b");
                sendIntent.setType("image/png");
                startActivity(sendIntent);
                finish();


            }
        });

    }
}
