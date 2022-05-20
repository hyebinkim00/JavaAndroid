package com.example.myapplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView ;
    TextView textView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("HomeFragment", "onCreateView");
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        textView = (TextView) view.findViewById(R.id.hoem_frag_text);
        installedApp();

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HomeFragment", "onCreate");
    }

    public void installedApp(){

        PackageManager packageManage = getContext().getPackageManager();

        List<PackageInfo> packageInfos = packageManage.getInstalledPackages(0);


        Log.d("Pack",packageInfos.get(0).packageName);
        Log.d("Pack", String.valueOf(packageInfos.size()));

       AppAdapter adapter =  new AppAdapter(packageInfos);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
       recyclerView.setAdapter(adapter);

       adapter.setClickListener(new AppAdapter.ItemClickListener() {
           @Override
           public void onItemClickListener(String name) {
               Toast.makeText(getContext(),name,Toast.LENGTH_SHORT).show();
           }
       });

    }

}