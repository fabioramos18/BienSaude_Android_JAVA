package com.example.biensaudev1.ui.booking;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biensaudev1.R;
import com.example.biensaudev1.ui.bookingType.BookingTypeFragment;
import com.google.android.material.button.MaterialButton;


public class BookingFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton radioButton;
    private String a = "txt";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_booking, container, false);
        return v;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroup);
        final NavController navController = Navigation.findNavController(view);

        Button next1 = view.findViewById(R.id.next1);
        next1.setOnClickListener(v1 -> {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton =  view.findViewById(radioId);

            if (radioButton.getId() == view.findViewById(R.id.radio_massage).getId()){

                a= "massages";
                BookingFragmentDirections.ActionNavBookingToBookingTypeFragment action = BookingFragmentDirections.actionNavBookingToBookingTypeFragment("type");
                action.setType(a);
                navController.navigate(action);
            }else if(radioButton.getId() == view.findViewById(R.id.radio_rehabilitation).getId()){
                a= "rehabilitations";
                BookingFragmentDirections.ActionNavBookingToBookingTypeFragment action = BookingFragmentDirections.actionNavBookingToBookingTypeFragment("type");
                action.setType(a);
                navController.navigate(action);
            }
            else{
                Toast.makeText(getContext(), "falhou", 3).show();
            }
        });
    }
}