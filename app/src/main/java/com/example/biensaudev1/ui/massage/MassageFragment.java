package com.example.biensaudev1.ui.massage;

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
import com.example.biensaudev1.adapters.MassageAll;
import com.example.biensaudev1.models.MassageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MassageFragment extends Fragment {

    RecyclerView massageRec;
    FirebaseFirestore db;

    //massage items
    List<MassageModel> massageModelList;
    MassageAll massageAll;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_massage , container, false);
        db = FirebaseFirestore.getInstance();

        massageRec = root.findViewById(R.id.massage_rec);

        //massage items
        massageRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        massageModelList = new ArrayList<>();
        massageAll = new MassageAll(getActivity(), massageModelList);
        massageRec.setAdapter(massageAll);

        db.collection("massages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                MassageModel massageModel = document.toObject(MassageModel.class);
                                massageModelList.add(massageModel);
                                massageAll.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(getActivity(),"Erro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }
}