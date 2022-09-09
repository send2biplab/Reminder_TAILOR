package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.ubk.casdis_tailor.R;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WPActivity extends AppCompatActivity {

    String number,path,Msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpactivity);
        Intent intent = getIntent();
         number = intent.getStringExtra("number");
         path = intent.getStringExtra("path");
         Msg = intent.getStringExtra("Msg");



        sendText(number,Msg);

    }


    @Override
    public void onResume(){
        super.onResume();

    }

    private void sendText(String number,String msg) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode(msg, "UTF-8");

            intent.setPackage("com.whatsapp");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));
            startActivity(intent);
            // finish();
            sendImageFile(number,path,Msg);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendImageFile(String number,String path,String msg) {

        ArrayList<Uri> files = new ArrayList<Uri>();
        Log.e("fdssdfsdfsdf..2","Misscalll....123"+path);
        String replace = path.replace("[","");
        System.out.println(replace);
        String replace1 = replace.replace("]","");
        System.out.println(replace1);
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
        System.out.println(myList.toString());

        for (int i = 0; i<myList.size();i++){
            System.out.println("suifhuire"+myList.get(i));
            String path2 = myList.get(i);
            Uri myUri = Uri.parse(path2);
            files.add(myUri);
        }

        Log.e("=====================222",number);

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra(Intent.EXTRA_STREAM, files);
        sendIntent.putExtra("jid", number + "@s.whatsapp.net");
        //sendIntent.putExtra(Intent.EXTRA_SUBJECT, "okkk");
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("image/png");
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  //permission ok
        startActivity(sendIntent);

//        Intent sendIntent = new Intent("android.intent.action.MAIN");
//        sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
//        sendIntent.setType("image/png");
//        sendIntent.putExtra("jid", number + "@s.whatsapp.net");
//        sendIntent.setType("text/plain");
//        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.setPackage("com.whatsapp");
//        startActivity(sendIntent);
//        files.clear();



    }
}