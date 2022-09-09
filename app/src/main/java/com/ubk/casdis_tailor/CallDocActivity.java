package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ubk.casdis_tailor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CallDocActivity extends AppCompatActivity {

    String number;


    ArrayList<Uri> files = new ArrayList<Uri>();

    TextView ddddddd;

    Spinner getPromoCode_PromoCode;
    DatabaseHandler  db;
    List<String> promocodesssList = new ArrayList<String>();
    String PromoCodeName,PromoCodeDescription;
    String msgtype;
    HashMap<String,String> promocodesssDesc=new HashMap<>();

    HashMap<String,String> promocodessstype=new HashMap<>();
    HashMap<String,String> imgpath=new HashMap<>();
    String getPromoCodePromoCodeeee;
   // String getPromoCodeMobileNo;
    String getPromoCodePromoCodeDesc;
    String imagepathhhh;
    String promocodeYype;
    String getPromoCodeName;
    String getStatus="Pending";


    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";

    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_doc);

        getPromoCode_PromoCode=findViewById(R.id.getPromoCode_PromoCode);
        Log.e("dfsdsfvd","vhghgchgcg");

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        number = b.getString("number");

        // String number2 = b.getString("number2");
        // String rand = b.getString("rand");

        String msg = b.getString("msg");
        String Imagepath = b.getString("Imagepath");


        Log.d("asasasass",""+number);
        Log.d("asasasass",""+Imagepath);


        db = new DatabaseHandler(this);

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.showAll_db_call();
        Log.d("byjumasasasasa", ""+arrayList_db_count);

        if (!arrayList_db_count.isEmpty()) {

            // promocodesssList.add("Select");

            promocodesssList.clear();

            for (int i=0;i<arrayList_db_count.size();i++)
            {
                HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);

                PromoCodeName=map.get("PromoCodeName");
                PromoCodeDescription=map.get("imgpath");
                msgtype=map.get("type");



                promocodesssList.add(PromoCodeName);
                promocodesssDesc.put(PromoCodeName,PromoCodeDescription);
                promocodessstype.put(PromoCodeName,msgtype);
                imgpath.put(PromoCodeName,map.get("imgpath"));

                // Log.e("tyjkjkbn",""+promocodesssList);
                // Log.d("rugisfsr11",""+promocodesssDesc);

            }


        }

        Log.e("sdsdsd","...."+promocodesssList);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, promocodesssList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getPromoCode_PromoCode.setAdapter(dataAdapter);


        getPromoCode_PromoCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getPromoCodePromoCodeeee=getPromoCode_PromoCode.getSelectedItem().toString();
                getPromoCodePromoCodeDesc=promocodesssDesc.get(getPromoCodePromoCodeeee);
                promocodeYype= promocodessstype.get(getPromoCodePromoCodeeee);

                imagepathhhh=imgpath.get(getPromoCodePromoCodeeee);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ddddddd=findViewById(R.id.AddPromoCode_submit);
        ddddddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("asasasass.......",""+number);

                Log.e("111","sdfwesd"+promocodeYype);

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String getDate = simpledateformat.format(calendar.getTime());

                sharedpreferences = CallDocActivity.this.getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);


              //  {


                System.out.println("rhgoih111"+ getPromoCodePromoCodeDesc);
                // System.out.println("rhgoih111"+ Arrays.toString(getPromoCodePromoCodeDesc));

                String replace = imagepathhhh.replace("[","");
                System.out.println(replace);
                String replace1 = replace.replace("]","");
                System.out.println(replace1);
                List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
                System.out.println(myList.toString());


                for (int i = 0; i<myList.size();i++){
                    System.out.println("suifhuire"+myList.get(i));
                    String path = myList.get(i);
                    Uri myUri = Uri.parse(path);
                    files.add(myUri);
                }




                Log.e("refvdcsxzfvdx","aaa"+number);


                        DatabaseHandler db = new DatabaseHandler(CallDocActivity.this);

                        db.CallstatusUpdate2(number);



                        String toNumber = number; // contains spaces.
                        toNumber = toNumber.replace("+", "").replace(" ", "");


                        Log.e("sdfsfsdfc","......."+toNumber);

                        Intent sendIntent = new Intent("android.intent.action.MAIN");
                        sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
                        sendIntent.setType("image/png");
                        sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
                        sendIntent.setType("text/plain");
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.setPackage("com.whatsapp.w4b");
                        startActivity(sendIntent);
                        files.clear();
                        finish();


            }
        });


    }
}