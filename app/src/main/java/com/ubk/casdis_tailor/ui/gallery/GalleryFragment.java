package com.ubk.casdis_tailor.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.downloader.PRDownloader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ubk.casdis_tailor.AddCodeActivity;
import com.ubk.casdis_tailor.Movie;
import com.ubk.casdis_tailor.Contact2;
import com.ubk.casdis_tailor.CustomListAdapter;
import com.ubk.casdis_tailor.DatabaseHandler;
import com.ubk.casdis_tailor.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    ListView msglist2;

    DatabaseHandler db;
    public static CustomListAdapter adapter;

    String[] messageArray2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
       /// final TextView textView = root.findViewById(R.id.text_gallery);

        Log.e("ergfeadsefs","List1");


        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), AddCodeActivity.class);
                startActivity(intent);


            }
        });

        PRDownloader.initialize(getContext());



        msglist2 = (ListView)root.findViewById(R.id.msglist2);


        db = new DatabaseHandler(getActivity());

        ArrayList arrayList_db_count = new ArrayList<>();
        arrayList_db_count = db.showAll_db();
        Log.d("byjum", ""+arrayList_db_count);

        List<Contact2> message2 = db.getAllContacts();

        Log.d("asdaSDASD111",""+message2.size());
        Log.d("asdaSDASD111",""+message2);


        if (!arrayList_db_count.isEmpty()) {


            List<Movie> movieList = new ArrayList<Movie>();


            messageArray2 = new String[message2.size()];
            for (int i = 0; i < message2.size(); i++) {

              //  Log.d("dfasdasda111", "" + message2.get(i).getPhoneNumber());
              //  Log.d("dfasdasda2222", "" + message2.get(i).getName());
                messageArray2[i] = "Keyword :- " + message2.get(i).getName() + "\n" + "Message :- " + message2.get(i).getPhoneNumber();

                Movie movie = new Movie();
                movie.setTitle("Keyword :- " + message2.get(i).getName());
                movie.setRating("Message :- " + message2.get(i).getPhoneNumber());
                movie.setYear(message2.get(i).getID());
                movie.setThumbnailUrl(String.valueOf(message2.get(i).getID()));

                movieList.add(movie);

            }

            Log.d("sdfsdfdsfsd",""+movieList);
            adapter = new CustomListAdapter(getActivity(), movieList);
            adapter.notifyDataSetChanged();
            msglist2.setAdapter(adapter);
        }
       // Log.e("dbhytj",""+ promocodesssList);



//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//              ///  textView.setText(s);
//
//            }
//        });


        return root;
    }
}