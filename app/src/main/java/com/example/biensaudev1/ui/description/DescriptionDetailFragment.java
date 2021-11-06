package com.example.biensaudev1.ui.description;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biensaudev1.R;
import com.example.biensaudev1.models.MassageModel;

public class DescriptionDetailFragment extends Fragment {

    TextView desc;

    public DescriptionDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v =inflater.inflate(R.layout.fragment_description_detail, container, false);


         desc = v.findViewById(R.id.description);
         //Bundle b1 = getArguments();
         //String description = b1.getString("recipients");
         //desc.setText(description);
        //String descriptionc = getArguments().getString("description");
       // Toast.makeText(getContext(), descriptionc,Toast.LENGTH_SHORT).show();


        return v;
    }
}