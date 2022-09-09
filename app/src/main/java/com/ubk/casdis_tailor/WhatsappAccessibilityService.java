package com.ubk.casdis_tailor;

import android.accessibilityservice.AccessibilityService;

import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import com.ubk.casdis_tailor.ui.home.HomeFragment;

import java.util.List;

public class WhatsappAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent (AccessibilityEvent event) {
        try {
            if (getRootInActiveWindow () == null) {

                Log.e("serviceee","00");
                return;
            }
            AccessibilityNodeInfoCompat rootInActiveWindow = AccessibilityNodeInfoCompat.wrap (getRootInActiveWindow ());

            Log.e("serviceee","11");
            Log.e("serviceee","11"+ HomeFragment.sendddd);

            if (HomeFragment.sendddd.equals("22"))
            {
                // Whatsapp send button id
                List<AccessibilityNodeInfoCompat> sendMessageNodeInfoList = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
                if (sendMessageNodeInfoList == null || sendMessageNodeInfoList.isEmpty()) {

                    Log.e("serviceee","22");
                    return;
                }

                AccessibilityNodeInfoCompat sendMessageButton = sendMessageNodeInfoList.get(0);
                if (!sendMessageButton.isVisibleToUser()) {
                    Log.e("serviceee","33");
                    return;
                }


                sendMessageButton.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.e("serviceee","44");
                Thread.sleep(1000);
//           // performGlobalAction(GLOBAL_ACTION_BACK);
                performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
                Thread.sleep(1000);
//           // performGlobalAction(GLOBAL_ACTION_BACK);
//
                performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);

           //     HomeFragment.sendddd="11";
                event.describeContents();
                Log.e("serviceee","55");
                // disableSelf();
                onDestroy();


            }


            //AccessibilityNodeInfo accessibilityNodeInfo2 = findAccessibilityNodeInfosByViewId.get(0);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onInterrupt() {
        System.out.println("Whatsapp Accessibility Service onInterrupt");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("serviceee","666");
        Intent intent=new Intent(getApplicationContext(),MainNewActivity.class);
        startActivity(intent);
    }
}