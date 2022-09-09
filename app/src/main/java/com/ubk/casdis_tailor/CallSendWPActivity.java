package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ubk.casdis_tailor.R;

import java.net.URLEncoder;
import java.util.ArrayList;

public class CallSendWPActivity extends AppCompatActivity {


    ArrayList<Uri> files = new ArrayList<Uri>();

    TextView ddddddd;

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

        Log.e("dfsdsfvd","vhghgchgcg");


        Bundle b = new Bundle();
        b = getIntent().getExtras();
        number = b.getString("number");
        // String number2 = b.getString("number2");
        // String rand = b.getString("rand");

        String msg = b.getString("msg");
        String Imagepath = b.getString("Imagepath");





        number = number.replace("+", "");


        Log.e("dfgsdsxws","number"+number);
        //  Log.e("dfgsdsxws","number"+msg);

        ddddddd=findViewById(R.id.AddPromoCode_submit);
        ddddddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                DatabaseHandler db = new DatabaseHandler(context);
//               db.deleteDeletePhoneCall(phoneNo);

            //    HomeFragment.sendddd="22";
                DatabaseHandler db = new DatabaseHandler(CallSendWPActivity.this);
               // db.deleteDeletePhoneCall("+"+number);
                db.CallstatusUpdate("+"+number);


                DbPassHandler dbpassword = new DbPassHandler(CallSendWPActivity.this);
                dbpassword.updatetime(String.valueOf(System.currentTimeMillis()));
//
             //db.HomePageDataInsert(number2,rand,getDate,Imagepath,"False");



                Intent intent = new Intent(Intent.ACTION_VIEW);

                try {
                    String url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode(msg, "UTF-8");
                    //String url = "https://api.whatsapp.com/send?phone="+ phoneNo +"&text=" +encodeURIComponent(imageURL);;
                    intent.setPackage("com.whatsapp.w4b");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    finish();

                } catch (Exception e){
                    e.printStackTrace();
                }

//
//                Intent sendIntent = new Intent("android.intent.action.MAIN");
//                sendIntent.putExtra(Intent.EXTRA_STREAM, files);
//                sendIntent.putExtra("jid", number + "@s.whatsapp.net");
//                sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.setPackage("com.whatsapp");
//                sendIntent.setType("image/png");
//                startActivity(sendIntent);
//                finish();



            }
        });

    }
}
