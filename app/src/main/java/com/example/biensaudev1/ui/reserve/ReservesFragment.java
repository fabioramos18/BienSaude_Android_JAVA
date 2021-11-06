package com.example.biensaudev1.ui.reserve;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.biensaudev1.R;
import com.example.biensaudev1.adapters.ReserveAdapter;
import com.example.biensaudev1.models.MassageModel;
import com.example.biensaudev1.models.ReserveModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ReservesFragment extends Fragment {

    List<ReserveModel> reserveModelList;
    ReserveAdapter reserveAdapter;
    RecyclerView reserveRec;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_reserves, container, false);

       db = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();


        reserveRec = view.findViewById(R.id.reserve_rec);
        reserveRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        reserveModelList = new ArrayList<>();
        reserveAdapter = new ReserveAdapter(getActivity(),reserveModelList);
        reserveRec.setAdapter(reserveAdapter);

        db.collection("bookings")
                //.orderBy("bookingday", Query.Direction.DESCENDING).startAfter(Timestamp.now())
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ReserveModel reserveModel = document.toObject(ReserveModel.class);
                                reserveModelList.add(reserveModel);
                                reserveAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(),"Erro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

       return view;
    }
}