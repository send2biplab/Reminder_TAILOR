package com.ubk.casdis_tailor.ui.permission;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.R;
import com.ubk.casdis_tailor.WhatsappAccessibilityService;

public class PermissionFragment extends Fragment {

    private PeemissionViewModel peemissionViewModel;
    Switch sw;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        peemissionViewModel =
                ViewModelProviders.of(this).get(PeemissionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_permission, container, false);

        Log.e("fhgdfdsaSDFBGB", "Permission ");

        //askPermissions();



        sw = (Switch) root.findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    Log.e("esdferfds","on");
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                    Log.e("esdferfds","off");
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                }
            }
        });




        return root;
    }

    private void askPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(getActivity(), permissions, 10);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 10: {
                for (int i = 0; i < grantResults.length; i++)
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        break;
                    }else{
                        if (!isAccessibilityOn(getActivity(), WhatsappAccessibilityService.class)) {
                            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                            startActivity(intent);
                            return;
                        }
                    }
                return;
            }
        }
    }

    private boolean isAccessibilityOn(Context context, Class<? extends AccessibilityService> clazz) {
        Log.e("gfxchgjhvv","dzd");
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


    @Override
    public void onResume() {

        Log.e("dsaydftghb","22");


        if (!isAccessibilityOn(getActivity(), WhatsappAccessibilityService.class)) {

            sw.setChecked(false);

//            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//            startActivity(intent);
            Log.e("dsaydftghb","11");
        }
        else
        {
            Log.e("esdferfds","000");
            sw.setChecked(true);

        }
        super.onResume();
    }


}