package com.ubk.casdis_tailor;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.ubk.casdis_tailor.R;
import com.ubk.casdis_tailor.ui.adddefoultmessage.AddViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PdfFragment extends Fragment {

    private AddViewModel addViewModel;
    ArrayList<String> selectedImageList;
    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();
    private final static int FILE_REQUEST_CODE = 1;
    TextView gallery,imgpath,AddPromoCode_submit;

    private static String dirPath;
    int imgcount=30;

    public static final String MyDocumentPDF = "AllDocumentPDF" ;
    public static final String ImagePathPDF = "ImagePathPDF";
    SharedPreferences sharedpreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        sharedpreferences = getActivity().getSharedPreferences(MyDocumentPDF, Context.MODE_PRIVATE);


        selectedImageList = new ArrayList<>();
        imgpath=root.findViewById(R.id.imgpath);
        AddPromoCode_submit=root.findViewById(R.id.AddPromoCode_submit);

        dirPath = Utils.getRootDirPath(getActivity());
        PRDownloader.initialize(getActivity());
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getContext(), config);

        Log.d("efsadfsdfsf",""+dirPath);

        Log.e("ergfeadsefs","List2");
        String path = sharedpreferences.getString(ImagePathPDF, "");

        if(path.equals(""))
        {
            Log.e("ergfeadsefs","111");
        }
        else
        {
            imgpath.setText(path);
            Log.e("ergfeadsefs","2222222");
        }
        Log.d("efsadfsdfsf===============",""+path);


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
                if (imgpath.equals(""))
                {
                    Toast.makeText(getContext(), "Please Select Document !!", Toast.LENGTH_LONG).show();
                }
                else
                {

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(ImagePathPDF, imgpath.getText().toString());

                    editor.commit();

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