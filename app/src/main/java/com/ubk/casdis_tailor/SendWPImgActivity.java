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

public class SendWPImgActivity extends AppCompatActivity {

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
    String getPromoCodeMobileNo;
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
        setContentView(R.layout.activity_imgsend_wp);


        getPromoCode_PromoCode=findViewById(R.id.getPromoCode_PromoCode);

       Bundle b = new Bundle();
        b = getIntent().getExtras();
        getPromoCodeMobileNo = b.getString("number");
        getPromoCodeName = b.getString("name");

        Log.e("zpppppppp",".."+getPromoCodeName);


//         String number2 = b.getString("number2");
//         String promocode = b.getString("promocode");
//
//        String desc = b.getString("desc");
//        String Imagepath = b.getString("Imagepath");
//
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        String  getDate = simpledateformat.format(calendar.getTime());
//
//
//
//        String replace = Imagepath.replace("[","");
//        System.out.println(replace);
//        String replace1 = replace.replace("]","");
//        System.out.println(replace1);
//        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
//        System.out.println(myList.toString());
//
//
//
//        for (int i = 0; i<myList.size();i++){
//            System.out.println("suifhuire"+myList.get(i));
//
//
//
//            String path = myList.get(i);
//
////
//            Uri myUri = Uri.parse(path);
//            files.add(myUri);
//        }


     //   number = number.replace("+", "");


      //  Log.e("dfgsdsxws","number"+number);
        //  Log.e("dfgsdsxws","number"+msg);
          db = new DatabaseHandler(this);

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.showAll_db();
        Log.d("byjum", ""+arrayList_db_count);

