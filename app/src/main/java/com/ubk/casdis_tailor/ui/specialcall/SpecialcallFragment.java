package com.ubk.casdis_tailor.ui.specialcall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.ubk.casdis_tailor.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SpecialcallFragment extends Fragment {

    private SpecialcallViewModel specialcallViewModel;

    TextView gallery,imgurl,Addspecial_submit;

    private final static int FILE_REQUEST_CODE = 1;

    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();

    ArrayList<String> selectedImageList;

    EditText special_msg;

    public static final String MySPECIALCALL = "Myspecialcall" ;
    public static final String SPECIALICALL = "SpecialCall";
    public static final String SPECIALIMAGECall = "SpecialImgcall";

    SharedPreferences sharedpreferences;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        specialcallViewModel =
                ViewModelProviders.of(this).get(SpecialcallViewModel.class);
        View root = inflater.inflate(R.layout.fragment_specialcall, container, false);


        Log.e("rstegrfedwsrtgrfedwsqa","Specialmsg");

        selectedImageList = new ArrayList<>();

        imgurl=root.findViewById(R.id.imgurl);
        gallery=root.findViewById(R.id.gallery);

        special_msg=root.findViewById(R.id.special_msg);

        Addspecial_submit=root.findViewById(R.id.Addspecial_submit);



        sharedpreferences = getActivity().getSharedPreferences(MySPECIALCALL, Context.MODE_PRIVATE);


        if (sharedpreferences.contains(SPECIALICALL)) {
            // name.setText();

            special_msg.setText(sharedpreferences.getString(SPECIALICALL, "").toString());
            imgurl.setText(sharedpreferences.getString(SPECIALIMAGECall, "").toString());

            //  Log.e("rerfwedwsaefvs222222",""+sharedpreferences.getString(Name, ""));
        }
        else {
            //  Log.e("rerfwedwsaefvs111111",""+sharedpreferences.getString(Name, ""));


        }





        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                        .setCheckPermission(true)
                       .setSelectedMediaFiles(mediaFiles)
                        .setShowFiles(true)
                        .setShowImages(true)
                        .setShowAudios(true)
                        .setShowVideos(true)
//                        .setIgnoreNoMedia(false)
//                        .enableVideoCapture(false)
//                        .enableImageCapture(false)
//                        .setIgnoreHiddenFile(false)
                        .setMaxSelection(4)
//
                       .build());
               startActivityForResult(intent, FILE_REQUEST_CODE);

            }
        });


//
        Addspecial_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (special_msg.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Message empty", Toast.LENGTH_SHORT).show();
                }
//                else if (imgurl.getText().toString().equals(""))
//                {
//                    Toast.makeText(getActivity(), "Document not selected", Toast.LENGTH_SHORT).show();
//                }
                else
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(SPECIALICALL, special_msg.getText().toString());
                    editor.putString(SPECIALIMAGECall, imgurl.getText().toString());

                    editor.commit();
                    Toast.makeText(getActivity(),"Thanks",Toast.LENGTH_LONG).show();

                }


            }
        });


//
//

        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("sdfgds","sssssssssssss");
        if (requestCode == FILE_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            List<MediaFile> mediaFiles = data.<MediaFile>getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);


    //    ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
          //  Log.e("dcsxwdasx","ssss"+mediaFiles.get(0));
            if(mediaFiles != null) {
              setMediaFiles(mediaFiles);


               Log.e("sdfgds","aaaaaaaaaaaa");
            } else {
                Toast.makeText(getContext(), "Image not selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles.clear();
        this.mediaFiles.addAll(mediaFiles);

        String[] mStrings = new String[mediaFiles.size()];

        MediaFile file = null;
        for (int i = 0; i < mediaFiles.size(); i++) {

            file = mediaFiles.get(i);

            selectedImageList.add(0, file.getPath());
            mStrings[i] = file.getPath();
            //  imgurl.setText(mediaFiles.get(0).getPath().toString());
        }
            String newString = Arrays.toString(mStrings);
            newString = newString.substring(1, newString.length()-1);

           // Log.e("erwfedwsaefdwsa",""+String.valueOf(selectedImageList));
            Log.e("erwfedwsaefdwsa11",""+String.valueOf(newString));
            imgurl.setText(String.valueOf(selectedImageList));

    }



}