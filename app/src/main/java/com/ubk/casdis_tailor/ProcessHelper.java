package com.ubk.casdis_tailor;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import java.net.URLEncoder;

public class ProcessHelper {
    private Context mContext;
     public ProcessHelper(Context context) {
        this.mContext=context;
    }

    public void readExcelFileFromAssets(String number,String message) {


        sendMessage(number,message);

    //    Log.e("rgtrfedwsqgbrf","fdfwdfd");
   }


    private void sendMessage(String strMobileNo,String messagee) {
        try {
            if (strMobileNo != null && !strMobileNo.equalsIgnoreCase("")) {

               // String strWhatsAppNo = "+91" + strMobileNo; // E164 format without '+' sign
                System.out.println("strWhatsAppNo: "+strMobileNo);

                //String url = "https://api.whatsapp.com/send?phone="+ strMobileNo +"&text=" + URLEncoder.encode(messagee, "UTF-8");
                PackageManager packageManager = mContext.getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);

                try {
                    String url = "https://api.whatsapp.com/send?phone="+ strMobileNo +"&text=" + URLEncoder.encode(messagee, "UTF-8");

                    Log.e("gfvdsxefd","grfvedsa"+url);
                    //String url = "https://api.whatsapp.com/send?phone="+ phoneNo +"&text=" +encodeURIComponent(imageURL);;
                    i.setPackage("com.whatsapp.w4b");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        mContext.startActivity(i);

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
