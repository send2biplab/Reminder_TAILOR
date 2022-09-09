package com.ubk.casdis_tailor;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ubk.casdis_tailor.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallContactAdapterManual extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    int count=1;

    public static final String MyCOUNTRY = "Mycountry";
    public static final String Code = "codeKey";
    public static final String prefix = "prefix";
    public static final String SPECIALIMAGE = "SpecialImg";

    SharedPreferences sharedpreferenceswp;
    public static final String MyPREFERENCES = "WHATSAPP" ;
    public static final String WPType = "WpType";

    public static final String MyDocument = "AllDocument" ;
    public static final String ImagePath = "ImagePath";
    SharedPreferences sharedpreferences2;

    ArrayList<Uri> files = new ArrayList<Uri>();
    SharedPreferences sharedpreferences;
    Map<String, Map<String, String>>  map2 = new HashMap<>();
    public CallContactAdapterManual(Context applicationContext, Map<String, Map<String, String>> mappp) {
        this.context = applicationContext;
        this.map2 = mappp;

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return map2.size();
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
    public View getView(final int i, View viewholder, ViewGroup viewGroup) {
        viewholder = inflter.inflate(R.layout.activity_list_cont, null);
        TextView country = (TextView) viewholder.findViewById(R.id.title);
        LinearLayout thumbnail =  viewholder.findViewById(R.id.thumbnail);

        sharedpreferenceswp = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences = context.getSharedPreferences(MyCOUNTRY, Context.MODE_PRIVATE);
        int k=i+1;
        country.setText( k+". Batch "+map2.get(String.valueOf(i+1)).size()+" Mo.");


        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("sdssdsdsdsdsd", "" + map2.get(String.valueOf(i + 1)));
                if (!isAccessibilityOn(context, WhatsappAccessibilityService.class)) {
                    Toast.makeText(context, "On Accessibility Service..",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (sharedpreferences.contains(Code)) {

                        Log.e("gsdfsdfsdf", "" + sharedpreferences.getString(prefix, "").toString());
                        /// Log.e("dscdddddddddd","imgg.."+messageArray2.length);

                        String message = sharedpreferences.getString(prefix, "").toString();
                        //String Imagepath = sharedpreferences.getString(SPECIALIMAGE, "").toString();
                        sharedpreferences2 = context.getSharedPreferences(MyDocument, Context.MODE_PRIVATE);
                        String imgpath = sharedpreferences2.getString(ImagePath, "");
                        String Msg =sharedpreferences2.getString("msgPath", "");

                        Log.e("dffdfdfd","==="+imgpath);
                        Log.e("dffdfdfd","==="+Msg);

                        String replace = imgpath.replace("[", "");
                        System.out.println(replace);
                        String replace1 = replace.replace("]", "");
                        System.out.println(replace1);
                        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(", ")));
                        System.out.println(myList.toString());


                        for (int i = 0; i < myList.size(); i++) {
                            System.out.println("suifhuire" + myList.get(i));

                            String path = myList.get(i);
                            Uri myUri = Uri.parse(path);
                            files.add(myUri);
                        }


                        //    DatabaseHandler db = new DatabaseHandler(context);

//                        Log.e("asasasasasasasasa",".."+message);
//                        Log.e("asasasasasasasasa",".."+Imagepath);
//                        Log.e("sfwsdfsdzfcsdzvcwsdcx","..."+map2.size());
//                        Log.e("sfwsdfsdzfcsdzvcwsdcx",i+"1..."+map2.get(String.valueOf(i + 1)));
//                        Log.e("sfwsdfsdzfcsdzvcwsdcx","2..."+map2.get(String.valueOf(i + 1)));


                        ArrayList<String> listItems=new ArrayList<String>();


                        for (Map.Entry<String, String> pair : map2.get(String.valueOf(i + 1)).entrySet()) {

//                            Log.e("asasasasasasas22222",pair.getKey());
//                            Log.e("asasasasasasas33333",pair.getValue());
//                            Log.e("asasasasasasas33333",pair.getValue());


                            String nnnnumber=pair.getValue();
                            nnnnumber = nnnnumber.replace("+", "");
                            HomeFragment.sendddd="22";
                            listItems.add(pair.getValue());
                        }


                        //////////////////start
                        final Handler handler1 = new Handler();

                        //  int Count =10;
                        //  int incriment=1;

                        // int divider=5;
                        // int x,reamening;


                     //   Log.e("adadasdasdasda",""+listItems.size());
                        if (listItems.size()>5)
                        {

                            for (int a = 0; a<=listItems.size() ; a += 5) {
                                int finalB = a;

                                handler1.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        /// Log.e("okkkkkkkkkkkkk", "" + finalB);

                                        int countinc=finalB+4;
                                        for (int a = finalB; a<=countinc; a ++) {
                                            if (countinc <= listItems.size() )
                                            {
                                                DatabaseHandler db2 = new DatabaseHandler(context);
                                              //  db2.CallstatusUpdate(listItems.get(a));
                                                String name;


                                                String s1 = sharedpreferenceswp.getString(WPType, "");
                                                if (s1.equals(""))
                                                {
                                                    new ProcessHelperWp(context).readExcelFileFromAssets(listItems.get(a),message,files);
                                                  //  Log.e("WHATSapp.....","REG.");
                                                   HomeFragment.sendddd="22";
                                                }
                                                else
                                                {
                                                    if (s1.equals("On"))
                                                    {
                                                     ///   Log.e("WHATSapp.....","BUS.");

                                                       HomeFragment.sendddd="22";
                                                        new ProcessHelperBusiness(context).readExcelFileFromAssets(listItems.get(a),message,files);
                                                    }
                                                    else
                                                    {
                                                      //  Log.e("WHATSapp.....","REG.");
                                                        HomeFragment.sendddd="22";
                                                        new ProcessHelperWp(context).readExcelFileFromAssets(listItems.get(a),message,files);
                                                    }
                                                }

                                            }

                                        }

                                    }
                                }, 4000 * a);

                            }




                            // Log.e("okkkkkk....",".."+x);
                            // Log.e("okkkkkk.R...",".."+reamening);
                        }
                        else
                        {
                            for (int a = 0; a<=listItems.size()-1 ;a++) {
                                int finalA = a;
                                DatabaseHandler db2 = new DatabaseHandler(context);
                              //  db2.CallstatusUpdate(listItems.get(a));
                                String name;
                                Log.e("sdfgasfasdfs","sdfsf"+listItems.get(a));
                             //   name=db2.getnamebypnone(listItems.get(a));


                             //   Log.e("sdsfsdfscsdf","..."+name);
//                                 String messagee;
//                                 if (name.equals("")|| name.equals("null"))
//                                 {
//                                     messagee=message;
//                                 }
//                                 else
//                                 {
//                                     messagee="Dear "+name+ ",\n" +message;
//                                 }
//                                 Log.e("okkkkkk....","..5"+messagee);
                                String s1 = sharedpreferenceswp.getString(WPType, "");
                                if (s1.equals(""))
                                {
                                    new ProcessHelperWp(context).readExcelFileFromAssets(listItems.get(a),message,files);
                                    Log.e("WHATSapp.....","REG.");
                                }
                                else
                                {
                                    if (s1.equals("On"))
                                    {
                                        Log.e("WHATSapp.....","BUS.");


                                        new ProcessHelperBusiness(context).readExcelFileFromAssets(listItems.get(a),message,files);
                                    }
                                    else
                                    {
                                        Log.e("WHATSapp.....","REG.");
                                        new ProcessHelperWp(context).readExcelFileFromAssets(listItems.get(a),message,files);
                                    }
                                }
                            }
                            // x = Count / divider;
                            Log.e("okkkkkk....","..5");
                        }
                        //////   enddd

                        for (int a = 0; a<=listItems.size()-1 ;a++) {
                            int finalA = a;
                            //  handler1.postDelayed(new Runnable() {

                        }



                    }
                    else {
                        Toast.makeText(context, " Enter Auto Send Data. ",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        // icon.setImageResource(flags[i]);
        return viewholder;
    }



    private boolean isAccessibilityOn(Context context, Class<? extends AccessibilityService> clazz) {
        Log.e("gfxchgjhvv2", "dzd");
        int accessibilityEnabled = 0;
        final String service = context.getPackageName() + "/" + clazz.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException ignored) {
        }
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                colonSplitter.setString(settingValue);
                while (colonSplitter.hasNext()) {
                    String accessibilityService = colonSplitter.next();

                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


}
