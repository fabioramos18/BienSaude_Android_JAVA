package com.example.biensaudev1.ui.rehabilitation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.biensaudev1.R;
import com.example.biensaudev1.adapters.MassageAll;
import com.example.biensaudev1.adapters.RehabilitationAll;
import com.example.biensaudev1.models.RehabilitationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class RehabilitationFragment extends Fragment {

    RecyclerView rehabilitationRec;
    FirebaseFirestore db;

    List<RehabilitationModel> rehabilitationModelList;
    RehabilitationAll rehabilitationAll;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rehabilitation , container, false);

        db = FirebaseFirestore.getInstance();

        rehabilitationRec = root.findViewById(R.id.rehabilitation_rec);

        //REHABILITATION items
        rehabilitationRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        rehabilitationModelList = new ArrayList<>();
        rehabilitationAll = new RehabilitationAll(getActivity(), rehabilitationModelList);
        rehabilitationRec.setAdapter(rehabilitationAll);


        db.collection("rehabilitations").orderBy("title").limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                RehabilitationModel rehabilitationModel = document.toObject(RehabilitationModel.class);
                                rehabilitationModelList.add(rehabilitationModel);
                                rehabilitationAll.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(getActivity(),"Erro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}