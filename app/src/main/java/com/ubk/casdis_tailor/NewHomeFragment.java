package com.ubk.casdis_tailor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.ubk.casdis_tailor.ui.adddefoultmessage.AddViewModel;

import com.ubk.casdis_tailor.ui.home.HomeFragment;


public class NewHomeFragment extends Fragment {

    private AddViewModel addViewModel;

   CardView customer,non_customer,delivery,trail,followup;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.activity_homenew, container, false);

        customer=root.findViewById(R.id.customer);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeFragment fragment1 = new HomeFragment();
                Bundle arguments = new Bundle();
                arguments.putString("Type", "CUSTOMER");
                fragment1.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment1);
                fragmentTransaction.commit();

            }
        });


        non_customer=root.findViewById(R.id.non_customer);
        non_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HomeFragment fragment1 = new HomeFragment();
                Bundle arguments = new Bundle();
                arguments.putString("Type", "NON CUSTOMER");
                fragment1.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment1);
                fragmentTransaction.commit();
            }
        });

        delivery=root.findViewById(R.id.delivery);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(),CallListActivity.class);
                intent.putExtra("type","DELIVERY_DATE");
                startActivity(intent);
                getActivity().finish();
            }
        });

        trail=root.findViewById(R.id.trail);
        trail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(),CallListActivity.class);
                intent.putExtra("type","TRAIL_DATE");
                startActivity(intent);
                getActivity().finish();
            }
        });


        followup=root.findViewById(R.id.followup);
        followup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(),CallListActivity.class);
                intent.putExtra("type","FOLLOWUP_DATE");
                startActivity(intent);
                getActivity().finish();

            }
        });


        return root;
    }

}