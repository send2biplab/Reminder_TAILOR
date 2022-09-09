package com.ubk.casdis_tailor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ubk.casdis_tailor.ui.home.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CallContactAdapter  extends BaseAdapter {

    Context context;
    String countryList[];
    String flags[];
    String status[];
    LayoutInflater inflter;

    int count=1;

    String typee;

    String MySPECIALCALL = "Myspecialcall" ;
    String SPECIALICALL = "SpecialCall";
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreferences1;

    public static final String MyCOUNTRY = "Mycountry" ;
    public static final String Code = "codeKey";
    public static final String prefix = "prefix";

    public static final String MyDocument = "AllDocument" ;
    public static final String ImagePath = "ImagePath";
    SharedPreferences sharedpreferences2;

    public static final String MyDocumentPDF = "AllDocumentPDF" ;
    public static final String ImagePathPDF = "ImagePathPDF";
    SharedPreferences sharedpreferences3;


    public CallContactAdapter(Context applicationContext, String[] countryList,String[] flags,String[] status,String typee) {
        this.context = applicationContext;
        this.countryList = countryList;
        this.flags = flags;
        this.status = status;
        this.typee = typee;
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

        RelativeLayout saasasasa=view.findViewById(R.id.saasasasa);





//        Log.e("asasasasas",""+status[i]);
//        if (status[i].equals("Done"))
//        {
           country.setText(" Phone No :- "+countryList[i]);
           description.setText(" Name :- "+status[i]);
//            country.setTextColor(Color.RED);
//        }
//        else
//        {
//            country.setText("Phone No :- "+countryList[i]);
//
//        }

        saasasasa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure, You wanted to make Erase Number data. ");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {


                                Log.e("------3333--",""+countryList[i]);
                                DatabaseHandler db = new DatabaseHandler(context);
                                 db.deleteDeletePhonhomee(countryList[i]);
                                 notifyDataSetChanged();



                                 Toast.makeText(context, "Successfully deleted", Toast.LENGTH_LONG).show();


                                Intent intent = new Intent(context,MainNewActivity.class);
                                context.startActivity(intent);


                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return false;

            }
        });

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                country.setTextColor(Color.RED);

                notifyDataSetChanged();


          Log.e("=====32323232==========",""+typee);//FOLLOWUP_DATE

                sharedpreferences2 = context.getSharedPreferences(MyDocument, Context.MODE_PRIVATE);
                sharedpreferences1 = context.getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);
                sharedpreferences = context.getSharedPreferences(MySPECIALCALL, Context.MODE_PRIVATE);
                sharedpreferences3 = context.getSharedPreferences(MyDocumentPDF, Context.MODE_PRIVATE);


                String jsonnn = sharedpreferences2.getString(typee, "");
                Log.e("====3232323===",""+jsonnn);

                   JSONObject obj = null;

                    obj = new JSONObject(jsonnn);


                Log.e("====3232323===", "" + obj.getString("msg"));

               // String path = sharedpreferences2.getString(ImagePath, "");
              //  String Msg =sharedpreferences2.getString("msgPath", "");
                String path = obj.getString("image");
                String Msg =obj.getString("msg");

                String path_pdf = sharedpreferences3.getString(ImagePathPDF, "");