        if (!arrayList_db_count.isEmpty()) {

            // promocodesssList.add("Select");

            promocodesssList.clear();

            for (int i=0;i<arrayList_db_count.size();i++)
            {
                HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);

                PromoCodeName=map.get("PromoCodeName");
                PromoCodeDescription=map.get("PromoCodeDescription");
                msgtype=map.get("type");

//                Log.d("PromoCodeName111",""+map.get("PromoCodeName"));
//                Log.d("PromoCodeDescription111",""+map.get("PromoCodeDescription"));

                promocodesssList.add(PromoCodeName);
                promocodesssDesc.put(PromoCodeName,PromoCodeDescription);
                promocodessstype.put(PromoCodeName,msgtype);
                imgpath.put(PromoCodeName,map.get("imgpath"));

                // Log.e("tyjkjkbn",""+promocodesssList);
                // Log.d("rugisfsr11",""+promocodesssDesc);

            }


        }



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, promocodesssList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getPromoCode_PromoCode.setAdapter(dataAdapter);


        //
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


                Log.e("111","sdfwesd"+promocodeYype);

                Calendar  calendar = Calendar.getInstance();
                SimpleDateFormat simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String getDate = simpledateformat.format(calendar.getTime());

                sharedpreferences = SendWPImgActivity.this.getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);

                if (promocodeYype.equals("Text"))
                {

                    Log.e("sfdsfds","sdfwesd"+promocodeYype);

                    Log.e("zppppppppq",".."+getPromoCodeName);

                    String phoneNo=""+sharedpreferences.getString(Code, "")+getPromoCodeMobileNo;
                    //  String message="Promo Code : "+getPromoCodePromoCodeeee+" and "+"Promo Code Description : "+getPromoCodePromoCodeDesc;
                    String message=getPromoCodePromoCodeDesc;

//                    Log.e("efd","dsdsd"+message);
//                    Log.e("efd......","dsdsd"+phoneNo);
//
//
//                    Log.e("oyfiflhgjbiyfg","yiyoygupgug"+phoneNo);

                    DatabaseHandler db = new DatabaseHandler(SendWPImgActivity.this);

                    if (getPromoCodeName.equals(""))
                    {
                        new ProcessHelper(SendWPImgActivity.this).readExcelFileFromAssets(phoneNo,getPromoCodePromoCodeDesc);
                    }
                    else
                    {
                        new ProcessHelper(SendWPImgActivity.this).readExcelFileFromAssets(phoneNo,"Hay "+getPromoCodeName+","+getPromoCodePromoCodeDesc);
                    }





                    db.mobileNoInsert(getPromoCodeMobileNo,getStatus,getStatus);
                    // DatabaseHandler db = new DatabaseHandler(getActivity());
                    // db.mobileNoInsert(getPromoCodeMobileNo,getPromoCodePromoCodeeee,getPromoCodePromoCodeDesc);
                   // db.allRecordInsert(getPromoCodeMobileNo,getPromoCodePromoCodeeee,getPromoCodePromoCodeDesc,getDate,getStatus,getPromoCodeName);

                    finish();



                }
                else
                {
                            try {
//
//                                String phoneNo="+"+sharedpreferences.getString(Code, "")+getPromoCodeMobileNo;
//
//                                Intent intent=new Intent(getContext(), SendWPImgActivity.class);
//                                intent.putExtra("number",phoneNo);
//                                intent.putExtra("number2",getPromoCodeMobileNo);
//                                intent.putExtra("promocode",getPromoCodePromoCodeeee);
//                                 intent.putExtra("desc",getPromoCodePromoCodeDesc);
//                                intent.putExtra("Imagepath",imagepathhhh);
//                                getActivity().startActivity(intent);



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

//
                                    Uri myUri = Uri.parse(path);
                                    files.add(myUri);
                                }

                       String phoneNo="+"+sharedpreferences.getString(Code, "")+getPromoCodeMobileNo;


                      Log.e("refvdcsxzfvdx","aaa"+phoneNo);

                    DatabaseHandler db = new DatabaseHandler(SendWPImgActivity.this);

                                if (db.MobileNoCheckIfMyTitleExists(getPromoCodeMobileNo))
                                {
                                    Log.e("erwefdsrgfsds","resxesaes");
                                    //db.mobileNoInsert(getPromoCodeMobileNo,getPromoCodePromoCodeeee,getPromoCodePromoCodeDesc);

                                }
                                else
                                {
                                    Log.e("erwefdsrgfsds","111111111");
                                    // db.mobileNoInsert(getPromoCodeMobileNo,getStatus,getStatus);

                                }


                       db.mobileNoInsert(getPromoCodeMobileNo,getStatus,getStatus);
               //      db.allRecordInsert(getPromoCodeMobileNo,getPromoCodePromoCodeeee,getPromoCodePromoCodeDesc,getDate,getStatus,getPromoCodeName);



                            String msggg;
                                if (getPromoCodeName.equals(""))
                                {
                                  //  new ProcessHelper(SendWPImgActivity.this).readExcelFileFromAssets(phoneNo,getPromoCodePromoCodeDesc);
                                    msggg=getPromoCodePromoCodeDesc;
                                }
                                else
                                {
                                   // new ProcessHelper(SendWPImgActivity.this).readExcelFileFromAssets(phoneNo,"Hay "+getPromoCodeName+","+getPromoCodePromoCodeDesc);

                                    msggg="Hey "+getPromoCodeName+","+getPromoCodePromoCodeDesc;
                                }



                                String toNumber = phoneNo; // contains spaces.
                                toNumber = toNumber.replace("+", "").replace(" ", "");

                              //  Log.e("gfdzfhgcfd","duzjfdg"+toNumber);

                                Intent sendIntent = new Intent("android.intent.action.MAIN");
                                sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
                                sendIntent.setType("image/png");
                                sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");

                                if (promocodeYype.equals("Document"))
                                {

                                }
                                else
                                {
                                    sendIntent.putExtra(Intent.EXTRA_TEXT, msggg);
                                }



                                sendIntent.setType("text/plain");
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.setPackage("com.whatsapp.w4b");
                                startActivity(sendIntent);
                                files.clear();
                                finish();

                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }



                }



//                HomeFragment.sendddd="22";
//                DatabaseHandler db = new DatabaseHandler(SendWPImgActivity.this);
//
//                db.mobileNoInsert(number2,"Pending","Pending");
//                db.allRecordInsert(number2,promocode,desc,getDate,"Pending");
//
//
//
//                String toNumber = number; // contains spaces.
//                toNumber = toNumber.replace("+", "").replace(" ", "");
//
//                //  Log.e("gfdzfhgcfd","duzjfdg"+toNumber);
//
//                Intent sendIntent = new Intent("android.intent.action.MAIN");
//                sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
//                sendIntent.setType("image/png");
//                sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
//                // sendIntent.putExtra(Intent.EXTRA_TEXT, "77");
//                sendIntent.setType("text/plain");
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.setPackage("com.whatsapp");
//                startActivity(sendIntent);
//                finish();
//                files.clear();


            }
        });


    }
}
