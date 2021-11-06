package com.example.biensaudev1.ui.location;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biensaudev1.R;


public class LocationFragment extends Fragment {


TextView descTxtView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View root = inflater.inflate(R.layout.fragment_location, container, false);

        descTxtView= root.findViewById(R.id.descTxtView);
        descTxtView.setMovementMethod(new ScrollingMovementMethod());

        return root;
    }
}