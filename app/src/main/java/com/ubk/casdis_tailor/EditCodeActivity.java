package com.ubk.casdis_tailor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.ubk.casdis_tailor.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EditCodeActivity extends AppCompatActivity {

    EditText AddPromoCode_promocode,AddPromoCode_description;
    TextView AddPromoCode_submit,AddPromoCode_Cancel;

    ImageView backkk;
    Spinner spinnertype;
    TextView gallery,pdfupload;
    ArrayList<String> selectedImageList;
    String[] projection = {MediaStore.MediaColumns.DATA};

    public static final int PICK_IMAGES = 2;
    ArrayList<ImageModel> imageList;
    String  setlcttype="Text & Document";
    String AddPromoCodePromocode,AddPromoCodeDescription;

    private final static int FILE_REQUEST_CODE = 1;
    //private FileListAdapter fileListAdapter;
    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();


    // private static String dirPath;

    int downloadIdFourteen;
    int i;

    private static String dirPath;

    TextView imgpath;

    LinearLayout messagesss;

    String[] messageArray2;

//    private ArrayList<Uri> photoPaths = new ArrayList<>();
//    private ArrayList<Uri> docPaths = new ArrayList<>();
//
//    public static final int RC_PHOTO_PICKER_PERM = 123;
//    public static final int RC_FILE_PICKER_PERM = 321;
//    private static final int CUSTOM_REQUEST_CODE = 532;
//    private int MAX_ATTACHMENT_COUNT = 10;

    private static final int REQUEST_CHOOSER = 1234;
    int imgcount=0;

    String idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_code);


        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            idd = extras.getString("idd");


            Log.e("sdfwedfwerfd","....."+idd);
        }


        DatabaseHandler db = new DatabaseHandler(EditCodeActivity.this);
       // db.All_Record_Show_db();

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.showAll_db_byid(idd);
        Log.d("byjum.......", ""+arrayList_db_count);

       // List<Contact2> message2 = db.getAllContacts();

        HashMap<String, String> map = (HashMap) arrayList_db_count.get(0);


        Log.d("asdaSDASD111",""+map);


        List<Movie> movieList = new ArrayList<Movie>();


       // messageArray2 = new String[message2.size()];
       // for (int i = 0; i < message2.size(); i++) {


       // }
     //   Log.e("gcfgchg","hhh"+message2.get(0).getKey_id());


            //  [{PromoCodeDescription=[/storage/emulated/0/Download/best-mlm-online-part-time-jobs-in-india-top-mlm-company-network-marketing-in-india-7-638.jpg], PromoCodeName=doc, imgpath=[/storage/emulated/0/Download/best-mlm-online-part-time-jobs-in-india-top-mlm-company-network-marketing-in-india-7-638.jpg], id=2, type=Document}]

     //    [{PromoCodeDescription=kvucu, PromoCodeName=hello, imgpath=, id=1, type=Text}]


        //lll
       // db.insert(AddPromoCodePromocode,AddPromoCodeDescription,setlcttype,imgpath.getText().toString());

        AddPromoCode_promocode=findViewById(R.id.AddPromoCode_promocode);
        AddPromoCode_description=findViewById(R.id.AddPromoCode_description);
        AddPromoCode_submit=findViewById(R.id.AddPromoCode_submit);
        AddPromoCode_Cancel=findViewById(R.id.AddPromoCode_Cancel);
        imgpath=findViewById(R.id.imgpath);


        selectedImageList = new ArrayList<>();

        spinnertype=findViewById(R.id.spinnertype);
        messagesss=findViewById(R.id.messagesss);
        pdfupload=findViewById(R.id.pdfupload);

        imageList = new ArrayList<>();

        AddPromoCode_submit.setText("Update");


        AddPromoCode_promocode.setText(String.valueOf(map.get("PromoCodeName")));
        AddPromoCode_description.setText(String.valueOf( map.get("PromoCodeDescription")));
        imgpath.setText(String.valueOf( map.get("PromoCodeDescription")));


        Log.e("ewfdsawarefdscaerds","effff"+String.valueOf( map.get("PromoCodeDescription")));



        dirPath = Utils.getRootDirPath(EditCodeActivity.this);

        PRDownloader.initialize(EditCodeActivity.this);


        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

        Log.d("efsadfsdfsf",""+dirPath);



        gallery=findViewById(R.id.gallery);






        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(EditCodeActivity.this, FilePickerActivity.class);
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
//
//