//                Log.e("======2321========",""+path);
//                Log.e("======2322========",""+Msg);
//                Log.e("======2323========",""+path_pdf);
//                Log.e("======2324========",""+flags[i]);


                if (sharedpreferences1.getString(Code, "").equals(""))
                {
                    Toast.makeText(context," Please Add Prefix. ",
                            Toast.LENGTH_SHORT).show();
                   // Toast.makeText(context," Please Prefix Contact Number", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    SharedPreferences sharedPrefs = context.getSharedPreferences("switch", Context.MODE_PRIVATE);
                    boolean switchState = sharedPrefs.getBoolean("NameOfThingToSave", false);
                    //switchCompat.setChecked(sharedPrefs.getBoolean("NameOfThingToSave", true));
                    Log.e("jaaakllkj", "" + switchState);

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

                    Log.e("====222===",""+countryList[i]);
                    Log.e("====222===",""+countryList.length);
                    //////////////////start
                    final Handler handler1 = new Handler();

                    if (switchState) {
                        HomeFragment.sendddd="22";
                        new ProcessHelperBusiness(context).readExcelFileFromAssets(sharedpreferences1.getString(Code, "")+countryList[i],Msg,files);
                        //  sendImageFileBusiness(sharedpreferences1.getString(Code, "")+countryList[i],path,Msg,path_pdf,flags[i]);
                    }
                    else
                    {
                        HomeFragment.sendddd="22";
                        new ProcessHelperWp(context).readExcelFileFromAssets(sharedpreferences1.getString(Code, "")+countryList[i],Msg,files);
                        //  sendImageFile(sharedpreferences1.getString(Code, "")+countryList[i],path,Msg,path_pdf,flags[i]);
                    }

                 }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        return view;
    }

    private void sendImageFile(String number,String path,String msg,String path_pdf,String id) {


        Log.e("=========32323=","___"+id);
        DatabaseHandler db = new DatabaseHandler(context);
      ///  db.statusUpdateImage(id);


        ArrayList<Uri> files = new ArrayList<Uri>();
        Log.e("fdssdfsdfsdf..2","Misscalll....123"+path);
        String replace = path.replace("[","");
        System.out.println(replace);
        String replace1 = replace.replace("]","");
        System.out.println(replace1);
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
        System.out.println(myList.toString());


//        //ArrayList<Uri> files2 = new ArrayList<Uri>();
//        Log.e("fdssdfsdfsdf..2","path_pdf....123"+path_pdf);
//        String replace2 = path_pdf.replace("[","");
//        System.out.println(replace2);
//        String replace3 = replace2.replace("]","");
//        System.out.println(replace3);
//        List<String> myList4 = new ArrayList<String>(Arrays.asList(replace3.split(", ")));
//        System.out.println(myList4.toString());

        for (int i = 0; i<myList.size();i++){
            System.out.println("suifhuire"+myList.get(i));
            String path2 = myList.get(i);
            Uri myUri = Uri.parse(path2);
            files.add(myUri);
        }
//
//        for (int i = 0; i<myList4.size();i++){
//            System.out.println("suifhuire"+myList4.get(i));
//            String path21 = myList4.get(i);
//            Uri myUri1 = Uri.parse(path21);
//            files.add(myUri1);
//        }


        Log.e("=====================222",""+msg);

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra(Intent.EXTRA_STREAM, files);
        sendIntent.putExtra("jid", number + "@s.whatsapp.net");
        //sendIntent.putExtra(Intent.EXTRA_SUBJECT, "okkk");
         sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("image/png");
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  //permission ok
        context.startActivity(sendIntent);



    }


    private void sendImageFileBusiness(String number,String path,String msg,String path_pdf,String id) {


        Log.e("=========32323=","___"+id);
        DatabaseHandler db = new DatabaseHandler(context);
      //statusUpdateImage  db.statusUpdateImage(id);


        ArrayList<Uri> files = new ArrayList<Uri>();
        Log.e("fdssdfsdfsdf..2","Misscalll....123"+path);
        String replace = path.replace("[","");
        System.out.println(replace);
        String replace1 = replace.replace("]","");
        System.out.println(replace1);
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
        System.out.println(myList.toString());


//        //ArrayList<Uri> files2 = new ArrayList<Uri>();
//        Log.e("fdssdfsdfsdf..2","path_pdf....123"+path_pdf);
//        String replace2 = path_pdf.replace("[","");
//        System.out.println(replace2);
//        String replace3 = replace2.replace("]","");
//        System.out.println(replace3);
//        List<String> myList4 = new ArrayList<String>(Arrays.asList(replace3.split(", ")));
//        System.out.println(myList4.toString());

        for (int i = 0; i<myList.size();i++){
            System.out.println("suifhuire"+myList.get(i));
            String path2 = myList.get(i);
            Uri myUri = Uri.parse(path2);
            files.add(myUri);
        }

//        for (int i = 0; i<myList4.size();i++){
//            System.out.println("suifhuire"+myList4.get(i));
//            String path21 = myList4.get(i);
//            Uri myUri1 = Uri.parse(path21);
//            files.add(myUri1);
//        }


        Log.e("=====================222",""+files);

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.putExtra(Intent.EXTRA_STREAM, files);
        sendIntent.putExtra("jid", number + "@s.whatsapp.net");
        //sendIntent.putExtra(Intent.EXTRA_SUBJECT, "okkk");
        //  sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setPackage("com.whatsapp.w4b");
        sendIntent.setType("image/png");
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  //permission ok
        context.startActivity(sendIntent);



    }



}
