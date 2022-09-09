package com.ubk.casdis_tailor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.ubk.casdis_tailor.BuildConfig;
import com.ubk.casdis_tailor.R;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainNewActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    EditText getPromoCode_MobileNo,dashboard_customer_name;
    Spinner getPromoCode_PromoCode;
    TextView getPromoCode_Sent;
    String PromoCodeName,PromoCodeDescription;
    DatabaseHandler db;
    List<String> promocodesssList = new ArrayList<String>();
    HashMap<String,String> promocodesssDesc=new HashMap<>();
    String getPromoCodePromoCodeeee;
    String getPromoCodeMobileNo;
    String getPromoCodePromoCodeDesc;
    Button getPromoCode_sms,checkPromoCode_whatsapp;
    String SMSphoneNo;
    String SMSmessage;
    String getStatus="Pending";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    AlertDialog alertDialog;
    Calendar calendar;
    SimpleDateFormat simpledateformat;
    String getDate;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";

    SharedPreferences sharedpreferences2;


    public static final String MyKEYWORD = "MyKeyword" ;
    public static final String Keyword = "Keyword";

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE= 2323;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);



        Log.e("sdfsdfsdfsdfsdf","asasas");


        mAppBarConfiguration = new AppBarConfiguration.Builder( R.id.nav_newhome,
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share,R.id.nav_add_defoult,R.id.nav_accessable)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //ServiceCommunicator.on
        calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        getDate = simpledateformat.format(calendar.getTime());

        getPromoCode_MobileNo=findViewById(R.id.getPromoCode_MobileNo);
        getPromoCode_PromoCode=findViewById(R.id.getPromoCode_PromoCode);
        getPromoCode_Sent=findViewById(R.id.getPromoCode_Sent);
        dashboard_customer_name=findViewById(R.id.dashboard_customer_name);

        sharedpreferences2 = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences2.contains(Name)) {
            // name.setText();


            Log.e("rerfwedwsaefvs222222",".1."+sharedpreferences2.getString(Name, ""));
        }
        else {
            Log.e("rerfwedwsaefvs111111",".2."+sharedpreferences2.getString(Name, ""));


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_new, menu);


        GlobalClass.item=menu.findItem(R.id.action_settings);
        GlobalClass.item2=menu.findItem(R.id.action_call_del);

        sharedpreferences2 = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        DatabaseHandler db = new DatabaseHandler(MainNewActivity.this);

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.showAll_db();
        Log.d("byjum.....", ""+arrayList_db_count);

        if (!arrayList_db_count.isEmpty()) {

         //   item2.setVisible(true);
        }
        else
        {
           // item2.setVisible(false);
        }


            return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.e("efwdqsadwesdqds","111111");

            Intent intent=new Intent(MainNewActivity.this,CallListActivity.class);
            intent.putExtra("type","DELIVERY_DATE");
            startActivity(intent);
            finish();

            Log.e("efwdqsadwesdqds","erfdasazfcfddss");
           // return true;
        }
        else if (id == R.id.action_view)
        {

            Intent intent=new Intent(MainNewActivity.this,CallListActivity.class);
            intent.putExtra("type","TRIAL_DATE");
            startActivity(intent);
            finish();

        }
        else if (id == R.id.action_call)
        {

            DatabaseHandler db = new DatabaseHandler(MainNewActivity.this);

            long time= System.currentTimeMillis();

            android.util.Log.i("Time Class ", " Time value in millisecinds "+time);


            ArrayList arrayList_db_count = new ArrayList<>();
            arrayList_db_count = db.MobileKeyword_db_Call(String.valueOf(time));

            Log.d("byjum...", "" + arrayList_db_count);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            if (!arrayList_db_count.isEmpty()) {


                for (int i = 0; i < arrayList_db_count.size(); i++) {

                    HashMap<String, String> map = (HashMap) arrayList_db_count.get(i);



                    Log.e("dffssf", "" + map.get("PromoCodedate"));

                    long milisec = Long.parseLong(map.get("PromoCodedate"));

                    Date date = new Date(milisec);
                    formatter.format(date);

                    Log.e("dffssf", "" + formatter.format(date));
                    db.deleteallCAllAll();

                    DbPassHandler dbpassword = new DbPassHandler(MainNewActivity.this);
                    dbpassword.updatetime(String.valueOf(System.currentTimeMillis()));

                }
                //onStart();
                Toast.makeText(this, "Remove Phone Number", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MainNewActivity.this,MainNewActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(this, "No Record", Toast.LENGTH_SHORT).show();
            }

//            Log.e("efwdqsadwesdqds","333333");
//            Intent intent=new Intent(MainNewActivity.this,CallListActivity.class);
//            startActivity(intent);
//            finish();
        }
        else if (id == R.id.action_call_del);
        {
            Log.e("efwdqsadwesdqds","4444444");



            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void RequestPermission() {
        // Check if Android P or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Show alert dialog to the user saying a separate permission is needed
            // Launch the settings activity if the user prefers
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + BuildConfig.APPLICATION_ID));
            startActivityForResult(intent,
                    ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }

}
