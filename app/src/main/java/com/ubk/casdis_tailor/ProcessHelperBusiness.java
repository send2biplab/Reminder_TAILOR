package com.ubk.casdis_tailor;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;


import com.ubk.casdis_tailor.ui.home.HomeFragment;

import java.net.URLEncoder;
import java.util.ArrayList;


public class ProcessHelperBusiness {
    private Context mContext;
    public ProcessHelperBusiness(Context context) {
        this.mContext=context;
    }

    public void readExcelFileFromAssets(String number, String message, ArrayList<Uri> files) {


        sendMessage(number,message,files);

        //    Log.e("rgtrfedwsqgbrf","fdfwdfd");
    }


    private void sendMessage(String strMobileNo,String messagee,ArrayList<Uri> files) {
        try {
            if (strMobileNo != null && !strMobileNo.equalsIgnoreCase("")) {

                // String strWhatsAppNo = "+91" + strMobileNo; // E164 format without '+' sign


                Log.e("asasasasasa","..."+files.toString());
                Log.e("asasasasasa","..."+files.size());


                System.out.println("strWhatsAppNo: "+strMobileNo);

                //String url = "https://api.whatsapp.com/send?phone="+ strMobileNo +"&text=" + URLEncoder.encode(messagee, "UTF-8");
                PackageManager packageManager = mContext.getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);

                try {
                 HomeFragment.sendddd="22";

                    if (files.toString().equals("[]"))
                    {
                        Log.e("1111111","..."+files.size());


                        String url = "https://api.whatsapp.com/send?phone="+ strMobileNo +"&text=" + URLEncoder.encode(messagee, "UTF-8");
//
//                    Log.e("gfvdsxefd","grfvedsa"+url);

                        //String url = "https://api.whatsapp.com/send?phone="+ phoneNo +"&text=" +encodeURIComponent(imageURL);;

                        //  i.setPackage("com.whatsapp");
                        i.setPackage("com.whatsapp.w4b");
                        i.setData(Uri.parse(url));
                        if (i.resolveActivity(packageManager) != null) {
                            mContext.startActivity(i);

                        }

                    }
                    else
                    {
                        strMobileNo = strMobileNo.replace("+", "");
                        //strMobileNo
                        //Log.e("22222222","..."+files.get(0));
                        Intent sendIntent = new Intent("android.intent.action.MAIN");
                        // sendIntent.putExtra(Intent.EXTRA_STREAM, files);
                        sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
                        sendIntent.putExtra("jid", strMobileNo + "@s.whatsapp.net");
                        sendIntent.putExtra(Intent.EXTRA_TEXT, messagee);
                        sendIntent.setAction(Intent.ACTION_SEND);
                        // sendIntent.setPackage("com.whatsapp");
                        sendIntent.setPackage("com.whatsapp.w4b");
                        sendIntent.setType("image/png");
                        mContext.startActivity(sendIntent);
                    }



                } catch (Exception e){
                    e.printStackTrace();
                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

