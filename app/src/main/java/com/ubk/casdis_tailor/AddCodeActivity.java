package com.ubk.casdis_tailor;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.ubk.casdis_tailor.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddCodeActivity extends AppCompatActivity {


    EditText AddPromoCode_promocode,AddPromoCode_description;
    TextView AddPromoCode_submit,AddPromoCode_Cancel;

    TextView gallery,pdfupload;
    ArrayList<String> selectedImageList;
   // String[] projection = {MediaStore.MediaColumns.DATA};


    ArrayList<ImageModel> imageList;
    String  setlcttype="Text & Document";
    String AddPromoCodePromocode,AddPromoCodeDescription;

    private final static int FILE_REQUEST_CODE = 1;
    //private FileListAdapter fileListAdapter;
    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();
    int i;

    private static String dirPath;

    TextView imgpath;

    LinearLayout messagesss;

    private static final int REQUEST_CHOOSER = 1234;
    int imgcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_code);

        AddPromoCode_promocode=findViewById(R.id.AddPromoCode_promocode);
        AddPromoCode_description=findViewById(R.id.AddPromoCode_description);
        AddPromoCode_submit=findViewById(R.id.AddPromoCode_submit);
        AddPromoCode_Cancel=findViewById(R.id.AddPromoCode_Cancel);
        imgpath=findViewById(R.id.imgpath);


        selectedImageList = new ArrayList<>();

      //  spinnertype=findViewById(R.id.spinnertype);
        messagesss=findViewById(R.id.messagesss);
        pdfupload=findViewById(R.id.pdfupload);

        imageList = new ArrayList<>();




        dirPath = Utils.getRootDirPath(AddCodeActivity.this);

        PRDownloader.initialize(AddCodeActivity.this);


        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

        Log.d("efsadfsdfsf",""+dirPath);



        gallery=findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(AddCodeActivity.this, FilePickerActivity.class);
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


        List<String> categories = new ArrayList<String>();
        categories.add("Select Type");
        categories.add("Text");
        categories.add("Document");
        categories.add("Text & Document");

      //  item

        imgcount=4;
        gallery.setVisibility(View.VISIBLE);
        messagesss.setVisibility(View.VISIBLE);



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        AddPromoCode_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (setlcttype.equals("Select Type"))
                {
                    Toast.makeText(AddCodeActivity.this, "Please Select Type !!", Toast.LENGTH_LONG).show();
                }

              else
                {

                    AddPromoCodeDescription = AddPromoCode_description.getText().toString();
                    AddPromoCodePromocode = AddPromoCode_promocode.getText().toString();

                       Log.e("dffgffdfdfdfdfdfd",".."+imgpath.getText());

                    if (AddPromoCodePromocode.equals(""))
                    {
                        Toast.makeText(AddCodeActivity.this, "Please enter Keyword !!", Toast.LENGTH_LONG).show();
                    }
                    else if (AddPromoCodeDescription.equals(""))
                    {
                        Toast.makeText(AddCodeActivity.this, "Please enter Message !!", Toast.LENGTH_LONG).show();
                    }
                    else if (imgpath.getText().equals(""))
                    {
                        Toast.makeText(AddCodeActivity.this, "Please Select Document !!", Toast.LENGTH_LONG).show();
                    }

                    else
                    {

                        Toast.makeText(AddCodeActivity.this, "Submit Successfully !!", Toast.LENGTH_LONG).show();

                        DatabaseHandler db = new DatabaseHandler(AddCodeActivity.this);
                        db.insert(AddPromoCodePromocode,AddPromoCodeDescription,setlcttype,imgpath.getText().toString());
                        Intent godashboard = new Intent(AddCodeActivity.this, MainNewActivity.class);
                        startActivity(godashboard);
                        finish();

                    }
                }


            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                Toast.makeText(AddCodeActivity.this, "Image not selected", Toast.LENGTH_SHORT).show();
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

        if (setlcttype.equals("Text & Document"))
        {

        }
        else
        {
           // AddPromoCode_description.setText(String.valueOf(selectedImageList));
        }


      //  fileListAdapter.notifyDataSetChanged();
    }




}
