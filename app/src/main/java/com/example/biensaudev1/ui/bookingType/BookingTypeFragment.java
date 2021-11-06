package com.example.biensaudev1.ui.bookingType;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biensaudev1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class BookingTypeFragment extends Fragment {



    String centerr, servicee;
    private static final String TAG = "BookingTypeFragment";
    private String type;
    AutoCompleteTextView autoCompleteTextView, autoComplete;
    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        type = getArguments().getString("a");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_booking_type, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            BookingTypeFragmentArgs args = BookingTypeFragmentArgs.fromBundle(getArguments());

            autoComplete =view.findViewById(R.id.autoComplete);
            autoCompleteTextView = view.findViewById(R.id.autoCompleteLocationn);

            String[] center = new String[] {
                    "Centro BienSaúde Lisboa"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.option_item, center);
            autoComplete.setAdapter(adapter);

            List<String> option = new ArrayList<String>();
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.option_item, option);
            autoCompleteTextView.setAdapter(arrayAdapter);



            String t = args.getType();

            CollectionReference subjectsRef = rootRef.collection(t);

            subjectsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String subject = document.getString("title");
                            option.add(subject);
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            });
            autoComplete.setOnItemClickListener((parent, view1, position, id) -> {
                centerr = autoComplete.getText().toString();
            });
            autoCompleteTextView.setOnItemClickListener((parent, view12, position, id) -> {
                servicee = autoCompleteTextView.getText().toString();
            });

            Log.i(TAG, "onViewCreated: "+ t );

            final NavController navController = Navigation.findNavController(view);

            Button next1 = view.findViewById(R.id.next1);
            next1.setOnClickListener(v -> {

                BookingTypeFragmentDirections.ActionBookingTypeFragmentToAvailabilityFragment action = BookingTypeFragmentDirections.actionBookingTypeFragmentToAvailabilityFragment();
                action.setType(t);
                action.setCenter(centerr);
                action.setService(servicee);
                navController.navigate(action);

            });
        }
    }
}