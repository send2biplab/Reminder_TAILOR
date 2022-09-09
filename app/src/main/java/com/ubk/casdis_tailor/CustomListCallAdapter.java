package com.ubk.casdis_tailor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.ubk.casdis_tailor.R;

import java.util.List;

public class CustomListCallAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;

    ProgressDialog mProgressDialog1;



    public CustomListCallAdapter(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        final TextView title = (TextView) convertView.findViewById(R.id.title);
        final TextView rating = (TextView) convertView.findViewById(R.id.rating);
        final TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        final TextView keywordid = (TextView) convertView.findViewById(R.id.keywordid);
        final RelativeLayout saasasasa = (RelativeLayout) convertView.findViewById(R.id.saasasasa);

        final ImageView delll = (ImageView) convertView.findViewById(R.id.delll);
        final ImageView edit = (ImageView) convertView.findViewById(R.id.edittt);
        final LinearLayout thumbnail = (LinearLayout) convertView.findViewById(R.id.thumbnail);



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(activity, EditCallDocActivity.class);

                intent.putExtra("idd",keywordid.getText().toString());
                activity.startActivity(intent);


            }
        });


        delll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("vdddddd", "releaseYear" + year.getText().toString());

                new AlertDialog.Builder(activity)
                        .setTitle("Delete Message")
                        .setMessage("Are you sure you want to delete this Message?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {



                                DatabaseHandler db2 = new DatabaseHandler(activity);
                                db2.deleteData_id2(keywordid.getText().toString());

                                Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
                                activity.startActivity(intent);
                                activity.finish();
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        // getting movie data for the row
        Movie m = movieItems.get(position);
        // title
        title.setText(m.getTitle());
        // rating
        rating.setText(String.valueOf(m.getRating()));
        year.setText(String.valueOf(m.getYear()));
        keywordid.setText(String.valueOf(m.getThumbnailUrl()));

        Log.d("fsdfsdfsdf1", ".."+m.getTitle());
        Log.d("fsdfsdfsdf2", "" + m.getRating());
        Log.d("KEYYYYYYYYYYYYid", "" + m.getThumbnailUrl());
//        if (m.getYear() == 1)
//        {
//            thumbnail.setVisibility(View.GONE);
//        }
//        else
//        {
//            thumbnail.setVisibility(View.VISIBLE);
//        }


        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }




}
