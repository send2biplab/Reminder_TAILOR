package com.ubk.casdis_tailor.ui.adddefoultmessage;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.google.gson.Gson;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.ubk.casdis_tailor.Utils;
import com.ubk.casdis_tailor.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AddMsgFragment extends Fragment {

    private AddViewModel addViewModel;
    ArrayList<String> selectedImageList;
    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();
    private final static int FILE_REQUEST_CODE = 1;
    TextView gallery,imgpath,AddPromoCode_submit;

    private static String dirPath;
    int imgcount=30;

    public static final String MyDocument = "AllDocument" ;


    public static final String ImagePath = "ImagePath";
    public static final String msgPath = "msgPath";


    SharedPreferences sharedpreferences;


    EditText AddPromoCode_description;

    TextView AddPromoCode_Cancel;


    String[] courses = { "Select Msg Type", "Delivery Msg",
            "Trial Msg", "Follow up"};

    Spinner spinnertype;

    String positionSelect="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        sharedpreferences = getActivity().getSharedPreferences(MyDocument, Context.MODE_PRIVATE);


        selectedImageList = new ArrayList<>();
        imgpath=root.findViewById(R.id.imgpath);
        AddPromoCode_Cancel=root.findViewById(R.id.AddPromoCode_Cancel);
        AddPromoCode_description=root.findViewById(R.id.AddPromoCode_description);
        AddPromoCode_submit=root.findViewById(R.id.AddPromoCode_submit);
        spinnertype=root.findViewById(R.id.spinnertype);

        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, courses);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnertype.setAdapter(ad);


        spinnertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int po, long l) {

                Log.e("====33333",""+po);

                if (po==1)
                {
                    positionSelect="DELIVERY_DATE";

                    String jsonnn = sharedpreferences.getString(positionSelect, "");
                    Log.e("====3232323===",""+jsonnn);
                    if (jsonnn.equals(""))
                    {
                        AddPromoCode_description.setText("");
                        imgpath.setText("");
                    }
                    else {
                        try {
                            JSONObject obj = new JSONObject(jsonnn);

                            Log.e("====3232323===", "" + obj.getString("msg"));

                            AddPromoCode_description.setText(obj.getString("msg"));
                            imgpath.setText(obj.getString("image"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }
                else if (po==2)
                {
                    positionSelect="TRIAL_DATE";
                    String jsonnn = sharedpreferences.getString(positionSelect, "");
                    Log.e("====3232323===",""+jsonnn);
                    if (jsonnn.equals(""))
                    {
                        AddPromoCode_description.setText("");
                        imgpath.setText("");
                    }
                    else {
                        try {
                            JSONObject obj = new JSONObject(jsonnn);

                            Log.e("====3232323===", "" + obj.getString("msg"));

                            AddPromoCode_description.setText(obj.getString("msg"));
                            imgpath.setText(obj.getString("image"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
                else if (po==3)
                {
                    positionSelect="FOLLOWUP_DATE";
                    String jsonnn = sharedpreferences.getString(positionSelect, "");
                    Log.e("====3232323===",""+jsonnn);
                    if (jsonnn.equals(""))
                    {
                        AddPromoCode_description.setText("");
                        imgpath.setText("");
                    }
                    else {
                        try {
                            JSONObject obj = new JSONObject(jsonnn);

                            Log.e("====3232323===", "" + obj.getString("msg"));

                            AddPromoCode_description.setText(obj.getString("msg"));
                            imgpath.setText(obj.getString("image"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
                else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dirPath = Utils.getRootDirPath(getActivity());
        PRDownloader.initialize(getActivity());
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getContext(), config);

       // Log.d("efsadfsdfsf",""+dirPath);

      //  Log.e("ergfeadsefs","List211");


     //   String path = sharedpreferences.getString(ImagePath, "");
      //  String msg = sharedpreferences.getString(msgPath, "");



//
//        if (msg.equals(""))
//        {
//
//        }
//        else
//        {
//            AddPromoCode_description.setText(msg);
//        }
//
//
//        if(path.equals(""))
//        {
//
//        }
//        else
//        {
//            imgpath.setText(path);
//
//        }



        AddPromoCode_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(positionSelect, "");
              //  editor.putString(msgPath, "");
                editor.commit();

                AddPromoCode_description.setText("");
                imgpath.setText("");
                Toast.makeText(getContext(), "Clear Successfully !!", Toast.LENGTH_LONG).show();
            }
        });

        gallery=root.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgpath.setText("");

                Intent intent = new Intent(getActivity(), FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                        .setCheckPermission(true)
                        .setSelectedMediaFiles(mediaFiles)
                        .setShowFiles(true)
                        .setShowImages(true)
                        .setShowAudios(true)
                        .setShowVideos(true)
                        .setIgnoreNoMedia(false)
                        .enableVideoCapture(false)
                        .enableImageCapture(false)
                        .setIgnoreHiddenFile(false)
                        .setMaxSelection(imgcount)
                        .build());
                startActivityForResult(intent, FILE_REQUEST_CODE);
            }
        });

        AddPromoCode_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgpath.equals("") || AddPromoCode_description.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Please Select Document !!", Toast.LENGTH_LONG).show();
                }
                else
                {

                    if (!positionSelect.equals(""))
                    {

                        HashMap<String, String> map = new HashMap<>();
                        map.put("msg", AddPromoCode_description.getText().toString());
                        map.put("image", imgpath.getText().toString());

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        Gson gson = new Gson();

                        String jsonText = gson.toJson(map);
                        editor.putString(positionSelect, jsonText);
                        editor.apply();
                        //String json = gson.toJson(list);

                        Log.e("=====",""+positionSelect);

                    }
                    else {
                        Toast.makeText(getContext(), "Please Select Msg type !!", Toast.LENGTH_LONG).show();
                    }



                  //  editor.putString("FOLLOWUP_DATE", json);

//                    editor.putString(ImagePath, imgpath.getText().toString());
//                    editor.putString(msgPath, AddPromoCode_description.getText().toString());
//
//                    editor.commit();

                    Toast.makeText(getContext(), "Submit Successfully !!", Toast.LENGTH_LONG).show();
                }


            }
        });



        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Log.e("hbbcjbad", "..."+data.getData());

        if (requestCode == FILE_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            List<MediaFile> mediaFiles = data.<MediaFile>getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);

            //Log.e("dcsxwdasx","ssss"+mediaFiles.get(1));
            if(mediaFiles != null) {
                setMediaFiles(mediaFiles);

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
            mStrings[i]=file.getPath();

            Log.e("dsfdasd","11"+file.getUri());
            // }
        }

        String newString = Arrays.toString(mStrings);
        newString = newString.substring(1, newString.length()-1);

        Log.e("erwfedwsaefdwsa",""+String.valueOf(selectedImageList));
        Log.e("erwfedwsaefdwsa11",""+String.valueOf(newString));
        imgpath.setText(String.valueOf(selectedImageList));




        //  fileListAdapter.notifyDataSetChanged();
    }





}