// Create the ACTION_GET_CONTENT Intent




            }
        });

        // Spinner click listener
        // spinnertype.setOnItemSelectedListener(AddCodeActivity.this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Type");
        categories.add("Text");
        categories.add("Document");
        categories.add("Text & Document");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnertype.setAdapter(dataAdapter);

        spinnertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String item = adapterView.getItemAtPosition(i).toString();

                Log.e("rrtgrew","wew"+item);
                if (item.equals("Select Type"))
                {

                }
                else
                {
                    setlcttype=item;

                    if (item.equals("Document"))
                    {
                        imgcount=30;
                        messagesss.setVisibility(View.GONE);
                        gallery.setVisibility(View.VISIBLE);
                        pdfupload.setVisibility(View.GONE);

                    }
                    else if (item.equals("Text & Document"))
                    {
                        imgcount=4;
                        gallery.setVisibility(View.VISIBLE);
                        messagesss.setVisibility(View.VISIBLE);

                    }
                    else {
                        messagesss.setVisibility(View.VISIBLE);
                        pdfupload.setVisibility(View.GONE);
                        gallery.setVisibility(View.GONE);

                        //AddPromoCode_description.setText("");
                    }


                }
                // Showing selected spinner item
                // Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        AddPromoCode_promocode.setText(String.valueOf(map.get("PromoCodeName")));
        AddPromoCode_description.setText(String.valueOf( map.get("PromoCodeDescription")));
        imgpath.setText(String.valueOf( map.get("imgpath")));



        if (map.get("type").equals("Document"))
        {
            imgcount=30;
            messagesss.setVisibility(View.GONE);
            gallery.setVisibility(View.VISIBLE);
            pdfupload.setVisibility(View.GONE);
            spinnertype.setSelection(2);

        }
        else if (map.get("type").equals("Text & Document"))
        {
            imgcount=4;
            gallery.setVisibility(View.VISIBLE);
            messagesss.setVisibility(View.VISIBLE);
            spinnertype.setSelection(3);
        }
        else {
            spinnertype.setSelection(1);
            messagesss.setVisibility(View.VISIBLE);
            pdfupload.setVisibility(View.GONE);
            gallery.setVisibility(View.GONE);
            imgpath.setVisibility(View.GONE);
            //  AddPromoCode_description.setText("");
        }


       // aa


        AddPromoCode_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (setlcttype.equals("Select Type"))
                {
                    Toast.makeText(EditCodeActivity.this, "Please Select Type !!", Toast.LENGTH_LONG).show();
                }

                else
                {

                    AddPromoCodeDescription = AddPromoCode_description.getText().toString();
                    AddPromoCodePromocode = AddPromoCode_promocode.getText().toString();


                    if (AddPromoCodePromocode.equals(""))
                    {
                        Toast.makeText(EditCodeActivity.this, "Please enter Title !!", Toast.LENGTH_LONG).show();
                    }
                    else if (AddPromoCodeDescription.equals(""))
                    {
                        Toast.makeText(EditCodeActivity.this, "Please enter Message !!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {

                        Toast.makeText(EditCodeActivity.this, "Submit Successfully !!", Toast.LENGTH_LONG).show();


                        DatabaseHandler db = new DatabaseHandler(EditCodeActivity.this);

                        //db.insert(AddPromoCodePromocode,AddPromoCodeDescription,setlcttype,imgpath.getText().toString());
                        db.sendmsgUpdate_byid(idd,AddPromoCodePromocode,AddPromoCodeDescription,setlcttype,imgpath.getText().toString());
                        Intent godashboard = new Intent(EditCodeActivity.this, MainNewActivity.class);
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
                Toast.makeText(EditCodeActivity.this, "Image not selected", Toast.LENGTH_SHORT).show();
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
            AddPromoCode_description.setText(String.valueOf(selectedImageList));
        }


        //  fileListAdapter.notifyDataSetChanged();
    }




}
